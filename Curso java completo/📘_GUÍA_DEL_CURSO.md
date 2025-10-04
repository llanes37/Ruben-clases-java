
# 📘 GUÍA DEL CURSO DE PROGRAMACIÓN EN JAVA
> Autor: Joaquín Rodríguez Llanes  
> Año: 2025  
> Versión del curso: 1.0  
> Estado: ✅ Completado hasta la UT16

---

## 🧠 OBJETIVO DEL CURSO

Aprender programación en Java **desde cero** hasta tener un nivel suficiente para:
- Crear estructuras de control.
- Usar funciones, clases, ficheros y excepciones.
- Manejar colecciones, enums, paquetes y fechas.
- Desarrollar un **proyecto final completo con menú** y funcionalidades reales.

---

## 🧰 REQUISITOS Y PREPARACIÓN DEL ENTORNO

### ✅ Necesitas tener instalado:
- [x] **JDK 17 o superior** (Recomendado: OpenJDK 23)
- [x] **Visual Studio Code** o cualquier otro editor (IntelliJ, NetBeans, Eclipse)
- [x] **Extensión Better Comments** para ver los colores en comentarios
- [x] (Opcional) **Code Runner** para ejecutar el código rápido y fácilmente
- [x] (Opcional) **Extension Pack for Java** si vas a depurar o compilar desde VS Code

---

## 🗂️ ESTRUCTURA DEL CURSO

El curso está dividido por unidades, cada una con su archivo `.java` y comentarios didácticos:

| Unidad | Archivo                            | Contenido principal                                              |
|--------|-------------------------------------|------------------------------------------------------------------|
| UT0    | `UT0_IntroduccionJava.java`         | Instalación, entorno, primeros pasos                             |
| UT1    | `UT1_VariablesTiposOperadores.java` | Variables, tipos y operadores                                    |
| UT2    | `UT2_ControlFlujo.java`             | Estructuras condicionales `if`, `switch`                         |
| UT3    | `UT3_Bucles.java`                   | Bucles `for`, `while`, `do while`                                |
| UT4    | `UT4_Funciones.java`                | Funciones, parámetros, retorno, recursividad                     |
| UT5    | `UT5_ClasesObjetos.java`            | Programación orientada a objetos: clases, atributos, métodos     |
| UT6    | `UT6_ExcepcionesManejoErrores.java` | Manejo de errores, try-catch-finally y excepciones personalizadas|
| UT7    | `UT7_CadenasTexto.java`             | Operaciones con Strings                                          |
| UT8    | `UT8_ArraysYStrings.java`           | Arrays, arrays multidimensionales y más sobre cadenas           |
| UT9    | `UT9_StringsAvanzados.java`         | `.split()`, `.replaceAll()`, regex básicos                      |
| UT10   | `UT10_MathYFechas.java`             | Clase Math, fechas con LocalDate y Calendar                     |
| UT11   | `UT11_ExpresionesRegulares.java`    | Regex, validación de datos con patrones                         |
| UT12   | `UT12_Ficheros.java`                | Leer, escribir y manejar ficheros                               |
| UT13   | `UT13_ColeccionesAvanzadas.java`    | Listas, mapas, sets y colecciones complejas                     |
| UT14   | `UT14_EnumYConstantes.java`         | Enumeraciones y uso de constantes                               |
| UT15   | `UT15_ModularidadYPaquetes.java`    | Uso de paquetes, modularidad y clases externas                  |
| UT16   | `UT16_ProyectoFinal.java`           | Proyecto completo con menús, clases, lógica y persistencia      |

---

## 🚀 ¿CÓMO EJECUTAR LOS ARCHIVOS?

### OPCIÓN 1: ✅ Recomendado - **Code Runner**
> Ideal para clases, ya que ejecuta al instante.

1. Instala la extensión `Code Runner`.
2. En configuración, **marca la opción**:  
   `code-runner.runInTerminal ✅` para poder escribir en consola.
3. Abre cualquier archivo `.java` y haz clic en ▶️ “Run Code”.

💡 **Si usas Scanner**, asegúrate de que **se ejecuta en terminal integrada** para poder introducir datos.

---

### OPCIÓN 2: Terminal manual (más profesional)
```bash
javac NombreArchivo.java   # Compila
java NombreArchivo         # Ejecuta
```

Ejemplo:
```bash
javac UT1_VariablesTiposOperadores.java
java UT1_VariablesTiposOperadores
```

---

## 📌 RECOMENDACIONES PARA EL CURSO

- Lee los comentarios en **verde, rojo, amarillo o azul** gracias a Better Comments:
  - `// * Verde: teoría explicativa`
  - `// ! Rojo: tareas del alumno`
  - `// ? Amarillo: ejemplos o preguntas`
  - `// 🔵 Azul: secciones destacadas`

- No borres los comentarios didácticos, son parte del aprendizaje.

- Completa las tareas marcadas con `// ! ✅ TAREA PARA EL ALUMNO:`  
  (A veces al final de cada unidad).

---

## 🎓 PROYECTO FINAL (UT16)

Este proyecto integra:
- Menús, lógica condicional, clases, ficheros y colecciones.
- Simula un sistema real: **gestor de alumnos**.
- Puedes ampliarlo para gestionar una base de datos, añadir login o interfaz gráfica.

---

## 🤔 DUDAS FRECUENTES

### ❓ ¿Por qué me da error de `ClassNotFoundException`?
Probablemente estás intentando ejecutar el archivo sin compilarlo primero, o sin usar el nombre correcto de la clase.

### ❓ ¿No se ve el Scanner para escribir?
Activa la ejecución en terminal para `Code Runner`:  
`code-runner.runInTerminal: true`

---



> 🚀 ¡Ánimo! Este curso está diseñado para que puedas aprender de forma clara, práctica y progresiva.  
> No te rindas, ¡lo dominarás paso a paso!
