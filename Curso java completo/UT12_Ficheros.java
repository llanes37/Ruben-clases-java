/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: MANEJO DE FICHEROS EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

 import java.io.File;                  // ? Para crear y comprobar archivos
 import java.io.FileWriter;           // ? Para escribir en archivos
 import java.io.IOException;          // ? Para manejar errores de entrada/salida
 import java.io.FileReader;           // ? Para leer archivos
 import java.io.BufferedReader;       // ? Para leer líneas completas
 import java.util.Scanner;            // ? Para leer datos del usuario
 
 public class UT12_Ficheros {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in); // 🛠️ Objeto para leer entradas del usuario
         int opcion;                          // 🎛️ Variable para controlar el menú
 
         // * MENÚ PRINCIPAL - Permite al usuario elegir qué acción realizar
         do {
             System.out.println("\n📂 MENÚ - MANEJO DE FICHEROS EN JAVA:");
             System.out.println("1. Crear un archivo de texto");
             System.out.println("2. Escribir en un archivo");
             System.out.println("3. Leer desde un archivo");
             System.out.println("4. Comprobar si un archivo existe");
             System.out.println("0. Salir");
             System.out.print("👉 Elige una opción: ");
             opcion = sc.nextInt();        // 📥 Lee la opción seleccionada
             sc.nextLine();               // 🧹 Limpia el buffer tras leer número
 
             switch (opcion) {
                 case 1 -> crearArchivo("archivo.txt");
                 case 2 -> escribirEnArchivo("archivo.txt");
                 case 3 -> leerArchivo("archivo.txt");
                 case 4 -> comprobarArchivo("archivo.txt");
                 case 0 -> System.out.println("👋 ¡Saliendo del programa!");
                 default -> System.out.println("⚠️ Opción no válida.");
             }
         } while (opcion != 0); // 🔁 Repite mientras no se elija salir
 
         sc.close(); // 🔐 Cerramos el Scanner al terminar
     }
 
     // * 📖 TEORÍA: Crear archivo
     // ──────────────────────────────────────────────
     // ? File permite representar un archivo físico en disco.
     // ? La función createNewFile() crea el archivo solo si no existe.
     public static void crearArchivo(String nombre) {
         try {
             File archivo = new File(nombre);        // 📦 Creamos un objeto File
             if (archivo.createNewFile()) {          // ✅ Si no existe, se crea
                 System.out.println("✅ Archivo creado: " + archivo.getName());
             } else {
                 System.out.println("ℹ️ El archivo ya existe.");
             }
         } catch (IOException e) {
             System.out.println("❌ Error al crear el archivo.");
             e.printStackTrace();
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Cambia el nombre del archivo a "notas.txt" y vuelve a probar
     }
 
     // * 📖 TEORÍA: Escribir en archivo
     // ──────────────────────────────────────────────
     // ? FileWriter permite escribir texto en el archivo (sobrescribe).
     // ? También se puede abrir en modo append (añadir al final).
     public static void escribirEnArchivo(String nombre) {
         try {
             FileWriter escritor = new FileWriter(nombre); // ✍️ Abrimos archivo (modo sobrescritura)
             escritor.write("Línea 1: Este es un ejemplo.\n"); // 📝 Escribimos una línea
             escritor.write("Línea 2: Prueba de escritura en archivo."); // 📝 Otra línea
             escritor.close(); // 🔐 Cerramos el archivo tras escribir
             System.out.println("📝 Escritura completada.");
         } catch (IOException e) {
             System.out.println("❌ Error al escribir en el archivo.");
             e.printStackTrace();
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Pide al usuario una frase por teclado y escríbela en el archivo
     }
 
     // * 📖 TEORÍA: Leer archivo
     // ──────────────────────────────────────────────
     // ? FileReader abre un archivo para lectura.
     // ? BufferedReader permite leer línea por línea.
     public static void leerArchivo(String nombre) {
         try {
             FileReader lector = new FileReader(nombre);             // 📖 Abrimos el archivo
             BufferedReader buffer = new BufferedReader(lector);     // 📥 Leemos líneas completas
             String linea;
             System.out.println("📖 Contenido del archivo:");
             while ((linea = buffer.readLine()) != null) {           // 🔁 Mientras haya líneas
                 System.out.println("👉 " + linea);                   // 📤 Mostramos la línea
             }
             buffer.close(); // 🔐 Cerramos el buffer
         } catch (IOException e) {
             System.out.println("❌ Error al leer el archivo.");
             e.printStackTrace();
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Prueba con otro archivo llamado "alumnos.txt"
     }
 
     // * 📖 TEORÍA: Comprobar archivo
     // ──────────────────────────────────────────────
     // ? Con File podemos verificar si un archivo existe y obtener información sobre él.
     public static void comprobarArchivo(String nombre) {
         File archivo = new File(nombre); // 📦 Creamos un objeto File
         if (archivo.exists()) {          // ✅ Si existe, mostramos detalles
             System.out.println("📦 El archivo '" + nombre + "' existe.");
             System.out.println("📏 Tamaño: " + archivo.length() + " bytes");
             System.out.println("📍 Ruta absoluta: " + archivo.getAbsolutePath());
         } else {
             System.out.println("❌ El archivo no existe.");
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Usa este método para verificar un archivo llamado "prueba.txt"
     }
 }
 