
# 🧠 UT1 - Variables, Tipos de Datos y Operadores en Java

---

## ✅ ¿Qué aprenderás en esta unidad?

- Declarar y utilizar variables en Java.
- Entender los distintos tipos de datos primitivos.
- Usar operadores aritméticos, lógicos y de comparación.
- Leer datos desde la consola con `Scanner`.

---

## 📘 Teoría Explicada

### 🔹 ¿Qué es una variable?

Una variable es un espacio en memoria donde podemos guardar un valor.  
En Java, **todas las variables deben ser declaradas con su tipo de dato**:

```java
int edad = 25;
double precio = 19.99;
String nombre = "Ana";
boolean esAlumno = true;
```

### 🔹 Tipos de datos más comunes en Java

| Tipo     | Descripción                  | Ejemplo        |
|----------|------------------------------|----------------|
| `int`    | Número entero                | `int edad = 30;` |
| `double` | Número decimal (alta precisión) | `double pi = 3.14;` |
| `char`   | Carácter individual          | `char letra = 'A';` |
| `boolean`| Lógico (true o false)        | `boolean activo = true;` |
| `String` | Cadena de texto (objeto)     | `String saludo = "Hola";` |

---

## 🧮 Operadores en Java

### ✏️ Aritméticos

```java
int a = 10, b = 3;
System.out.println(a + b);  // Suma
System.out.println(a - b);  // Resta
System.out.println(a * b);  // Multiplicación
System.out.println(a / b);  // División
System.out.println(a % b);  // Módulo
```

### ⚖️ Comparación

```java
System.out.println(a > b);   // Mayor que
System.out.println(a == b);  // Igual
System.out.println(a != b);  // Distinto
```

### 🔁 Lógicos

```java
boolean esMayor = (a > b) && (a != b);
```

---

## 🧪 Entrada por teclado

```java
Scanner sc = new Scanner(System.in);
System.out.print("Introduce tu nombre: ");
String nombre = sc.nextLine();
System.out.println("Hola " + nombre);
sc.close();
```

---

## 💻 Mini ejercicio resuelto

```java
Scanner sc = new Scanner(System.in);
System.out.print("Introduce dos números: ");
int num1 = sc.nextInt();
int num2 = sc.nextInt();
int suma = num1 + num2;
System.out.println("Resultado: " + suma);
sc.close();
```

---

## 🏁 Tareas para el alumno

1. Crea una variable de cada tipo de dato y muéstrala por pantalla.
2. Pide al usuario su nombre, edad y estatura, y muéstralos juntos.
3. Haz un programa que reciba dos números y devuelva su suma, resta, producto y módulo.

---

🧩 ¡Continúa con la **UT2 - Estructuras de control** para dominar condiciones y bucles!
