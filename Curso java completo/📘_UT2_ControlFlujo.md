# 📘 UT2 - ESTRUCTURAS DE CONTROL DE FLUJO

> 📆 Unidad 2 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Esta unidad te permitirá controlar cómo fluye la ejecución de tu programa.

---

## 🧠 ¿Qué es el control de flujo?

En un programa, las instrucciones normalmente se ejecutan de forma secuencial (una tras otra), pero muchas veces es necesario **tomar decisiones** o **repetir bloques de código** según ciertas condiciones. Para eso existen las **estructuras de control de flujo**.

### 🔸 Tipos principales:

1. **Condicionales**: `if`, `else if`, `else`, `switch`
2. **Bucles** (repetición): `for`, `while`, `do-while`
3. **Interrupción de flujo**: `break`, `continue`, `return`

---

## 🔎 CONDICIONALES EN JAVA

### 🔹 `if`, `else if`, `else`

Esta estructura permite ejecutar un bloque de código si se cumple una condición. Si no se cumple, puedes usar `else` o `else if` para manejar otras posibilidades.

#### Ejemplo explicativo:
```java
int edad = 20;

if (edad >= 18) {
    System.out.println("Es mayor de edad");
} else {
    System.out.println("Es menor de edad");
}
```

**Explicación:**
- Si `edad` es mayor o igual que 18, se ejecuta el primer bloque.
- Si no, se ejecuta el bloque del `else`.

#### Estructura general:
```java
if (condicion) {
    // Código si se cumple la condición
} else if (otraCondicion) {
    // Otra opción
} else {
    // Ninguna condición se cumplió
}
```

---

### 🔹 `switch`

Sirve para evaluar **varias posibles opciones** de una misma variable. Es más limpio que varios `if-else` seguidos.

#### Ejemplo:
```java
int dia = 3;

switch (dia) {
    case 1: System.out.println("Lunes"); break;
    case 2: System.out.println("Martes"); break;
    case 3: System.out.println("Miércoles"); break;
    default: System.out.println("Día no válido");
}
```

**Explicación:**
- El valor de `dia` se compara con cada `case`. Si coincide, ejecuta ese bloque.
- `break` evita que se ejecuten los casos siguientes.
- `default` es lo que se ejecuta si no coincide ningún `case`.

---

## 🔁 BUCLES EN JAVA

Los bucles permiten repetir un bloque de código varias veces.

### 🔹 `for`

Ideal cuando sabes cuántas veces debes repetir algo.

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Iteración: " + i);
}
```

**Explicación:**
- `int i = 1`: inicialización.
- `i <= 5`: condición para seguir.
- `i++`: incremento.

### 🔹 `while`

Se ejecuta mientras una condición sea verdadera.

```java
int i = 1;
while (i <= 5) {
    System.out.println("Número: " + i);
    i++;
}
```

### 🔹 `do-while`

Parecido a `while`, pero **se ejecuta al menos una vez**, incluso si la condición es falsa al inicio.

```java
int i = 1;
do {
    System.out.println("Valor: " + i);
    i++;
} while (i <= 5);
```

---

## 🧪 INTERRUPTORES DE FLUJO: `break` y `continue`

### 🔹 `break`

Sale completamente del bucle.

### 🔹 `continue`

Salta la iteración actual y sigue con la siguiente.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break;        // Detiene el bucle si i == 5
    if (i % 2 == 0) continue; // Salta los números pares
    System.out.println(i);
}
```

---

## 🎯 TAREAS PROPUESTAS

### ✅ Nivel Básico

- Escribe un programa que diga si un número es par o impar.
- Pide un número del 1 al 7 e imprime el día de la semana con `switch`.

### 🔁 Nivel Intermedio

- Usa un `for` para imprimir los números del 10 al 1 en orden inverso.
- Crea un menú que se repita hasta que el usuario pulse 0 (con `do-while`).

### 💡 Nivel Avanzado

- Programa un sistema que permita introducir edades hasta que el usuario pulse `-1`, y luego calcule:
  - Edad media
  - Edad máxima
  - Edad mínima

---

📁 Archivo relacionado: `UT2_ControlFlujo.java`

