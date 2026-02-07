/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 ACTIVIDADES DE THREADS (HILOS) - PARTE 1 (Ejercicios 1 al 6)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.Arrays;   // ? Para convertir arrays a String con Arrays.toString()
import java.util.Scanner;  // ? Para leer datos del usuario por consola

public class ActividadesThreads_Parte1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 🛠️ Objeto para leer entradas del usuario
        int opcion;                          // 🎛️ Variable para controlar el menú

        // * MENÚ PRINCIPAL - Permite al usuario elegir qué ejercicio ejecutar
        do {
            System.out.println("\n🧵 MENÚ - ACTIVIDADES DE THREADS (HILOS):");
            System.out.println("1. Ejercicio 1: Suma en un Thread");
            System.out.println("2. Ejercicio 2: Main y Thread saludan");
            System.out.println("3. Ejercicio 3: Dos Threads imprimen su nombre");
            System.out.println("4. Ejercicio 4: Thread repite una letra 4 veces");
            System.out.println("5. Ejercicio 5: Vector x2 SIN espera (Race Condition)");
            System.out.println("6. Ejercicio 6: Vector x2 CON join (Sincronizado)");
            System.out.println("0. Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();        // 📥 Lee la opción seleccionada
            sc.nextLine();               // 🧹 Limpia el buffer tras leer número

            switch (opcion) {
                case 1 -> ejercicio1();
                case 2 -> ejercicio2();
                case 3 -> ejercicio3();
                case 4 -> ejercicio4(sc);
                case 5 -> ejercicio5();
                case 6 -> ejercicio6();
                case 0 -> System.out.println("👋 ¡Saliendo del programa!");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0); // 🔁 Repite mientras no se elija salir

        sc.close(); // 🔐 Cerramos el Scanner al terminar
    }

    // * 📖 TEORÍA: ¿Qué es un Thread (Hilo)?
    // ──────────────────────────────────────────────
    // ? Un Thread es la unidad más pequeña de ejecución dentro de un proceso.
    // ? En Java, el hilo principal es el método main().
    // ? Podemos crear hilos adicionales que se ejecutan de forma CONCURRENTE.
    // ? Formas de crear un hilo:
    // ?   1. Extender la clase Thread y sobrescribir run()
    // ?   2. Implementar la interfaz Runnable
    // ? Métodos importantes:
    // ?   - start()    → Inicia el hilo (llama a run() en paralelo)
    // ?   - run()      → Código que ejecuta el hilo
    // ?   - join()     → Espera a que el hilo termine
    // ?   - getName()  → Devuelve el nombre del hilo
    // ?   - sleep(ms)  → Pausa el hilo durante X milisegundos

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 1: Suma en un Thread
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa que ejecute, en un thread, la suma de los valores 300 y 500.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna que representa el hilo que realiza la suma
    static class HiloSuma extends Thread {
        private int valorA;  // 🔢 Primer valor a sumar
        private int valorB;  // 🔢 Segundo valor a sumar

        // ? Constructor: recibe los dos valores que se van a sumar
        public HiloSuma(int valorA, int valorB) {
            this.valorA = valorA;   // 📥 Asigna el primer valor
            this.valorB = valorB;   // 📥 Asigna el segundo valor
        }

        // ? Método run(): contiene el código que ejecutará el hilo
        @Override
        public void run() {
            int resultado = valorA + valorB;  // ➕ Realizamos la suma
            System.out.println("🧵 [" + getName() + "] La suma de " + valorA + " + " + valorB + " = " + resultado);
        }
    }

    public static void ejercicio1() {
        System.out.println("\n📝 EJERCICIO 1: Suma de 300 + 500 en un Thread");
        System.out.println("──────────────────────────────────────────────");

        HiloSuma hilo = new HiloSuma(300, 500);  // 🧵 Creamos el hilo con los valores
        System.out.println("🚀 [Main] Iniciando el hilo de suma...");
        hilo.start();  // ▶️ Arranca el hilo (llama a run() en paralelo)

        // ? Esperamos a que el hilo termine
        try {
            hilo.join(); // ⏳ El main espera a que el hilo termine
        } catch (InterruptedException e) {
            System.out.println("⚠️ El hilo fue interrumpido.");
        }

        System.out.println("✅ [Main] El hilo de suma ha finalizado.");

        // ! ✅ TAREA ALUMNO:
        // * Cambia los valores 300 y 500 por otros números y comprueba el resultado
        // * Modifica HiloSuma para que haga una RESTA en vez de una suma
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 2: Main y Thread saludan
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main imprima por pantalla: "Hola, soy el main"
    // ? y un thread que escriba: "Hola, soy " + el nombre del thread.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna que representa el hilo que saluda
    static class HiloSaludo extends Thread {

        // ? Constructor: permite asignar un nombre personalizado al hilo
        public HiloSaludo(String nombre) {
            super(nombre);  // 📝 Llama al constructor de Thread para asignar el nombre
        }

        // ? Método run(): imprime el saludo con el nombre del hilo
        @Override
        public void run() {
            System.out.println("🧵 Hola, soy " + getName());  // 📤 Imprime el nombre del hilo
        }
    }

    public static void ejercicio2() {
        System.out.println("\n📝 EJERCICIO 2: Main y Thread saludan");
        System.out.println("──────────────────────────────────────────────");

        System.out.println("🏠 Hola, soy el main");  // 📤 El main saluda primero

        HiloSaludo hilo = new HiloSaludo("MiHiloSaludador");  // 🧵 Creamos hilo con nombre
        hilo.start();  // ▶️ El hilo comienza su ejecución

        // ? Esperamos a que el hilo termine
        try {
            hilo.join();  // ⏳ Esperamos que termine
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción al esperar el hilo.");
        }

        System.out.println("✅ [Main] Ejercicio 2 completado.");

        // ! ✅ TAREA ALUMNO:
        // * Quita el join() y ejecuta varias veces. ¿Cambia el orden de los mensajes?
        // * Crea el hilo SIN pasarle nombre y observa qué nombre le asigna Java
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 3: Dos Threads imprimen su nombre
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree 2 threads, y cada uno de ellos
    // ? imprimirá por pantalla el nombre del thread.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna para este ejercicio
    static class HiloNombre extends Thread {

        // ? Constructor con nombre
        public HiloNombre(String nombre) {
            super(nombre);  // 📝 Asignamos nombre al hilo
        }

        // ? Método run(): imprime el nombre del hilo
        @Override
        public void run() {
            System.out.println("🧵 Soy el hilo: " + getName());  // 📤 Imprime su nombre
        }
    }

    public static void ejercicio3() {
        System.out.println("\n📝 EJERCICIO 3: Dos Threads imprimen su nombre");
        System.out.println("──────────────────────────────────────────────");

        HiloNombre hilo1 = new HiloNombre("Hilo-Alfa");   // 🧵 Primer hilo
        HiloNombre hilo2 = new HiloNombre("Hilo-Beta");   // 🧵 Segundo hilo

        System.out.println("🚀 [Main] Lanzando los dos hilos...");

        hilo1.start();  // ▶️ Arrancamos Hilo-Alfa
        hilo2.start();  // ▶️ Arrancamos Hilo-Beta

        // ? Esperamos a que ambos terminen
        try {
            hilo1.join();  // ⏳ Esperamos a Hilo-Alfa
            hilo2.join();  // ⏳ Esperamos a Hilo-Beta
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción al esperar los hilos.");
        }

        System.out.println("✅ [Main] Ambos hilos han terminado.");

        // ! ✅ TAREA ALUMNO:
        // * Añade un tercer hilo llamado "Hilo-Gamma" y lánzalo junto con los otros
        // * Haz que cada hilo imprima su nombre 3 veces con un bucle for
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 4: Thread repite una letra 4 veces
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main reciba por consola una letra.
    // ? Después, creará un thread y este imprimirá por pantalla la letra 4 veces.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que recibe una letra y la repite
    static class HiloRepiteLetra extends Thread {
        private char letra;          // 🔤 La letra que repetirá el hilo
        private int repeticiones;    // 🔁 Cuántas veces la repetirá

        // ? Constructor: recibe la letra y el número de repeticiones
        public HiloRepiteLetra(char letra, int repeticiones) {
            this.letra = letra;               // 📥 Guarda la letra
            this.repeticiones = repeticiones; // 📥 Guarda las repeticiones
        }

        // ? Método run(): imprime la letra tantas veces como se indicó
        @Override
        public void run() {
            System.out.println("🧵 [" + getName() + "] Imprimiendo '" + letra + "' " + repeticiones + " veces:");
            for (int i = 1; i <= repeticiones; i++) {  // 🔁 Bucle de repetición
                System.out.println("   → Repetición " + i + ": " + letra);
            }
        }
    }

    public static void ejercicio4(Scanner sc) {
        System.out.println("\n📝 EJERCICIO 4: Thread repite una letra 4 veces");
        System.out.println("──────────────────────────────────────────────");

        System.out.print("🔤 Introduce una letra: ");
        String entrada = sc.nextLine();  // 📥 Leemos la entrada del usuario

        // ? Validación: comprobamos que haya escrito algo
        if (entrada.isEmpty()) {
            System.out.println("⚠️ No has introducido ninguna letra.");
            return;  // 🔚 Salimos del método
        }

        char letra = entrada.charAt(0);  // 🔤 Tomamos el primer carácter

        HiloRepiteLetra hilo = new HiloRepiteLetra(letra, 4);  // 🧵 Creamos el hilo
        System.out.println("🚀 [Main] Lanzando hilo para repetir '" + letra + "'...");
        hilo.start();  // ▶️ Arrancamos el hilo

        // ? Esperamos a que termine
        try {
            hilo.join();  // ⏳ Esperamos su finalización
        } catch (InterruptedException e) {
            System.out.println("⚠️ El hilo fue interrumpido.");
        }

        System.out.println("✅ [Main] Ejercicio 4 completado.");

        // ! ✅ TAREA ALUMNO:
        // * Modifica el programa para que el usuario elija cuántas veces repetir
        // * Crea DOS hilos: uno que repita la letra en mayúscula y otro en minúscula
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 5: Vector x2 SIN espera (Race Condition)
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree un vector de enteros de tamaño 10.
    // ? El vector debe tener valores en todas sus posiciones.
    // ? Después, creará un thread que recibirá como parámetro este vector
    // ? y multiplicará por 2 todas sus posiciones (guardándolas en el mismo vector).
    // ? Sin ningún tipo de espera, el programa principal escribirá todas las
    // ? posiciones del vector. Explica el resultado.
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: Condición de Carrera (Race Condition)
    // ──────────────────────────────────────────────
    // ? Una Race Condition ocurre cuando dos hilos acceden al MISMO recurso
    // ? al MISMO tiempo sin sincronización.
    // ? Resultado: datos inconsistentes e impredecibles.
    // ? Solución: usar join() para esperar, o synchronized para proteger.

    // * Clase interna: hilo que multiplica por 2 cada posición de un vector
    static class HiloMultiplicaVector extends Thread {
        private int[] vector;  // 📦 Referencia al vector que vamos a modificar

        // ? Constructor: recibe el vector por referencia
        public HiloMultiplicaVector(int[] vector) {
            this.vector = vector;  // 📥 Guarda la REFERENCIA al vector (no una copia)
        }

        // ? Método run(): multiplica cada posición del vector por 2
        @Override
        public void run() {
            System.out.println("🧵 [" + getName() + "] Comenzando a multiplicar...");
            for (int i = 0; i < vector.length; i++) {  // 🔁 Recorremos el vector
                vector[i] = vector[i] * 2;  // ✖️ Multiplicamos por 2
                try {
                    Thread.sleep(50);  // 💤 Pausa para simular trabajo
                } catch (InterruptedException e) {
                    System.out.println("⚠️ Hilo interrumpido.");
                }
            }
            System.out.println("🧵 [" + getName() + "] He terminado de multiplicar.");
        }
    }

    public static void ejercicio5() {
        System.out.println("\n📝 EJERCICIO 5: Vector x2 SIN espera (Race Condition)");
        System.out.println("──────────────────────────────────────────────");

        int[] vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  // 📦 Vector con valores 1-10

        System.out.println("📦 Vector ORIGINAL: " + Arrays.toString(vector));

        HiloMultiplicaVector hilo = new HiloMultiplicaVector(vector);  // 🧵 Creamos el hilo
        hilo.start();  // ▶️ El hilo comienza a multiplicar

        // ! ⚠️ NO usamos join() aquí - El main NO espera al hilo

        System.out.println("\n🏠 [Main] Imprimiendo SIN ESPERAR al hilo:");
        System.out.print("📦 Vector AHORA:    [");
        for (int i = 0; i < vector.length; i++) {  // 🔁 Recorremos el vector
            System.out.print(vector[i]);            // 📤 Imprimimos cada valor
            if (i < vector.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // ? Ahora sí esperamos para mostrar el resultado final
        try {
            hilo.join();  // ⏳ Esperamos que termine
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        System.out.println("📦 Vector FINAL:    " + Arrays.toString(vector));

        // * 📖 EXPLICACIÓN DEL RESULTADO:
        // ──────────────────────────────────────────────
        // ? El vector "AHORA" muestra una MEZCLA de valores:
        // ?   - Algunas posiciones YA están multiplicadas (el hilo llegó antes)
        // ?   - Otras posiciones tienen el valor original (el hilo no llegó)
        // ? Esto es una CONDICIÓN DE CARRERA (Race Condition):
        // ?   - Main y hilo compiten por acceder al mismo vector
        // ?   - El resultado es IMPREDECIBLE
        // ? El Ejercicio 6 muestra la SOLUCIÓN con join()

        // ! ✅ TAREA ALUMNO:
        // * Ejecuta este ejercicio varias veces. ¿El vector "AHORA" cambia?
        // * Quita el Thread.sleep(50) del run() y vuelve a ejecutar
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 6: Vector x2 CON join (Sincronizado)
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree un vector de enteros de tamaño 10.
    // ? El vector debe tener valores en todas sus posiciones.
    // ? Después, creará un thread que recibirá como parámetro este vector
    // ? y multiplicará por 2 todas sus posiciones (guardándolas en el mismo vector).
    // ? Finalmente, el programa principal ESPERARÁ a que el thread termine
    // ? y escribirá todas las posiciones del vector. Explica el resultado.
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: El método join()
    // ──────────────────────────────────────────────
    // ? join() hace que el hilo que lo llama ESPERE a que otro hilo termine.
    // ? Sin join(): main y hilo corren a la vez → resultados impredecibles
    // ? Con join(): main espera al hilo → resultado siempre correcto

    // * Clase interna: versión del hilo para este ejercicio
    static class HiloMultiplicaVectorV2 extends Thread {
        private int[] vector;  // 📦 Referencia al vector

        public HiloMultiplicaVectorV2(int[] vector) {
            this.vector = vector;  // 📥 Guarda la referencia
        }

        @Override
        public void run() {
            System.out.println("🧵 [" + getName() + "] Multiplicando el vector por 2...");
            for (int i = 0; i < vector.length; i++) {
                vector[i] = vector[i] * 2;  // ✖️ Multiplicamos por 2
                try {
                    Thread.sleep(100);  // 💤 Simula trabajo costoso
                } catch (InterruptedException e) {
                    System.out.println("⚠️ Hilo interrumpido.");
                }
            }
            System.out.println("🧵 [" + getName() + "] ¡Multiplicación completada!");
        }
    }

    public static void ejercicio6() {
        System.out.println("\n📝 EJERCICIO 6: Vector x2 CON join (Sincronizado)");
        System.out.println("──────────────────────────────────────────────");

        int[] vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  // 📦 Vector con valores 1-10

        System.out.println("📦 Vector ORIGINAL:     " + Arrays.toString(vector));
        System.out.println("📦 Resultado ESPERADO:  [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]");

        HiloMultiplicaVectorV2 hilo = new HiloMultiplicaVectorV2(vector);

        System.out.println("\n🚀 [Main] Lanzando hilo para multiplicar...");
        hilo.start();  // ▶️ El hilo comienza a trabajar

        // * ¡AQUÍ ESTÁ LA DIFERENCIA CON EL EJERCICIO 5!
        try {
            System.out.println("⏳ [Main] Esperando a que el hilo termine (join)...");
            hilo.join();  // ⏳ EL MAIN SE DETIENE HASTA QUE EL HILO TERMINE
        } catch (InterruptedException e) {
            System.out.println("⚠️ El main fue interrumpido.");
        }

        System.out.println("\n🏠 [Main] El hilo ha terminado. Imprimiendo vector:");
        System.out.println("📦 Vector RESULTANTE:   " + Arrays.toString(vector));

        // ? Verificación: comprobamos que todos los valores están x2
        boolean correcto = true;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] != (i + 1) * 2) {
                correcto = false;
                break;
            }
        }
        System.out.println((correcto ? "✅" : "❌") + " Resultado " + (correcto ? "CORRECTO" : "INCORRECTO"));

        // * 📖 EXPLICACIÓN DEL RESULTADO:
        // ──────────────────────────────────────────────
        // ? El vector SIEMPRE será [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
        // ? ¿Por qué? Porque usamos join():
        // ?   - El main ESPERA a que el hilo termine antes de imprimir
        // ?   - Cuando el main imprime, el hilo YA ha multiplicado TODO
        // ?   - No hay condición de carrera: primero trabaja el hilo, luego lee el main
        // ?
        // ? COMPARACIÓN con Ejercicio 5:
        // ?   - Ejercicio 5 (SIN join): resultado impredecible (mezcla de valores)
        // ?   - Ejercicio 6 (CON join): resultado siempre correcto

        // ! ✅ TAREA ALUMNO:
        // * Compara los resultados del Ejercicio 5 y 6. ¿Cuál es más fiable?
        // * Modifica el hilo para que DIVIDA entre 2 en vez de multiplicar
        // * Crea DOS hilos: uno que multiplique por 2 y otro que sume 10
    }
}
