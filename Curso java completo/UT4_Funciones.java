/*
 * ******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: FUNCIONES EN JAVA**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender qué es una función y su importancia en la programación.**
 * ✅ **Aprender a declarar y llamar funciones en Java.**
 * ✅ **Distinguir entre funciones con y sin retorno de valores.**
 * ✅ **Utilizar funciones con parámetros.**
 * ✅ **Aplicar funciones para estructurar y modularizar el código.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************
 */

/*
 * 🧠 **TEORÍA GLOBAL: FUNCIONES EN JAVA**
 * ──────────────────────────────────────────────────────────────
 * 
 * 🔵 **¿Qué es una Función?**
 *  - Una función (o método) es un bloque de código que realiza una tarea específica.
 *  - Permite **reutilizar código** y **organizar mejor** un programa.
 *  - En Java, todas las funciones deben pertenecer a una clase.
 * 
 * 🔹 **Sintaxis básica de una función en Java**
 * 
 * ```java
 * public static tipo_retorno nombreFuncion(parámetros) {
 *     // Cuerpo de la función
 *     return valor; // Si tiene retorno
 * }
 * ```
 * 
 * 🔹 **Tipos de Funciones**
 * 
 * 1️⃣ **Funciones sin retorno y sin parámetros:** Solo ejecutan instrucciones.
 * 2️⃣ **Funciones con parámetros:** Reciben datos para operar con ellos.
 * 3️⃣ **Funciones con retorno:** Devuelven un resultado al llamarlas.
 */

 import java.util.Scanner;

 public class UT4_Funciones {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 🛠️ Creamos un objeto Scanner para recibir datos del usuario.

        // 🔵 **EJECUCIÓN DE FUNCIONES**
        // ───────────────────────────────────────────────────────────────
        // ✅ Si una función está comentada en el `main()`, NO se ejecutará su código.
        // ✅ Puedes probar descomentando cada función para ver su salida en la consola.

        System.out.println("🔹 Llamando a la función sin parámetros...");
        imprimirMensaje(); // 🟢 Llama a la función `imprimirMensaje()`, que imprime un mensaje en pantalla.

        System.out.println("\n🔹 Llamando a la función con parámetros...");
        saludar("Joaquín"); // 🟢 Llama a `saludar(String nombre)`, pasándole el argumento "Joaquín".

        System.out.println("\n🔹 Llamando a la función con retorno...");
        int resultado = sumar(10, 5); // 🟢 Llama a `sumar(int a, int b)`, guarda el resultado en `resultado`.
        System.out.println("La suma es: " + resultado); // 📌 Imprime el valor retornado.

        System.out.println("\n🔹 Llamando a la función de sobrecarga...");
        double resultado2 = sumar(10.5, 4.3); // 🟢 Llama a la versión sobrecargada de `sumar()` con valores decimales.
        System.out.println("La suma de decimales es: " + resultado2); // 📌 Imprime el resultado de la suma decimal.

        System.out.println("\n🔹 Llamando a la función recursiva...");
        int factorialDe5 = factorial(5); // 🟢 Llama a la función `factorial(int n)`, pasando el valor `5`.
        System.out.println("El factorial de 5 es: " + factorialDe5); // 📌 Imprime el resultado del factorial.

        // 🔴 **PRUEBA DE COMENTAR FUNCIONES**
        // ───────────────────────────────────────────────────────────────
        // Si comentas una llamada, esa función no se ejecutará.
        // Ejemplo:
        // // imprimirMensaje();  ← Si esta línea está comentada, no se verá el mensaje de la función `imprimirMensaje()`.
        // // int resultado = sumar(10, 5);  ← Si esta línea está comentada, `sumar()` no se ejecutará y no habrá resultado.

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Modifica el código para agregar una función que calcule el área de un círculo dado su radio.
        // * Luego, llama a esa función desde el `main()` y muestra el resultado.

        sc.close(); // 🚪 Cerramos el Scanner para liberar recursos.
    }

 
     // * 📖 TEORÍA: Función sin parámetros y sin retorno
     // ────────────────────────────────────────────────────────────────────
     // ? Una función sin parámetros es aquella que no recibe valores al ser llamada.
     // ? Una función sin retorno no devuelve ningún valor al finalizar su ejecución.
     // ? Se usa cuando solo queremos ejecutar código sin recibir ni devolver datos.
     public static void imprimirMensaje() {
         System.out.println("¡Hola! Este es un mensaje desde una función sin parámetros ni retorno.");
     }
 
     // * 📖 TEORÍA: Función con parámetros y sin retorno
     // ────────────────────────────────────────────────────────────────────
     // ? Una función con parámetros recibe valores al ser llamada.
     // ? Se usa para reutilizar código y hacerlo más flexible.
     public static void saludar(String nombre) {
         System.out.println("¡Hola, " + nombre + "! Bienvenido al mundo de Java.");
     }
 
     // * 📖 TEORÍA: Función con parámetros y con retorno
     // ────────────────────────────────────────────────────────────────────
     // ? Una función con retorno devuelve un valor al finalizar su ejecución.
     // ? Se usa cuando necesitamos un resultado calculado dentro de la función.
     public static int sumar(int a, int b) {
         return a + b; // Devuelve la suma de los dos números
     }
 
     // * 📖 TEORÍA: Sobrecarga de funciones (métodos con el mismo nombre pero distintos parámetros)
     // ────────────────────────────────────────────────────────────────────
     // ? Java permite definir múltiples funciones con el mismo nombre, siempre que tengan diferentes parámetros.
     public static double sumar(double a, double b) {
         return a + b; // Devuelve la suma de los dos números con decimales
     }
 
     // * 📖 TEORÍA: Función recursiva
     // ────────────────────────────────────────────────────────────────────
     // ? Una función recursiva es aquella que se llama a sí misma.
     // ? Se usa para problemas que pueden dividirse en subproblemas más pequeños.
     public static int factorial(int n) {
         if (n == 0) {
             return 1; // Caso base
         }
         return n * factorial(n - 1); // Llamada recursiva
     }
 
     // * 📖 TEORÍA: Función con entrada del usuario
     // ────────────────────────────────────────────────────────────────────
     // ? Una función también puede recibir entrada directamente del usuario en su interior.
     public static void leerNumero() {
         Scanner sc = new Scanner(System.in);
         System.out.print("Introduce un número: ");
         int num = sc.nextInt();
         System.out.println("Has introducido: " + num);
         sc.close();
     }
 
     // * 📖 TEORÍA: Función que retorna un valor booleano
     // ────────────────────────────────────────────────────────────────────
     // ? Se usa cuando necesitamos una respuesta de tipo verdadero/falso.
     public static boolean esPar(int numero) {
         return numero % 2 == 0; // Devuelve true si el número es par
     }
 
     // * 📖 TEORÍA: Función con múltiples valores de retorno usando un array
     // ────────────────────────────────────────────────────────────────────
     // ? Si queremos devolver más de un valor, podemos usar un array.
     public static int[] calcularCuadradoYCubo(int numero) {
         int[] resultados = {numero * numero, numero * numero * numero};
         return resultados;
     }
 
     // * 📖 TEORÍA: Función con manejo de excepciones
     // ────────────────────────────────────────────────────────────────────
     // ? Podemos manejar errores dentro de una función para evitar fallos en el programa.
     public static void dividirNumeros() {
         Scanner sc = new Scanner(System.in);
         try {
             System.out.print("Introduce el numerador: ");
             int numerador = sc.nextInt();
             System.out.print("Introduce el denominador: ");
             int denominador = sc.nextInt();
 
             int resultado = numerador / denominador;
             System.out.println("El resultado de la división es: " + resultado);
         } catch (ArithmeticException e) {
             System.out.println("⚠️ Error: No se puede dividir por cero.");
         }
         sc.close();
     }
 
     // ! ✅ TAREA PARA EL ALUMNO:
     // * 1️⃣ Implementa una función llamada `calcularAreaRectangulo` que reciba base y altura y devuelva el área.
     // * 2️⃣ Implementa una función llamada `esPrimo` que determine si un número es primo o no.
     // * 3️⃣ Crea una función que convierta grados Celsius a Fahrenheit y viceversa.
     // * 4️⃣ Llama a estas funciones en `main()` para probar su funcionamiento.
 }