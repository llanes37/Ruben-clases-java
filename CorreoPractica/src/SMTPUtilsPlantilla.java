// *******************************************************************************************
// * 🧰 SMTPUtilsPlantilla
// * -----------------------------------------------------------------------------------------
// * Clase de utilidades PARA QUE EL ALUMNO COMPLETE métodos usados por el cliente SMTP.
// * Usa comentarios estilo Better Comments para guiar paso a paso.
// *
// * 🔧 Instrucciones:
// *   1. Completa cada método marcado con TODO.
// *   2. Compila y prueba tras cada avance.
// *   3. Lee README_PracticaSMTP.md para la secuencia recomendada.
// *
// * 🧪 Objetivos:
// *   - Manejar envío de comandos.
// *   - Leer y validar códigos de respuesta.
// *   - Validar emails simples.
// *   - Preparar terreno para extensiones futuras.
// *******************************************************************************************

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class SMTPUtilsPlantilla {

    // * 📌 Regex MUY sencilla para email (el alumno puede mejorarla).
    private static final Pattern SIMPLE_EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // * 📨 ENVÍO DE COMANDO + LECTURA DE UNA LÍNEA DE RESPUESTA
    // TODO: Implementar: escribir comando + CRLF, flush y luego leer línea desde 'in'.
    public static String sendCommand(BufferedWriter out, BufferedReader in, String command) throws IOException {
        // ? Imprime en consola el comando (para seguimiento)
        System.out.println("Cliente: " + command);
        // TODO: Escribir el comando usando write y terminando con \r\n
        // TODO: Hacer flush()
        // TODO: Leer la línea de respuesta y retornarla
        return ""; // ! Temporal, sustituir
    }

    // * ✅ COMPROBAR SI LA RESPUESTA EMPIEZA POR UNO DE LOS CÓDIGOS ESPERADOS
    // TODO: Extraer prefijo numérico, parsearlo y comparar con la lista.
    public static boolean responseStartsWith(String response, int... expectedCodes) {
        // ? Manejar null o respuesta demasiado corta
        // TODO: Validar argumento
        // TODO: Extraer los 3 primeros caracteres
        // TODO: Convertir a entero (NumberFormatException -> false)
        // TODO: Recorrer expectedCodes y si alguno coincide -> true
        return false; // ! Cambiar al terminar
    }

    // * 🔍 VALIDAR EMAIL SIMPLE
    // TODO: Usar el patrón SIMPLE_EMAIL y retornar boolean.
    public static boolean isValidEmail(String email) {
        // ? Null o vacío -> false
        // TODO: Implementar comprobación con matcher
        return false; // ! Cambiar
    }

    // * 🧪 LECTURA DE UNA LÍNEA (Simplificado, sin multilinea)
    // TODO: Retornar in.readLine(); capturar IOException si se requiere en el cliente.
    public static String readLine(BufferedReader in) throws IOException {
        return ""; // ! Cambiar
    }

    // * 🧩 POSIBLE MEJORA: Leer respuestas multilinea (prefijo '250-' ... '250 ').
    // TODO (Opcional avanzado): Implementar método que acumule líneas hasta final.
    public static String readMultilineIfNeeded(BufferedReader in) throws IOException {
        // * Por ahora devolver solo la primera línea.
        return readLine(in); // TODO: Expandir lógica
    }

    // * 📤 UTILIDAD PARA ESCRIBIR LÍNEA CON CRLF
    // TODO: Implementar writeLine: out.write(line + "\r\n"); (sin flush) para usar en bloque DATA.
    public static void writeLine(BufferedWriter out, String line) throws IOException {
        // TODO: Escribir línea con CRLF
    }

    // * 🛡️ COMPROBAR CÓDIGO ÚNICO ESPERADO (Ej: 220 tras conexión)
    // TODO: Usar responseStartsWith internamente.
    public static void requireCode(String response, int expectedCode) {
        // ? Si no coincide -> imprimir aviso (no lanzar excepción en versión básica)
        // TODO: Implementar comparación y mensaje de error si no corresponde
    }

    // * 🔐 AUTH LOGIN (Opcional futuro)
    // TODO: Método para codificar Base64 usuario/contraseña y enviar secuencia AUTH LOGIN.
}
