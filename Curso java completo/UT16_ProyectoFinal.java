/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 16: PROYECTO FINAL - SISTEMA DE GESTIÓN DE ALUMNOS
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/*
 * ******************************************************************************************
 * 🧠 OBJETIVO DEL PROYECTO:
 * ──────────────────────────────────────────────────────────────
 * ✅ Crear una aplicación básica de consola que permita gestionar alumnos.
 * ✅ Aplicar: clases, arrays, bucles, condicionales, herencia, polimorfismo, excepciones, modularidad.
 * ✅ La aplicación debe permitir: agregar, listar, buscar y eliminar alumnos.
 * ✅ BONUS: aplicar interfaces, manejar errores y simular una base de datos en memoria.
 ******************************************************************************************
 */

 import java.util.ArrayList;
 import java.util.InputMismatchException;
 import java.util.Scanner;
 
 // * Clase Principal (Main)
 public class UT16_ProyectoFinal {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         GestorAlumnos gestor = new GestorAlumnos();
 
         int opcion;
 
         // 🔁 Bucle de menú principal
         do {
             System.out.println("\n🎓 MENÚ PRINCIPAL - GESTIÓN DE ALUMNOS");
             System.out.println("1. Agregar Alumno");
             System.out.println("2. Listar Alumnos");
             System.out.println("3. Buscar Alumno");
             System.out.println("4. Eliminar Alumno");
             System.out.println("5. Salir");
             System.out.print("👉 Opción: ");
 
             try {
                 opcion = sc.nextInt();
                 sc.nextLine(); // Limpia buffer
 
                 switch (opcion) {
                     case 1:
                         gestor.agregarAlumno(sc);
                         break;
                     case 2:
                         gestor.listarAlumnos();
                         break;
                     case 3:
                         gestor.buscarAlumno(sc);
                         break;
                     case 4:
                         gestor.eliminarAlumno(sc);
                         break;
                     case 5:
                         System.out.println("👋 ¡Gracias por usar el sistema!");
                         break;
                     default:
                         System.out.println("⚠️ Opción no válida.");
                 }
             } catch (InputMismatchException e) {
                 System.out.println("❌ Error: Debes ingresar un número.");
                 sc.nextLine(); // Limpiar buffer
                 opcion = 0; // Reiniciamos
             }
         } while (opcion != 5);
 
         sc.close();
     }
 }
 
 /*
  * ******************************************************************************************
  * 📘 Clase Alumno (POJO con atributos, constructor, getters, toString)
  * ******************************************************************************************
  */
 class Alumno {
     private String nombre;
     private int edad;
     private double nota;
 
     public Alumno(String nombre, int edad, double nota) {
         this.nombre = nombre;
         this.edad = edad;
         this.nota = nota;
     }
 
     public String getNombre() {
         return nombre;
     }
 
     public int getEdad() {
         return edad;
     }
 
     public double getNota() {
         return nota;
     }
 
     @Override
     public String toString() {
         return "👨‍🎓 Nombre: " + nombre + ", Edad: " + edad + ", Nota: " + nota;
     }
 }
 
 /*
  * ******************************************************************************************
  * 📦 Clase GestorAlumnos (gestiona la lógica del sistema)
  * ******************************************************************************************
  */
 class GestorAlumnos {
     private ArrayList<Alumno> lista = new ArrayList<>();
 
     // ✅ Método para agregar alumno
     public void agregarAlumno(Scanner sc) {
         System.out.print("📝 Nombre del alumno: ");
         String nombre = sc.nextLine();
 
         System.out.print("🎂 Edad: ");
         int edad = sc.nextInt();
 
         System.out.print("📊 Nota final: ");
         double nota = sc.nextDouble();
         sc.nextLine();
 
         Alumno nuevo = new Alumno(nombre, edad, nota);
         lista.add(nuevo);
 
         System.out.println("✅ Alumno agregado correctamente.");
     }
 
     // ✅ Listar todos los alumnos
     public void listarAlumnos() {
         if (lista.isEmpty()) {
             System.out.println("📭 No hay alumnos registrados.");
         } else {
             System.out.println("📚 Lista de alumnos:");
             for (Alumno a : lista) {
                 System.out.println(a);
             }
         }
     }
 
     // ✅ Buscar alumno por nombre
     public void buscarAlumno(Scanner sc) {
         System.out.print("🔍 Nombre a buscar: ");
         String nombre = sc.nextLine();
 
         boolean encontrado = false;
         for (Alumno a : lista) {
             if (a.getNombre().equalsIgnoreCase(nombre)) {
                 System.out.println("✅ Alumno encontrado: " + a);
                 encontrado = true;
                 break;
             }
         }
 
         if (!encontrado) {
             System.out.println("❌ Alumno no encontrado.");
         }
     }
 
     // ✅ Eliminar alumno por nombre
     public void eliminarAlumno(Scanner sc) {
         System.out.print("🗑️ Nombre del alumno a eliminar: ");
         String nombre = sc.nextLine();
 
         Alumno alumnoEliminar = null;
 
         for (Alumno a : lista) {
             if (a.getNombre().equalsIgnoreCase(nombre)) {
                 alumnoEliminar = a;
                 break;
             }
         }
 
         if (alumnoEliminar != null) {
             lista.remove(alumnoEliminar);
             System.out.println("🗑️ Alumno eliminado correctamente.");
         } else {
             System.out.println("❌ Alumno no encontrado.");
         }
     }
 }
 
 /*
  * ******************************************************************************************
  * ✅ TAREAS PARA EL ALUMNO:
  * ──────────────────────────────────────────────────────────────
  * 1️⃣ Añadir validaciones para evitar edades o notas negativas.
  * 2️⃣ Crear una subclase `AlumnoBecado` que herede de `Alumno` y tenga atributo `tipoBeca`.
  * 3️⃣ Usar polimorfismo para mostrar si el alumno tiene beca o no.
  * 4️⃣ Exportar la lista a un archivo de texto.
  * 5️⃣ Separar las clases en paquetes: `modelo`, `servicio`, `main`.
  ******************************************************************************************
  */
 