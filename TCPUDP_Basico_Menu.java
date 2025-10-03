
// Importamos las librerías necesarias para entrada/salida, red, codificación, fechas y utilidades.
import java.io.*; // Para flujos de entrada/salida
import java.net.*; // Para sockets TCP/UDP
import java.nio.charset.StandardCharsets; // Para codificación UTF-8
import java.time.LocalDateTime; // Para obtener la hora actual
import java.time.format.DateTimeFormatter; // Para formatear la hora
import java.util.Random; // Para generar aleatorios (simular pérdidas)
import java.util.Scanner; // Para leer del teclado

/*
================================================================================
   TCP/UDP — Fundamentos en Java (Versión SENCILLA con MENÚ)
   Autor: ChatGPT (para las clases de Rubén)

   ▶ Objetivo
   Arrancar desde CERO con 4 ejercicios muy básicos que expliquen los conceptos
   fundamentales de TCP y UDP. Cada ejercicio incluye comentarios con estilo
   "Better Comments" y pequeños "retos extra" para practicar.

   ▶ Cómo usar
   1) Guarda este archivo como: TCPUDP_Basico_Menu.java
   2) Compila y ejecuta:
        javac TCPUDP_Basico_Menu.java && java TCPUDP_Basico_Menu
   3) Elige ejercicio desde el MENÚ. No se imprimen enunciados largos; toda la
      explicación está en comentarios dentro del código.

   ▶ Convenciones de "Better Comments"
      // ! Importante    // * Info general    // ? Nota    // TODO: Tarea extra
================================================================================
*/
// Clase principal que contiene el menú y los ejercicios básicos de TCP/UDP.
public class TCPUDP_Basico_Menu {

    // Scanner global para leer la entrada del usuario por consola.
    private static final Scanner SC = new Scanner(System.in);

    // Método principal: crea una instancia y ejecuta el menú.
    public static void main(String[] args) {
        new TCPUDP_Basico_Menu().run();
    }

    // Bucle principal del menú: muestra opciones y ejecuta el ejercicio elegido.
    private void run() {
        while (true) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("TCP/UDP — Fundamentos (Menú básico)");
            System.out.println("────────────────────────────────────────");
            System.out.println("1) UDP Eco (eco simple de datagramas)");
            System.out.println("2) TCP Eco (eco simple por líneas)");
            System.out.println("3) UDP Ping con timeout y reintento");
            System.out.println("4) TCP Daytime (servidor de hora)");
            System.out.println("9) Ejecutar los 4 ejercicios");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String opt = SC.nextLine().trim(); // Leemos la opción del usuario
            switch (opt) {
                case "1" -> e1_udpEco(); // Ejecuta ejercicio 1
                case "2" -> e2_tcpEco(); // Ejecuta ejercicio 2
                case "3" -> e3_udpPingTimeout(); // Ejecuta ejercicio 3
                case "4" -> e4_tcpDaytime(); // Ejecuta ejercicio 4
                case "9" -> { e1_udpEco(); e2_tcpEco(); e3_udpPingTimeout(); e4_tcpDaytime(); } // Ejecuta todos
                case "0" -> { System.out.println("Fin."); return; } // Sale del programa
                default -> System.out.println("Opción no válida."); // Opción incorrecta
            }
        }
    }

    // ======================================================================
    // ! EJERCICIO 1 — UDP ECO (MUY BÁSICO)
    // * Qué se aprende:
    //      - Qué es un DatagramSocket (no orientado a conexión).
    //      - Enviar y recibir DatagramPacket.
    //      - Trabajar con bytes y UTF-8.
    // * Flujo DEMO:
    //      1) Arrancamos un servidor UDP en puerto EFÍMERO (0).
    //      2) El cliente envía 3 mensajes y espera el eco del servidor.
    //      3) Enviamos "FIN" para que el servidor termine.
    // ? Nota: No hay reintentos ni pérdidas simuladas. Lo más simple posible.
    // TODO: Retos extra
    //      - Añade cuenta de mensajes atendidos en el servidor.
    //      - Cambia el eco para que el servidor responda en MAYÚSCULAS.
    //      - Implementa un "ACK" propio con numeración (1,2,3...).
    // ======================================================================
    // ===================== EJERCICIO 1: UDP ECO =====================
    private void e1_udpEco() {
        System.out.println("\n[E1] UDP Eco");

        // Clase interna para el servidor UDP que hace eco de los mensajes recibidos.
        class UdpEchoServer extends Thread {
            volatile boolean running = true; // Controla si el servidor sigue activo
            DatagramSocket ds; // Socket UDP del servidor
            int port = -1; // Puerto en el que escucha el servidor
            @Override public void run() {
                try (DatagramSocket s = new DatagramSocket(0)) { // Puerto efímero (aleatorio)
                    ds = s;
                    port = s.getLocalPort(); // Guardamos el puerto asignado
                    byte[] buf = new byte[1024]; // Buffer para recibir datos
                    while (running) {
                        DatagramPacket p = new DatagramPacket(buf, buf.length); // Preparamos paquete para recibir
                        s.receive(p); // Espera bloqueante hasta recibir un datagrama
                        String msg = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8); // Convertimos a texto
                        // eco: respondemos con el mismo mensaje recibido
                        byte[] out = msg.getBytes(StandardCharsets.UTF_8);
                        DatagramPacket r = new DatagramPacket(out, out.length, p.getAddress(), p.getPort()); // Preparamos respuesta
                        s.send(r); // Enviamos eco al cliente
                        if ("FIN".equalsIgnoreCase(msg.trim())) break; // Si recibe "FIN", termina el servidor
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor UDP (E1) detenido: " + e.getMessage());
                }
            }
            // Método para detener el servidor de forma segura
            void stopServer() { running = false; if (ds != null) ds.close(); }
        }

        UdpEchoServer server = new UdpEchoServer(); // Creamos el servidor
        server.start(); // Lo arrancamos en un hilo aparte
        int port = awaitPort(() -> server.port, 2000); // Esperamos a que el servidor esté listo
        if (port < 0) { System.out.println("No se pudo iniciar servidor UDP."); return; }

        // Cliente UDP de prueba: envía varios mensajes y espera el eco
        try (DatagramSocket c = new DatagramSocket()) {
            String[] msgs = { "hola", "udp", "mundo", "FIN" }; // Mensajes a enviar
            for (String m : msgs) {
                byte[] data = m.getBytes(StandardCharsets.UTF_8); // Convertimos a bytes
                c.send(new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), port)); // Enviamos al servidor
                byte[] buf = new byte[1024]; // Buffer para la respuesta
                DatagramPacket resp = new DatagramPacket(buf, buf.length);
                c.receive(resp); // Esperamos la respuesta (eco)
                String eco = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8); // Convertimos a texto
                System.out.println("Cliente -> '" + m + "' | Servidor -> '" + eco + "'"); // Mostramos resultado
            }
        } catch (IOException e) {
            System.out.println("Cliente UDP (E1) error: " + e.getMessage());
        } finally {
            server.stopServer(); // Detenemos el servidor al acabar
        }
    }

    // ======================================================================
    // ! EJERCICIO 2 — TCP ECO (LÍNEAS)
    // * Qué se aprende:
    //      - ServerSocket (orientado a conexión) y Socket.
    //      - Lectura/escritura por líneas con BufferedReader/PrintWriter.
    // * Flujo DEMO:
    //      1) Servidor TCP en puerto efímero (0). Acepta UN cliente.
    //      2) Cliente envía 3 líneas. El servidor responde con eco.
    //      3) Con "FIN" el servidor cierra la conexión.
    // TODO: Retos extra
    //      - Permite múltiples clientes (cada uno en su hilo).
    //      - Registra cada mensaje con timestamp en un fichero .log.
    //      - Añade comando /mayus en servidor para responder en mayúsculas.
    // ======================================================================
    // ===================== EJERCICIO 2: TCP ECO =====================
    private void e2_tcpEco() {
        System.out.println("\n[E2] TCP Eco");

        // Clase interna para el servidor TCP que hace eco de las líneas recibidas.
        class TcpEchoServer extends Thread {
            volatile boolean running = true; // Controla si el servidor sigue activo
            ServerSocket ss; // ServerSocket para aceptar conexiones
            int port = -1; // Puerto en el que escucha el servidor
            @Override public void run() {
                try (ServerSocket s = new ServerSocket(0)) { // Puerto efímero (aleatorio)
                    ss = s;
                    port = s.getLocalPort(); // Guardamos el puerto asignado
                    try (Socket cli = s.accept(); // Espera a que un cliente se conecte
                         BufferedReader in = new BufferedReader(new InputStreamReader(cli.getInputStream(), StandardCharsets.UTF_8)); // Para leer líneas
                         PrintWriter out = new PrintWriter(new OutputStreamWriter(cli.getOutputStream(), StandardCharsets.UTF_8), true)) { // Para escribir líneas
                        String line;
                        while ((line = in.readLine()) != null) { // Lee línea a línea
                            out.println(line); // Hace eco de la línea recibida
                            if ("FIN".equalsIgnoreCase(line.trim())) break; // Si recibe "FIN", termina
                        }
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor TCP (E2) detenido: " + e.getMessage());
                }
            }
            // Método para detener el servidor de forma segura
            void stopServer() { running = false; try { if (ss != null) ss.close(); } catch (IOException ignored) {} }
        }

        TcpEchoServer server = new TcpEchoServer(); // Creamos el servidor
        server.start(); // Lo arrancamos en un hilo aparte
        int port = awaitPort(() -> server.port, 2000); // Esperamos a que el servidor esté listo
        if (port < 0) { System.out.println("No se pudo iniciar servidor TCP."); return; }

        // Cliente TCP de prueba: envía varias líneas y espera el eco
        try (Socket s = new Socket("127.0.0.1", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true)) {
            String[] lines = { "hola", "tcp", "eco", "FIN" }; // Líneas a enviar
            for (String l : lines) {
                out.println(l); // Enviamos línea al servidor
                String eco = in.readLine(); // Esperamos el eco
                System.out.println("Cliente -> '" + l + "' | Servidor -> '" + eco + "'"); // Mostramos resultado
            }
        } catch (IOException e) {
            System.out.println("Cliente TCP (E2) error: " + e.getMessage());
        } finally {
            server.stopServer(); // Detenemos el servidor al acabar
        }
    }

    // ======================================================================
    // ! EJERCICIO 3 — UDP PING CON TIMEOUT Y REINTENTO
    // * Qué se aprende:
    //      - Configurar timeout con setSoTimeout en DatagramSocket.
    //      - Implementar reintentos cuando no hay respuesta (UDP es no fiable).
    // * Flujo DEMO:
    //      1) Servidor UDP responde "pong" si recibe "ping". A veces NO responde
    //         para simular pérdidas (30% aprox.).
    //      2) Cliente envía "ping" y reintenta hasta 3 veces con timeout=300 ms.
    // TODO: Retos extra
    //      - Añade contador de pérdidas y cálculo de tasa de éxito.
    //      - Añade "ping <n>" -> responde "pong <n>".
    //      - Implementa ventanita de reenvío con numeración (1..N).
    // ======================================================================
    // ===================== EJERCICIO 3: UDP PING CON TIMEOUT Y REINTENTO =====================
    private void e3_udpPingTimeout() {
        System.out.println("\n[E3] UDP Ping con timeout + reintento");
        final Random RND = new Random(); // Generador aleatorio para simular pérdidas

        // Clase interna para el servidor UDP que responde "pong" a "ping" (a veces no responde)
        class UdpPingServer extends Thread {
            volatile boolean running = true; // Controla si el servidor sigue activo
            DatagramSocket ds; // Socket UDP del servidor
            int port = -1; // Puerto en el que escucha el servidor
            @Override public void run() {
                try (DatagramSocket s = new DatagramSocket(0)) { // Puerto efímero (aleatorio)
                    ds = s;
                    port = s.getLocalPort(); // Guardamos el puerto asignado
                    byte[] buf = new byte[256]; // Buffer para recibir datos
                    while (running) {
                        DatagramPacket p = new DatagramPacket(buf, buf.length); // Preparamos paquete para recibir
                        s.receive(p); // Espera bloqueante hasta recibir un datagrama
                        String msg = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8).trim(); // Convertimos a texto
                        // 30% de veces "no respondemos" (pérdida simulada)
                        if (!"ping".equalsIgnoreCase(msg) || RND.nextDouble() < 0.30) {
                            continue; // Simulamos pérdida: no respondemos
                        }
                        byte[] out = "pong".getBytes(StandardCharsets.UTF_8); // Respuesta
                        s.send(new DatagramPacket(out, out.length, p.getAddress(), p.getPort())); // Enviamos "pong"
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor UDP (E3) detenido: " + e.getMessage());
                }
            }
            // Método para detener el servidor de forma segura
            void stopServer() { running = false; if (ds != null) ds.close(); }
        }

        UdpPingServer server = new UdpPingServer(); // Creamos el servidor
        server.start(); // Lo arrancamos en un hilo aparte
        int port = awaitPort(() -> server.port, 2000); // Esperamos a que el servidor esté listo
        if (port < 0) { System.out.println("No se pudo iniciar servidor UDP."); return; }

        // Cliente UDP de prueba: envía "ping" y reintenta hasta 3 veces si no recibe respuesta
        try (DatagramSocket c = new DatagramSocket()) {
            c.setSoTimeout(300); // Timeout de 300 ms para recibir respuesta
            int maxRetries = 3; // Número máximo de reintentos
            for (int intento = 1; intento <= maxRetries; intento++) {
                byte[] out = "ping".getBytes(StandardCharsets.UTF_8); // Mensaje a enviar
                c.send(new DatagramPacket(out, out.length, InetAddress.getByName("127.0.0.1"), port)); // Enviamos "ping"
                try {
                    byte[] buf = new byte[32]; // Buffer para la respuesta
                    DatagramPacket resp = new DatagramPacket(buf, buf.length);
                    c.receive(resp); // Esperamos la respuesta
                    String ans = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8); // Convertimos a texto
                    System.out.println("Respuesta: " + ans + " (intento " + intento + ")"); // Mostramos resultado
                    break; // Si recibimos respuesta, salimos del bucle
                } catch (SocketTimeoutException ste) {
                    System.out.println("Timeout (intento " + intento + "), reintentando..."); // No hubo respuesta, reintentamos
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente UDP (E3) error: " + e.getMessage());
        } finally {
            server.stopServer(); // Detenemos el servidor al acabar
        }
    }

    // ======================================================================
    // ! EJERCICIO 4 — TCP DAYTIME (HORA DEL SERVIDOR)
    // * Qué se aprende:
    //      - Patrón petición-respuesta con cierre inmediato de conexión.
    //      - Formatear fechas y enviar una sola línea al cliente.
    // * Flujo DEMO:
    //      1) Servidor TCP escucha en puerto efímero (0).
    //      2) Cliente conecta, lee 1 línea con la fecha/hora y cierra.
    // TODO: Retos extra
    //      - Devuelve la hora en distintos formatos (ISO, HH:mm:ss, epoch).
    //      - Permite múltiples clientes (cada uno en su hilo).
    //      - Añade comando "/utc" para devolver hora en UTC.
    // ======================================================================
    // ===================== EJERCICIO 4: TCP DAYTIME (HORA DEL SERVIDOR) =====================
    private void e4_tcpDaytime() {
        System.out.println("\n[E4] TCP Daytime (hora del servidor)");

        // Clase interna para el servidor TCP que envía la hora actual al cliente
        class DaytimeServer extends Thread {
            volatile boolean running = true; // Controla si el servidor sigue activo
            ServerSocket ss; // ServerSocket para aceptar conexiones
            int port = -1; // Puerto en el que escucha el servidor
            @Override public void run() {
                try (ServerSocket s = new ServerSocket(0)) { // Puerto efímero (aleatorio)
                    ss = s;
                    port = s.getLocalPort(); // Guardamos el puerto asignado
                    Socket cli = s.accept(); // Espera a que un cliente se conecte
                    try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(cli.getOutputStream(), StandardCharsets.UTF_8))) {
                        String now = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()); // Obtenemos la hora actual
                        out.write(now); // Enviamos la hora al cliente
                        out.write("\n"); // Nueva línea
                        out.flush(); // Aseguramos que se envía
                    } finally {
                        try { cli.close(); } catch (IOException ignored) {} // Cerramos el socket del cliente
                    }
                } catch (IOException e) {
                    if (running) System.out.println("Servidor Daytime detenido: " + e.getMessage());
                }
            }
            // Método para detener el servidor de forma segura
            void stopServer() { running = false; try { if (ss != null) ss.close(); } catch (IOException ignored) {} }
        }

        DaytimeServer server = new DaytimeServer(); // Creamos el servidor
        server.start(); // Lo arrancamos en un hilo aparte
        int port = awaitPort(() -> server.port, 2000); // Esperamos a que el servidor esté listo
        if (port < 0) { System.out.println("No se pudo iniciar servidor Daytime."); return; }

        // Cliente TCP de prueba: conecta y lee la hora enviada por el servidor
        try (Socket s = new Socket("127.0.0.1", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8))) {
            String line = in.readLine(); // Leemos la línea con la hora
            System.out.println("Hora recibida: " + line); // Mostramos la hora recibida
        } catch (IOException e) {
            System.out.println("Cliente Daytime error: " + e.getMessage());
        } finally {
            server.stopServer(); // Detenemos el servidor al acabar
        }
    }

    // ─────────────────────────── Utilidades ───────────────────────────
    // ===================== UTILIDAD: Espera activa hasta que el puerto esté listo =====================
    private static int awaitPort(IntSupplier portSupplier, long timeoutMs) {
        long end = System.currentTimeMillis() + timeoutMs; // Momento de fin
        while (System.currentTimeMillis() < end) {
            int p = portSupplier.getAsInt(); // Obtenemos el puerto
            if (p > 0) return p; // Si es válido, lo devolvemos
            try { Thread.sleep(10); } catch (InterruptedException ignored) {} // Esperamos un poco
        }
        return -1; // Si no se obtiene en el tiempo dado, devolvemos -1
    }
    // Interfaz funcional para obtener el puerto de forma flexible
    private interface IntSupplier { int getAsInt(); }
}
