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
 *                        📚 **TEORÍA Y CONCEPTOS: CONTROL DE FLUJO (IF, ELSE, SWITCH)**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender cómo funcionan las estructuras de control en Java.**
 * ✅ **Utilizar sentencias `if`, `else if`, y `else` para la toma de decisiones.**
 * ✅ **Trabajar con la estructura `switch` para evaluar múltiples opciones.**
 * ✅ **Ejecutar solo fragmentos de código en distintos IDEs.**
 * ✅ **Entender el uso de comentarios multilínea en Java.**
 * ✅ **Combinar ambas estructuras en un programa más avanzado.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************
 */

/*
 * 🧠 **TEORÍA GLOBAL: CONTROL DE FLUJO EN JAVA**
 * ──────────────────────────────────────────────────────────────
 * 
 * 🔵 **¿Qué es el Control de Flujo?**
 *  - En un programa, las instrucciones se ejecutan secuencialmente, pero a veces es necesario 
 *    **tomar decisiones** o **repetir acciones** según ciertas condiciones.
 *  - Java ofrece varias estructuras para modificar el flujo de ejecución del programa.
 * 
 * 🔹 **Tipos de Control de Flujo en Java**
 * 
 * 1️⃣ **Condicionales (`if`, `else if`, `else`)**  
 *      - Se usan para ejecutar código basado en una condición.  
 *      - Ejemplo: Si un número es positivo, imprimir "Es positivo".
 * 
 * 📌 **Ejemplo de `if-else`:**
 * 
 * ```java
 * int numero = 5;
 * if (numero > 0) {
 *     System.out.println("El número es positivo.");
 * } else if (numero < 0) {
 *     System.out.println("El número es negativo.");
 * } else {
 *     System.out.println("El número es cero.");
 * }
 * ```
 * 
 * 2️⃣ **Selección múltiple (`switch`)**  
 *      - Se usa cuando hay varias opciones posibles.  
 *      - Ejemplo: Determinar el día de la semana según un número.
 * 
 * 📌 **Ejemplo de `switch`:**
 * 
 * ```java
 * int dia = 3;
 * switch (dia) {
 *     case 1 -> System.out.println("Lunes");
 *     case 2 -> System.out.println("Martes");
 *     case 3 -> System.out.println("Miércoles");
 *     default -> System.out.println("Día no válido");
 * }
 * ```
 * 
 */

/*
 * 🔴 **USO DE COMENTARIOS EN JAVA**
 * ──────────────────────────────────────────────────────────────
 * ✅ Java permite tres tipos de comentarios:
 * 
 * 1️⃣ **Comentarios de una sola línea:** Se usa `//` al inicio de la línea.
 * ```java
 * // Esto es un comentario de una línea
 * int x = 10;
 * ```
 * 
 * 2️⃣ **Comentarios multilínea:** Se usa `/* ... * /` para comentarios largos.
 * ```java
 * /*
 *  * Este es un comentario de varias líneas.
 *  * Se puede usar para describir en detalle lo que hace un bloque de código.
 *  * /
 * ```
 * 
 * 3️⃣ **Comentarios de documentación:** Se usa `/** ... * /` y se pueden generar documentos HTML con `javadoc`.
 * ```java
 * /**
 *  * Método que suma dos números enteros.
 *  * @param a Primer número
 *  * @param b Segundo número
 *  * @return La suma de `a` y `b`
 *  * /
 * public int sumar(int a, int b) {
 *     return a + b;
 * }
 * ```
 */

/*
 * 🔵 **¿CÓMO EJECUTAR SOLO UN FRAGMENTO DE CÓDIGO?**
 * ──────────────────────────────────────────────────────────────
 * Dependiendo del entorno que uses, puedes ejecutar partes específicas del código de la siguiente manera:
 * 
 * 🟢 **Visual Studio Code:**
 *  ✅ Usa la extensión **Better Comments** para resaltar comentarios.
 *  ✅ Usa el botón ▶️ que aparece junto a `main()` para ejecutar el código completo.
 *  ✅ Para ejecutar solo una parte del código:
 *      - Comenta el resto del código con `/* ... * /`
 *      - Usa `System.exit(0);` después del bloque que quieras ejecutar.
 * 
 * 🟡 **NetBeans:**
 *  ✅ Presiona `Ctrl + Shift + F6` para ejecutar solo el archivo actual.
 *  ✅ Comenta partes del código que no necesites ejecutar usando `/* ... * /`
 * 
 * 🔴 **IntelliJ IDEA:**
 *  ✅ Usa `Shift + F10` para ejecutar el código.
 *  ✅ Puedes usar `Run Configuration` para seleccionar qué partes del código ejecutar.
 * 
 * 📌 **Ejemplo de ejecución parcial en cualquier IDE:**
 * ```java
 * public static void main(String[] args) {
 *     System.out.println("Este código se ejecutará.");
 *     System.exit(0); // Detiene la ejecución aquí.
 *     System.out.println("Esta línea no se ejecutará.");
 * }
 * ```
 */

 import java.util.Scanner; // 📌 Importamos Scanner para leer la entrada del usuario

 public class UT2_ControlFlujo {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in); // 🛠️ Creamos un objeto Scanner para recibir datos del usuario
 
         // * 📖 TEORÍA: Uso de `if`, `else if` y `else`
         // ────────────────────────────────────────────────────────────
         // ? `if` permite ejecutar un bloque de código si se cumple una condición.
         // ? `else if` permite evaluar otras condiciones adicionales.
         // ? `else` se ejecuta si ninguna de las condiciones anteriores se cumple.
 
         // * ✨ EJEMPLO 1: Verificar si un número es positivo o negativo
         System.out.print("👉 Ingresa un número: "); // 📝 Pedimos al usuario que ingrese un número
         int numero = sc.nextInt(); // 📥 Capturamos el número ingresado
 
         // 🔽 Evaluamos el número ingresado con `if-else`
         if (numero > 0) { // 🟢 Si el número es mayor que 0
             System.out.println("✅ El número es positivo.");
         } else if (numero < 0) { // 🔴 Si el número es menor que 0
             System.out.println("❌ El número es negativo.");
         } else { // ⚠ Si el número es 0
             System.out.println("⚠ El número es cero.");
         }
 
         // * ✨ EJEMPLO 2: Determinar si un número es par o impar
         System.out.print("👉 Ingresa otro número: "); // 📝 Pedimos otro número
         int otroNumero = sc.nextInt(); // 📥 Capturamos el número ingresado
 
         // 🔽 Verificamos si el número es par o impar
         if (otroNumero % 2 == 0) { // 🟢 Si el número es divisible por 2, es par
             System.out.println("✅ El número " + otroNumero + " es par.");
         } else { // 🔴 Si el número no es divisible por 2, es impar
             System.out.println("❌ El número " + otroNumero + " es impar.");
         }
 
         // ! ✅ TAREA PARA EL ALUMNO:
         // * Modifica el código para verificar si un número ingresado es múltiplo de 3 y/o de 5.
 
         // * 📖 TEORÍA: Uso de `switch`
         // ────────────────────────────────────────────────────────────
         // ? `switch` permite evaluar múltiples casos sin escribir múltiples `if`.
         // ? Es útil cuando hay opciones limitadas y predefinidas.
 
         // * ✨ EJEMPLO 3: Determinar el día de la semana
         System.out.print("👉 Ingresa un número del 1 al 7 para conocer el día de la semana: "); // 📝 Pedimos un número del 1 al 7
         int dia = sc.nextInt(); // 📥 Capturamos el número ingresado
 
         // 🔽 Evaluamos el día usando `switch`
         switch (dia) {
             case 1:
                 System.out.println("Lunes"); // 🟢 Día 1: Lunes
                 break;
             case 2:
                 System.out.println("Martes"); // 🟢 Día 2: Martes
                 break;
             case 3:
                 System.out.println("Miércoles"); // 🟢 Día 3: Miércoles
                 break;
             case 4:
                 System.out.println("Jueves"); // 🟢 Día 4: Jueves
                 break;
             case 5:
                 System.out.println("Viernes"); // 🟢 Día 5: Viernes
                 break;
             case 6:
                 System.out.println("Sábado"); // 🟢 Día 6: Sábado
                 break;
             case 7:
                 System.out.println("Domingo"); // 🟢 Día 7: Domingo
                 break;
             default:
                 System.out.println("⚠ Número inválido. Debe ser entre 1 y 7."); // ❌ Número fuera del rango
         }
 
         // * ✨ EJEMPLO 4: Menú de opciones con `switch`
         System.out.println("\n📌 Menú de opciones:");
         System.out.println("1. Ver saldo");
         System.out.println("2. Retirar dinero");
         System.out.println("3. Depositar dinero");
         System.out.println("4. Salir");
         System.out.print("👉 Selecciona una opción: "); // 📝 Pedimos una opción al usuario
         int opcion = sc.nextInt(); // 📥 Capturamos la opción ingresada
 
         // 🔽 Evaluamos la opción usando `switch`
         switch (opcion) {
             case 1:
                 System.out.println("Tu saldo actual es: 500€"); // 🏦 Mostrar saldo
                 break;
             case 2:
                 System.out.println("Has retirado 100€."); // 💸 Retirar dinero
                 break;
             case 3:
                 System.out.println("Has depositado 200€."); // 💰 Depositar dinero
                 break;
             case 4:
                 System.out.println("Saliendo del sistema..."); // 🚪 Salir del sistema
                 break;
             default:
                 System.out.println("⚠ Opción inválida. Inténtalo de nuevo."); // ❌ Opción no válida
         }
 
         // * 📖 EJERCICIO FINAL: Combinación de `if-else` y `switch`
         System.out.print("\n👉 Ingresa tu tipo de usuario (admin, usuario, invitado): "); // 📝 Pedimos el tipo de usuario
         String tipoUsuario = sc.next().toLowerCase(); // 📥 Capturamos el tipo de usuario en minúsculas
 
         System.out.print("👉 Ingresa tu edad: "); // 📝 Pedimos la edad
         int edadUsuario = sc.nextInt(); // 📥 Capturamos la edad ingresada
 
         // 🔽 Evaluamos el tipo de usuario con `switch`
         switch (tipoUsuario) {
             case "admin":
                 System.out.println("🔹 Acceso completo al sistema."); // 🏆 Admin tiene acceso total
                 break;
             case "usuario":
                 if (edadUsuario >= 18) { // 🟢 Si el usuario tiene 18 años o más
                     System.out.println("✅ Acceso permitido a contenido avanzado.");
                 } else { // 🔴 Si es menor de edad
                     System.out.println("⚠ Acceso restringido para menores de edad.");
                 }
                 break;
             case "invitado":
                 System.out.println("⚠ Solo puedes ver contenido público."); // 🔹 Invitado tiene acceso limitado
                 break;
             default:
                 System.out.println("❌ Tipo de usuario no reconocido."); // ❌ Si no es admin, usuario o invitado
         }
 
         // ? Cerramos el Scanner para liberar recursos
         sc.close();
     }
 }
 