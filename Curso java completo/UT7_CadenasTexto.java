/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 7: Cadenas de Texto (Strings) y Métodos Comunes
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

 import java.util.Scanner;

 public class UT7_CadenasTexto {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // * 🧠 TEORÍA: ¿Qué es una cadena (String)?
         // -----------------------------------------------------
         // ? Una cadena (String) es una secuencia de caracteres.
         // ? Es un tipo de dato muy utilizado para trabajar con texto.
         // ? En Java, las cadenas son objetos, lo que significa que tienen métodos asociados.
 
         // * 🔵 EJEMPLO 1: Métodos básicos de String
         ejemploMetodosBasicos();
 
         // * 🔵 EJEMPLO 2: Comparación de Strings
         ejemploComparacion();
 
         // * 🔵 EJEMPLO 3: Extracción de subcadenas y búsquedas
         ejemploSubstringYBusqueda();
 
         // * 🔵 EJEMPLO 4: Transformación y limpieza
         ejemploTransformacion();
 
         // * 🔴 EJERCICIO FINAL:
         // ? Pide al usuario que ingrese una frase y luego:
         //      - Muestre cuántas palabras tiene.
         //      - Diga si contiene una palabra clave como "java".
         //      - Muestre la frase en mayúsculas.
         // 🔽 Debajo puedes desarrollar el código:
         // -----------------------------------------------------
         // System.out.println("👉 Ingresa una frase para analizarla: ");
         // String frase = sc.nextLine();
         // TODO: Escribe aquí el código que resuelva el ejercicio final.
 
         sc.close();
     }
 
     // 🔵 EJEMPLO 1: Métodos básicos de String
     public static void ejemploMetodosBasicos() {
         System.out.println("\n🔹 EJEMPLO 1: Métodos básicos de String");
 
         String texto = "Hola mundo";
         System.out.println("📌 Longitud del texto: " + texto.length());
         System.out.println("📌 Primer carácter: " + texto.charAt(0));
         System.out.println("📌 ¿Empieza con 'Hola'? " + texto.startsWith("Hola"));
         System.out.println("📌 ¿Termina con 'mundo'? " + texto.endsWith("mundo"));
 
         // ! ✅ TAREA PARA EL ALUMNO:
         // * Declara tu propio String y usa `.length()` y `.charAt()` para mostrar información.
     }
 
     // 🔵 EJEMPLO 2: Comparación de Strings
     public static void ejemploComparacion() {
         System.out.println("\n🔹 EJEMPLO 2: Comparación de Strings");
 
         String a = "Java";
         String b = "java";
 
         System.out.println("📌 ¿Son iguales (equals)? " + a.equals(b));
         System.out.println("📌 ¿Son iguales ignorando mayúsculas? " + a.equalsIgnoreCase(b));
 
         // ! ✅ TAREA PARA EL ALUMNO:
         // * Pide dos palabras por teclado y compara si son iguales ignorando mayúsculas.
     }
 
     // 🔵 EJEMPLO 3: Substring y búsqueda
     public static void ejemploSubstringYBusqueda() {
         System.out.println("\n🔹 EJEMPLO 3: Substring y búsqueda");
 
         String frase = "Bienvenido al curso de Java";
         System.out.println("📌 Substring (11 a 16): " + frase.substring(11, 16));
         System.out.println("📌 ¿Contiene 'curso'? " + frase.contains("curso"));
         System.out.println("📌 Posición de 'Java': " + frase.indexOf("Java"));
 
         // ! ✅ TAREA PARA EL ALUMNO:
         // * Usa una frase tuya y extrae una palabra usando `.substring()`.
     }
 
     // 🔵 EJEMPLO 4: Transformación y limpieza de texto
     public static void ejemploTransformacion() {
         System.out.println("\n🔹 EJEMPLO 4: Transformación y limpieza");
 
         String texto = "   Java Avanzado   ";
         System.out.println("📌 Texto original: '" + texto + "'");
         System.out.println("📌 En mayúsculas: " + texto.toUpperCase());
         System.out.println("📌 En minúsculas: " + texto.toLowerCase());
         System.out.println("📌 Sin espacios: '" + texto.trim() + "'");
 
         // ! ✅ TAREA PARA EL ALUMNO:
         // * Crea una cadena con espacios y transforma el texto a mayúsculas, luego elimina los espacios con `.trim()`.
     }
 }
 