# Actividades TCP/UDP — Ejemplo Básico

Este documento explica los conceptos fundamentales de las actividades implementadas en el archivo `ActividadesTCPUDP_Basico.java`. El objetivo es proporcionar una guía clara y sencilla para entender los ejercicios y los conceptos de redes involucrados.

---

## ¿Qué es UDP?

UDP (User Datagram Protocol) es un protocolo de comunicación que permite enviar y recibir datos entre dispositivos. Es rápido y eficiente, pero no garantiza:

- **Entrega de datos**: Los paquetes pueden perderse.
- **Orden de los datos**: Los paquetes pueden llegar desordenados.

### Ventajas de UDP:
- Baja latencia.
- Ideal para aplicaciones donde la velocidad es más importante que la fiabilidad (e.g., videojuegos, streaming).

### Ejemplo en el archivo:
En el ejercicio 1, se implementa un servidor UDP que:
1. Recibe un número del cliente.
2. Responde con el doble de ese número.

#### Código del servidor UDP:
```java
class Server extends Thread {
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                int number = Integer.parseInt(received.trim());
                int doubled = number * 2;
                byte[] response = String.valueOf(doubled).getBytes();
                DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor UDP: " + e.getMessage());
        }
    }
}
```

#### TODOs (Retos extra):
- Permitir enviar varios números en un solo mensaje (formato CSV).
- Implementar reintentos con ACKs para garantizar la entrega.
- Añadir un checksum para validar la integridad de los datos.

---

## ¿Qué es TCP?

TCP (Transmission Control Protocol) es un protocolo de comunicación que garantiza:

- **Entrega confiable**: Los datos llegan completos y en el orden correcto.
- **Control de flujo**: Evita que un dispositivo envíe más datos de los que el receptor puede manejar.

### Ventajas de TCP:
- Fiabilidad.
- Ideal para aplicaciones donde la precisión es crucial (e.g., chats, transferencias de archivos).

### Ejemplo en el archivo:
En el ejercicio 2, se implementa un chat básico entre dos clientes:
1. El servidor acepta conexiones de dos clientes.
2. Los mensajes enviados por un cliente se reenvían al otro.

#### Código del servidor TCP:
```java
class Server extends Thread {
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(12346)) {
            Socket client1 = serverSocket.accept();
            Socket client2 = serverSocket.accept();

            new Thread(() -> handleClient(client1, client2)).start();
            new Thread(() -> handleClient(client2, client1)).start();
        } catch (IOException e) {
            System.out.println("Error en el servidor TCP: " + e.getMessage());
        }
    }

    private void handleClient(Socket from, Socket to) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(from.getInputStream()));
             PrintWriter writer = new PrintWriter(to.getOutputStream(), true)) {
            String message;
            while ((message = reader.readLine()) != null) {
                writer.println("Mensaje recibido: " + message);
            }
        } catch (IOException e) {
            System.out.println("Error al manejar cliente: " + e.getMessage());
        }
    }
}
```

#### TODOs (Retos extra):
- Permitir más de dos clientes simultáneamente.
- Añadir soporte para comandos como `/nick` (cambiar nombre) y `/quit` (salir).
- Guardar un registro (log) de las conversaciones en un archivo.

---

## Cómo ejecutar el programa

1. **Compilar el archivo:**
   ```bash
   javac ActividadesTCPUDP_Basico.java
   ```

2. **Ejecutar el programa:**
   ```bash
   java ActividadesTCPUDP_Basico
   ```

3. **Seleccionar una opción del menú:**
   - `1`: Ejecuta el servidor UDP.
   - `2`: Ejecuta el chat TCP.
   - `0`: Salir del programa.

---

## Conceptos adicionales

### ¿Qué es un puerto?
Un puerto es un número que identifica un proceso específico en un dispositivo. Por ejemplo:
- El servidor UDP usa el puerto `12345`.
- El servidor TCP usa el puerto `12346`.

### ¿Qué es localhost?
`localhost` es un alias que se refiere a la máquina local (IP: `127.0.0.1`). Se utiliza para pruebas locales.

### ¿Qué es un socket?
Un socket es un punto final para la comunicación entre dos dispositivos. Puede ser:
- **Socket UDP**: No requiere conexión previa.
- **Socket TCP**: Requiere establecer una conexión antes de enviar datos.

---

## Notas finales

Este programa es una introducción básica a los conceptos de redes. Los ejercicios están diseñados para ser simples pero extensibles, permitiendo a los estudiantes experimentar y aprender de manera práctica.

¡Diviértete programando y aprendiendo sobre redes!