// ==============================================================================
//   FTTP‑Lite (Práctica guiada SIN código) — Plantilla en Java con MENÚ
//   Autor: JOAQUIN
//   Curso 2025–2026
//
//   ▶ Propósito didáctico
//     Esta plantilla NO incluye implementación, NI prints, NI lógica resuelta.
//     Te da la ESTRUCTURA completa y comentarios detallados para que rellenes
//     el código tú mismo. Es una variante más simple basada en el ejercicio
//     visto en clase (UDP → parse → TCP), pensada para reforzar conceptos.
//
//   ▶ Qué vas a construir (visión general)
//     1) SERVIDOR (UDP:2600):
//          • Escucha datagramas con formato textual exacto: [puerto,nombre,fechaISO]
//          • Al recibir uno, muestra (con logs que tú añadirás):
//              - IP del remitente
//              - Fecha/hora de recepción (lado servidor)
//              - Fecha declarada por el cliente (campo del mensaje)
//              - Nombre del cliente
//              - Puerto TCP al que conectarse
//          • Abre conexión TCP hacia (IP_remitente, puerto) y lee líneas hasta EOF.
//          • Maneja errores sin detener el bucle de escucha UDP.
//
//     2) CLIENTE (sencillo):
//          • Abre un ServerSocket TCP en puerto efímero (0) y guarda el puerto real.
//          • Construye el payload: [puerto,nombre,fechaISO].
//          • Envía un datagrama UDP al servidor (IP, puerto 2600).
//          • Acepta la conexión TCP entrante (hecha por el servidor) y envía 3–5 líneas.
//          • Cierra flujos/sockets y muestra un resumen (con tus propios logs).
//
//   ▶ Reglas de la plantilla
//     • No hay código implementado: SOLO comentarios guía y métodos vacíos.
//     • Mantén el formato exacto del payload. Evita espacios extra.
//     • Trabaja incrementalmente: primero UDP, luego TCP, luego pulir.
//     • Usa try‑with‑resources y captura excepciones específicas.
//
//   ▶ Convenciones “Better Comments”
//       // ! Importante    // * Info    // ? Nota    // TODO: tarea    // ✔ Hecho
// ==============================================================================

import java.io.*;       // (InputStream/OutputStream, Reader/Writer, Buffered...)
import java.net.*;      // (DatagramSocket, DatagramPacket, ServerSocket, Socket)
import java.nio.charset.StandardCharsets; // (UTF‑8)
import java.time.LocalDateTime;          // (fecha cliente/servidor)
import java.time.format.DateTimeFormatter; // (ISO_LOCAL_DATE_TIME)
import java.util.Scanner;                // (opcional para el menú)

public class FTTP_Practica_Guiada {

    // ───────────────────────── Configuración general ─────────────────────────
    // ! Puerto UDP del servidor FTTP‑Lite. Puedes dejar 2600 o hacerlo configurable.
    private static final int UDP_PORT = 2600;
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // * Campos útiles para el CLIENTE (puedes usarlos para coordinar pasos)
    private ServerSocket clientInboundRef; // Guardará el ServerSocket(0) del cliente
    private int clientTcpPort;             // Puerto TCP real asignado al cliente

    // * Referencia del hilo de servidor para control desde el menú (opcional)
    private FttpLiteServer serverRef;

    // ──────────────────────────── Entry point ────────────────────────────────
    public static void main(String[] args) {
        // TODO: Instanciar y lanzar el menú principal.
        // new FTTP_Practica_Guiada().run();
    }

    // ───────────────────────────── Menú (esqueleto) ──────────────────────────
    private void run() {
        // TODO: Implementar un bucle de menú con Scanner (si quieres), con opciones:
        //   1) startServer()
        //   2) launchClientSimple("127.0.0.1")
        //   3) printHints()
        //   0) stopServerIfRunning() y salir
        // * Recuerda: aquí NO ponemos System.out.println en la plantilla final.
        //   Deja comentarios donde colocarías tus propios logs.
    }

    // ========================== OPCIÓN 1 — Servidor ===========================
    private void startServer() {
        // TODO: Si ya hay un servidor vivo (serverRef != null && serverRef.isAlive()), no arrancar otro.
        // TODO: Crear instancia de FttpLiteServer y hacer start().
        // ? Sugerencia: informa con tus logs (que tú añadirás) que el servidor está arrancado.
    }

    private void stopServerIfRunning() {
        // TODO: Si serverRef != null, invocar requestStop() y join() con un pequeño timeout.
        // ? Sugerencia: captura InterruptedException si usas join().
    }

    // ========================== OPCIÓN 2 — Cliente ============================
    private void launchClientSimple(String serverIp) {
        // * Secuencia recomendada de pasos (completa cada método de abajo):
        //   1) startClientTcpListener();            // abre ServerSocket(0) y guarda clientTcpPort
        //   2) String nombre = System.getProperty("user.name", "alumno");
        //      String fechaIso = ISO.format(LocalDateTime.now());
        //      String payload = buildHelloPayload(clientTcpPort, nombre, fechaIso);
        //   3) sendUdpHello(serverIp, UDP_PORT, payload);
        //   4) acceptAndSendDataOverTcp();          // escribe 3–5 líneas y cierra
        //   5) printClientSummary(nombre, fechaIso);
        // * Coloca tus logs donde te ayuden a depurar (no en esta plantilla).
    }

    // =================== MÉTODOS A RELLENAR (lado CLIENTE) ====================

    /**
     * * Abre un ServerSocket en puerto 0 (efímero) y guarda:
     *     - la referencia en clientInboundRef
     *     - el puerto real en clientTcpPort (usar getLocalPort())
     * TODO: Implementa la apertura y deja el socket listo para accept().
     * TODO: Maneja IOException adecuadamente.
     */
    private void startClientTcpListener() {
        // TODO: clientInboundRef = new ServerSocket(0);
        // TODO: clientTcpPort = clientInboundRef.getLocalPort();
        // TODO: (opcional) tiempo de espera, política de cierre, etc.
    }

    /**
     * * Construye y devuelve el payload EXACTO: [puerto,nombre,fechaISO]
     *   Ejemplo: [53111,joaquin,2025-10-10T10:15:30]
     * TODO: Evita espacios. Usa los parámetros en ese orden.
     */
    private String buildHelloPayload(int tcpPort, String nombre, String fechaIso) {
        // TODO: return "[" + tcpPort + "," + nombre + "," + fechaIso + "]";
        return null; // ← Sustituye por el String real
    }

    /**
     * * Envía el datagrama UDP con el payload al servidor FTTP‑Lite.
     *   Pasos guía:
     *     1) Crear DatagramSocket (try‑with‑resources)
     *     2) Obtener bytes UTF‑8 de payload
     *     3) Construir DatagramPacket(bytes, len, InetAddress.getByName(serverIp), udpPort)
     *     4) ds.send(packet)
     * TODO: Maneja UnknownHostException/IOException según proceda.
     */
    private void sendUdpHello(String serverIp, int udpPort, String payload) {
        // TODO: Implementación UDP según pasos guía.
    }

    /**
     * * Acepta la conexión TCP del servidor y envía 3–5 líneas de texto + "FIN".
     *   Pasos guía:
     *     1) Socket s = clientInboundRef.accept()
     *     2) Crear BufferedWriter sobre s.getOutputStream() (UTF‑8)
     *     3) Escribir varias líneas (por ejemplo, encabezado + 3 líneas de contenido + FIN)
     *     4) flush() y cerrar salida (el cierre de output indica EOF al otro lado)
     *     5) Cerrar el socket
     * TODO: Maneja IOException. Considera try‑with‑resources para el writer.
     */
    private void acceptAndSendDataOverTcp() {
        // TODO: Implementación TCP lado CLIENTE (escritura de 3–5 líneas + FIN).
    }

    /**
     * * Imprime un pequeño resumen final del envío (con tus propios logs):
     *     - nombre, fechaIso, puerto usado, nº de líneas/bytes enviados (si los cuentas)
     * TODO: Añade la información que te resulte útil para depurar o evaluar.
     */
    private void printClientSummary(String nombre, String fechaIso) {
        // TODO: Tus logs/resumen aquí (cuando implementes tu versión).
    }

    // =================== MÉTODOS A RELLENAR (lado SERVIDOR) ===================

    /**
     * Hilo del servidor FTTP‑Lite: escucha UDP (UDP_PORT), parsea, conecta por TCP.
     * Recomendación de flujo dentro de run():
     *   1) Crear DatagramSocket(UDP_PORT) y setSoTimeout(… p.ej. 500 ms)
     *   2) Bucle while(running):
     *       2.1) receive(packet)
     *       2.2) Decodificar String UTF‑8 con new String(getData(), 0, getLength(), UTF_8)
     *       2.3) Hello h = parseHello(mensaje)
     *       2.4) Conectar Socket tcp = new Socket(remitenteIP, h.puertoTcp)
     *       2.5) Leer líneas con BufferedReader hasta EOF y (opcional) contarlas
     *       2.6) Cerrar TCP y continuar escuchando UDP
     *   3) Manejo de excepciones: SocketTimeoutException (control de bucle), otras IOException
     *   4) Cierre ordenado de recursos al terminar
     */
    private static class FttpLiteServer extends Thread {
        private volatile boolean running = true;
        public void requestStop() { running = false; }
        @Override public void run() {
            // TODO: Implementa el esquema descrito arriba.
        }
    }

    // ====================== Utilidades comunes a rellenar =====================

    /**
     * * Parsea el mensaje con formato [puerto,nombre,fechaISO] y devuelve un bean Hello.
     *   Pasos guía:
     *     1) Validar que empieza por '[' y termina por ']'
     *     2) Quitar corchetes (substring)
     *     3) split con límite 3 → parts[0]=puerto, [1]=nombre, [2]=fecha
     *     4) trim() de cada parte y parseInt de puerto
     *     5) Validaciones básicas (no vacíos, puerto>0)
     *     6) return new Hello(puerto, nombre, fecha)
     * TODO: Lanza IllegalArgumentException si el formato es inválido.
     */
    private static Hello parseHello(String raw) {
        // TODO: Implementa el parseo robusto según los pasos guía.
        return null; // ← Sustituye por un Hello real cuando implementes
    }

    /** Bean sencillo para el mensaje HELLO. */
    private static class Hello {
        final int puertoTcp; final String nombre; final String fechaIso;
        Hello(int puertoTcp, String nombre, String fechaIso) {
            this.puertoTcp = puertoTcp; this.nombre = nombre; this.fechaIso = fechaIso;
        }
    }

    // ============================== Pistas / Checklist ========================

    private void printHints() {
        // TODO: Si quieres, imprime o documenta en README las siguientes pistas:
        // 1) ServerSocket del cliente: new ServerSocket(0) → getLocalPort(). Guarda la ref y el puerto.
        // 2) UDP envío: DatagramSocket + DatagramPacket(bytes UTF‑8, InetAddress.getByName(ip), puerto).
        // 3) Recepción UDP: new String(packet.getData(), 0, packet.getLength(), UTF_8).
        // 4) Formato estricto: [puerto,nombre,fechaISO] (sin espacios extra).
        // 5) TCP servidor: new Socket(remitenteIP, hello.puertoTcp) + BufferedReader.readLine().
        // 6) TCP cliente: accept() + BufferedWriter.write()/newLine()/flush() + cierre de output.
        // 7) Control de cierre: escribir "FIN" y cerrar salida para señalar EOF.
        // 8) Excepciones: captura SocketTimeoutException para no bloquear el hilo.
        // 9) Mejora tus logs con timestamps y prefijos [SERVIDOR]/[CLIENTE]/[UTIL].
        // 10) Ampliaciones: validar fecha ISO, medir bytes/tiempos, permitir JSON alternativo.
    }
}
