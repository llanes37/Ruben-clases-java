# 🚨 UT6 - Excepciones y Manejo de Errores

> ✨ Unidad 6 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprenderás a controlar errores en tiempo de ejecución

---

## 🚀 ¿Qué aprenderás en esta unidad?

- Comprender qué es una excepción en Java.
- Usar bloques `try`, `catch`, `finally` y `throw`.
- Capturar y gestionar errores comunes como `ArithmeticException` y `InputMismatchException`.
- Crear tus propias excepciones personalizadas.
- Simular errores en programas para controlarlos correctamente.

---

## 🧠 Teoría detallada

### 💡 ¿Qué es una excepción?

Una excepción es un **evento anómalo** que ocurre durante la ejecución de un programa, y que interrumpe el flujo normal del mismo.
En Java, todas las excepciones derivan de la clase `Throwable`.

### 📚 Bloque `try-catch`

Sirve para **capturar errores** y evitar que el programa se detenga.

```java
try {
    int resultado = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("❌ Error: No se puede dividir por cero.");
}
```

### 🔍 Múltiples `catch`

Puedes capturar diferentes errores específicos o usar un `Exception` general:

```java
try {
    int num = sc.nextInt();
} catch (InputMismatchException e) {
    System.out.println("⚠️ Error de tipo de dato.");
} catch (Exception e) {
    System.out.println("Error desconocido.");
}
```

### 📄 Bloque `finally`

Se ejecuta siempre, haya error o no. Ideal para **cerrar recursos**:

```java
Scanner sc = null;
try {
    sc = new Scanner(System.in);
} catch (Exception e) {
    System.out.println("Error");
} finally {
    if (sc != null) sc.close();
}
```

### ⛔️ Lanzar errores con `throw`

Puedes **lanzar excepciones manualmente** para controlar condiciones propias:

```java
if (edad < 0) {
    throw new IllegalArgumentException("Edad no válida");
}
```

### 🛡️ Crear Excepciones Personalizadas

```java
class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}
```

Uso:

```java
if (edad < 0) throw new EdadInvalidaException("Edad negativa");
```

---

## 🛠️ Ejemplos Completos

### 🔹 Ejemplo 1: División entre cero

```java
try {
    int r = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("No se puede dividir entre cero");
}
```

---

### 🔹 Ejemplo 2: Error de entrada

```java
try {
    Scanner sc = new Scanner(System.in);
    int edad = sc.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Debes introducir un número");
}
```

---

### 🔹 Ejemplo 3: Uso de `finally`

```java
Scanner sc = null;
try {
    sc = new Scanner(System.in);
    int edad = sc.nextInt();
} catch (Exception e) {
    System.out.println("Error de entrada");
} finally {
    if (sc != null) sc.close();
    System.out.println("Scanner cerrado correctamente");
}
```

---

### 🔹 Ejemplo 4: Excepción personalizada

```java
public class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}

// Uso:
if (edad < 0) throw new EdadInvalidaException("Edad negativa no permitida");
```

---

## 🔢 Ejercicio Final: Cajero Automático con Excepciones

```java
// Simula un cajero que permite retirar dinero, validando:
// - Que el monto sea > 0
// - Que no exceda el saldo disponible
// - Lanzar SaldoInsuficienteException si hay problema
```

---

## 🎯 Tareas para el Alumno

- ✅ Captura una excepción usando `try-catch` al dividir por cero.
- ✅ Usa `finally` para cerrar recursos aunque haya error.
- ✅ Crea una excepción personalizada para edades menores de 0.
- ✅ Simula un banco que lanza `SaldoInsuficienteException`.
- ✅ Pide datos por consola, usa `try-catch` para errores de tipo.

---

## 📌 Conclusión

El manejo de errores es esencial para **evitar que nuestros programas se detengan abruptamente**.
Con `try-catch` y excepciones personalizadas, logramos mayor control, robustez y profesionalismo en nuestras aplicaciones.

---

✨ Continúa con la **UT7 - Cadenas de Texto** para trabajar Strings a nivel básico y avanzado.

