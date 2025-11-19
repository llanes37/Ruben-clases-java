// *******************************************************************************************
// * 📚 CLIENTE SMTP BÁSICO EN JAVA
// * -----------------------------------------------------------------------------------------
// * Este ejemplo muestra cómo comunicarse directamente con un servidor SMTP usando sockets.
// * Se construyen manualmente los comandos del protocolo: HELO, MAIL FROM, RCPT TO, DATA, QUIT.
// *
// * 🎯 Objetivos didácticos:
// *   - Entender el flujo mínimo para enviar un correo sin librerías externas.
// *   - Practicar I/O con sockets y manejo de respuestas del servidor.
// *   - Diferenciar cabeceras y cuerpo del mensaje (separación por línea en blanco).
// *
// * ⚠ Limitaciones de este ejemplo:
// *   - No usa EHLO (extensiones), autenticación (AUTH), ni cifrado (STARTTLS).
// *   - No valida códigos de respuesta: solo los imprime. (Ver versión mejorada en ClienteDidactico).
// *   - Requiere que el servidor permita envío sin autenticación desde tu IP (muy raro hoy).
// *
// * 🛠 Mejoras sugeridas:
// *   - Implementar EHLO y parsear capacidades.
// *   - Agregar comprobación de códigos (220, 250, 354, 221...).
// *   - Añadir STARTTLS y luego AUTH LOGIN / PLAIN.
// *   - Manejar respuestas multilinea (250-...).
// *
// * 🧩 Referencia rápida del protocolo SMTP (básico):
// *   220 -> Servicio listo.
// *   HELO/EHLO -> Saludo inicial.
// *   250 -> OK (aceptación de comando / destinatario / etc.).
// *   MAIL FROM -> Indica el remitente.
// *   RCPT TO -> Indica destinatario.
// *   DATA -> Inicio del cuerpo, servidor responde 354.
// *   Línea sola con '.' -> Finaliza el cuerpo.
// *   QUIT -> Cierra sesión (221).
// *******************************************************************************************

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) {
        // * 🔧 Configuración básica del servidor SMTP
        String servidor = "192.168.128.2"; // ? IP del servidor SMTP (ajústalo a tu entorno)
        int puerto = 25;                    // ! Puerto 25 = SMTP sin cifrado (hoy muchos bloquean este puerto)

        // TODO: Añadir socket.setSoTimeout(...) si se quisiera definir tiempo de espera.

        try (
                // * 🔌 Apertura del socket (TCP) hacia el servidor SMTP
                Socket socket = new Socket(servidor, puerto);
                // * 📥 Canal de lectura (respuesta del servidor)
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // * 📤 Canal de escritura (envío de comandos)
                BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                // * 🖱️ Scanner para leer datos que el usuario introducirá por consola
                Scanner sc = new Scanner(System.in)
        ) {
            // * 1️⃣ Banner inicial del servidor: normalmente código 220
            String banner = entrada.readLine();
            System.out.println("Servidor: " + banner); // ? Podríamos verificar que empiece por 220

            // * 2️⃣ HELO: saludo básico (en producción debería usarse EHLO para extensiones)
            System.out.println("Cliente: HELO ruben.foo");
            salida.write("HELO ruben.foo\r\n");
            salida.flush(); // ! flush = fuerza el envío inmediato
            System.out.println("Servidor: " + entrada.readLine());

            // * 3️⃣ (Redundante) Segundo HELO. No es necesario repetirlo; se mantiene para mostrar envío de comandos.
            System.out.println("Cliente: HELO ruben.foo");
            salida.write("HELO ruben.foo\r\n");
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine());

            // * 📝 Solicitar datos dinámicamente: remitente, destinatario y mensaje
            System.out.print("Correo del remitente: ");
            String remitente = sc.nextLine(); // ? Formato ideal: usuario@dominio

            System.out.print("Correo del destinatario: ");
            String destinatario = sc.nextLine();

            System.out.print("Mensaje a enviar: ");
            String mensaje = sc.nextLine();

            // * 4️⃣ MAIL FROM: indicamos el remitente al servidor
            System.out.println("Cliente: MAIL FROM:<" + remitente + ">");
            salida.write("MAIL FROM:<" + remitente + ">\r\n");
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine()); // TODO: Verificar que sea 250

            // * 5️⃣ RCPT TO: destinatario
            System.out.println("Cliente: RCPT TO:<" + destinatario + ">");
            salida.write("RCPT TO:<" + destinatario + ">\r\n");
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine()); // TODO: Verificar 250 (o 251)

            // * 6️⃣ DATA: inicia el modo de escritura del cuerpo
            System.out.println("Cliente: DATA");
            salida.write("DATA\r\n");
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine()); // ? Debe responder 354

            // * 7️⃣ Enviamos cabeceras mínimas + cuerpo
            // * Cabeceras (From/To/Subject) seguidas de una línea en blanco y luego el cuerpo del mensaje.
            salida.write("From: " + remitente + "\r\n");
            salida.write("To: " + destinatario + "\r\n");
            salida.write("Subject: Prueba desde cliente SMTP Java\r\n");
            salida.write("\r\n"); // ! Línea vacía para separar cabeceras del cuerpo
            salida.write(mensaje + "\r\n"); // * Cuerpo principal
            salida.write(".\r\n"); // ! Punto solo en una línea -> fin del cuerpo según SMTP
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine()); // TODO: Verificar 250 (aceptación del mensaje)

            // * 8️⃣ QUIT: cerramos la sesión de manera ordenada
            System.out.println("Cliente: QUIT");
            salida.write("QUIT\r\n");
            salida.flush();
            System.out.println("Servidor: " + entrada.readLine()); // ? Esperado 221

            // ✅ Mensaje final de confirmación local (no del servidor)
            System.out.println("Mensaje enviado correctamente.");

        } catch (UnknownHostException e) {
            // ! No se puede resolver el nombre/IP del servidor
            System.err.println("Error: host desconocido (" + servidor + ")");
        } catch (ConnectException e) {
            // ! Fallo al establecer la conexión (servidor caído / puerto filtrado)
            System.err.println("Error: no se pudo conectar al servidor en el puerto " + puerto);
        } catch (SocketTimeoutException e) {
            // ! El servidor tardó demasiado en responder
            System.err.println("Error: conexión expirada con el servidor SMTP.");
        } catch (IOException e) {
            // ! Cualquier otra excepción de I/O
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            // ! Excepción genérica (último recurso)
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
