/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2025
 *  UNIDAD: BUCLES EN JAVA (for, while, do-while)
 *  REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.Scanner; // Para leer datos del usuario

public class UT_Bucles {

    // * TEORIA GENERAL DE BUCLES EN JAVA
    // ? Un bucle repite un bloque de codigo mientras una condicion lo permita.
    // ? Tipos principales:
    // ? - for: ideal cuando controlamos el numero de repeticiones.
    // ? - while: util cuando repetimos segun una condicion.
    // ? - do-while: garantiza al menos 1 ejecucion antes de validar.
    // ?
    // ? Esquemas:
    // ? for (inicio; condicion; actualizacion) { ... }
    // ? while (condicion) { ... }
    // ? do { ... } while (condicion);
    // ?
    // ? Conceptos clave:
    // ? - Contador: variable de control (ej: i, j, n).
    // ? - Acumulador: guarda resultados parciales (ej: suma += i).
    // ? - Condicion de parada: evita bucles infinitos.
    // !
    // ! Error tipico: olvidar actualizar el contador dentro de while.
    // ! Buen habito: pensar siempre en "inicio, condicion y actualizacion".

    // * COLORES ANSI PARA CONSOLA
    // ? Si la terminal no soporta ANSI, el programa funciona igual.
    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROJO = "\u001B[31m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // ? Objeto para entrada por teclado
        int opcion; // ? Controla el menu principal

        // * MENU PRINCIPAL
        // ? Se repite hasta que el usuario elige salir.
        do {
            mostrarMenu();
            System.out.print(AZUL + "Elige una opcion: " + RESET);
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> contarDel1Al10();
                case 2 -> cuentaAtras(sc);
                case 3 -> tablaMultiplicar(sc);
                case 4 -> sumaHastaN(sc);
                case 5 -> adivinaNumero(sc);
                case 6 -> paresDel1Al50();
                case 7 -> mostrarTeoriaRapida();
                case 0 -> System.out.println(VERDE + "Saliendo del programa. Hasta luego." + RESET);
                default -> System.out.println(ROJO + "Opcion no valida. Prueba de nuevo." + RESET);
            }
        } while (opcion != 0);

        sc.close(); // ? Cerramos Scanner al finalizar
    }

    // * BLOQUE VISUAL DEL MENU
    // ? Muestra opciones de practica guiada de bucles.
    private static void mostrarMenu() {
        System.out.println("\n" + AMARILLO + "==============================================" + RESET);
        System.out.println(AMARILLO + "         MENU - PRACTICA DE BUCLES" + RESET);
        System.out.println(AMARILLO + "==============================================" + RESET);
        System.out.println("1. Contar del 1 al 10 (for)");
        System.out.println("2. Cuenta atras desde un numero (while)");
        System.out.println("3. Tabla de multiplicar (for)");
        System.out.println("4. Sumar numeros hasta N (for)");
        System.out.println("5. Adivina el numero secreto (do-while)");
        System.out.println("6. Mostrar pares del 1 al 50 (for)");
        System.out.println("7. Teoria rapida de bucles");
        System.out.println("0. Salir");
    }

    // * TEORIA: BUCLE FOR
    // ? Se usa cuando conocemos el numero de repeticiones.
    // ? Estructura clara: inicio, condicion y actualizacion en una sola linea.
    // ? Ejemplo: for (int i = 1; i <= 10; i++) { ... }
    // ! Prueba: cambia el limite de 10 a 20 y observa la salida.
    private static void contarDel1Al10() {
        System.out.println(AZUL + "\n[for] Contando del 1 al 10:" + RESET);
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // * TEORIA: BUCLE WHILE
    // ? Repite mientras la condicion sea verdadera.
    // ? Muy util cuando no conocemos el numero exacto de vueltas.
    // ? Recuerda actualizar la variable de control para poder salir.
    // ! Prueba: iniciar con un numero negativo y analizar el resultado.
    private static void cuentaAtras(Scanner sc) {
        System.out.print(AZUL + "\nIntroduce un numero para la cuenta atras: " + RESET);
        int n = leerEntero(sc);

        System.out.println(AZUL + "[while] Cuenta atras:" + RESET);
        while (n >= 0) {
            System.out.print(n + " ");
            n--;
        }
        System.out.println();
    }

    // * TEORIA: FOR + OPERACION MATEMATICA
    // ? Recorremos del 1 al 10 y calculamos numero * i.
    // ? Patron comun para generar tablas, reportes y secuencias.
    // ! Tarea: amplia la tabla hasta 12.
    private static void tablaMultiplicar(Scanner sc) {
        System.out.print(AZUL + "\nIntroduce un numero para ver su tabla: " + RESET);
        int numero = leerEntero(sc);

        System.out.println(AZUL + "Tabla del " + numero + ":" + RESET);
        for (int i = 1; i <= 10; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }

    // * TEORIA: ACUMULADOR
    // ? Un acumulador suma (o combina) valores en cada iteracion.
    // ? Patron:
    // ? 1) Inicializar (suma = 0)
    // ? 2) Recorrer datos
    // ? 3) Actualizar (suma += valor)
    // ! Si N < 1, no hay suma natural valida de 1 a N.
    private static void sumaHastaN(Scanner sc) {
        System.out.print(AZUL + "\nIntroduce N para sumar de 1 a N: " + RESET);
        int n = leerEntero(sc);

        if (n < 1) {
            System.out.println(ROJO + "N debe ser mayor o igual que 1." + RESET);
            return;
        }

        int suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += i;
        }
        System.out.println(VERDE + "La suma de 1 a " + n + " es: " + suma + RESET);
    }

    // * TEORIA: BUCLE DO-WHILE
    // ? Ejecuta primero y pregunta despues por la condicion.
    // ? Se usa mucho en menus y validaciones de entrada.
    // ! Tarea: anadir contador de intentos e informar al final.
    private static void adivinaNumero(Scanner sc) {
        final int secreto = 7; // ? Numero fijo para practicar logica
        int intento;

        System.out.println(AZUL + "\nAdivina el numero secreto (entre 1 y 10)." + RESET);
        do {
            System.out.print("Tu intento: ");
            intento = leerEntero(sc);

            if (intento < secreto) {
                System.out.println(AMARILLO + "Mas alto." + RESET);
            } else if (intento > secreto) {
                System.out.println(AMARILLO + "Mas bajo." + RESET);
            }
        } while (intento != secreto);

        System.out.println(VERDE + "Correcto. Has acertado el numero secreto." + RESET);
    }

    // * TEORIA: FILTRAR DATOS EN BUCLES
    // ? Recorremos un rango y aplicamos una condicion.
    // ? Operador modulo (%):
    // ? - i % 2 == 0 -> par
    // ? - i % 2 != 0 -> impar
    // ! Tarea: mostrar solo impares del 1 al 50.
    private static void paresDel1Al50() {
        System.out.println(AZUL + "\nNumeros pares del 1 al 50:" + RESET);
        for (int i = 1; i <= 50; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // * BLOQUE EXTRA DE TEORIA RESUMIDA
    // ? Opcion pensada para repaso rapido antes de hacer ejercicios.
    private static void mostrarTeoriaRapida() {
        System.out.println(AZUL + "\n========== TEORIA RAPIDA DE BUCLES ==========" + RESET);
        System.out.println("FOR:");
        System.out.println("- Control exacto de repeticiones.");
        System.out.println("- Ideal para contadores y tablas.");
        System.out.println();
        System.out.println("WHILE:");
        System.out.println("- Repite mientras una condicion sea verdadera.");
        System.out.println("- Recomendado cuando el final depende de datos.");
        System.out.println();
        System.out.println("DO-WHILE:");
        System.out.println("- Ejecuta al menos una vez.");
        System.out.println("- Muy util en menus y validaciones.");
        System.out.println();
        System.out.println("CONSEJOS:");
        System.out.println("- Define inicio, condicion y actualizacion.");
        System.out.println("- Evita bucles infinitos.");
        System.out.println("- Usa nombres claros para contador/acumulador.");
        System.out.println(VERDE + "=============================================\n" + RESET);
    }

    // * UTILIDAD DE ENTRADA SEGURA
    // ? Evita errores si el usuario escribe texto en lugar de numero.
    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print(ROJO + "Entrada no valida. Introduce un numero: " + RESET);
            sc.next(); // ? Descarta la entrada incorrecta
        }
        int valor = sc.nextInt();
        sc.nextLine(); // ? Limpia salto de linea pendiente
        return valor;
    }
}
