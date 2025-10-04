# 📘 UT15 - Modularidad y Paquetes en Java

> 📆 Unidad 15 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 En esta unidad aprenderás a organizar mejor tus programas dividiéndolos en módulos y paquetes.

---

## 🌟 Objetivos de la Unidad

- Comprender qué es la **modularidad** y por qué es importante.
- Aprender a usar **paquetes** (`package`) para organizar el código.
- Importar clases de otros archivos y paquetes.
- Crear una estructura de carpetas organizada en tus proyectos.
- Entender la relación entre modularidad y reutilización de código.

---

## 🧠 Teoría: Modularidad

La **modularidad** es una técnica de programación que consiste en dividir un programa en partes más pequeñas y manejables llamadas **módulos**. Cada módulo realiza una función específica.

### ✨ Ventajas de la Modularidad
- Código más limpio y mantenible.
- Permite trabajar en equipo dividiendo tareas.
- Facilita la reutilización de clases o funciones.
- Mejora la depuración y pruebas.

---

## 📂 Paquetes en Java (`package`)

Un **paquete** es un conjunto de clases relacionadas agrupadas en una carpeta. Se define al principio de un archivo Java usando la palabra clave `package`.

### ✅ Ejemplo:

```java
package utilidades;

public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```

> Este archivo debería estar ubicado en la carpeta `utilidades/Calculadora.java`

---

## 📚 Importar clases desde otro paquete

Para usar una clase definida en otro paquete, se utiliza `import`.

```java
import utilidades.Calculadora;

public class Main {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println("Resultado: " + calc.sumar(4, 6));
    }
}
```

> Ambas clases deben compilarse desde el directorio principal y respetar la estructura de carpetas.

---

## ⚡ Estructura de Carpetas Recomendadas

```
CursoJavaCompleto/
│
├── main/
│   ├── Main.java
│
├── utilidades/
│   ├── Calculadora.java
│   ├── ConversorTemperatura.java
```

- Usa nombres significativos para los paquetes.
- Cada carpeta representa un paquete.
- Usa `package` al inicio de cada archivo Java.

---

## 👩‍💼 Ejemplo Completo

### Archivo: `modelos/Persona.java`
```java
package modelos;

public class Persona {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}
```

### Archivo: `main/Main.java`
```java
import modelos.Persona;

public class Main {
    public static void main(String[] args) {
        Persona p = new Persona("Laura");
        p.saludar();
    }
}
```

> Al compilar y ejecutar, asegúrate de tener el classpath correcto o usar un IDE que lo gestione automáticamente.

---

## 🔮 Buenas prácticas con paquetes
- Evita nombres ambiguos o genéricos como `clase1`, `codigo`, etc.
- Agrupa clases similares en el mismo paquete.
- No pongas todas las clases en un solo archivo.
- Usa IDEs como VS Code, IntelliJ o NetBeans para que gestionen la estructura correctamente.

---

## 📊 Tareas para el Alumno

1. Crea un paquete llamado `vehiculos` y una clase `Coche` dentro.
2. Define atributos y un método `mostrarDatos()`.
3. Desde `Main.java` (fuera del paquete), crea un objeto `Coche` e imprímelo.
4. Crea otro paquete llamado `utilidades` con funciones estáticas para sumar, restar, etc.

---

## 📌 Conclusión

Dividir tu proyecto en paquetes mejora la organización, hace tu código más limpio y profesional, y permite escalar a proyectos grandes. La modularidad es una habilidad esencial en el desarrollo profesional en Java.

> 📁 Sigue con la **UT16 - Proyecto Final** para aplicar todo lo aprendido hasta ahora.

