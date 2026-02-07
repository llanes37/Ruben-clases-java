/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 ACTIVIDADES DE THREADS (HILOS) - PARTE 2 (Ejercicios 7 al 16)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.ArrayList;  // ? Para almacenar listas dinámicas de threads
import java.util.Arrays;     // ? Para convertir arrays a String con Arrays.toString()
import java.util.Scanner;    // ? Para leer datos del usuario por consola

public class ActividadesThreads_Parte2 {

    // * Variable global para el Ejercicio 14
    static int SUMATOTAL = 0;  // 🔢 Variable compartida entre hilos

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 🛠️ Objeto para leer entradas del usuario
        int opcion;                          // 🎛️ Variable para controlar el menú

        // * MENÚ PRINCIPAL - Permite al usuario elegir qué ejercicio ejecutar
        do {
            System.out.println("\n🧵 MENÚ - ACTIVIDADES DE THREADS (HILOS) - PARTE 2:");
            System.out.println("7.  Ejercicio 7:  N Threads imprimen su nombre");
            System.out.println("8.  Ejercicio 8:  Invertir palabras con Threads");
            System.out.println("9.  Ejercicio 9:  Números primos (pool de 5 threads)");
            System.out.println("10. Ejercicio 10: N Threads en ORDEN (sincronizados)");
            System.out.println("11. Ejercicio 11: 2 Threads + Main imprime FIN");
            System.out.println("12. Ejercicio 12: 2 Threads escriben 's' y 'o'");
            System.out.println("13. Ejercicio 13: 2 Threads escriben 's' y HOLA");
            System.out.println("14. Ejercicio 14: Variable global SUMATOTAL");
            System.out.println("15. Ejercicio 15: Cuadrados con paralelismo");
            System.out.println("16. Ejercicio 16: Medir tiempos secuencial vs paralelo");
            System.out.println("0.  Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();        // 📥 Lee la opción seleccionada
            sc.nextLine();               // 🧹 Limpia el buffer tras leer número

            switch (opcion) {
                case 7 -> ejercicio7(sc);
                case 8 -> ejercicio8(sc);
                case 9 -> ejercicio9(sc);
                case 10 -> ejercicio10(sc);
                case 11 -> ejercicio11();
                case 12 -> ejercicio12();
                case 13 -> ejercicio13();
                case 14 -> ejercicio14();
                case 15 -> ejercicio15();
                case 16 -> ejercicio16();
                case 0 -> System.out.println("👋 ¡Saliendo del programa!");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0); // 🔁 Repite mientras no se elija salir

        sc.close(); // 🔐 Cerramos el Scanner al terminar
    }

    // * 📖 TEORÍA PARTE 2: Conceptos avanzados de Threads
    // ──────────────────────────────────────────────
    // ? ArrayList<Thread>: permite almacenar múltiples hilos para gestionarlos
    // ? synchronized: palabra clave para proteger secciones críticas
    // ? Race Condition: cuando varios hilos modifican la misma variable sin control
    // ? Pool de threads: limitar el número de hilos activos simultáneamente
    // ? System.currentTimeMillis(): obtiene el tiempo actual en milisegundos

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 7: N Threads imprimen su nombre
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa que cree N threads, y cada uno de ellos imprimirá
    // ? por pantalla el nombre del thread.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que imprime su nombre
    static class HiloNombreN extends Thread {

        // ? Constructor con nombre personalizado
        public HiloNombreN(String nombre) {
            super(nombre);  // 📝 Asignamos nombre al hilo
        }

        // ? Método run(): imprime el nombre del hilo
        @Override
        public void run() {
            System.out.println("🧵 Soy el hilo: " + getName());
        }
    }

    public static void ejercicio7(Scanner sc) {
        System.out.println("\n📝 EJERCICIO 7: N Threads imprimen su nombre");
        System.out.println("──────────────────────────────────────────────");

        System.out.print("🔢 ¿Cuántos threads quieres crear? N = ");
        int n = sc.nextInt();  // 📥 Leemos el número de threads

        // ? Validación: N debe ser mayor que 0
        if (n <= 0) {
            System.out.println("⚠️ N debe ser mayor que 0.");
            return;
        }

        // ? Usamos un ArrayList para almacenar todos los hilos
        ArrayList<Thread> listaHilos = new ArrayList<>();  // 📦 Lista de hilos

        // * Paso 1: Creamos los N hilos y los guardamos en la lista
        System.out.println("🚀 [Main] Creando " + n + " threads...");
        for (int i = 1; i <= n; i++) {
            HiloNombreN hilo = new HiloNombreN("Thread-" + i);  // 🧵 Creamos hilo con nombre
            listaHilos.add(hilo);  // 📥 Lo añadimos a la lista
        }

        // * Paso 2: Iniciamos todos los hilos
        for (Thread hilo : listaHilos) {
            hilo.start();  // ▶️ Arrancamos cada hilo
        }

        // * Paso 3: Esperamos a que todos terminen
        for (Thread hilo : listaHilos) {
            try {
                hilo.join();  // ⏳ Esperamos a que termine
            } catch (InterruptedException e) {
                System.out.println("⚠️ Interrupción al esperar.");
            }
        }

        System.out.println("✅ [Main] Los " + n + " threads han terminado.");

        // ! ✅ TAREA ALUMNO:
        // * Prueba con N = 10 y observa el orden de impresión
        // * ¿Sale siempre en el mismo orden? ¿Por qué?
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 8: Invertir palabras con Threads
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main vaya pidiendo por consola palabras.
    // ? Al recibir una, creará un thread que se encargará de darle la vuelta
    // ? a la palabra e imprimirla por pantalla.
    // ? El main seguirá pidiendo palabras hasta que reciba la palabra "SALIR".
    // ? El programa principal no puede terminar hasta asegurarse de que todos
    // ? los threads han terminado.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que invierte una palabra
    static class HiloInvertirPalabra extends Thread {
        private String palabra;  // 🔤 La palabra a invertir

        // ? Constructor: recibe la palabra
        public HiloInvertirPalabra(String palabra) {
            this.palabra = palabra;  // 📥 Guarda la palabra
        }

        // ? Método run(): invierte la palabra y la imprime
        @Override
        public void run() {
            // ? StringBuilder permite construir strings de forma eficiente
            StringBuilder invertida = new StringBuilder(palabra);  // 📦 Creamos StringBuilder
            invertida.reverse();  // 🔄 Invertimos la palabra
            System.out.println("🧵 [" + getName() + "] \"" + palabra + "\" → \"" + invertida + "\"");
        }
    }

    public static void ejercicio8(Scanner sc) {
        System.out.println("\n📝 EJERCICIO 8: Invertir palabras con Threads");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("💡 Escribe palabras y se invertirán. Escribe SALIR para terminar.\n");

        ArrayList<Thread> listaHilos = new ArrayList<>();  // 📦 Lista para guardar los hilos
        String palabra;

        // * Bucle principal: pedir palabras hasta "SALIR"
        do {
            System.out.print("🔤 Introduce una palabra: ");
            palabra = sc.nextLine();  // 📥 Leemos la palabra

            // ? Si no es "SALIR", creamos y lanzamos un hilo
            if (!palabra.equalsIgnoreCase("SALIR")) {
                HiloInvertirPalabra hilo = new HiloInvertirPalabra(palabra);  // 🧵 Creamos hilo
                listaHilos.add(hilo);  // 📥 Lo guardamos en la lista
                hilo.start();  // ▶️ Lo arrancamos
            }
        } while (!palabra.equalsIgnoreCase("SALIR"));  // 🔁 Hasta que escriba SALIR

        // * Esperamos a que TODOS los hilos terminen
        System.out.println("\n⏳ [Main] Esperando a que todos los threads terminen...");
        for (Thread hilo : listaHilos) {
            try {
                hilo.join();  // ⏳ Esperamos a cada hilo
            } catch (InterruptedException e) {
                System.out.println("⚠️ Interrupción al esperar.");
            }
        }

        System.out.println("✅ [Main] Todos los threads han terminado. Total: " + listaHilos.size());

        // ! ✅ TAREA ALUMNO:
        // * Prueba con palabras como "HOLA", "JAVA", "THREAD"
        // * Modifica el hilo para que además cuente las vocales de la palabra
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 9: Números primos (pool de 5 threads)
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main vaya pidiendo por consola números enteros.
    // ? Al recibir uno, creará un thread que determinará si es primo o no.
    // ? El main seguirá pidiendo enteros hasta que reciba el número -1.
    // ? Control de errores: no permitir números negativos (excepto -1).
    // ? IMPORTANTE: Se limitan los threads a 5. Si no quedan disponibles,
    // ? se esperará hasta que alguno acabe.
    // ? Recuerda: un número es primo si solo es divisible entre sí mismo y 1.
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: Pool de Threads
    // ──────────────────────────────────────────────
    // ? Un pool limita el número de hilos activos simultáneamente.
    // ? Evita saturar el sistema con demasiados hilos.
    // ? Cuando un hilo termina, se puede crear otro nuevo.

    // * Clase interna: hilo que determina si un número es primo
    static class HiloPrimo extends Thread {
        private int numero;  // 🔢 El número a comprobar

        // ? Constructor: recibe el número
        public HiloPrimo(int numero) {
            this.numero = numero;  // 📥 Guarda el número
        }

        // ? Método para comprobar si un número es primo
        private boolean esPrimo(int n) {
            if (n <= 1) return false;  // 0 y 1 no son primos
            if (n <= 3) return true;   // 2 y 3 son primos
            if (n % 2 == 0) return false;  // Pares no son primos
            // ? Comprobamos divisores impares hasta la raíz cuadrada
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;  // ❌ Es divisible, no es primo
            }
            return true;  // ✅ Es primo
        }

        // ? Método run(): comprueba y muestra el resultado
        @Override
        public void run() {
            // ? Simulamos un pequeño retardo para ver el efecto del pool
            try {
                Thread.sleep(500);  // 💤 Simula trabajo
            } catch (InterruptedException e) {
                System.out.println("⚠️ Hilo interrumpido.");
            }

            String resultado = esPrimo(numero) ? "ES PRIMO ✅" : "NO es primo ❌";
            System.out.println("🧵 [" + getName() + "] El número " + numero + " " + resultado);
        }
    }

    public static void ejercicio9(Scanner sc) {
        System.out.println("\n📝 EJERCICIO 9: Números primos (pool de 5 threads)");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("💡 Introduce números para comprobar si son primos.");
        System.out.println("⚠️ Máximo 5 threads simultáneos. Escribe -1 para terminar.\n");

        final int MAX_THREADS = 5;  // 🔒 Límite de threads simultáneos
        ArrayList<Thread> hilosActivos = new ArrayList<>();  // 📦 Lista de hilos activos

        int numero;
        int contadorHilos = 0;  // 🔢 Contador para nombrar los hilos

        // * Bucle principal: pedir números hasta -1
        do {
            System.out.print("🔢 Introduce un número entero: ");
            
            // ? Validamos que sea un número entero
            if (!sc.hasNextInt()) {
                System.out.println("⚠️ Error: Debes introducir un número entero.");
                sc.nextLine();  // 🧹 Limpiamos el buffer
                continue;
            }
            
            numero = sc.nextInt();  // 📥 Leemos el número
            sc.nextLine();  // 🧹 Limpiamos el buffer

            // ? Si es -1, salimos del bucle
            if (numero == -1) break;

            // ? Control de errores: no permitir negativos
            if (numero < 0) {
                System.out.println("⚠️ Error: No se permiten números negativos (excepto -1).");
                continue;
            }

            // * Limpiamos hilos que ya han terminado de la lista
            hilosActivos.removeIf(hilo -> !hilo.isAlive());  // 🧹 Eliminamos los terminados

            // * Si hemos llegado al límite, esperamos a que alguno termine
            while (hilosActivos.size() >= MAX_THREADS) {
                System.out.println("⏳ Pool lleno (" + MAX_THREADS + " threads). Esperando...");
                try {
                    Thread.sleep(100);  // 💤 Pequeña espera
                } catch (InterruptedException e) {
                    System.out.println("⚠️ Interrupción.");
                }
                hilosActivos.removeIf(hilo -> !hilo.isAlive());  // 🧹 Limpiamos finalizados
            }

            // * Creamos y lanzamos el nuevo hilo
            contadorHilos++;
            HiloPrimo hilo = new HiloPrimo(numero);  // 🧵 Creamos hilo
            hilo.setName("Primo-" + contadorHilos);  // 📝 Le damos nombre
            hilosActivos.add(hilo);  // 📥 Lo añadimos a la lista
            hilo.start();  // ▶️ Lo arrancamos
            System.out.println("   → Thread creado. Activos: " + hilosActivos.size() + "/" + MAX_THREADS);

        } while (numero != -1);  // 🔁 Hasta que escriba -1

        // * Esperamos a que TODOS los hilos terminen
        System.out.println("\n⏳ [Main] Esperando a que todos los threads terminen...");
        for (Thread hilo : hilosActivos) {
            try {
                hilo.join();  // ⏳ Esperamos a cada hilo
            } catch (InterruptedException e) {
                System.out.println("⚠️ Interrupción al esperar.");
            }
        }

        System.out.println("✅ [Main] Todos los threads han terminado.");

        // ! ✅ TAREA ALUMNO:
        // * Prueba con varios números rápidamente para ver el pool en acción
        // * Cambia MAX_THREADS a 3 y observa la diferencia
        // * Añade un contador de números primos encontrados
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 10: N Threads en ORDEN (sincronizados)
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa que cree N threads, y cada uno de ellos imprimirá
    // ? por pantalla el nombre del thread.
    // ? Asegura que se ejecutan en ORDEN: primero thread1, después thread2, etc.
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: Sincronización con wait() y notify()
    // ──────────────────────────────────────────────
    // ? Para garantizar orden, usamos un objeto de bloqueo (lock).
    // ? wait(): el hilo espera hasta ser notificado.
    // ? notify(): despierta a un hilo que estaba esperando.
    // ? O más simple: con join() esperamos a que termine antes de lanzar el siguiente.

    // * Clase interna: hilo que imprime su nombre
    static class HiloOrdenado extends Thread {

        public HiloOrdenado(String nombre) {
            super(nombre);  // 📝 Asignamos nombre
        }

        @Override
        public void run() {
            System.out.println("🧵 Ejecutando: " + getName());
        }
    }

    public static void ejercicio10(Scanner sc) {
        System.out.println("\n📝 EJERCICIO 10: N Threads en ORDEN (sincronizados)");
        System.out.println("──────────────────────────────────────────────");

        System.out.print("🔢 ¿Cuántos threads quieres crear? N = ");
        int n = sc.nextInt();  // 📥 Leemos el número de threads

        if (n <= 0) {
            System.out.println("⚠️ N debe ser mayor que 0.");
            return;
        }

        System.out.println("🚀 [Main] Ejecutando " + n + " threads EN ORDEN...\n");

        // * Método simple: lanzar uno, esperar con join, lanzar el siguiente
        for (int i = 1; i <= n; i++) {
            HiloOrdenado hilo = new HiloOrdenado("Thread-" + i);  // 🧵 Creamos hilo
            hilo.start();  // ▶️ Lo arrancamos

            try {
                hilo.join();  // ⏳ ESPERAMOS a que termine ANTES de lanzar el siguiente
            } catch (InterruptedException e) {
                System.out.println("⚠️ Interrupción.");
            }
        }

        System.out.println("\n✅ [Main] Todos los threads han terminado EN ORDEN.");

        // * 📖 EXPLICACIÓN:
        // ──────────────────────────────────────────────
        // ? Al usar join() después de start(), el main espera a que termine
        // ? ANTES de crear y lanzar el siguiente hilo.
        // ? Esto garantiza el orden: Thread-1, Thread-2, Thread-3...
        // ? Pero OJO: perdemos el paralelismo (se ejecutan uno tras otro).

        // ! ✅ TAREA ALUMNO:
        // * Compara este ejercicio con el 7. ¿Cuál es más rápido? ¿Por qué?
        // * Investiga cómo usar wait() y notify() para ordenar sin perder paralelismo
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 11: 2 Threads + Main imprime FIN
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree 2 threads, cada uno de ellos imprimirá
    // ? por pantalla el nombre del thread.
    // ? Cuando acaben, el main imprimirá "FIN".
    // ──────────────────────────────────────────────────────────────────────────

    // * Reutilizamos HiloNombreN del ejercicio 7

    public static void ejercicio11() {
        System.out.println("\n📝 EJERCICIO 11: 2 Threads + Main imprime FIN");
        System.out.println("──────────────────────────────────────────────");

        HiloNombreN hilo1 = new HiloNombreN("Thread-Uno");   // 🧵 Primer hilo
        HiloNombreN hilo2 = new HiloNombreN("Thread-Dos");   // 🧵 Segundo hilo

        System.out.println("🚀 [Main] Lanzando los 2 threads...");

        hilo1.start();  // ▶️ Arrancamos hilo 1
        hilo2.start();  // ▶️ Arrancamos hilo 2

        // * Esperamos a que ambos terminen ANTES de imprimir FIN
        try {
            hilo1.join();  // ⏳ Esperamos a hilo 1
            hilo2.join();  // ⏳ Esperamos a hilo 2
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        // * Solo imprimimos FIN cuando ambos han terminado
        System.out.println("\n🏁 [Main] FIN");

        // ! ✅ TAREA ALUMNO:
        // * ¿Qué pasaría si no usáramos join()? Pruébalo quitando los join.
        // * Añade un tercer hilo y haz que FIN aparezca al final igualmente.
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 12: 2 Threads escriben 's' y 'o'
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree dos threads.
    // ? El primer thread escribirá 20 veces la letra 's' por pantalla.
    // ? El segundo thread escribirá 20 veces la letra 'o' por pantalla.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que imprime una letra N veces
    static class HiloLetra extends Thread {
        private char letra;      // 🔤 La letra a imprimir
        private int veces;       // 🔁 Cuántas veces

        public HiloLetra(char letra, int veces) {
            this.letra = letra;  // 📥 Guarda la letra
            this.veces = veces;  // 📥 Guarda las repeticiones
        }

        @Override
        public void run() {
            for (int i = 0; i < veces; i++) {
                System.out.print(letra);  // 📤 Imprime la letra (sin salto de línea)
            }
        }
    }

    public static void ejercicio12() {
        System.out.println("\n📝 EJERCICIO 12: 2 Threads escriben 's' y 'o'");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("💡 Observa cómo se entrelazan las letras:\n");

        HiloLetra hiloS = new HiloLetra('s', 20);  // 🧵 Thread que imprime 's'
        HiloLetra hiloO = new HiloLetra('o', 20);  // 🧵 Thread que imprime 'o'

        hiloS.start();  // ▶️ Arrancamos hilo s
        hiloO.start();  // ▶️ Arrancamos hilo o

        // * Esperamos a que terminen
        try {
            hiloS.join();
            hiloO.join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        System.out.println("\n\n✅ [Main] Ejercicio completado.");

        // * 📖 EXPLICACIÓN:
        // ──────────────────────────────────────────────
        // ? El resultado será algo como: ssssoooosssooosssooo...
        // ? Las letras se ENTRELAZAN porque ambos hilos se ejecutan concurrentemente.
        // ? El orden exacto depende del planificador del SO y cambia en cada ejecución.

        // ! ✅ TAREA ALUMNO:
        // * Ejecuta varias veces y observa si el patrón cambia
        // * Añade Thread.sleep(10) en el bucle y observa la diferencia
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 13: 2 Threads escriben 's' y HOLA
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa cuyo main cree dos threads.
    // ? El primer thread escribirá 20 veces la letra 's' por pantalla.
    // ? El segundo thread escribirá 20 veces la palabra "HOLA" por pantalla.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que imprime un texto N veces
    static class HiloTexto extends Thread {
        private String texto;    // 📝 El texto a imprimir
        private int veces;       // 🔁 Cuántas veces

        public HiloTexto(String texto, int veces) {
            this.texto = texto;  // 📥 Guarda el texto
            this.veces = veces;  // 📥 Guarda las repeticiones
        }

        @Override
        public void run() {
            for (int i = 0; i < veces; i++) {
                System.out.print(texto);  // 📤 Imprime el texto
            }
        }
    }

    public static void ejercicio13() {
        System.out.println("\n📝 EJERCICIO 13: 2 Threads escriben 's' y HOLA");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("💡 Observa cómo se entrelazan:\n");

        HiloTexto hiloS = new HiloTexto("s", 20);       // 🧵 Thread que imprime 's'
        HiloTexto hiloHola = new HiloTexto("HOLA", 20); // 🧵 Thread que imprime 'HOLA'

        hiloS.start();     // ▶️ Arrancamos hilo s
        hiloHola.start();  // ▶️ Arrancamos hilo HOLA

        // * Esperamos a que terminen
        try {
            hiloS.join();
            hiloHola.join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        System.out.println("\n\n✅ [Main] Ejercicio completado.");

        // * 📖 EXPLICACIÓN:
        // ──────────────────────────────────────────────
        // ? El resultado será algo como: sssHOLAHOLAsssHOLAsss...
        // ? La palabra HOLA puede quedar "cortada" entre las 's'.
        // ? Esto muestra claramente la concurrencia en acción.

        // ! ✅ TAREA ALUMNO:
        // * Cambia "HOLA" por tu nombre y observa el resultado
        // * ¿Cómo harías para que "HOLA" nunca se corte? (pista: synchronized)
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 14: Variable global SUMATOTAL
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa con una variable global SUMATOTAL.
    // ? El main creará dos threads que hagan 50 incrementos cada uno a esta variable.
    // ? Cuando hayan terminado, el main escribirá por pantalla el resultado.
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: Race Condition (Condición de Carrera)
    // ──────────────────────────────────────────────
    // ? Cuando dos hilos incrementan la misma variable sin sincronización,
    // ? pueden ocurrir PÉRDIDAS de incrementos.
    // ? Ejemplo: hilo1 lee 5, hilo2 lee 5, ambos escriben 6 → ¡perdimos un incremento!
    // ? Resultado esperado: 100 (50+50). Resultado real: probablemente MENOS de 100.

    // * Clase interna: hilo que incrementa SUMATOTAL
    static class HiloSumador extends Thread {
        private int incrementos;  // 🔢 Número de incrementos a hacer

        public HiloSumador(int incrementos) {
            this.incrementos = incrementos;  // 📥 Guarda el número
        }

        @Override
        public void run() {
            for (int i = 0; i < incrementos; i++) {
                SUMATOTAL++;  // ➕ Incrementamos la variable global
                // ! ⚠️ Esta operación NO es atómica: leer, sumar, escribir
            }
            System.out.println("🧵 [" + getName() + "] He hecho " + incrementos + " incrementos.");
        }
    }

    public static void ejercicio14() {
        System.out.println("\n📝 EJERCICIO 14: Variable global SUMATOTAL");
        System.out.println("──────────────────────────────────────────────");

        // * Reiniciamos la variable global
        SUMATOTAL = 0;  // 🔄 Ponemos a 0 antes de empezar

        System.out.println("📦 SUMATOTAL inicial: " + SUMATOTAL);
        System.out.println("📊 Esperado: 100 (50 incrementos x 2 hilos)");

        HiloSumador hilo1 = new HiloSumador(50);  // 🧵 Hilo que hace 50 incrementos
        HiloSumador hilo2 = new HiloSumador(50);  // 🧵 Hilo que hace 50 incrementos

        hilo1.setName("Sumador-1");
        hilo2.setName("Sumador-2");

        System.out.println("\n🚀 [Main] Lanzando los 2 hilos sumadores...");

        hilo1.start();  // ▶️ Arrancamos hilo 1
        hilo2.start();  // ▶️ Arrancamos hilo 2

        // * Esperamos a que terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        // * Mostramos el resultado
        System.out.println("\n📦 SUMATOTAL final: " + SUMATOTAL);

        if (SUMATOTAL == 100) {
            System.out.println("✅ Resultado CORRECTO (tuviste suerte, puede variar)");
        } else {
            System.out.println("❌ Resultado INCORRECTO - ¡Race Condition detectada!");
            System.out.println("   Se esperaban 100, pero obtuvimos " + SUMATOTAL);
        }

        // * 📖 EXPLICACIÓN:
        // ──────────────────────────────────────────────
        // ? El resultado puede ser MENOR que 100 debido a la Race Condition.
        // ? La operación SUMATOTAL++ no es atómica (leer-sumar-escribir).
        // ? Solución: usar synchronized o AtomicInteger.

        // ! ✅ TAREA ALUMNO:
        // * Ejecuta varias veces. ¿Siempre sale 100?
        // * Aumenta los incrementos a 10000 y observa más errores
        // * Investiga cómo usar synchronized para arreglarlo
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 15: Cuadrados con paralelismo
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Haz un programa que, dado un vector de enteros con los números del 100 al 150
    // ? (incluidos), calcule el cuadrado de todos sus números y lo imprima por pantalla.
    // ? Utiliza paralelismo.
    // ──────────────────────────────────────────────────────────────────────────

    // * Clase interna: hilo que calcula cuadrados de una porción del vector
    static class HiloCuadrados extends Thread {
        private int[] vector;     // 📦 Vector original
        private int[] resultado;  // 📦 Vector de resultados
        private int inicio;       // 🏁 Índice de inicio
        private int fin;          // 🏁 Índice de fin

        public HiloCuadrados(int[] vector, int[] resultado, int inicio, int fin) {
            this.vector = vector;
            this.resultado = resultado;
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public void run() {
            for (int i = inicio; i < fin; i++) {
                resultado[i] = vector[i] * vector[i];  // 📐 Calculamos el cuadrado
            }
        }
    }

    public static void ejercicio15() {
        System.out.println("\n📝 EJERCICIO 15: Cuadrados con paralelismo");
        System.out.println("──────────────────────────────────────────────");

        // * Creamos el vector del 100 al 150 (51 elementos)
        int[] vector = new int[51];       // 📦 Vector original
        int[] resultado = new int[51];    // 📦 Vector de resultados

        for (int i = 0; i < vector.length; i++) {
            vector[i] = 100 + i;  // 📥 Llenamos con 100, 101, 102... 150
        }

        System.out.println("📦 Vector original: " + Arrays.toString(vector));

        // * Dividimos el trabajo en 2 hilos (puedes usar más)
        int mitad = vector.length / 2;  // 📐 Punto medio

        HiloCuadrados hilo1 = new HiloCuadrados(vector, resultado, 0, mitad);        // 🧵 Primera mitad
        HiloCuadrados hilo2 = new HiloCuadrados(vector, resultado, mitad, vector.length); // 🧵 Segunda mitad

        hilo1.setName("Cuadrados-1");
        hilo2.setName("Cuadrados-2");

        System.out.println("\n🚀 [Main] Lanzando 2 hilos para calcular cuadrados...");

        hilo1.start();  // ▶️ Arrancamos hilo 1
        hilo2.start();  // ▶️ Arrancamos hilo 2

        // * Esperamos a que terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ Interrupción.");
        }

        // * Mostramos el resultado
        System.out.println("\n📦 Cuadrados calculados:");
        for (int i = 0; i < resultado.length; i++) {
            System.out.println("   " + vector[i] + "² = " + resultado[i]);
        }

        System.out.println("\n✅ [Main] Cálculo paralelo completado.");

        // ! ✅ TAREA ALUMNO:
        // * Divide el trabajo en 4 hilos en vez de 2
        // * Añade un Thread.sleep() en el cálculo y mide si hay diferencia
    }

    // ══════════════════════════════════════════════════════════════════════════
    // * 📝 EJERCICIO 16: Medir tiempos secuencial vs paralelo
    // ══════════════════════════════════════════════════════════════════════════
    // ? ENUNCIADO:
    // ? Se puede utilizar System.currentTimeMillis() para conseguir el tiempo del sistema.
    // ? Con una simple resta es posible calcular el tiempo de ejecución de un programa.
    // ? Mide el tiempo de ejecución del ejercicio anterior en una ejecución secuencial
    // ? y el tiempo de ejecución en la ejecución paralela.
    // ? Si el tamaño del vector es N, ¿a partir de qué orden de magnitud de N
    // ? empiezas a ver alguna diferencia?
    // ──────────────────────────────────────────────────────────────────────────

    // * 📖 TEORÍA: Medir tiempos con System.currentTimeMillis()
    // ──────────────────────────────────────────────
    // ? long inicio = System.currentTimeMillis();  // Marca inicio
    // ? ... código a medir ...
    // ? long fin = System.currentTimeMillis();     // Marca fin
    // ? long duracion = fin - inicio;              // Tiempo en milisegundos

    public static void ejercicio16() {
        System.out.println("\n📝 EJERCICIO 16: Medir tiempos secuencial vs paralelo");
        System.out.println("──────────────────────────────────────────────");

        // * Tamaño del vector para ver diferencias (probamos varios)
        int[] tamanios = {1000, 10000, 100000, 1000000, 10000000};

        System.out.println("💡 Comparando tiempos para diferentes tamaños de N:\n");
        System.out.println("┌──────────────┬────────────────┬────────────────┬───────────┐");
        System.out.println("│      N       │  Secuencial(ms)│  Paralelo(ms)  │  Diferencia│");
        System.out.println("├──────────────┼────────────────┼────────────────┼───────────┤");

        for (int N : tamanios) {
            // * Creamos vectores
            int[] vector = new int[N];
            int[] resultadoSeq = new int[N];
            int[] resultadoPar = new int[N];

            for (int i = 0; i < N; i++) {
                vector[i] = i;
            }

            // ════════════════════════════════════════════════════════════════
            // * EJECUCIÓN SECUENCIAL
            // ════════════════════════════════════════════════════════════════
            long inicioSeq = System.currentTimeMillis();  // ⏱️ Marca inicio

            for (int i = 0; i < N; i++) {
                resultadoSeq[i] = vector[i] * vector[i];  // 📐 Cálculo secuencial
            }

            long finSeq = System.currentTimeMillis();     // ⏱️ Marca fin
            long tiempoSeq = finSeq - inicioSeq;          // 📊 Tiempo total

            // ════════════════════════════════════════════════════════════════
            // * EJECUCIÓN PARALELA (4 hilos)
            // ════════════════════════════════════════════════════════════════
            long inicioPar = System.currentTimeMillis();  // ⏱️ Marca inicio

            int numHilos = 4;
            Thread[] hilos = new Thread[numHilos];
            int tamanioPorHilo = N / numHilos;

            for (int h = 0; h < numHilos; h++) {
                final int inicio = h * tamanioPorHilo;
                final int fin = (h == numHilos - 1) ? N : (h + 1) * tamanioPorHilo;
                final int[] v = vector;
                final int[] r = resultadoPar;

                hilos[h] = new Thread(() -> {
                    for (int i = inicio; i < fin; i++) {
                        r[i] = v[i] * v[i];  // 📐 Cálculo paralelo
                    }
                });
                hilos[h].start();
            }

            // Esperamos a todos los hilos
            for (Thread hilo : hilos) {
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    System.out.println("⚠️ Interrupción.");
                }
            }

            long finPar = System.currentTimeMillis();     // ⏱️ Marca fin
            long tiempoPar = finPar - inicioPar;          // 📊 Tiempo total

            // * Mostramos resultados
            String diferencia = (tiempoSeq - tiempoPar) > 0 ? 
                "Paralelo +" + (tiempoSeq - tiempoPar) + "ms" : 
                "Seq +" + (tiempoPar - tiempoSeq) + "ms";

            System.out.printf("│ %,10d   │      %6d     │      %6d     │ %9s │%n", 
                N, tiempoSeq, tiempoPar, diferencia);
        }

        System.out.println("└──────────────┴────────────────┴────────────────┴───────────┘");

        // * 📖 EXPLICACIÓN:
        // ──────────────────────────────────────────────
        // ? Para tamaños pequeños (N < 10000), el overhead de crear hilos
        // ? hace que la versión paralela sea incluso MÁS LENTA.
        // ? A partir de N ~ 100000 o 1000000, el paralelismo empieza a notarse.
        // ? La diferencia depende del número de núcleos de tu CPU.

        System.out.println("\n📊 CONCLUSIÓN:");
        System.out.println("   - Para N pequeño: la versión secuencial es más rápida");
        System.out.println("   - Para N grande (>100000): el paralelismo empieza a compensar");
        System.out.println("   - El punto de inflexión depende de tu CPU y el tipo de tarea");

        // ! ✅ TAREA ALUMNO:
        // * Ejecuta varias veces y anota los resultados
        // * Prueba con 2, 4 y 8 hilos. ¿Cuál es mejor para tu máquina?
        // * Añade un cálculo más costoso (ej: Math.sqrt()) y compara de nuevo
    }
}
