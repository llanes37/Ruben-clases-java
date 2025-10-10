// ==============================================================================
//   FTTP (File Transfer Provengana Protocol) — Simulación en Java con MENÚ
//   Autor: ChatGPT para clases DAM (M0490: Programación de Servicios y Procesos)
//   Curso 2025–2026
//
//   ▶ Basado en el enunciado del PDF y siguiendo el mismo estilo
//     pedagógico/estructura del archivo de ejemplo con menú de TCP/UDP (comentarios
//     tipo "Better Comments").
//
//   ▶ ¿Qué pide el enunciado (traducción al castellano)?
//     1) Servidor FTTP: escucha por UDP en el puerto 2500. Cuando llega un datagrama
//        con el formato [puerto, nombre, fechaISO], indica por pantalla:
//          - que se ha recibido una conexión,
//          - la fecha/hora de recepción (servidor),
//          - la fecha que declara el cliente y
//          - el nombre del cliente.
//        A continuación, el SERVIDOR abre una conexión TCP hacia la IP y el "puerto"
//        indicados por el cliente (el cliente debe haber preparado previamente su
//        ServerSocket escuchando). Una vez conectado, el servidor lee los datos que
//        el cliente le envía por ese TCP hasta que el cliente cierra. El servidor debe
//        capturar excepciones y seguir a la espera de nuevas conexiones UDP.
//
//     2) Cliente FTTP: arranca un ServerSocket TCP en un puerto libre (o configurable),
//        construye el mensaje UDP [puerto, nombre, fechaISO] y lo envía al servidor
//        FTTP (IP/puerto UDP 2500). Cuando el servidor se conecta por TCP, el cliente
//        envía las "dades" (varias líneas a modo de demo) y cierra.
//
//   ▶ Cómo usar (compilar/ejecutar)
//       javac FTTP_Provengana_Menu.java && java FTTP_Provengana_Menu
//     MENÚ:
//       1) Arrancar Servidor FTTP (UDP:2500, TCP cliente on‑demand)
//       2) Lanzar Cliente FTTP (demo → localhost)
//       9) Demo completa (arranca servidor, lanza cliente, muestra flujo)
//       0) Salir
//
//   ▶ Convenciones de "Better Comments"
//      // ! Importante    // * Info general    // ? Nota    // TODO: Tarea/Mejora
// ==============================================================================

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FTTP_Programa_Menu {

    // ──────────────────────────────────────────────────────────────────────────
    // Configuración general
    // ──────────────────────────────────────────────────────────────────────────
    private static final Scanner SC = new Scanner(System.in);
    private static final int FTTP_UDP_PORT = 2500; // ! Puerto UDP del servidor FTTP (según enunciado)
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Referencia al hilo del servidor para poder pararlo desde el menú (opcional)
    private FttpServer serverRef;

    public static void main(String[] args) {
        new FTTP_Programa_Menu().run();
    }

    private void run() {
        while (true) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("FTTP — Simulación (Menú)");
            System.out.println("────────────────────────────────────────");
            System.out.println("1) Arrancar servidor FTTP (UDP: " + FTTP_UDP_PORT + ")");
            System.out.println("2) Lanzar cliente FTTP (demo → 127.0.0.1)");
            System.out.println("9) Demo completa (servidor + cliente)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String opt = SC.nextLine().trim();
            switch (opt) {
                case "1" -> startServer();
                case "2" -> launchClientDemo("127.0.0.1");
                case "9" -> demoCompleta();
                case "0" -> { stopServerIfRunning(); System.out.println("Fin."); return; }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // ===================== OPCIÓN 1 — Arrancar servidor FTTP =====================
    private void startServer() {
        if (serverRef != null && serverRef.isAlive()) {
            System.out.println("Servidor FTTP ya está en ejecución.");
            return;
        }
        serverRef = new FttpServer();
        serverRef.start();
        System.out.println("Servidor FTTP arrancado. Escuchando UDP en puerto " + FTTP_UDP_PORT + ".");
        System.out.println("→ Deja esta ventana abierta. Puedes lanzar el cliente (opción 2) desde otra consola.");
    }

    private void stopServerIfRunning() {
        if (serverRef != null) {
            serverRef.requestStop();
            try { serverRef.join(500); } catch (InterruptedException ignored) {}
        }
    }

    // ===================== OPCIÓN 2 — Lanzar cliente FTTP (demo) =====================
    private void launchClientDemo(String serverIp) {
        // * Preparamos el "TCP inbound" del cliente: será el puerto al que el SERVIDOR se conectará.
        try (ServerSocket inbound = new ServerSocket(0)) { // puerto efímero
            int tcpPort = inbound.getLocalPort();
            String nombre = System.getProperty("user.name", "alumno");
            String fechaIso = ISO.format(LocalDateTime.now());

            // * Construimos el payload FTTP según enunciado: [puerto, nombre, fecha]
            String payload = "[" + tcpPort + "," + nombre + "," + fechaIso + "]";

            // * Enviamos el datagrama UDP al servidor FTTP (IP del servidor, puerto 2500)
            try (DatagramSocket udp = new DatagramSocket()) {
                byte[] data = payload.getBytes(StandardCharsets.UTF_8);
                DatagramPacket pkt = new DatagramPacket(
                        data, data.length,
                        InetAddress.getByName(serverIp),
                        FTTP_UDP_PORT
                );
                udp.send(pkt);
                System.out.println("[CLIENTE] Enviado UDP a " + serverIp + ":" + FTTP_UDP_PORT + " -> " + payload);
            }

            // * Esperamos la conexión TCP SALIENTE del servidor (el servidor actuará ahora como cliente TCP)
            try (Socket s = inbound.accept();
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8))) {
                System.out.println("[CLIENTE] Conexión TCP aceptada desde " + s.getRemoteSocketAddress());

                // * Enviamos "datos" de ejemplo. En real podría ser un fichero grande.
                out.write("FTTP/1.0 Demo\n");
                out.write("Nombre=" + nombre + "\n");
                out.write("Fecha=" + fechaIso + "\n");
                out.write("Contenido=Hola desde el cliente FTTP (línea 1)\n");
                out.write("Contenido=Hola desde el cliente FTTP (línea 2)\n");
                out.write("FIN\n");
                out.flush();
                System.out.println("[CLIENTE] Datos enviados. Cerrando TCP…");
            }

            System.out.println("[CLIENTE] Finalizado.");
        } catch (IOException e) {
            System.out.println("[CLIENTE] Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    // ===================== OPCIÓN 9 — Demo completa =====================
    private void demoCompleta() {
        startServer();
        sleep(200); // pequeña espera para asegurar que el UDP está bind
        launchClientDemo("127.0.0.1");
        // ? Dejamos el servidor vivo para nuevas pruebas. Parar manualmente con 0) Salir
    }

    // ─────────────────────────── Implementación Servidor ───────────────────────────
    // El servidor FTTP: escucha UDP:2500, parsea [puerto, nombre, fecha], imprime info y
    // abre una conexión TCP hacia el cliente para recibir datos de forma robusta.
    private static class FttpServer extends Thread {
        private volatile boolean running = true;

        public void requestStop() { running = false; }

        @Override public void run() {
            System.out.println("[SERVIDOR] FTTP escuchando UDP en puerto " + FTTP_UDP_PORT + "…");
            try (DatagramSocket ds = new DatagramSocket(FTTP_UDP_PORT)) {
                ds.setSoTimeout(500); // ? Permite despertar periódicamente para comprobar 'running'
                byte[] buf = new byte[2048];
                while (running) {
                    try {
                        DatagramPacket p = new DatagramPacket(buf, buf.length);
                        ds.receive(p); // Bloquea hasta recibir datagrama o timeout
                        String msg = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8).trim();

                        // * Parseo del formato [puerto, nombre, fecha]
                        FttpHello hello = FttpHello.parse(msg);

                        // * Información de recepción
                        String now = ISO.format(LocalDateTime.now());
                        System.out.println("\n[SERVIDOR] Conexión FTTP recibida de " + p.getAddress().getHostAddress());
                        System.out.println("           Fecha recepción (servidor): " + now);
                        System.out.println("           Fecha declarada (cliente):  " + hello.fechaIso);
                        System.out.println("           Nombre cliente:             " + hello.nombre);
                        System.out.println("           Puerto TCP destino:         " + hello.puertoTcp);

                        // * Conectar por TCP al cliente (IP remitente del UDP, puerto indicado)
                        InetAddress clientIp = p.getAddress();
                        try (Socket s = new Socket(clientIp, hello.puertoTcp);
                             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8))) {
                            System.out.println("[SERVIDOR] TCP conectado a " + s.getRemoteSocketAddress() + ". Recibiendo datos…");
                            String line;
                            while ((line = in.readLine()) != null) {
                                System.out.println("[SERVIDOR] «" + line + "»");
                            }
                            System.out.println("[SERVIDOR] Fin de datos (cliente cerró). Listo para nuevas conexiones UDP.");
                        } catch (IOException e) {
                            System.out.println("[SERVIDOR] Error TCP al conectar/leer: " + e.getMessage());
                        }
                    } catch (SocketTimeoutException ste) {
                        // Despertar periódico para revisar 'running'
                    } catch (IllegalArgumentException | IndexOutOfBoundsException iae) {
                        System.out.println("[SERVIDOR] Datagrama con formato inválido. Se ignora. Detalle: " + iae.getMessage());
                    } catch (IOException e) {
                        if (running) System.out.println("[SERVIDOR] Error UDP: " + e.getMessage());
                    }
                }
            } catch (SocketException se) {
                System.out.println("[SERVIDOR] No se pudo abrir UDP:" + se.getMessage());
            }
            System.out.println("[SERVIDOR] Detenido.");
        }
    }

    // ─────────────────────────── Mensaje HELLO FTTP ───────────────────────────
    // Formato textual: [puerto, nombre, fechaISO]  → Ej: [53111, joaquin, 2025-10-10T10:15:30]
    private static class FttpHello {
        final int puertoTcp;
        final String nombre;
        final String fechaIso;
        private FttpHello(int puertoTcp, String nombre, String fechaIso) {
            this.puertoTcp = puertoTcp; this.nombre = nombre; this.fechaIso = fechaIso;
        }
        static FttpHello parse(String raw) {
            String s = raw.trim();
            if (!s.startsWith("[") || !s.endsWith("]"))
                throw new IllegalArgumentException("Se esperaba formato [a,b,c]");
            s = s.substring(1, s.length()-1); // quita corchetes
            // split en 3 campos máximo
            String[] parts = s.split(",", 3);
            if (parts.length != 3) throw new IllegalArgumentException("Faltan campos");
            int port = Integer.parseInt(parts[0].trim());
            String nombre = parts[1].trim();
            String fecha = parts[2].trim();
            if (nombre.isEmpty() || fecha.isEmpty()) throw new IllegalArgumentException("Campos vacíos");
            return new FttpHello(port, nombre, fecha);
        }
    }

    // ─────────────────────────── Utilidades ───────────────────────────
    private static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }

    // ======================================================================
    // TODO: Retos extra propuestos
    //  - Validar que la fecha ISO del cliente tiene formato correcto (DateTimeFormatter.parse)
    //  - Guardar un log de conexiones en un fichero (timestamp, IP, nombre, bytes recibidos)
    //  - Permitir que el cliente lea un archivo real y lo envíe por TCP al servidor
    //  - En el servidor, permitir múltiples conexiones TCP concurrentes por cada datagrama (pool)
    //  - Hacer robusto el parseo permitiendo JSON: {"port":123, "name":"...", "date":"..."}
    // ======================================================================
}
