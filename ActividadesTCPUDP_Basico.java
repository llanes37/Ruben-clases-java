import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
================================================================================
   Actividades TCP/UDP — Ejemplo Básico
   Autor: ChatGPT (simplificado para las clases de Rubén)

   ▶ Cómo usar
   1) Guarda este archivo como: ActividadesTCPUDP_Basico.java
   2) Compila/Ejecuta en VS Code con "Run Java" o en terminal:
        javac ActividadesTCPUDP_Basico.java && java ActividadesTCPUDP_Basico
   3) Aparece un MENÚ. Elige el ejercicio que quieras ejecutar.

   💡 Objetivo pedagógico
   - Introducir conceptos básicos de redes (UDP/TCP) con ejemplos simples.
   - Facilitar la comprensión mediante comentarios detallados y ejercicios extra.
================================================================================
*/

public class ActividadesTCPUDP_Basico {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        // Punto de entrada principal: se crea una instancia y se ejecuta el menú.
        new ActividadesTCPUDP_Basico().run();
    }

    private void run() {
        // Bucle principal que muestra el menú y ejecuta la opción seleccionada.
        while (true) {
            System.out.println("\n================ MENÚ PRINCIPAL ================");
            System.out.println("1) UDP: Servidor responde con el doble del número recibido");
            System.out.println("2) TCP: Chat básico entre dos clientes");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");
            String opt = SC.nextLine().trim(); // Leer la opción del usuario.
            switch (opt) {
                case "1" -> ejercicio1_udpDoblar(); // Ejecutar ejercicio 1.
                case "2" -> ejercicio2_tcpChat(); // Ejecutar ejercicio 2.
                case "0" -> { // Salir del programa.
                    System.out.println("¡Hasta pronto!");
                    return;
                }
                default -> System.out.println("Opción no válida."); // Manejar opciones inválidas.
            }
        }
    }

    // ======================================================================
    // ! EJERCICIO 1 (UDP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa un servidor UDP que reciba un número y responda con el doble."
    // ? DEMO: Servidor y cliente se ejecutan en la misma app (localhost).
    // TODO: Reto extra
    //   1) Añade soporte para enviar más de un número por datagrama (CSV).
    //   2) Implementa un mecanismo de reintento con ACKs.
    //   3) Añade un checksum simple para validar los datos recibidos.
    // ======================================================================
    private void ejercicio1_udpDoblar() {
        System.out.println("\n[Ejercicio 1] UDP: Servidor responde con el doble del número recibido");
        final int PORT = 12345; // Puerto fijo para el servidor UDP.

        // Clase interna que representa el servidor UDP.
        class Server extends Thread {
            public void run() {
                try (DatagramSocket socket = new DatagramSocket(PORT)) {
                    System.out.println("Servidor UDP escuchando en el puerto " + PORT);
                    byte[] buffer = new byte[1024]; // Buffer para recibir datos.
                    while (true) {
                        // Recibir un datagrama del cliente.
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                        // Convertir los datos recibidos a un número entero.
                        String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                        int number = Integer.parseInt(received.trim());

                        // Calcular el doble del número recibido.
                        int doubled = number * 2;

                        // Enviar la respuesta al cliente.
                        byte[] response = String.valueOf(doubled).getBytes(StandardCharsets.UTF_8);
                        DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                        socket.send(responsePacket);
                    }
                } catch (IOException e) {
                    System.out.println("Error en el servidor UDP: " + e.getMessage());
                }
            }
        }

        // Iniciar el servidor en un hilo separado.
        new Server().start();
    }

    // ======================================================================
    // ! EJERCICIO 2 (TCP) — TRADUCCIÓN DEL ENUNCIADO
    // * "Implementa un chat básico entre dos clientes usando TCP."
    // ? DEMO: Servidor acepta dos clientes y reenvía mensajes entre ellos.
    // TODO: Reto extra
    //   1) Permite más de dos clientes simultáneamente.
    //   2) Añade soporte para comandos como /nick y /quit.
    //   3) Implementa persistencia del chat en un archivo de log.
    // ======================================================================
    private void ejercicio2_tcpChat() {
        System.out.println("\n[Ejercicio 2] TCP: Chat básico entre dos clientes");
        final int PORT = 12346; // Puerto fijo para el servidor TCP.

        // Clase interna que representa el servidor TCP.
        class Server extends Thread {
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("Servidor TCP escuchando en el puerto " + PORT);

                    // Aceptar conexiones de dos clientes.
                    Socket client1 = serverSocket.accept();
                    Socket client2 = serverSocket.accept();

                    // Crear hilos para manejar la comunicación entre los clientes.
                    new Thread(() -> handleClient(client1, client2)).start();
                    new Thread(() -> handleClient(client2, client1)).start();
                } catch (IOException e) {
                    System.out.println("Error en el servidor TCP: " + e.getMessage());
                }
            }

            // Manejar la comunicación entre dos clientes.
            private void handleClient(Socket from, Socket to) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(from.getInputStream()));
                     PrintWriter writer = new PrintWriter(to.getOutputStream(), true)) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        // Reenviar el mensaje recibido al otro cliente.
                        writer.println("Mensaje recibido: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar cliente: " + e.getMessage());
                }
            }
        }

        // Iniciar el servidor en un hilo separado.
        new Server().start();
    }
}