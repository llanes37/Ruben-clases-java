/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 REPOSITORIO PRIVADO EN GITHUB (ACCESO SOLO PARA ALUMNOS AUTORIZADOS)
 *  ❌ PROHIBIDA SU DISTRIBUCIÓN SIN PERMISO DEL AUTOR
 * ******************************************************************************************
 */
/*
 * ******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: VARIABLES, TIPOS DE DATOS Y OPERADORES**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Declarar y utilizar variables en Java.**
 * ✅ **Conocer los diferentes tipos de datos en Java.**
 * ✅ **Utilizar operadores aritméticos, lógicos y de comparación.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************
 */

/*
 * 🛠️ **INSTALACIÓN Y CONFIGURACIÓN DEL ENTORNO**
 * ──────────────────────────────────────────────────────────────
 * Si aún no has configurado tu entorno, revisa la guía en **UT0_IntroduccionJava**.
 * Asegúrate de tener instalado:
 * ✅ **JDK**
 * ✅ **Un IDE o editor de código (VS Code, NetBeans, IntelliJ IDEA)**
 * ✅ **Extension Pack for Java en VS Code (Opcional, pero recomendado)**
 * ❌ **IMPORTANTE: Instalar la extensión "Better Comments" en VS Code**
 *    - Abre **Visual Studio Code**.
 *    - Ve al panel de **Extensiones** (icono de cuadrado con 4 piezas).
 *    - Busca **Better Comments** e instálalo.
 *    - Esto permitirá ver los comentarios con colores correctamente.
 */

import java.util.Scanner;  // Para leer datos ingresados por el usuario

public class UT1_VariablesTiposOperadores {

    public static void main(String[] args) {
        // * 📖 TEORÍA: ¿Qué es una variable?
        // ────────────────────────────────────────────────────────────
        // ? Una variable es un espacio en memoria donde se almacena un valor que puede cambiar durante la ejecución del programa.
        // ? En Java, cada variable debe declararse con un tipo de dato específico.

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Declara una variable `altura` de tipo `double` y asígnale un valor.
        // * Declara una variable `esEstudiante` de tipo `boolean` y asígnale `true` o `false`.

        // * ✨ EJEMPLO 1: Declaración y uso de variables
        // ────────────────────────────────────────────────────────────
        int edad = 25; // ? Variable de tipo entero
        double precio = 19.99; // ? Variable de tipo decimal
        String nombre = "Joaquín"; // ? Variable de texto
        boolean esProgramador = true; // ? Variable booleana (true/false)

        // * 📖 TEORÍA: Tipos de datos en Java
        // ────────────────────────────────────────────────────────────
        // ? Java tiene varios tipos de datos, algunos de los más usados son:
        //      * `byte` - Entero pequeño (-128 a 127)
        //      * `short` - Entero (-32,768 a 32,767)
        //      * `int` - Entero estándar
        //      * `long` - Entero largo
        //      * `float` - Decimal de precisión simple
        //      * `double` - Decimal de doble precisión
        //      * `char` - Carácter único ('A', 'B', 'C', etc.)
        //      * `boolean` - Valor lógico (true o false)

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Declara una variable de tipo `char` y asígnale una letra.
        // * Declara una variable de tipo `float` y asígnale un número con decimales.

        // * 📖 TEORÍA: Operadores en Java
        // ────────────────────────────────────────────────────────────
        // ? Java soporta varios operadores:
        // * Aritméticos: +, -, *, /, %
        // * Comparación: ==, !=, >, <, >=, <=
        // * Lógicos: &&, ||, !

        // * ✨ EJEMPLO 2: Uso de operadores aritméticos
        // ────────────────────────────────────────────────────────────
        int a = 10, b = 5;
        System.out.println("Suma: " + (a + b));
        System.out.println("Resta: " + (a - b));
        System.out.println("Multiplicación: " + (a * b));
        System.out.println("División: " + (a / b));
        System.out.println("Módulo (resto de la división): " + (a % b));

        // * ✨ EJEMPLO 3: Uso de operadores de comparación
        // ────────────────────────────────────────────────────────────
        System.out.println("¿A es mayor que B? " + (a > b));
        System.out.println("¿A es igual a B? " + (a == b));
        System.out.println("¿A es diferente de B? " + (a != b));

        // * 📖 TEORÍA: Leer datos del usuario
        // ────────────────────────────────────────────────────────────
        // ? Para leer datos en Java usamos la clase `Scanner`.

        // * ✨ EJEMPLO 4: Leer datos del usuario
        Scanner sc = new Scanner(System.in); // ? Crear un objeto Scanner
        System.out.print("👉 Ingresa tu nombre: ");
        String nombreUsuario = sc.nextLine(); // ? Leer un String ingresado por el usuario

        System.out.print("👉 Ingresa tu edad: ");
        int edadUsuario = sc.nextInt(); // ? Leer un número entero ingresado por el usuario

        // ? Mostramos la información ingresada
        System.out.println("Hola, " + nombreUsuario + "! Tienes " + edadUsuario + " años.");

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Pide al usuario que ingrese dos números y muestra el resultado de sumarlos.
        
        // ? Cerramos el Scanner
        sc.close();
    }
}
