import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

/*
================================================================================
   Actividades TCP/UDP — DAM M0490 (Traducción + Menú autoejecutable)
   Autor: ChatGPT (preparado para las clases de Rubén)

   ▶ Cómo usar
   1) Guarda este archivo como: ActividadesTCPUDP_Menu.java
   2) Compila/Ejecuta en VS Code con "Run Java" o en terminal:
        javac ActividadesTCPUDP_Menu.java && java ActividadesTCPUDP_Menu
   3) Aparece un MENÚ. Elige el ejercicio que quieras ejecutar.
   4) Los enunciados están traducidos y documentados con "Better Comments":
      // ! Importante, // * Info, // ? Nota, // TODO: Retos extra

   💡 Objetivo pedagógico
   - Tener un único .java con MENÚ + DEMOS locales (servidor/cliente en localhost).
   - El alumno lee el enunciado (comentarios) y ejecuta cada práctica por separado.
   - Al final de cada práctica hay "TODOs" (retos extra) para consolidar.
================================================================================
*/

public class ActividadesTCPUDP_Menu {

	// Clase principal que agrupa varios ejercicios/demos de redes (UDP/TCP).
	// Cada ejercicio está implementado como método que a su vez puede lanzar
	// hilos internos para servidor/cliente. Esta clase sirve como laboratorio
	// para ejecutar las demos una a una desde un menú interactivo.

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
		// Punto de entrada: crea una instancia y arranca el bucle del menú.
        new ActividadesTCPUDP_Menu().run();
    }

    private void run() {
		// Bucle principal que muestra el menú y ejecuta el ejercicio seleccionado.
		// Se usan lambdas en el switch para mantener el código compacto.
        while (true) {
            System.out.println("\n──────────────────────────────────────────────────────────────────────────────");
            System.out.println("ACTIVIDADES TCP/UDP — MENÚ PRINCIPAL");
            System.out.println("──────────────────────────────────────────────────────────────────────────────");
            System.out.println("1) UDP: números -> servidor responde EL DOBLE (con 20% pérdidas simuladas)");
            System.out.println("2) UDP: ping -> pong (puerto 25565)");
            System.out.println("3) TCP: mini chat a 2 clientes (puerto 26404)");
            System.out.println("4) TCP: logging de identificadores + OK + volcado a fichero (puerto 27010)");
            System.out.println("5) UDP→TCP: handshake y envío de bytes 'Never gonna give you up'");
            System.out.println("9) Ejecutar TODO en orden");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");
            String opt = SC.nextLine().trim();
            switch (opt) {
                case "1" -> ejercicio1_udpDoblarConPerdidas();
                case "2" -> ejercicio2_udpPingPong();
                case "3" -> ejercicio3_tcpChat();
                case "4" -> ejercicio4_tcpLogging();
                case "5" -> ejercicio5_udpToTcpHandshake();
                case "9" -> {
					// Opción ejecutar todo en orden (útil para demos).
                    ejercicio1_udpDoblarConPerdidas();
                    ejercicio2_udpPingPong();
                    ejercicio3_tcpChat();
                    ejercicio4_tcpLogging();
                    ejercicio5_udpToTcpHandshake();
                }
                case "0" -> { System.out.println("¡Hasta pronto!"); return; }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // ======================================================================
    // ! EJERCICIO 1 (UDP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa una aplicación UDP en la que un cliente envíe NÚMEROS a un
    // *  servidor. El servidor responde con EL DOBLE de ese número. Añade el
    // *  código necesario para SIMULAR que un 20% de los paquetes se PIERDEN.
    // *  Usa el puerto que quieras."
    //
    // ? DEMO: Servidor y cliente se ejecutan en la misma app (localhost).
    // TODO: Reto extra
    //   1) Añade un "ACK" y reintentos con timeout para mitigar pérdidas.
    //   2) Añade checksum simple y descarta datagramas corruptos.
    //   3) Permite enviar más de un número por datagrama (CSV) y responde lista.
    // ======================================================================
    private void ejercicio1_udpDoblarConPerdidas() {
        System.out.println("\n[Ejercicio 1] UDP doble con pérdidas (20%)");
        // Generador aleatorio para simular pérdidas de datagramas.
        Random rnd = new Random();

        // Servidor UDP (puerto efímero: 0)
        class Server extends Thread {
            volatile boolean running = true;
            DatagramSocket ds;
            int port = -1;
            public void run() {
                try (DatagramSocket s = new DatagramSocket(0)) {
					// Se crea socket en puerto efímero y se guarda para tests.
                    ds = s;
                    port = s.getLocalPort();
                    byte[] buf = new byte[1024];
                    while (running) {
                        // Espera bloqueante por un datagrama.
                        DatagramPacket p = new DatagramPacket(buf, buf.length);
                        s.receive(p);
                        // Simulación de pérdidas ~20%: simplemente no respondemos.
                        if (rnd.nextDouble() < 0.2) {
                            // * Se "pierde": no respondemos. Esto simula un entorno no confiable.
                            continue;
                        }
                        // Procesamos el contenido: se espera un número en texto.
                        String text = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8).trim();
                        int n = Integer.parseInt(text);
                        int doubled = n * 2;
                        // Enviamos la respuesta con el doble.
                        byte[] out = ("" + doubled).getBytes(StandardCharsets.UTF_8);
                        DatagramPacket r = new DatagramPacket(out, out.length, p.getAddress(), p.getPort());
                        s.send(r);
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor UDP (E1) detenido: " + e.getMessage());
                }
            }
            void stopServer() { running = false; if (ds != null) ds.close(); }
        }

        Server server = new Server();
        server.start();
        int port = awaitPort(() -> server.port, 2000);
        if (port == -1) { System.out.println("No se pudo iniciar el servidor UDP."); return; }
        System.out.println("Servidor UDP en puerto: " + port);

        // Cliente de prueba que envía números y espera respuesta con timeout.
        try (DatagramSocket c = new DatagramSocket()) {
            // Timeout para no bloquear indefinidamente cuando hay pérdidas.
            c.setSoTimeout(500);
            for (int i = 1; i <= 10; i++) {
                byte[] out = ("" + i).getBytes(StandardCharsets.UTF_8);
                c.send(new DatagramPacket(out, out.length, InetAddress.getByName("127.0.0.1"), port));
                byte[] buf = new byte[128];
                DatagramPacket resp = new DatagramPacket(buf, buf.length);
                try {
                    // Intentamos recibir; si no llega, asumimos pérdida simulada.
                    c.receive(resp);
                    String ans = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8);
                    System.out.printf("Enviado=%d | Recibido=%s%n", i, ans);
                } catch (SocketTimeoutException ste) {
                    // Timeout: datagrama perdido o servidor no respondió.
                    System.out.printf("Enviado=%d | (perdido)%n", i);
                }
            }
        } catch (Exception e) {
            System.out.println("Cliente UDP (E1) error: " + e.getMessage());
        } finally {
            // Aseguramos el cierre del servidor al finalizar la demo.
            server.stopServer();
        }
    }

    // ======================================================================
    // ! EJERCICIO 2 (UDP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa una aplicación UDP con un compañero. Uno hará de SERVIDOR y
    // *  el otro de CLIENTE. Cada vez que el servidor reciba el texto 'ping',
    // *  deberá responder 'pong' al cliente. Si recibe cualquier otra cosa,
    // *  NO hace nada. Usa el puerto 25565."
    //
    // ? DEMO: Se lanza servidor en 25565 y un cliente local envía varias tramas.
    // TODO: Reto extra
    //   1) Añade soporte para 'ping <n>' devolviendo 'pong <n>'.
    //   2) Cuenta pings atendidos por IP y muéstralo cada X segundos.
    //   3) Protege con "token" simple (el servidor ignora pings sin token).
    // ======================================================================
    private void ejercicio2_udpPingPong() {
        System.out.println("\n[Ejercicio 2] UDP ping/pong (puerto 25565)");
        final int PORT = 25565;

        class Server extends Thread {
            volatile boolean running = true;
            DatagramSocket ds;
            public void run() {
                try (DatagramSocket s = new DatagramSocket(PORT)) {
					// Se enlaza al puerto fijo para recibir pings.
                    ds = s;
                    byte[] buf = new byte[512];
                    while (running) {
                        // Recibe datagramas y responde solo si el mensaje es "ping".
                        DatagramPacket p = new DatagramPacket(buf, buf.length);
                        s.receive(p);
                        String msg = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8);
                        if ("ping".equalsIgnoreCase(msg.trim())) {
                            // Respuesta: "pong" al remitente.
                            byte[] out = "pong".getBytes(StandardCharsets.UTF_8);
                            s.send(new DatagramPacket(out, out.length, p.getAddress(), p.getPort()));
                        }
                        // Si el mensaje no es 'ping' se ignora deliberadamente.
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor UDP (E2) detenido: " + e.getMessage());
                }
            }
            void stopServer() { running = false; if (ds != null) ds.close(); }
        }

        Server server = new Server(); server.start();
        sleep(200); // breve espera

        // Cliente de prueba que envía varios mensajes y espera pong según corresponda.
        try (DatagramSocket c = new DatagramSocket()) {
            c.setSoTimeout(400);
            String[] msgs = {"ping", "hola", "PING", "ping"};
            for (String m : msgs) {
                byte[] out = m.getBytes(StandardCharsets.UTF_8);
                c.send(new DatagramPacket(out, out.length, InetAddress.getByName("127.0.0.1"), PORT));
                byte[] buf = new byte[32];
                DatagramPacket resp = new DatagramPacket(buf, buf.length);
                try {
                    // Solo recibiremos algo cuando el servidor reconozca 'ping'.
                    c.receive(resp);
                    String ans = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8);
                    System.out.printf("Cliente envió='%s' | Servidor respondió='%s'%n", m, ans);
                } catch (SocketTimeoutException ste) {
                    // Correcto si no era 'ping' (no esperamos respuesta).
                    System.out.printf("Cliente envió='%s' | (sin respuesta, correcto si no era 'ping')%n", m);
                }
            }
        } catch (Exception e) {
            System.out.println("Cliente UDP (E2) error: " + e.getMessage());
        } finally {
            // Paramos el servidor tras la demo.
            server.stopServer();
        }
    }

    // ======================================================================
    // ! EJERCICIO 3 (TCP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa una aplicación TCP para CHATEAR con un compañero. Cuando uno
    // *  envía un mensaje, al otro le aparece por terminal. Usa el puerto 26404."
    //
    // ? DEMO: Servidor acepta 2 clientes y reenvía mensajes entre ellos.
    // TODO: Reto extra
    //   1) Permite más clientes y añade /nick y /quit.
    //   2) Añade timestamps y persistencia del chat en un .log.
    //   3) Implementa comandos /whisper <nick> <msg> (privado).
    // ======================================================================
    private void ejercicio3_tcpChat() {
        System.out.println("\n[Ejercicio 3] TCP Chat (puerto 26404)");
        final int PORT = 26404;

        class ChatServer extends Thread {
            volatile boolean running = true;
            ServerSocket ss;
            // Conjunto concurrente de clientes conectados; se usa para broadcast.
            final Set<Socket> clients = ConcurrentHashMap.newKeySet();
            public void run() {
                try (ServerSocket server = new ServerSocket(PORT)) {
					// Acepta conexiones hasta 2 clientes en esta demo.
                    ss = server;
                    while (running && clients.size() < 2) {
                        Socket c = server.accept();
                        clients.add(c);
                        // Para cada cliente arrancamos un hilo que escucha sus mensajes.
                        new Thread(() -> listenFrom(c)).start();
                    }
                } catch (IOException e) {
                    if (running) System.out.println("ChatServer detenido: " + e.getMessage());
                }
            }
            private void listenFrom(Socket c) {
                // Lee líneas del cliente y las reenvía al resto.
                try (BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        broadcast(c, line);
                    }
                } catch (IOException ignored) {
                } finally {
                    // Al desconectar, cerramos socket y lo quitamos del conjunto.
                    try { c.close(); } catch (IOException ignored) {}
                    clients.remove(c);
                }
            }
            private void broadcast(Socket from, String msg) {
                // Envía msg a todos los clientes excepto al remitente.
                for (Socket s : clients) {
                    if (s == from) continue;
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true);
                        out.println(msg);
                    } catch (IOException ignored) {}
                }
            }
            void stopServer() { running = false; try { if (ss != null) ss.close(); } catch (IOException ignored) {} }
        }

        ChatServer server = new ChatServer(); server.start();
        sleep(200);

        // Dos clientes "automáticos" para la demo
        class Bot extends Thread {
            final String name;
            Bot(String n) { name = n; }
            public void run() {
                try (Socket s = new Socket("127.0.0.1", PORT);
                     BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true)) {
                    // Envío 2 mensajes y leo 2 del otro
                    out.println("[" + name + "] Hola!");
                    String r1 = in.readLine();
                    System.out.println(name + " recibió -> " + r1);
                    out.println("[" + name + "] ¿Qué tal?");
                    String r2 = in.readLine();
                    System.out.println(name + " recibió -> " + r2);
                } catch (IOException e) {
                    System.out.println("Cliente " + name + " error: " + e.getMessage());
                }
            }
        }
        Bot a = new Bot("Alice");
        Bot b = new Bot("Bob");
        a.start(); b.start();
        try { a.join(); b.join(); } catch (InterruptedException ignored) {}

        server.stopServer();
    }

    // ======================================================================
    // ! EJERCICIO 4 (TCP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa una aplicación TCP con LOGGING. Diferentes clientes deben
    // *  conectarse y enviar un IDENTIFICADOR (extraído a partir de la hora del
    // *  dispositivo) al servidor. El servidor recibe esas conexiones, RESPONDE
    // *  'OK' y CIERRA la conexión. Después, ESCRIBE en un archivo la información
    // *  recibida. Usa el puerto 27010."
    //
    // ? DEMO: Lanzamos 3 clientes concurrentes. Log se guarda en tmp.
    // TODO: Reto extra
    //   1) Cambia el formato del log a CSV/JSON.
    //   2) Añade rotación de logs por fecha y nivel (INFO/WARN/ERROR).
    //   3) Autenticación simple por clave compartida.
    // ======================================================================
    private void ejercicio4_tcpLogging() {
        System.out.println("\n[Ejercicio 4] TCP Logging (puerto 27010)");
        final int PORT = 27010;
        // Directorio temporal donde guardaremos el log de conexiones.
        final Path logDir;
        try {
            logDir = Files.createTempDirectory("tcp_logging_");
        } catch (IOException e) {
            System.out.println("No se pudo crear carpeta temporal: " + e.getMessage());
            return;
        }
        final Path logFile = logDir.resolve("conexiones.log");
        System.out.println("Log en: " + logFile.toAbsolutePath());

        class LogServer extends Thread {
            volatile boolean running = true;
            ServerSocket ss;
            public void run() {
                try (ServerSocket s = new ServerSocket(PORT)) {
                    ss = s;
                    while (running) {
                        // Para cada conexión arrancamos un hilo que lee el identificador,
						// responde "OK" y escribe una línea en el fichero de log.
                        Socket c = s.accept();
                        new Thread(() -> handle(c)).start();
                    }
                } catch (IOException e) {
                    if (running) System.out.println("LogServer detenido: " + e.getMessage());
                }
            }
            private void handle(Socket c) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(c.getOutputStream(), StandardCharsets.UTF_8), true)) {
                    // Leemos el identificador enviado por el cliente.
                    String id = in.readLine(); // identificador
                    // Respondemos OK y cerramos por el protocolo simple definido.
                    out.println("OK");
                    // Creamos la línea de log con timestamp e IP remota.
                    String ts = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    String line = ts + " | " + c.getInetAddress().getHostAddress() + " | id=" + id + System.lineSeparator();
                    // Escritura segura: sincronizamos por la representación del path
                    // para evitar condiciones de carrera entre hilos.
                    synchronized (logFile.toString().intern()) {
                        Files.writeString(logFile, line, StandardCharsets.UTF_8,
                                Files.exists(logFile) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
                    }
                } catch (IOException ignored) {
                } finally {
                    try { c.close(); } catch (IOException ignored) {}
                }
            }
            void stopServer() { running = false; try { if (ss != null) ss.close(); } catch (IOException ignored) {} }
        }

        LogServer server = new LogServer(); server.start();
        sleep(200);

        class Client extends Thread {
            final String name;
            Client(String n) { name = n; }
            public void run() {
                try (Socket s = new Socket("127.0.0.1", PORT);
                     BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true)) {
                    String id = name + "-" + System.currentTimeMillis();
                    out.println(id);
                    String resp = in.readLine();
                    System.out.println("Cliente " + name + " -> respuesta: " + resp);
                } catch (IOException e) {
                    System.out.println("Cliente " + name + " error: " + e.getMessage());
                }
            }
        }

        Client c1 = new Client("A");
        Client c2 = new Client("B");
        Client c3 = new Client("C");
        c1.start(); c2.start(); c3.start();
        try { c1.join(); c2.join(); c3.join(); } catch (InterruptedException ignored) {}
        server.stopServer();

        // Leemos y mostramos algunas líneas del log para verificar el resultado.
        try {
            System.out.println("--- Contenido del log ---");
            Files.lines(logFile).limit(5).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("No se pudo leer el log: " + e.getMessage());
        }
    }

    // ======================================================================
    // ! EJERCICIO 5 (UDP→TCP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa una aplicación de SERVIDOR que escuche por un puerto UDP.
    // *  Cuando se reciba un mensaje de un cliente por ese puerto, el servidor
    // *  ENVIARÁ los siguientes BYTES por TCP en el puerto (port+1):
    // *      0x4e6576657220676f6e6e61206769766520796f75207570
    // *  Es decir, si la comunicación inicial ha sido por el puerto 2500 UDP, la
    // *  siguiente será por 2501 TCP. Tanto servidor como cliente, al iniciarse,
    // *  reciben por terminal cuál es el puerto inicial."
    //
    // ? DEMO: Tomamos P=2500 (UDP). Cliente abre escucha TCP en (P+1) y envía
    // ?       un datagrama UDP al servidor para disparar el envío por TCP.
    // TODO: Reto extra
    //   1) Añade autenticación: el UDP debe incluir un token y el TCP lo valida.
    //   2) Soporta múltiples clientes simultáneos (cada uno con su (P+1)).
    //   3) Reintentos si el cliente TCP no está listo (backoff exponencial).
    // ======================================================================
    private void ejercicio5_udpToTcpHandshake() {
        System.out.println("\n[Ejercicio 5] UDP→TCP handshake (P=2500 UDP, P+1 TCP)");
        final int P = 2500;
        // Bytes a enviar por TCP: hexadecimal que corresponde a la frase en ASCII.
        final byte[] BYTES = hexToBytes("4e6576657220676f6e6e61206769766520796f75207570"); // "Never gonna give you up"

        // Servidor UDP que, al recibir cualquier mensaje, abre conexión TCP al cliente: (ip, P+1)
        class Server extends Thread {
            volatile boolean running = true;
            DatagramSocket ds;
            public void run() {
                try (DatagramSocket s = new DatagramSocket(P)) {
                    ds = s;
                    System.out.println("Servidor: escuchando UDP en " + P + " y enviará TCP a (P+1)=" + (P+1));
                    byte[] buf = new byte[256];
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    // Para la demo solo procesamos un datagrama y luego finalizamos.
                    s.receive(p); // recibimos 1 datagrama para la demo
                    InetAddress clientIp = p.getAddress();
                    int tcpPort = P + 1;
                    // Abrimos un socket TCP hacia el puerto (P+1) del cliente y enviamos BYTES.
                    try (Socket tcp = new Socket(clientIp, tcpPort)) {
                        tcp.getOutputStream().write(BYTES);
                        tcp.getOutputStream().flush();
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor (E5) detenido: " + e.getMessage());
                }
            }
            void stopServer() { running = false; if (ds != null) ds.close(); }
        }

        // Cliente: abre socket TCP de escucha en (P+1) y envía un UDP al servidor en P
        class Client extends Thread {
            public void run() {
                try (ServerSocket tcpListen = new ServerSocket(P + 1)) {
					// Enviamos un datagrama UDP para notificar al servidor nuestra IP/puerto.
                    try (DatagramSocket c = new DatagramSocket()) {
                        byte[] hello = "hi".getBytes(StandardCharsets.UTF_8);
                        c.send(new DatagramPacket(hello, hello.length, InetAddress.getByName("127.0.0.1"), P));
                    }
                    // Aceptamos la conexión TCP entrante del servidor que enviará los bytes.
                    try (Socket s = tcpListen.accept()) {
                        byte[] buf = s.getInputStream().readAllBytes();
                        System.out.println("Cliente: recibido por TCP (" + buf.length + " bytes) -> '" +
                                new String(buf, StandardCharsets.UTF_8) + "'");
                    }
                } catch (IOException e) {
                    System.out.println("Cliente (E5) error: " + e.getMessage());
                }
            }
        }

        Server server = new Server(); server.start();
        sleep(100);
        Client client = new Client(); client.start();
        try { client.join(); } catch (InterruptedException ignored) {}
        server.stopServer();
    }

    // ─────────────────────────── Utilidades internas ───────────────────────────

    private static int awaitPort(IntSupplier portSupplier, long timeoutMs) {
		// Espera activa durante timeoutMs hasta que portSupplier devuelva un puerto válido (>0).
        long t0 = System.currentTimeMillis();
        while (System.currentTimeMillis() - t0 < timeoutMs) {
            int p = portSupplier.getAsInt();
            if (p > 0) return p;
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
        }
        return -1;
    }

    private static void sleep(long ms) {
		// Pequeña utilidad para pausar el hilo actual sin propagar InterruptedException.
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    private static byte[] hexToBytes(String hex) {
		// Convierte una cadena hexadecimal (sin prefijo 0x) en un array de bytes.
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(hex.charAt(i), 16) << 4)
                               +  Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    // Pequeña interfaz funcional para awaitPort
    private interface IntSupplier { int getAsInt(); }
}
