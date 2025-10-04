/******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: EXCEPCIONES Y MANEJO DE ERRORES EN JAVA**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender el concepto de excepciones en Java y su importancia.**
 * ✅ **Distinguir entre excepciones verificadas y no verificadas.**
 * ✅ **Utilizar bloques try-catch para manejar errores en tiempo de ejecución.**
 * ✅ **Implementar excepciones personalizadas para mejorar la robustez del código.**
 * ✅ **Aplicar el uso de finally para liberar recursos.**
 * ✅ **Lanzar y capturar múltiples excepciones.**
 * ✅ **Practicar con ejercicios interactivos para reforzar el conocimiento.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************/

 import java.util.InputMismatchException;
 import java.util.Scanner;
 import java.io.File;
 import java.io.FileNotFoundException;
 
 /*
  * 🧠 **TEORÍA GLOBAL: EXCEPCIONES EN JAVA**
  * ──────────────────────────────────────────────────────────────
  * 
  * 🔵 **¿Qué es una Excepción?**
  *  - Es un evento inesperado que ocurre durante la ejecución de un programa y que interrumpe su flujo normal.
  *  - Java proporciona un mecanismo de manejo de excepciones para evitar que el programa se detenga abruptamente.
  * 
  * 🔹 **Tipos de Excepciones:**
  * 
  * 1️⃣ **Excepciones Verificadas (Checked Exceptions):**
  *      - Se detectan en tiempo de compilación.
  *      - Ejemplo: `FileNotFoundException` al intentar leer un archivo que no existe.
  * 
  * 2️⃣ **Excepciones No Verificadas (Unchecked Exceptions):**
  *      - Ocurren en tiempo de ejecución.
  *      - Ejemplo: `ArithmeticException` al dividir por cero.
  */
 
 public class UT6_ExcepcionesManejoErrores {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // 🔵 **EJEMPLOS DE MANEJO DE EXCEPCIONES**
         System.out.println("🔹 Ejemplo 1: Captura de excepciones con try-catch...");
         manejarExcepcionAritmetica();
 
         System.out.println("\n🔹 Ejemplo 2: Capturar múltiples excepciones...");
         manejarMultiplesExcepciones();
 
         System.out.println("\n🔹 Ejemplo 3: Uso de finally para liberar recursos...");
         usoDeFinally();
 
         System.out.println("\n🔹 Ejemplo 4: Captura de excepción en lectura de archivo...");
         leerArchivo("archivo_inexistente.txt");
 
         System.out.println("\n🔹 Ejemplo 5: Excepciones personalizadas...");
         try {
             validarEdad(-5);
         } catch (EdadInvalidaException e) {
             System.out.println("⚠️ Error: " + e.getMessage());
         }
 
         sc.close();
     }
 
     // * 📖 TEORÍA: Manejo de excepciones aritméticas
     // ────────────────────────────────────────────────────────────
     // ? En Java, la división entre cero lanza una `ArithmeticException`.
     // ? Podemos capturar esta excepción para evitar que el programa se detenga.
     public static void manejarExcepcionAritmetica() {
         try {
             int resultado = 10 / 0; // ⚠️ División por cero
             System.out.println("El resultado es: " + resultado);
         } catch (ArithmeticException e) {
             System.out.println("⚠️ Error: No se puede dividir por cero.");
         }
     }
 
     // * 📖 TEORÍA: Manejo de múltiples excepciones
     // ────────────────────────────────────────────────────────────
     // ? Un `try` puede capturar varias excepciones distintas.
     public static void manejarMultiplesExcepciones() {
         Scanner sc = new Scanner(System.in);
         try {
             System.out.print("Introduce un número entero: ");
             int num = sc.nextInt(); // ⚠️ Puede lanzar InputMismatchException
             System.out.println("Número ingresado: " + num);
         } catch (InputMismatchException e) {
             System.out.println("⚠️ Error: Debes ingresar un número entero.");
         } catch (Exception e) {
             System.out.println("⚠️ Error general: " + e.getMessage());
         }
     }
 
     // * 📖 TEORÍA: Uso de finally
     // ────────────────────────────────────────────────────────────
     // ? El bloque `finally` se ejecuta siempre, haya o no una excepción.
     public static void usoDeFinally() {
         Scanner sc = null;
         try {
             sc = new Scanner(System.in);
             System.out.print("Introduce un número: ");
             int num = sc.nextInt();
             System.out.println("Número ingresado: " + num);
         } catch (InputMismatchException e) {
             System.out.println("⚠️ Error: Entrada inválida.");
         } finally {
             if (sc != null) {
                 sc.close();
                 System.out.println("✅ Scanner cerrado correctamente.");
             }
         }
     }
 
     // * 📖 TEORÍA: Captura de excepciones en lectura de archivos
     // ────────────────────────────────────────────────────────────
     // ? Si intentamos abrir un archivo que no existe, se lanza `FileNotFoundException`.
     public static void leerArchivo(String nombreArchivo) {
         try {
             File archivo = new File(nombreArchivo);
             Scanner lector = new Scanner(archivo);
             while (lector.hasNextLine()) {
                 System.out.println(lector.nextLine());
             }
             lector.close();
         } catch (FileNotFoundException e) {
             System.out.println("⚠️ Error: Archivo no encontrado -> " + nombreArchivo);
         }
     }
 
     // * 📖 TEORÍA: Excepciones personalizadas
     // ────────────────────────────────────────────────────────────
     // ? Podemos definir nuestras propias excepciones extendiendo la clase `Exception`.
     public static void validarEdad(int edad) throws EdadInvalidaException {
         if (edad < 0) {
             throw new EdadInvalidaException("La edad no puede ser negativa.");
         }
         System.out.println("Edad válida: " + edad);
     }
 }
 
 // * 📖 DEFINICIÓN DE UNA EXCEPCIÓN PERSONALIZADA
 class EdadInvalidaException extends Exception {
     public EdadInvalidaException(String mensaje) {
         super(mensaje);
     }
 }
 
 /*
  * 🚀 **TAREAS PARA EL ALUMNO**
  * ──────────────────────────────────────────────────────────────
  * ✅ 1️⃣ Modifica el método `manejarMultiplesExcepciones` para seguir pidiendo el número hasta que sea válido.
  * ✅ 2️⃣ Crea una nueva excepción personalizada `SaldoInsuficienteException` y úsala en un método `retirarDinero()`.
  * ✅ 3️⃣ Implementa una función `convertirTextoAEntero()` que capture `NumberFormatException` y siga pidiendo entrada.
  */
 