# 🧩 UT8 - Arrays y Cadenas de Texto Avanzadas

> 📆 Unidad 8 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprenderás a manejar arrays unidimensionales, multidimensionales y trabajar a fondo con `String`.

---

## 🌟 Objetivos de la Unidad

- Declarar y manipular **arrays** (vectores) en Java.
- Trabajar con **matrices** (arrays bidimensionales).
- Utilizar métodos comunes de la clase `String`.
- Aprender a recorrer arrays y realizar operaciones sobre ellos.

---

## 🧠 Teoría: Arrays

### 🔹 ¿Qué es un array?
Un **array** es una colección de elementos del mismo tipo que se almacenan en posiciones contiguas de memoria.

```java
int[] numeros = new int[5]; // Array de 5 enteros
numeros[0] = 10;
numeros[1] = 20;
```

Podemos recorrer un array con un bucle:

```java
for (int i = 0; i < numeros.length; i++) {
    System.out.println(numeros[i]);
}
```

---

## 🔁 Inicialización directa de arrays

```java
String[] frutas = {"Manzana", "Pera", "Plátano"};
for (String fruta : frutas) {
    System.out.println(fruta);
}
```

---

## 🧮 Arrays Bidimensionales (Matrices)

```java
int[][] matriz = new int[2][3];
matriz[0][0] = 1;
matriz[0][1] = 2;
matriz[1][0] = 3;

for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println();
}
```

---

## 🔤 Trabajar con Cadenas de Texto (`String`)

```java
String texto = "Hola Mundo";
System.out.println(texto.length());        // Tamaño del texto
System.out.println(texto.toUpperCase());   // Mayúsculas
System.out.println(texto.toLowerCase());   // Minúsculas
System.out.println(texto.charAt(0));       // Primer carácter
System.out.println(texto.substring(0, 4)); // Subcadena
```

---

## 🔎 Métodos comunes de `String`

| Método              | Descripción                             |
|----------------------|-----------------------------------------|
| `length()`           | Devuelve la longitud de la cadena       |
| `charAt(i)`          | Devuelve el carácter en la posición i   |
| `substring(i, j)`    | Extrae una subcadena                    |
| `toUpperCase()`      | Convierte a mayúsculas                  |
| `toLowerCase()`      | Convierte a minúsculas                  |
| `equals(str)`        | Compara dos cadenas                     |
| `contains(str)`      | Verifica si contiene una subcadena      |

---

## 💻 Mini Ejemplo: Buscar palabras

```java
String frase = "La programación en Java es divertida";
if (frase.contains("Java")) {
    System.out.println("Se encontró la palabra Java!");
}
```

---

## 🎯 Tareas para el Alumno

1. Crea un array de 10 números enteros, pídelos por teclado y muéstralos en orden inverso.
2. Crea un programa que almacene nombres en un array y muestre cuáles empiezan por vocal.
3. Usa `String` para pedir al usuario una frase y muestra:
   - Cuántas palabras tiene
   - Cuántas veces aparece la letra "a"

---

## 📌 Conclusión

Esta unidad te brinda las herramientas para trabajar con estructuras de datos básicas y manipular cadenas de texto eficientemente. Estas habilidades son esenciales para procesar información en cualquier programa.
