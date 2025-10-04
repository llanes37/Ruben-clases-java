# 🔤 UT7 - Cadenas de Texto en Java

> 📆 Unidad 7 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 En esta unidad aprenderás a manipular y trabajar con Strings (cadenas de texto) en Java.

---

## 💡 ¿Qué aprenderás en esta unidad?

- Crear y manipular objetos de tipo `String`.
- Utilizar métodos comunes de la clase `String`.
- Concatenar textos de diferentes formas.
- Comparar cadenas.
- Extraer y reemplazar partes de un texto.
- Convertir texto a mayúsculas o minúsculas.

---

## 📘 Teoría detallada

### 🔎 ¿Qué es un String?

Un `String` es un **objeto** que representa una secuencia de caracteres.
Se declara entre comillas dobles:

```java
String saludo = "Hola mundo";
```

En realidad, `String` es una clase, por lo tanto tiene métodos que nos permiten trabajar con texto.

---

### ✍️ Métodos básicos de String

```java
String texto = "Programación en Java";
System.out.println(texto.length()); // Longitud
System.out.println(texto.toUpperCase()); // Mayúsculas
System.out.println(texto.toLowerCase()); // Minúsculas
System.out.println(texto.contains("Java")); // Contiene "Java"?
System.out.println(texto.charAt(0)); // Carácter en posición 0
System.out.println(texto.substring(0, 5)); // Subcadena de 0 a 5
```

---

### 👍 Concatenación de cadenas

Puedes unir dos o más textos usando el operador `+` o el método `concat()`:

```java
String nombre = "Ana";
String saludo = "Hola " + nombre;
System.out.println(saludo); // Hola Ana

String apellido = "García";
String nombreCompleto = nombre.concat(" ").concat(apellido);
System.out.println(nombreCompleto); // Ana García
```

---

### 📊 Comparar cadenas

```java
String a = "java";
String b = "Java";
System.out.println(a.equals(b)); // false
System.out.println(a.equalsIgnoreCase(b)); // true
```

---

### 🔄 Reemplazar texto y buscar posiciones

```java
String frase = "Aprender Java es divertido";
System.out.println(frase.replace("Java", "Python"));
System.out.println(frase.indexOf("Java"));
System.out.println(frase.lastIndexOf("e"));
```

---

## 📂 Tareas para el Alumno

- ✅ Pide al usuario su nombre completo y muéstralo en mayúsculas.
- ✅ Extrae su nombre y apellido usando `substring()`.
- ✅ Cuenta cuántas letras tiene su nombre usando `length()`.
- ✅ Compara si el nombre ingresado es igual a tu nombre con `equals()`.
- ✅ Reemplaza "Java" por "Python" en una frase y muéstrala.

---

## 📆 Conclusión

Esta unidad te permite dominar la manipulación de texto, esencial para cualquier proyecto Java.
Dominar los `String` te permitirá leer y transformar datos, mostrar mensajes personalizados, validar entradas del usuario, y mucho más.

---

📁 Archivo relacionado: `UT7_CadenasTexto.java`

