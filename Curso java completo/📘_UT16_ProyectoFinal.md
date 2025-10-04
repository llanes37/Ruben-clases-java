# 📘 UT16 - Proyecto Final Java

> 📆 Unidad 16 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🧩 Proyecto integrador que engloba todos los conocimientos del curso.

---

## 🌟 Objetivo de la unidad

Crear una aplicación Java completa y funcional que incluya:

- Variables y estructuras de control.
- Funciones, clases, objetos y encapsulación.
- Lectura y escritura de ficheros.
- Colecciones y estructuras avanzadas (List, Map, Set).
- Modularidad y paquetes.

---

## 🧠 Contexto del proyecto

Imagina que eres el responsable de un **sistema de gestión de alumnos en una academia militar**. El sistema deberá:

- Registrar nuevos alumnos.
- Mostrar todos los alumnos.
- Modificar sus datos.
- Eliminar alumnos.
- Exportar la información a un fichero.

---

## 🧰 Estructura recomendada del proyecto

### 📁 Paquetes:

- `model`: Clases como `Alumno`, `Curso`, etc.
- `service`: Lógica de negocio, validaciones, búsquedas...
- `util`: Utilidades generales como lectura de ficheros, validadores, etc.
- `app`: Contiene la clase `Main.java` con el menú principal.

---

## 🏗️ Ejemplo de Clase `Alumno`

```java
package model;

public class Alumno {
    private String nombre;
    private int edad;
    private String curso;

    public Alumno(String nombre, int edad, String curso) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCurso() { return curso; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public String toString() {
        return nombre + " - " + edad + " años - Curso: " + curso;
    }
}
```

---

## 🧪 Funcionalidades a implementar en el menú

```java
1. Registrar alumno
2. Listar alumnos
3. Buscar alumno por nombre
4. Modificar datos
5. Eliminar alumno
6. Exportar a fichero
0. Salir
```

---

## 💻 Recomendaciones técnicas

- Usa `Scanner` para entradas por consola.
- Usa `ArrayList` o `HashMap` para guardar alumnos.
- Usa `FileWriter` y `BufferedReader` para trabajar con ficheros.
- Usa métodos estáticos en `Main.java` para el menú.
- Opcional: crea submenús o validaciones de entrada.

---

## 🎯 Ejercicio final (Descripción)

> Diseña e implementa una aplicación llamada `AcademiaApp` siguiendo el modelo explicado, aplicando todos los conocimientos del curso.

- Comienza creando los paquetes y clases base.
- Implementa un menú que permita realizar todas las operaciones.
- Asegúrate de que el código sea claro, modular y bien comentado.
- Exporta la información a un fichero al cerrar el programa.

---

## 📝 Reflexión final

Este proyecto no solo sirve para demostrar todo lo aprendido, sino que podría ser la base para futuros sistemas más complejos. Es tu momento de **crear algo real** y **práctico** usando Java.

---

🧩 Archivos relacionados: `Alumno.java`, `Main.java`, `ServicioAlumno.java`, `Utilidades.java`

