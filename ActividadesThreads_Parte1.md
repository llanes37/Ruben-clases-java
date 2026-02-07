# Actividades de Threads (Hilos) en Java - Parte 1 (Ejercicios 1-6)

Documento introductorio y **muy completo** para entender y estudiar el archivo `ActividadesThreads_Parte1.java`.

> Nota: el `.java` incluye comentarios con emojis y separadores "de dibujo". En este `.md` priorizo claridad y teoria (ASCII, sin tildes) para evitar problemas de codificacion.

---

## Indice

1. [Objetivo del archivo](#objetivo-del-archivo)
2. [Como compilar y ejecutar](#como-compilar-y-ejecutar)
3. [Teoria base: procesos, hilos, concurrencia](#teoria-base-procesos-hilos-concurrencia)
4. [Crear hilos en Java](#crear-hilos-en-java)
5. [Ciclo de vida y metodos clave](#ciclo-de-vida-y-metodos-clave)
6. [Datos compartidos y race conditions](#datos-compartidos-y-race-conditions)
7. [Estructura del programa (menu + ejercicios)](#estructura-del-programa-menu--ejercicios)
8. [Ejercicio 1 - Suma en un hilo](#ejercicio-1---suma-en-un-hilo)
9. [Ejercicio 2 - Main y thread saludan](#ejercicio-2---main-y-thread-saludan)
10. [Ejercicio 3 - Dos threads imprimen su nombre](#ejercicio-3---dos-threads-imprimen-su-nombre)
11. [Ejercicio 4 - Repetir una letra](#ejercicio-4---repetir-una-letra)
12. [Ejercicio 5 - Vector x2 sin esperar (race condition)](#ejercicio-5---vector-x2-sin-esperar-race-condition)
13. [Ejercicio 6 - Vector x2 con `join()` (sincronizado)](#ejercicio-6---vector-x2-con-join-sincronizado)
14. [Errores tipicos y checklist](#errores-tipicos-y-checklist)
15. [Siguientes pasos (para subir nivel)](#siguientes-pasos-para-subir-nivel)

---

## Objetivo del archivo

`ActividadesThreads_Parte1.java` es un mini-laboratorio con **menu por consola** para ejecutar 6 ejercicios de hilos (threads). Cada ejercicio ensena un concepto concreto:

- Como crear un thread extendiendo `Thread`
- Diferencia entre `start()` y `run()`
- Poner nombre a los hilos y ver el orden de ejecucion
- Ejecutar varios hilos y esperar a que terminen con `join()`
- Entender una **condicion de carrera (race condition)** al compartir un array

---

## Como compilar y ejecutar

El codigo usa `switch` con flechas (`case 1 -> ...`), que requiere un **JDK moderno** (en la practica, **Java 14+**, y perfecto con Java 17/21).

En la carpeta donde esta el archivo:

```bash
javac ActividadesThreads_Parte1.java
java ActividadesThreads_Parte1
```

Si te da error por version, revisa:

```bash
java -version
javac -version
```

---

## Teoria base: procesos, hilos, concurrencia

### Proceso vs hilo

- **Proceso**: un programa en ejecucion, con su memoria y recursos (archivos, sockets, etc.).
- **Hilo (Thread)**: una linea de ejecucion dentro del proceso.

En Java, cuando ejecutas un programa, existe al menos un hilo: el **hilo principal**, que ejecuta `main()`.

### Concurrencia vs paralelismo

- **Concurrencia**: varias tareas avanzan de forma intercalada (aunque sea en 1 solo nucleo).
- **Paralelismo**: varias tareas se ejecutan **a la vez** (en varios nucleos).

Lo importante para estos ejercicios: aunque no haya paralelismo real, el orden de ejecucion entre hilos puede variar y producir resultados diferentes.

### Por que el orden puede cambiar

El sistema operativo y la JVM van alternando CPU entre hilos (planificacion). Cambios pequenos en el tiempo (carga del PC, `sleep()`, etc.) pueden hacer que:

- Un hilo imprima antes que otro.
- El `main` lea datos "a medias" si otro hilo los esta modificando.

Por eso, en concurrencia, una regla de oro es: **si el orden importa, hay que coordinarlo**.

---

## Crear hilos en Java

En Java hay dos formas clasicas:

### 1) Extender `Thread` (lo que usa este archivo)

1. Creas una clase que **extiende** `Thread`
2. Sobrescribes `run()` con el codigo a ejecutar
3. Creas el objeto
4. Llamas a `start()` (no a `run()`)

Ventaja: simple para aprender.

Inconveniente: ya estas gastando la herencia (Java solo permite heredar de una clase).

### 2) Implementar `Runnable` (muy comun en proyectos reales)

Creas una clase que implementa `Runnable` y la pasas a un `Thread`:

```java
Runnable tarea = () -> System.out.println("Hola desde runnable");
Thread hilo = new Thread(tarea, "Hilo-Runnable");
hilo.start();
```

Ventaja: separa "tarea" de "hilo" y es mas flexible.

### En proyectos reales: `ExecutorService`

Cuando empiezas a crear muchos threads manualmente, aparece un problema: **crear threads es caro**, y gestionarlos a mano es dificil. Por eso se usan pools de hilos (`Executors.newFixedThreadPool(...)`) y se envian tareas.

Aqui no lo necesitas todavia, pero es el siguiente paso natural cuando entiendes `Thread`/`Runnable`.

---

## Ciclo de vida y metodos clave

### Estados tipicos de un hilo (resumen)

Un `Thread` puede pasar por estados (simplificado):

- `NEW`: creado pero no iniciado (`new Thread(...)`)
- `RUNNABLE`: listo para ejecutar (o ejecutandose)
- `BLOCKED`: esperando un monitor (por `synchronized`)
- `WAITING`: esperando indefinidamente (ej. `join()` sin tiempo)
- `TIMED_WAITING`: esperando con tiempo (ej. `sleep(ms)` o `join(ms)`)
- `TERMINATED`: termino `run()`

En estos ejercicios veras sobre todo `NEW`, `RUNNABLE`, `WAITING` (por `join`) y `TIMED_WAITING` (por `sleep`).

### `start()` vs `run()`

- `start()` **crea/lanza** un nuevo hilo y hace que ese hilo ejecute `run()` en paralelo.
- `run()` llamado directamente **NO crea un hilo nuevo**: se ejecuta como una llamada normal dentro del hilo actual.

Este es el error #1 al aprender threads.

### `join()`

`join()` hace que **el hilo que llama a `join()` espere** a que termine el otro hilo.

Ejemplo mental:

- Si `main` hace `hilo.start();` y luego `hilo.join();`
- Entonces `main` se para hasta que `hilo` termine su `run()`

Ademas de esperar, `join()` tambien te da una garantia importante de memoria: cuando `join()` termina, los cambios hechos por el hilo ya son visibles para el que espero.

Importante:

- `join()` no "sincroniza datos" en general: solo coordina el momento (espera/fin) y la visibilidad al terminar.
- `join()` NO garantiza el orden entre dos hilos (por ejemplo, no garantiza si imprime antes Alfa o Beta).

### `sleep(ms)`

`Thread.sleep(100)`:

- No duerme al programa; **duerme al hilo actual**
- Se usa para simular trabajo o para ver la intercalacion entre hilos
- Puede lanzar `InterruptedException`, asi que hay que manejarla

`sleep()` es util para:

- Simular trabajo (ver timings)
- Hacer reproducible (mas visible) un problema de concurrencia

Pero **no es** una solucion a una race condition.

### `interrupt()` (muy importante en proyectos reales)

Un hilo se puede interrumpir con `interrupt()`. Muchas operaciones bloqueantes (como `sleep`, `join`, `wait`) reaccionan lanzando `InterruptedException`.

En codigo real, normalmente haces una de estas dos cosas:

- Sales de la tarea (terminas `run()`)
- O restauras el estado de interrupcion: `Thread.currentThread().interrupt()`

Aqui los ejercicios lo manejan con un `catch` e imprimen un mensaje, que esta bien para empezar.

### `getName()` y nombres de hilo

Un hilo tiene un nombre:

- Si no lo pones, Java asigna uno (`Thread-0`, `Thread-1`, ...)
- Puedes asignarlo con `super(nombre)` en el constructor o con `setName(...)`

Nombrar hilos ayuda a depurar y entender el orden de ejecucion.

---

## Datos compartidos y race conditions

### Que significa "compartir datos"

Dos hilos comparten datos cuando acceden al mismo objeto en memoria (heap). En estos ejercicios se comparte, por ejemplo:

- Un `int[] vector` que se pasa por referencia (realmente se pasa una copia de la referencia).

### Condicion de carrera (race condition)

Una **race condition** pasa cuando:

- Dos (o mas) hilos acceden al mismo dato
- Al menos uno lo modifica
- No hay coordinacion (sincronizacion/espera)
- El resultado depende del orden exacto (no determinista)

Consecuencia: resultados **impredecibles**.

> Importante: impredecible no significa aleatorio; significa que depende de planificacion del sistema operativo, carga de CPU, tiempos, etc.

### Visibilidad y atomicidad (dos palabras clave)

Cuando hay varios hilos, suelen aparecer dos tipos de problemas:

- **Visibilidad**: un hilo cambia un valor, pero otro hilo no lo "ve" cuando esperas (por caches/reordenamientos). `join()` y `synchronized` ayudan a dar garantias de visibilidad.
- **Atomicidad**: operaciones que parecen "una sola cosa" en realidad son varios pasos. Por ejemplo, `contador++` no es atomico (leer, sumar, escribir). Los arrays tambien son mutables y pueden quedar en estados intermedios si se leen mientras se escriben.

En estos ejercicios, el problema principal es que el `main` lee un `int[]` mientras otro hilo lo esta modificando.

### Soluciones tipicas (muy por encima)

- Coordinacion temporal: `join()` (como en el ejercicio 6)
- Exclusion mutua: `synchronized` / locks (solo 1 hilo entra a la zona critica)
- Estructuras concurrentes o atomicas: `AtomicInteger`, `ConcurrentHashMap`, `BlockingQueue`, etc.

---

## Estructura del programa (menu + ejercicios)

El `main()`:

1. Crea un `Scanner`
2. Muestra un menu (1-6) en bucle
3. Llama a `ejercicioN()` segun la opcion
4. Cierra el `Scanner` al final

Detalles utiles del `main`:

- Tras leer la opcion con `nextInt()`, el codigo hace `sc.nextLine()` para limpiar el salto de linea pendiente. Si no lo haces, la siguiente lectura con `nextLine()` (como en el ejercicio 4) puede salir vacia.
- El `switch` usa `case N -> ...`, que es sintaxis moderna (por eso se recomienda Java 14+).

El archivo usa varias **clases internas `static`** para definir hilos especificos de cada ejercicio:

- `HiloSuma`
- `HiloSaludo`
- `HiloNombre`
- `HiloRepiteLetra`
- `HiloMultiplicaVector`
- `HiloMultiplicaVectorV2`

La idea didactica: cada ejercicio es autocontenido.

---

## Ejercicio 1 - Suma en un hilo

### Enunciado (resumen)

Ejecutar en un thread la suma de `300 + 500`.

### Que hace el codigo

- Crea la clase `HiloSuma extends Thread` con dos atributos (`valorA`, `valorB`).
- En `run()` calcula el resultado y lo imprime.
- En `ejercicio1()`:
  - Crea el hilo con 300 y 500
  - Llama a `start()`
  - Luego hace `join()` para esperar
  - Finalmente confirma desde `main` que termino

### Que aprendes aqui

- Un hilo ejecuta lo que haya dentro de `run()`
- El `main` puede seguir, pero con `join()` decides esperar
- Como pasar datos al hilo mediante el constructor

### Ideas de practica

- Cambia los valores.
- Modifica `HiloSuma` para que haga una resta.

### Salida tipica (ejemplo)

No es exacta al 100% (puede variar el nombre del hilo), pero veras algo como:

```
[Main] Iniciando el hilo de suma...
[Thread-0] La suma de 300 + 500 = 800
[Main] El hilo de suma ha finalizado.
```

---

## Ejercicio 2 - Main y thread saludan

### Enunciado (resumen)

`main` imprime "Hola, soy el main" y un hilo imprime "Hola, soy " + el nombre del hilo.

### Que hace el codigo

`HiloSaludo extends Thread`:

- Recibe un nombre en el constructor y llama a `super(nombre)` para fijarlo.
- En `run()` imprime el saludo con `getName()`.

En `ejercicio2()`:

- Imprime el saludo del main
- Crea y lanza el hilo
- Usa `join()` para esperar y luego termina el ejercicio

### Que aprendes aqui

- Asignar nombre a un hilo
- Ver el nombre desde dentro del hilo (`getName()`)
- Que sin `join()` el orden puede variar

### Prueba recomendada

Quita el `join()` y ejecuta 5-10 veces:

- A veces primero aparece el mensaje del `main`.
- A veces el hilo imprime antes (o el orden se mezcla).

Tambien prueba a crear el hilo sin nombre (sin `super(nombre)`) para ver el nombre automatico (`Thread-0`, `Thread-1`, ...).

---

## Ejercicio 3 - Dos threads imprimen su nombre

### Enunciado (resumen)

`main` crea 2 threads y cada uno imprime su nombre.

### Que hace el codigo

`HiloNombre extends Thread`:

- Constructor con nombre (`super(nombre)`)
- `run()` imprime su nombre

En `ejercicio3()`:

- Se crean dos hilos (`Hilo-Alfa` y `Hilo-Beta`)
- Se lanzan con `start()`
- Se espera a ambos con `join()`

### Que aprendes aqui

- Lanzar varios hilos
- Esperar a varios hilos
- El orden de impresion entre hilos **no esta garantizado**

### Idea clave

Aunque esperes con `join()`, `join()` no decide si imprime antes `Hilo-Alfa` o `Hilo-Beta`. Solo garantiza que el `main` no continua hasta que ambos terminan.

---

## Ejercicio 4 - Repetir una letra

### Enunciado (resumen)

El usuario escribe una letra; se crea un thread que imprime esa letra 4 veces.

### Que hace el codigo

En `ejercicio4(Scanner sc)`:

1. Pide una entrada al usuario (`nextLine()`)
2. Valida que no este vacia
3. Toma el primer caracter (`charAt(0)`)
4. Crea `HiloRepiteLetra(letra, 4)`
5. Lanza y espera con `join()`

`HiloRepiteLetra extends Thread`:

- Guarda `letra` y `repeticiones`
- En `run()` recorre un `for` e imprime cada repeticion

### Que aprendes aqui

- Pasar parametros desde consola a un hilo
- Hacer trabajo repetitivo dentro de `run()`

### Variaciones para practicar

- Pide tambien el numero de repeticiones por consola.
- Anade `Thread.sleep(100)` dentro del bucle para ver el efecto.
- Crea 2 hilos: uno imprime la letra en mayuscula y otro en minuscula.

---

## Ejercicio 5 - Vector x2 sin esperar (race condition)

### Enunciado (resumen)

`main` crea un vector de 10 enteros y lanza un thread que multiplica por 2 cada posicion. **Sin esperar**, el `main` imprime el vector.

### Que hace el codigo

`HiloMultiplicaVector extends Thread`:

- Recorre el array
- Multiplica `vector[i] *= 2`
- Usa `Thread.sleep(50)` para simular trabajo y ver el intercalado

En `ejercicio5()`:

1. Crea `vector = [1..10]`
2. Lanza el hilo
3. Imprime inmediatamente el vector (sin `join()`)
4. Luego si hace `join()` e imprime el vector final

### Por que ocurre el "resultado raro"

Porque el array es un recurso compartido:

- El hilo modifica posiciones
- El `main` lee a la vez

En el momento de imprimir, puedes ver una mezcla de valores originales y valores ya multiplicados.

Ejemplo posible:

```
Vector AHORA: [2, 4, 6, 4, 5, 6, 7, 8, 9, 10]
```

### Que aprendes aqui

- Que es una condicion de carrera
- Que el resultado es impredecible sin coordinacion

### Linea de tiempo (intuicion)

Piensa que pasa esto:

1. `main` crea el vector `[1..10]`
2. `main` llama a `hilo.start()`
3. El hilo empieza a modificar: primero `vector[0]`, luego `vector[1]`, etc.
4. Mientras tanto, **`main` imprime** el vector sin esperar

Como el hilo va "por delante" en algunas posiciones y "por detras" en otras, el `main` ve una foto del vector en medio del proceso.

El `Thread.sleep(50)` dentro del hilo existe para que sea mas facil ver el efecto: ralentiza el hilo y aumenta las probabilidades de que el `main` imprima cuando el vector aun esta a medias.

### Pregunta tipica

Por que, aunque haya race condition, al final siempre se ve bien?

Porque despues de imprimir "AHORA", el codigo hace `join()` y luego imprime "FINAL". Esa segunda impresion ya ocurre cuando el hilo termino, y por eso el vector final queda completamente multiplicado.

---

## Ejercicio 6 - Vector x2 con `join()` (sincronizado)

### Enunciado (resumen)

Igual que el ejercicio 5, pero ahora el `main` **espera** a que el thread termine y despues imprime el vector.

### Que hace el codigo

En `ejercicio6()`:

1. Crea el vector `[1..10]`
2. Lanza el hilo
3. Hace `hilo.join()`
4. Imprime el vector resultante
5. Verifica si el resultado es correcto

### Por que ahora siempre sale bien

Porque `join()`:

- Evita que `main` lea el vector a mitad de modificacion
- Garantiza que el hilo ha terminado antes de imprimir

Resultado esperado:

```
[2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
```

### Comparacion directa con el ejercicio 5

- Ejercicio 5: `main` imprime el vector mientras el hilo esta trabajando -> mezcla/impredecible.
- Ejercicio 6: `main` hace `join()` antes de imprimir -> el hilo ya termino -> resultado estable.

En examen/teoria, esta idea suele resumirse asi:

- Sin `join()` (o sin sincronizacion): no hay orden garantizado, aparecen estados intermedios.
- Con `join()`: impones un orden (primero trabaja el hilo, luego lee el `main`).

---

## Errores tipicos y checklist

- Llamar a `run()` en vez de `start()`
- Olvidar `join()` cuando necesitas esperar
- Compartir datos mutables sin coordinacion (race condition)
- Usar `sleep()` como si fuera una solucion (solo cambia tiempos)
- Mezclar `nextInt()` y `nextLine()` sin limpiar buffer (por eso el `main` hace `sc.nextLine()` tras leer la opcion)

Mini-checklist para revisar un ejercicio:

- Quien escribe y quien lee el dato?
- Hay algun dato compartido mutable (array/objeto) entre hilos?
- Si el orden importa, donde pones el `join()`?

---

## Siguientes pasos (para subir nivel)

1. Rehacer los ejercicios usando `Runnable` y lambdas.
2. Aprender sincronizacion real: `synchronized`, locks, `volatile`, atomicos.
3. Usar `ExecutorService` para gestionar tareas.

Preguntas rapidas (para autoevaluarte):

- Que diferencia hay entre llamar `run()` y `start()`?
- Que garantiza `join()` y que NO garantiza?
- Por que el ejercicio 5 puede imprimir un vector mezclado?
- Por que `sleep()` no es una forma de sincronizacion?
