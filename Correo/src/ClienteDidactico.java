// *******************************************************************************************
// * 📚 CLIENTE SMTP DIDÁCTICO EN JAVA
// * -----------------------------------------------------------------------------------------
// * Esta versión mejora el ejemplo básico (`Cliente.java`) añadiendo métodos reutilizables,
// * verificación de códigos de respuesta, validación de correos y comentarios explicativos.
// *
// * 💡 Objetivos extra:
// *   - Mostrar refactorización: separar lógica de negocio en métodos pequeños.
// *   - Comprobar códigos numéricos (mínimo) para detectar errores tempranos.
// *   - Ejemplo de posible transición a EHLO y extensiones.
// *
// * 🧪 NOTA: No incluye STARTTLS ni AUTH todavía (ver TODOs). Se asume servidor abierto.
// *******************************************************************************************

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ClienteDidactico {

    // * 🔐 Regex muy simple para validar formato básico de correo.
    // ! NO cubre todos los casos RFC 5322, solo uso formativo.
    private static final Pattern EMAIL_SIMPLE = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void main(String[] args) {
        String servidor = "192.168.128.2"; // TODO: Ajustar al entorno real
        int puerto = 25; // ? Puerto estándar sin cifrado

        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner sc = new Scanner(System.in)) {

            // * 1️⃣ Banner inicial (esperamos 220)
            String banner = readLine(in);
            logServidor(banner);
            checkCodeStartsWith(banner, 220);

            // * 2️⃣ Intentar EHLO primero (más moderno). Si falla, probar HELO.
            if (!sendAndExpect(out, in, "EHLO ruben.foo", 250)) {
                // ! Fallback
                sendAndExpect(out, in, "HELO ruben.foo", 250);
            }

            // * 📝 Recoger datos del usuario
            String remitente = askValidEmail(sc, "Correo del remitente: ");
            String destinatario = askValidEmail(sc, "Correo del destinatario: ");
            System.out.print("Mensaje a enviar: ");
            String mensaje = sc.nextLine();

            // * 3️⃣ MAIL FROM
            sendAndExpect(out, in, "MAIL FROM:<" + remitente + ">", 250);

            // * 4️⃣ RCPT TO
            sendAndExpect(out, in, "RCPT TO:<" + destinatario + ">", 250, 251);

            // * 5️⃣ DATA
            if (sendAndExpect(out, in, "DATA", 354)) {
                // * Cabeceras básicas
                writeLine(out, "From: " + remitente);
                writeLine(out, "To: " + destinatario);
                writeLine(out, "Subject: Prueba desde ClienteDidactico");
                writeLine(out, ""); // Línea vacía
                writeLine(out, mensaje);
                writeLine(out, "."); // Terminar cuerpo
                out.flush();
                // * Respuesta tras el cuerpo
                String postData = readLine(in);
                logServidor(postData);
                checkCodeStartsWith(postData, 250);
            }

            // * 6️⃣ QUIT
            sendAndExpect(out, in, "QUIT", 221);

            System.out.println("✅ Mensaje enviado (según respuestas del servidor).");

        } catch (UnknownHostException e) {
            System.err.println("Error: host desconocido -> " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println("Error: no se pudo establecer conexión (puerto bloqueado / servidor caído).");
        } catch (SocketTimeoutException e) {
            System.err.println("Error: tiempo de espera superado.");
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // * 🧪 Método para enviar un comando y verificar que el código numérico de respuesta
    // * coincide con al menos uno de los esperados.
    private static boolean sendAndExpect(BufferedWriter out, BufferedReader in, String comando, int... codigosEsperados) throws IOException {
        System.out.println("Cliente: " + comando);
        writeLine(out, comando);
        out.flush();
        String respuesta = readLine(in);
        logServidor(respuesta);
        return codeMatches(respuesta, codigosEsperados);
    }

    // * ✔ Comprueba si la respuesta empieza por alguno de los códigos aceptables.
    private static boolean codeMatches(String respuesta, int... codigos) {
        if (respuesta == null || respuesta.length() < 3) return false;
        String prefijo = respuesta.substring(0, 3);
        try {
            int code = Integer.parseInt(prefijo);
            for (int c : codigos) if (c == code) return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
        return false;
    }

    // * 📤 Utilidad para escribir una línea terminada en CRLF.
    private static void writeLine(BufferedWriter out, String linea) throws IOException {
        out.write(linea + "\r\n");
    }

    // * 📥 Leer línea (simplificado: no maneja respuestas multilinea tipo 250-...).
    private static String readLine(BufferedReader in) throws IOException {
        return in.readLine();
    }

    // * 🖨 Log formateado del servidor.
    private static void logServidor(String linea) {
        System.out.println("Servidor: " + linea);
    }

    // * 🔍 Verificar que la respuesta comienza con el código esperado (didáctico, error simple).
    private static void checkCodeStartsWith(String respuesta, int codigo) {
        if (respuesta == null || respuesta.length() < 3) {
            System.err.println("! Respuesta inválida o vacía (esperado: " + codigo + ")");
            return;
        }
        String prefijo = respuesta.substring(0, 3);
        if (!prefijo.equals(String.valueOf(codigo))) {
            System.err.println("! Código inesperado. Recibido " + prefijo + ", esperaba " + codigo);
        }
    }

    // * 🧪 Solicita y valida un email sencillo; reintenta si no cumple el regex.
    private static String askValidEmail(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String correo = sc.nextLine().trim();
            if (EMAIL_SIMPLE.matcher(correo).matches()) {
                return correo;
            }
            System.err.println("! Formato de correo no válido. Intenta de nuevo.");
        }
    }

    // TODO: Implementar STARTTLS (necesitaría negociar y envolver en SSLSocket).
    // TODO: Añadir autenticación AUTH LOGIN (codificación Base64 usuario/contraseña).
    // TODO: Manejar respuestas multilinea (leer hasta línea sin '-').
    // TODO: Permitir múltiples destinatarios (varios RCPT TO).
    // TODO: Construir correos MIME (adjuntos, texto/HTML).
}
