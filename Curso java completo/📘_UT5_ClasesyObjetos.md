# 🏗️ UT5 - Clases y Objetos

> 📆 Unidad 5 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprenderás los fundamentos de la programación orientada a objetos (POO)

---

## 🌟 ¿Qué aprenderás en esta unidad?

- Comprender qué es una clase y qué es un objeto.
- Definir atributos (propiedades) y métodos (funciones dentro de una clase).
- Crear instancias de una clase (objetos).
- Acceder y modificar datos usando métodos.
- Aplicar la encapsulación (uso de `private`, `public`, `getters` y `setters`).

---

## 🧠 Teoría detallada

### 📌 ¿Qué es la Programación Orientada a Objetos?

La POO permite modelar el mundo real usando objetos que tienen **propiedades** (atributos) y **comportamientos** (métodos).  
Java es un lenguaje orientado a objetos y **todo gira en torno a clases y objetos**.

---

### 🧱 ¿Qué es una clase?

Una **clase** es como un plano o molde que describe cómo debe ser un objeto.  
Define:

- 🧩 Atributos → variables que describen el estado del objeto.  
- ⚙️ Métodos → funciones que describen su comportamiento.

```java
public class Persona {
    String nombre;
    int edad;

    // Método
    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}
```

---

### 🧐 ¿Qué es un objeto?

Un **objeto** es una instancia concreta de una clase.  
Con `new`, creamos objetos a partir del molde:

```java
Persona p1 = new Persona();
p1.nombre = "Joaquín";
p1.edad = 30;
p1.saludar(); // Imprime: Hola, soy Joaquín
```

---

### 🔒 Encapsulación: `private`, `getters` y `setters`

Para proteger los datos, usamos **encapsulación**: declaramos atributos como `private` y accedemos a ellos con métodos públicos llamados **getters y setters**.

```java
public class CuentaBancaria {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double nuevoSaldo) {
        if (nuevoSaldo >= 0) {
            saldo = nuevoSaldo;
        }
    }
}
```

---

### 🛠️ Constructores

Un **constructor** es un método especial que se llama automáticamente al crear un objeto con `new`. Sirve para inicializar valores.

```java
public class Alumno {
    String nombre;
    int nota;

    // Constructor
    public Alumno(String nombre, int nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public void mostrarDatos() {
        System.out.println(nombre + " tiene una nota de " + nota);
    }
}

// Ejemplo de uso
Alumno a1 = new Alumno("Laura", 9);
a1.mostrarDatos(); // Laura tiene una nota de 9
```

---

### 🔀 Métodos con retorno y parámetros

Los métodos pueden **recibir datos** y **devolver resultados**:

```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}

// Uso:
Calculadora calc = new Calculadora();
int resultado = calc.sumar(5, 3); // resultado = 8
```

---

## 🌟 Tareas para el Alumno

- ✅ Crea una clase `Coche` con atributos marca, modelo y velocidad.
- ✅ Añade métodos `acelerar()`, `frenar()` y `mostrarInfo()`.
- ✅ Usa constructores para inicializar un objeto `Coche`.
- ✅ Aplica encapsulación: los atributos deben ser `private` y usarse con getters y setters.
- ✅ Haz un menú interactivo para crear, mostrar y modificar un objeto `Coche`.

---

## 📌 Conclusión

Esta unidad es esencial para afianzar los conocimientos sobre clases y objetos y su aplicación práctica en proyectos Java.  
Dominar la POO es clave para escribir programas más robustos, organizados y escalables.

---

