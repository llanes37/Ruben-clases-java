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
 *                        📚 **TEORÍA Y CONCEPTOS: INTRODUCCIÓN A JAVA**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender qué es Java y por qué es tan utilizado.**
 * ✅ **Configurar el entorno de desarrollo para empezar a programar.**
 * ✅ **Escribir tu primer programa en Java con salida en consola.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************
 */

/*
 * 🛠️ **INSTALACIÓN Y CONFIGURACIÓN DEL ENTORNO**
 * ──────────────────────────────────────────────────────────────
 * Para poder ejecutar código Java correctamente, sigue los siguientes pasos:
 * 
 * ❌ **IMPORTANTE: Instalar la extensión "Better Comments" en VS Code**
 *    - Abre **Visual Studio Code**.
 *    - Ve al panel de **Extensiones** (icono de cuadrado con 4 piezas).
 *    - Busca **Better Comments** e instálalo.
 *    - Esto permitirá ver los comentarios con colores correctamente.
 *
 * 🟡 **1️⃣ Instalar Java Development Kit (JDK)**
 * ──────────────────────────────────────────────────────────────
 * ✅ **Paso 1:** Descarga la última versión del JDK desde [aquí](https://www.oracle.com/java/technologies/javase-downloads.html).
 * ✅ **Paso 2:** Instala el JDK siguiendo las instrucciones para tu sistema operativo.
 * ✅ **Paso 3:** Verifica la instalación abriendo una terminal y ejecutando:
 *    - `java -version` (Debe mostrar la versión instalada).
 *    - `javac -version` (Debe mostrar la versión del compilador de Java).
 * ✅ **Paso 4:** Configura la variable de entorno `JAVA_HOME` si es necesario.
 *
 * 
 * 🔵 **2️⃣ Instalar y preparar un IDE (Entorno de Desarrollo)**
 * ──────────────────────────────────────────────────────────────
 * Puedes programar en Java usando diferentes IDEs. A continuación, te explicamos cómo configurar cada uno:
 *
 *  
 * 🟢 **Visual Studio Code (Recomendado)**
 * ──────────────────────────────────────────────────────────────
 * ✅ **Instalar VS Code:** Descárgalo desde [Visual Studio Code](https://code.visualstudio.com/).
 * ✅ **Instalar la extensión de Java:**
 *    - Abre **Visual Studio Code**.
 *    - En la barra lateral izquierda, haz clic en **Extensiones** (icono de cuadrado con 4 piezas).
 *    - Busca **Extension Pack for Java** e instálalo.
 * ✅ **Beneficios:**  
 *    - Se agregará el **botón de ejecución automática** encima del método `main`.
 *    - Permite ver los comentarios con colores gracias a la extensión **Better Comments**.
 * 
 * 🟡 **Cómo ejecutar un programa en VS Code**
 * ✅ **Método 1 (Más fácil):**  
 *    - Abre el archivo `.java` en **Visual Studio Code**.  
 *    - Haz clic en el botón ▶️ "Run" que aparece encima del método `main()`.  
 * ✅ **Método 2 (Teclado):**  
 *    - Presiona `Ctrl + F5` para ejecutar el programa sin abrir la terminal.  
 * ✅ **Método 3 (Manual desde terminal):**  
 *    - Abre la terminal (`Ctrl + Ñ` en Windows o `Ctrl + Shift + ñ` en Mac/Linux).
 *    - Compila el archivo con `javac NombreArchivo.java`.
 *    - Ejecuta con `java NombreArchivo`.
 *
 * 
 * 🔴 **NetBeans**
 * ──────────────────────────────────────────────────────────────
 * ✅ **Instalar NetBeans:** Descárgalo desde [NetBeans](https://netbeans.apache.org/).
 * ✅ **Preparación:**
 *    - Durante la instalación, selecciona la opción que incluya **JDK y Apache NetBeans**.
 *    - Asegúrate de que el **JDK está instalado y configurado correctamente**.
 * ✅ **Cómo ejecutar un programa en NetBeans**
 *    - Abre NetBeans y crea un **nuevo proyecto Java**.
 *    - En el explorador de proyectos, haz clic derecho sobre el archivo `Main.java` y selecciona **Run File** (`Shift + F6`).
 *    - Para ejecutar todo el proyecto, presiona `F6`.
 *
 * 
 * 🔵 **IntelliJ IDEA**
 * ──────────────────────────────────────────────────────────────
 * ✅ **Instalar IntelliJ:** Descárgalo desde [IntelliJ IDEA](https://www.jetbrains.com/idea/).
 * ✅ **Preparación:**
 *    - Durante la instalación, selecciona la opción **IntelliJ IDEA Community Edition**.
 *    - Configura el **JDK** en `File > Project Structure > SDKs`.
 * ✅ **Cómo ejecutar un programa en IntelliJ IDEA**
 *    - Abre IntelliJ y crea un **nuevo proyecto Java**.
 *    - En el explorador de archivos, haz clic derecho sobre la clase principal y selecciona **Run 'ClasePrincipal'**.
 *    - También puedes presionar `Shift + F10` para ejecutar el código.
 *
 * 🟢 **Recomendación Final**
 * **Visual Studio Code** es la mejor opción para programadores que comienzan con Java debido a su facilidad de uso y las extensiones como **Better Comments** para mejorar la legibilidad del código.
 */
import java.util.Scanner;  // Para leer datos ingresados por el usuario

public class UT0_IntroduccionJava {

    public static void main(String[] args) {
        // * 📖 TEORÍA: ¿Qué es Java?
        // ────────────────────────────────────────────────────────────
        // ? Java es un lenguaje de programación orientado a objetos y multiplataforma.
        // ? Fue desarrollado por Sun Microsystems (ahora Oracle) en 1995.
        // ? Su código fuente se compila a *bytecode*, que es ejecutado por la *Máquina Virtual de Java (JVM)*.
        // ? Es uno de los lenguajes más populares debido a su versatilidad y estabilidad.

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Investiga: ¿Cuáles son las principales diferencias entre Java y otros lenguajes como Python o C++?
        // * Añade un comentario en este código explicando las ventajas de Java.

        // * ✨ EJEMPLO 1: Primer programa en Java
        // ────────────────────────────────────────────────────────────
        System.out.println("¡Hola, mundo! Bienvenido a Java."); // ? Muestra un mensaje en consola

        // * 📖 TEORÍA: Declaración de Variables en Java
        // ────────────────────────────────────────────────────────────
        // ? Java es un lenguaje fuertemente tipado, lo que significa que cada variable tiene un tipo específico.
        // ? Algunos tipos de datos primitivos en Java son:
        //      * `int` - Números enteros
        //      * `double` - Números decimales
        //      * `boolean` - Valores verdadero/falso
        //      * `String` - Cadenas de texto

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Declara una variable `altura` de tipo `double` y asígnale un valor.
        // * Declara una variable `esEstudiante` de tipo `boolean` y asígnale `true` o `false`.

        // * ✨ EJEMPLO 2: Declaración de variables en Java
        int edad = 25; // ? Variable de tipo entero
        double precio = 19.99; // ? Variable de tipo decimal
        String nombre = "Joaquín"; // ? Variable de texto
        boolean esProgramador = true; // ? Variable booleana (true/false)

        // * 📖 TEORÍA: Imprimir en Consola
        // ────────────────────────────────────────────────────────────
        // ? Se usa `System.out.println()` para mostrar información en pantalla.

        // * ✨ EJEMPLO 3: Imprimir variables en consola
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Precio: " + precio);
        System.out.println("¿Es programador? " + esProgramador);

        // * 📖 TEORÍA: Leer datos del usuario
        // ────────────────────────────────────────────────────────────
        // ? Para leer datos en Java usamos la clase `Scanner`.

        // * ✨ EJEMPLO 4: Leer datos del usuario
        Scanner sc = new Scanner(System.in);
        System.out.print("👉 Ingresa tu nombre: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("👉 Ingresa tu edad: ");
        int edadUsuario = sc.nextInt();

        // ? Mostramos la información ingresada
        System.out.println("Hola, " + nombreUsuario + "! Tienes " + edadUsuario + " años.");

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Agrega una nueva pregunta que solicite al usuario su ciudad.
        // * Muestra la ciudad en un mensaje junto con su nombre y edad.

        // ? Cerramos el Scanner
        sc.close();
    }
}
