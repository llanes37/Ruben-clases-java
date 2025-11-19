// *******************************************************************************************
// * 🧪 ClientePlantilla – Construye tu propio cliente SMTP
// * -----------------------------------------------------------------------------------------
// * INSTRUCCIONES PARA EL ALUMNO:
// *   - Este archivo está incompleto: rellena cada TODO siguiendo el orden propuesto.
// *   - Apóyate en 'SMTPUtilsPlantilla' para lógica reutilizable.
// *   - Compila y prueba después de cada bloque (banner, EHLO, MAIL, RCPT...).
// *
// * ESTILO DE COMENTARIOS (Better Comments):
// *   // * Explicación general
// *   // ? Pregunta / reflexión
// *   // ! Advertencia / precaución
// *   // TODO: Tarea a realizar
// *
// * OBJETIVO FINAL: Enviar un correo simple y cerrar conexión limpiamente.
// *******************************************************************************************

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClientePlantilla {

    public static void main(String[] args) {
        // * 🔧 Configuración inicial (ajusta IP y puerto según tu entorno de pruebas)
        String servidor = "192.168.128.2"; // TODO: Cambiar si tu servidor es distinto
        int puerto = 25; // ! Puerto SMTP sin cifrado (puede estar bloqueado en tu red)

        // TODO: (Opcional) Añadir timeout: socket.setSoTimeout(ms);

        try (
                // * 1️⃣ Crear socket TCP hacia el servidor SMTP
                // TODO: Instanciar Socket con servidor y puerto
                Socket socket = new Socket(servidor, puerto);
                // * 2️⃣ Crear flujos de lectura y escritura (texto)
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner sc = new Scanner(System.in)
        ) {
            // * 3️⃣ Leer banner inicial (esperado código 220)
            // TODO: Usar SMTPUtilsPlantilla.readLine(in) y mostrarlo
            String banner = SMTPUtilsPlantilla.readLine(in); // TODO: validar código 220
            System.out.println("Servidor: " + banner);

            // TODO: Verificar banner con requireCode(...)

            // * 4️⃣ Enviar EHLO (si falla, probar HELO)
            // TODO: Usar SMTPUtilsPlantilla.sendCommand(out, in, "EHLO tuDominio")
            String respEhlo = SMTPUtilsPlantilla.sendCommand(out, in, "EHLO alumno.test");
            System.out.println("Servidor: " + respEhlo);
            // TODO: Validar código 250. Si no es 250, enviar HELO y validar.

            // * 📝 Pedir datos al usuario
            System.out.print("Correo remitente: ");
            String remitente = sc.nextLine(); // TODO: Validar formato con isValidEmail
            System.out.print("Correo destinatario: ");
            String destinatario = sc.nextLine(); // TODO: Validar formato también
            System.out.print("Mensaje (línea única): ");
            String mensaje = sc.nextLine();

            // * 5️⃣ MAIL FROM
            // TODO: Construir comando MAIL FROM y enviarlo, validar código 250
            String respMail = SMTPUtilsPlantilla.sendCommand(out, in, "MAIL FROM:<" + remitente + ">");
            System.out.println("Servidor: " + respMail);

            // * 6️⃣ RCPT TO
            // TODO: Enviar RCPT TO y validar (250 o 251)
            String respRcpt = SMTPUtilsPlantilla.sendCommand(out, in, "RCPT TO:<" + destinatario + ">");
            System.out.println("Servidor: " + respRcpt);

            // * 7️⃣ DATA
            // TODO: Enviar DATA y esperar código 354 antes de mandar cuerpo
            String respData = SMTPUtilsPlantilla.sendCommand(out, in, "DATA");
            System.out.println("Servidor: " + respData);

            // TODO: Validar 354

            // * 8️⃣ Cabeceras y cuerpo
            // TODO: Usar writeLine para enviar From, To, Subject y línea en blanco
            SMTPUtilsPlantilla.writeLine(out, "From: " + remitente);
            SMTPUtilsPlantilla.writeLine(out, "To: " + destinatario);
            SMTPUtilsPlantilla.writeLine(out, "Subject: Prueba alumno");
            SMTPUtilsPlantilla.writeLine(out, ""); // línea vacía separadora
            // TODO: Escribir cuerpo (mensaje)
            SMTPUtilsPlantilla.writeLine(out, mensaje);
            // TODO: Finalizar con punto solo
            SMTPUtilsPlantilla.writeLine(out, ".");
            out.flush();

            // TODO: Leer respuesta tras cuerpo y validar código 250
            String respPostData = SMTPUtilsPlantilla.readLine(in);
            System.out.println("Servidor: " + respPostData);

            // * 9️⃣ QUIT
            // TODO: Enviar QUIT y validar código 221
            String respQuit = SMTPUtilsPlantilla.sendCommand(out, in, "QUIT");
            System.out.println("Servidor: " + respQuit);

            // * ✅ Resumen final
            System.out.println("Fin de la sesión SMTP (revisa códigos para confirmar éxito).");

        } catch (UnknownHostException e) {
            System.err.println("! Host desconocido: " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println("! No se pudo conectar (puerto bloqueado/servidor caído).");
        } catch (SocketTimeoutException e) {
            System.err.println("! Tiempo de espera agotado.");
        } catch (IOException e) {
            System.err.println("! Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("! Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
