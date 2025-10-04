/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 14: ENUMERACIONES Y CONSTANTES EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

 import java.util.Scanner;

 public class UT14_EnumYConstantes {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         System.out.println("🎯 Bienvenido a la unidad sobre ENUM y CONSTANTES");
 
         // * EJEMPLO 1: Uso de Enum en condiciones
         ejemploEnum();
 
         // * EJEMPLO 2: Uso de constantes con final
         ejemploConstantes();
 
         // ! ✅ TAREA ALUMNO:
         // * 1️⃣ Crea un enum llamado `DiaSemana` con los días de la semana y muestra un mensaje distinto por cada uno.
         // * 2️⃣ Crea una clase `Matematicas` con constantes como PI y E, y un método para calcular el área de un círculo.
 
         sc.close();
     }
 
     // * 📖 TEORÍA: ¿Qué es un ENUM?
     // ──────────────────────────────────────────────────────────────
     // ? Un Enum es un tipo especial de clase que representa un conjunto de constantes (valores fijos).
     // ? Se usa cuando una variable solo puede tomar uno de un conjunto predefinido de valores.
     // ? Ejemplos comunes: días de la semana, tipos de moneda, estados de un pedido, etc.
 
     // * ✨ EJEMPLO 1: Enum con estructura de control
     public static void ejemploEnum() {
         System.out.println("\n🔷 EJEMPLO ENUM: Días de la semana");
 
         Dia diaActual = Dia.MIERCOLES; // ✅ Asignamos un valor del enum
 
         switch (diaActual) {
             case LUNES -> System.out.println("🗓️ Hoy es Lunes, inicio de semana.");
             case MARTES -> System.out.println("💼 Martes de productividad.");
             case MIERCOLES -> System.out.println("🧠 Mitad de semana.");
             case JUEVES -> System.out.println("🚀 Casi viernes.");
             case VIERNES -> System.out.println("🎉 Viernes al fin.");
             case SABADO, DOMINGO -> System.out.println("😎 ¡Es fin de semana!");
         }
 
         // * También puedes recorrer todos los valores del enum:
         System.out.println("\n📋 Todos los días:");
         for (Dia d : Dia.values()) {
             System.out.println("🔹 " + d);
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Pide al usuario que ingrese un día (como texto) y muestra el mensaje correspondiente usando Enum.valueOf()
     }
 
     // * 📖 ENUM PERSONALIZADO: Días de la semana
     enum Dia {
         LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
     }
 
     // * 📖 TEORÍA: ¿Qué es una constante?
     // ──────────────────────────────────────────────────────────────
     // ? Una constante es una variable cuyo valor NO puede cambiar.
     // ? En Java se define con `final` y se escribe en mayúsculas por convención.
     // ? Son útiles para valores fijos como PI, IVA, límites, etc.
 
     // * ✨ EJEMPLO 2: Uso de constantes
     public static void ejemploConstantes() {
         System.out.println("\n🔷 EJEMPLO CONSTANTES");
 
         final double PI = 3.1416; // 🔒 Constante que no se puede modificar
         final double GRAVEDAD = 9.81;
 
         double radio = 5.0;
         double area = PI * radio * radio; // 🧮 Fórmula del área del círculo
 
         System.out.println("📐 Área de un círculo con radio 5: " + area);
         System.out.println("🌍 Valor de la gravedad: " + GRAVEDAD);
 
         // ! ✅ TAREA ALUMNO:
         // * Crea tus propias constantes: IVA (0.21), MAX_USUARIOS (100), etc.
         // * Usa estas constantes en un pequeño cálculo o validación.
     }
 }
 