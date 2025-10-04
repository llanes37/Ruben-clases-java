# 📘 UT9 - Strings Avanzados y Expresiones Regulares (Regex)

> 📆 Unidad 9 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprenderás técnicas avanzadas para manipular cadenas de texto y validar datos con expresiones regulares.

---

## 🌟 ¿Qué aprenderás en esta unidad?

- Usar métodos avanzados de la clase `String`: `.split()`, `.replaceAll()`, `.matches()`.
- Conocer los fundamentos de las **expresiones regulares (regex)**.
- Validar cadenas como correos, DNI, teléfonos, etc.
- Hacer sustituciones y búsquedas inteligentes en texto.

---

## 🧠 Teoría detallada

### 🔍 ¿Qué son las expresiones regulares (regex)?

Una **expresión regular** es un patrón que permite buscar y validar texto. Se usan con métodos como `matches()`, `replaceAll()` o con la clase `Pattern`.

```java
"12345".matches("\\d+"); // true, si son solo números
"hola@gmail.com".matches(".+@.+\\..+"); // valida estructura simple de email
```

---

## 🔧 Métodos avanzados de `String`

### 🔹 `.split(String regex)`
Divide una cadena en partes según el patrón:
```java
String datos = "nombre;apellido;edad";
String[] partes = datos.split(";");
```

### 🔹 `.replaceAll(String regex, String reemplazo)`
Reemplaza todas las ocurrencias que cumplan con un patrón:
```java
String texto = "123-456-789";
String limpio = texto.replaceAll("-", ""); // Resultado: "123456789"
```

### 🔹 `.matches(String regex)`
Devuelve `true` si la cadena **cumple exactamente** con el patrón:
```java
"abc123".matches("[a-z]{3}\\d{3}"); // true
```

---

## 🔣 Ejemplos de patrones comunes (regex)

| Validación       | Patrón Regex                   |
|------------------|---------------------------------|
| Solo dígitos     | `\d+`                          |
| Solo letras      | `[a-zA-Z]+`                    |
| DNI español      | `\d{8}[A-Z]`                   |
| Email simple     | `.+@.+\..+`                    |
| Teléfono español | `6\d{8}`                       |

---

## 💻 Mini ejercicios resueltos

### 1. Validar un DNI:
```java
Scanner sc = new Scanner(System.in);
System.out.print("Introduce un DNI: ");
String dni = sc.nextLine();
if (dni.matches("\\d{8}[A-Z]")) {
    System.out.println("✅ DNI válido");
} else {
    System.out.println("❌ DNI incorrecto");
}
```

### 2. Separar palabras por espacios:
```java
String frase = "Hola mundo desde Java";
String[] palabras = frase.split(" ");
for (String palabra : palabras) {
    System.out.println(palabra);
}
```

---

## 🎯 Tareas para el Alumno

- ✅ Escribe un programa que pida al usuario una frase y cuente cuántas palabras tiene.
- ✅ Valida un número de teléfono móvil español (debe empezar por 6 y tener 9 dígitos).
- ✅ Valida un email correctamente escrito (usa `.matches()`).
- ✅ Reemplaza todas las vocales de una frase por asteriscos.
- ✅ Crea una función que reciba una cadena y determine si es un palíndromo.

---

## 📌 Conclusión

Las expresiones regulares y los métodos avanzados de `String` te permiten manipular texto de forma poderosa. Son útiles en validación de datos, filtros, búsqueda y más. Esta unidad es esencial para trabajar con formularios y entradas del usuario en el mundo real.

🧩 ¡En la próxima unidad verás cómo trabajar con fechas, números y la clase Math!