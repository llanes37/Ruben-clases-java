# 🔧 UT4 - Funciones

## 📘 Objetivos de la Unidad
- Explicar los conceptos fundamentales de **funciones** en Java.
- Incluir ejemplos prácticos con código comentado.
- Plantear tareas y ejercicios para practicar.

---

## 🧠 Teoría

Las **funciones** (o métodos) permiten encapsular bloques de código reutilizable. Son una parte fundamental de la programación estructurada y orientada a objetos en Java.

### 🔹 Definición de una función

```java
// Declaración de una función simple que no devuelve nada
tatic void saludar() {
    System.out.println("Hola desde una función");
}
```

### 🔹 Función con parámetros

```java
static void saludarNombre(String nombre) {
    System.out.println("Hola, " + nombre);
}
```

### 🔹 Función que devuelve un valor

```java
static int sumar(int a, int b) {
    return a + b;
}
```

### 🔹 Llamada a funciones

```java
public static void main(String[] args) {
    saludar(); // llamada a función sin parámetros
    saludarNombre("Joaquín"); // llamada con parámetro
    int resultado = sumar(5, 7); // llamada con retorno
    System.out.println("Resultado: " + resultado);
}
```

---

## 📚 Buenas Prácticas

- Usar nombres descriptivos para las funciones.
- Evitar funciones demasiado largas (usa métodos auxiliares).
- Utilizar comentarios para documentar funciones.
- Agrupar funciones relacionadas dentro de clases.

---

## 🎓 Ejemplo completo comentado

```java
// Ejemplo: calcular el área de un círculo
tatic double calcularAreaCirculo(double radio) {
    return Math.PI * radio * radio;
}

public static void main(String[] args) {
    double area = calcularAreaCirculo(4.5);
    System.out.println("Área del círculo: " + area);
}
```

---

## 🎯 Tareas para el Alumno

- ✅ Lee el código anterior y modifícalo para hacerlo más completo.
- ✅ Implementa una función que convierta grados Celsius a Fahrenheit.
- ✅ Crea una función que determine si un número es primo.
- ✅ Realiza una calculadora simple con funciones: sumar, restar, multiplicar, dividir.

---

## 📌 Conclusión

Esta unidad es esencial para afianzar los conocimientos sobre funciones y su aplicación práctica en proyectos Java. Aprender a separar lógica en funciones mejora la legibilidad, mantenibilidad y reutilización del código.
