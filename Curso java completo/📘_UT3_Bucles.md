# 🔄 UT3 - Bucles en Java

## 🎯 Objetivos de la Unidad

- Comprender el uso de estructuras repetitivas (`for`, `while`, `do-while`).
- Aplicar bucles para resolver problemas repetitivos.
- Usar condicionales dentro de bucles.
- Mejorar la lógica del programa con estructuras de control.

---

## 🧠 Teoría

### 🔁 Bucle `for`
Se usa cuando sabemos exactamente cuántas veces queremos repetir una acción.

```java
for (int i = 0; i < 5; i++) {
    System.out.println("Iteración: " + i);
}
```

### 🔁 Bucle `while`
Se ejecuta mientras se cumpla una condición.

```java
int i = 0;
while (i < 5) {
    System.out.println("Iteración: " + i);
    i++;
}
```

### 🔁 Bucle `do-while`
Ejecuta al menos una vez, y luego verifica la condición.

```java
int i = 0;
do {
    System.out.println("Iteración: " + i);
    i++;
} while (i < 5);
```

---

## 🛠️ Ejercicios Propuestos

### ✅ Ejercicio 1
Crea un programa que imprima los números del 1 al 100 usando un `for`.

### ✅ Ejercicio 2
Pide números al usuario hasta que introduzca un `0` con `while`.

### ✅ Ejercicio 3
Crea un menú interactivo con `do-while` con opciones (1. Saludar, 2. Salir).

---

## 🧪 Práctica Recomendada

> Implementa un programa que calcule la suma de los números pares del 1 al 100.
> Añade un mensaje especial cuando el número sea múltiplo de 10.

---

## 📎 Archivo Java asociado

`UT3_Bucles.java`