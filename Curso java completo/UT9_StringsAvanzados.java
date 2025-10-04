/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 9: STRINGS AVANZADOS Y MANIPULACIÓN DE TEXTO
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

/*
 * ******************************************************************************************
 *                        📚 TEORÍA Y CONCEPTOS: STRINGS EN JAVA (AVANZADO)
 * ──────────────────────────────────────────────────────────────────────────────
 * ✅ Los Strings son objetos inmutables que representan secuencias de caracteres.
 * ✅ Java proporciona numerosos métodos para analizarlos, transformarlos o validarlos.
 * ✅ Usaremos `.charAt()`, `.length()`, `.substring()`, `.equals()`, `.contains()`, entre otros.
 * ✅ Al final, practicarás con ejercicios reales y tareas guiadas con Better Comments.
 *
 * 🛠️ Para ver colores en los comentarios, instala la extensión "Better Comments" en VS Code.
 * ******************************************************************************************
 */


import java.util.Scanner;

public class UT9_StringsAvanzados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ✅ Menú principal
        int opcion;
        do {
            System.out.println("\n🎯 MENÚ DE STRINGS AVANZADOS");
            System.out.println("1. Contar vocales en una palabra");
            System.out.println("2. Invertir una cadena");
            System.out.println("3. Validar correo electrónico");
            System.out.println("4. Ejercicio final: Analizador de texto completo");
            System.out.println("0. Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> contarVocales(sc);
                case 2 -> invertirCadena(sc);
                case 3 -> validarEmail(sc);
                case 4 -> ejercicioFinalTexto(sc); // Solo teoría, implementación sugerida
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // 🔵 EJEMPLO 1: Contar vocales en una cadena
    public static void contarVocales(Scanner sc) {
        System.out.println("\n🔡 EJEMPLO 1: Contar vocales");
        System.out.print("Introduce una palabra: ");
        String palabra = sc.nextLine().toLowerCase(); // Convertir a minúsculas

        int contador = 0;
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if ("aeiou".indexOf(letra) != -1) {
                contador++;
            }
        }

        System.out.println("✅ Total de vocales: " + contador);

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Haz que también cuente vocales mayúsculas (sin usar toLowerCase).
        // * Muestra cuántas veces aparece cada vocal individualmente.
    }

    // 🔵 EJEMPLO 2: Invertir una cadena
    public static void invertirCadena(Scanner sc) {
        System.out.println("\n🔡 EJEMPLO 2: Invertir texto");
        System.out.print("Introduce una frase: ");
        String texto = sc.nextLine();

        String invertido = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            invertido += texto.charAt(i);
        }

        System.out.println("🔁 Texto invertido: " + invertido);

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Crea una función que verifique si una palabra es un palíndromo (ej: "oso", "reconocer").
    }

    // 🔵 EJEMPLO 3: Validación de email
    public static void validarEmail(Scanner sc) {
        System.out.println("\n🔡 EJEMPLO 3: Validar correo electrónico");
        System.out.print("Introduce un email: ");
        String email = sc.nextLine();

        if (email.contains("@") && email.endsWith(".com")) {
            System.out.println("✅ Email válido.");
        } else {
            System.out.println("❌ Email inválido.");
        }

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Valida que el email tenga mínimo 5 caracteres antes del @.
        // * Rechaza dominios como ".xyz" o ".abc".
    }

    // 🔴 EJERCICIO FINAL: Analizador de texto completo
    public static void ejercicioFinalTexto(Scanner sc) {
        /*
         * 🧪 EJERCICIO FINAL:
         * El alumno debe pedir al usuario una frase larga e implementar:
         * 1. Cuántas palabras tiene.
         * 2. Cuántos espacios hay.
         * 3. Cuántas veces aparece una letra específica (que también pedirá).
         * 4. Mostrar la primera y última palabra.
         * 5. Mostrar la frase toda en mayúsculas y luego en minúsculas.
         */
    }
}
