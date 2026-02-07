# Resumen Ampliado: `UT1_HiloSimple.java`

## 1. Objetivo de la practica
Este programa introduce la concurrencia basica en Java usando la clase `Thread`.
La idea principal es entender como:

- crear hilos,
- lanzarlos en paralelo,
- pausar su ejecucion,
- esperar su finalizacion desde `main`,
- y gestionar interrupciones de forma segura.

## 2. Estructura general del archivo
El archivo contiene:

- clase principal `UT1_HiloSimple`,
- clase interna `HiloTrabajador extends Thread`,
- constantes de colores ANSI para mejorar la salida visual,
- metodos auxiliares para dividir responsabilidades.

Separar en metodos mejora la legibilidad y facilita pruebas o ampliaciones.

## 3. Teoria clave de hilos (UT1)

### `Thread`
Un hilo es una unidad de ejecucion ligera dentro de un proceso.
Varios hilos pueden ejecutarse "a la vez" (concurrencia), compartiendo memoria del proceso.

### `start()` vs `run()`
- `start()` crea un hilo nuevo y entonces ejecuta `run()` en ese hilo.
- `run()` llamado directamente no crea hilo nuevo; se ejecuta en el hilo actual.

### `sleep(ms)`
Pausa temporal del hilo actual durante una cantidad de milisegundos.
Puede lanzar `InterruptedException`.

### `join()`
Permite que un hilo espere a que otro termine.
En este ejercicio, `main` hace `join()` para cerrar el programa de forma ordenada.

### `InterruptedException`
Aparece cuando un hilo en espera (`sleep`, `join`, etc.) recibe una interrupcion.
Buenas practicas:

- registrar el evento,
- finalizar de forma controlada o restaurar el estado de interrupcion con `Thread.currentThread().interrupt()`.

## 4. Ciclo de vida simplificado de un hilo
1. `NEW`: creado con `new Thread(...)`.
2. `RUNNABLE`: despues de `start()`, listo para ejecutarse.
3. `TIMED_WAITING`: al usar `sleep(ms)`.
4. `TERMINATED`: cuando `run()` termina.

## 5. Clase `HiloTrabajador`
Cada objeto `HiloTrabajador` tiene:

- `nombre`: identifica el hilo en consola (`Hilo-A`, `Hilo-B`),
- `pausams`: tiempo de pausa entre pasos.

En `run()`:
1. Recorre 5 pasos con un bucle.
2. Muestra avance por consola.
3. Duerme el hilo con `Thread.sleep(pausams)`.
4. Si hay interrupcion, informa y termina.
5. Si completa todos los pasos, muestra mensaje de exito.

## 6. Flujo completo del `main`
1. Llama a `mostrarCabecera()`.
2. Ejecuta `ejecutarEjemploBasico()`.
3. Llama a `mostrarResumenTeoria()`.

Dentro de `ejecutarEjemploBasico()`:
1. Crea dos hilos (`Hilo-A` y `Hilo-B`) con pausas distintas.
2. Inicia ambos con `start()`.
3. Mientras corren, `main` realiza 3 tareas locales.
4. Al final, `main` espera a ambos con `join()`.
5. Muestra mensaje final de cierre correcto.

## 7. Comportamiento esperado en consola
El orden exacto de lineas puede cambiar entre ejecuciones, porque depende del planificador del sistema operativo.
Eso es normal en concurrencia.

Se espera ver:
- pasos mezclados de `Hilo-A` y `Hilo-B`,
- tareas del `main` intercaladas,
- cierre final despues del `join()`.

## 8. Uso de colores ANSI
Se definen constantes como:
- `ROJO`,
- `VERDE`,
- `AMARILLO`,
- `AZUL`,
- `CIAN`,
- `RESET`.

Sirven para diferenciar visualmente mensajes de:
- progreso,
- estado del hilo principal,
- errores/interrupciones,
- finalizacion correcta.

Nota: si una consola no soporta ANSI, el programa sigue funcionando, pero pueden verse codigos de escape.

## 9. Metodos del programa
- `main(String[] args)`: orquesta toda la ejecucion.
- `ejecutarEjemploBasico()`: crea, lanza y sincroniza hilos.
- `mostrarCabecera()`: imprime titulo.
- `mostrarResumenTeoria()`: repaso final y actividades.
- `dormir(int ms)`: pausa segura para el hilo actual.

## 10. Buenas practicas que aplica
- Nombres claros para hilos (`Hilo-A`, `Hilo-B`).
- Reutilizacion de logica con metodo auxiliar `dormir`.
- Control explicito de excepciones.
- Restaurar interrupcion en `main` cuando corresponde.
- Estructura modular para mantener el codigo limpio.

## 11. Errores comunes que evita este ejercicio
- Llamar `run()` en lugar de `start()`.
- No usar `join()` y cerrar `main` antes de tiempo.
- Ignorar interrupciones sin gestionarlas.
- Mezclar toda la logica en un solo metodo largo.

## 12. Actividades de ampliacion sugeridas
1. Crear `Hilo-C` con pausa distinta.
2. Usar `join(long millis)` para practicar esperas con timeout.
3. Interrumpir un hilo desde `main` tras 2 segundos.
4. Cambiar el numero de pasos a 10 y comparar resultados.
5. Reescribir el ejemplo usando `Runnable` en lugar de extender `Thread`.
6. Evolucionar a `ExecutorService` para introducir gestion moderna de tareas.

## 13. Mini conclusion
`UT1_HiloSimple.java` es una base solida para empezar concurrencia en Java.
Combina teoria y practica con un ejemplo facil de seguir:

- ejecucion paralela real,
- sincronizacion con `join()`,
- pausas con `sleep()`,
- control correcto de interrupciones.
