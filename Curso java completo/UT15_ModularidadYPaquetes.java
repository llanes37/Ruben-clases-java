/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 15: MODULARIDAD Y PAQUETES EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/*
 * ******************************************************************************************
 *                        📘 TEORÍA Y CONCEPTOS: MODULARIDAD Y PAQUETES
 * ──────────────────────────────────────────────────────────────────────────────
 * ✅ La modularidad permite dividir el programa en archivos y clases pequeñas, organizadas.
 * ✅ Los paquetes (`package`) agrupan clases relacionadas, mejorando la mantenibilidad del código.
 * ✅ Se usa `import` para acceder a clases de otros paquetes.
 * ✅ En Java, un archivo puede pertenecer a un paquete (especificado en la primera línea).
 * ✅ Una clase puede acceder a métodos de otras clases si están públicas (`public`) y bien importadas.
 ******************************************************************************************
 */

 import java.util.Scanner; // ✅ Importamos desde el paquete java.util

 // En un proyecto modular, esta clase estaría en el paquete `principal`
 public class UT15_ModularidadYPaquetes {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // 🔹 Usamos funciones externas de otra clase como si fueran parte del proyecto modular
         System.out.println("📦 MODULARIDAD EN ACCIÓN:");
         Utilidades.saludar("Joaquín");
 
         int resultado = Utilidades.sumar(5, 8);
         System.out.println("🔢 Resultado de la suma: " + resultado);
 
         // ✨ Otra función
         double area = Utilidades.calcularAreaCirculo(4.5);
         System.out.println("📐 Área de círculo (r=4.5): " + area);
 
         // ! ✅ TAREA ALUMNO:
         // * 1️⃣ Crea una clase nueva llamada `Calculadora` (en otro archivo del mismo paquete).
         // * 2️⃣ Incluye métodos: restar, multiplicar y dividir.
         // * 3️⃣ Llama a esos métodos desde aquí, como se hace con `Utilidades`.
 
         sc.close();
     }
 }
 
 /*
  * 🔹 Esta clase representa una clase de utilidad que normalmente estaría en otro archivo.
  * 🔸 En un proyecto real, estaría ubicada en un paquete como `util` o `herramientas`.
  */
 class Utilidades {
 
     // * ✨ Función con parámetro y sin retorno
     public static void saludar(String nombre) {
         System.out.println("👋 ¡Hola, " + nombre + "! Bienvenido al curso modularizado.");
     }
 
     // * ✨ Función con parámetros y retorno
     public static int sumar(int a, int b) {
         return a + b;
     }
 
     // * ✨ Función que usa constante
     public static double calcularAreaCirculo(double radio) {
         final double PI = 3.1416; // 🔒 Constante
         return PI * radio * radio;
     }
 }
 
 /*
  * ******************************************************************************************
  * ✅ RECOMENDACIÓN PARA ENTORNO REAL:
  * - Crea paquetes como: `main`, `util`, `model`, `services`, `controllers`, etc.
  * - Dentro de cada paquete, coloca las clases según su funcionalidad.
  * - Desde una clase `Main`, importa las demás usando `import paquete.clase;`
  * 
  * 🔧 EJEMPLO EN VISUAL STUDIO CODE (o cualquier IDE):
  * ───────────────────────────────────────────────
  * 1. Crea carpetas con nombres de paquetes.
  * 2. En la parte superior de los archivos, añade: `package nombrePaquete;`
  * 3. Usa `import` para traer otras clases.
  * 4. Compila con: `javac paquete/*.java`
  * 5. Ejecuta con: `java paquete.ClasePrincipal`
  ******************************************************************************************
  */
 