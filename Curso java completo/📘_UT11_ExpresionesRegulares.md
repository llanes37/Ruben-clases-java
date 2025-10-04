# 📘 UT11 - Expresiones Regulares en Java

> 🗖 Unidad 11 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprende a validar datos y buscar patrones dentro de cadenas de texto usando **regex**.

---

## 🌟 ¿Qué aprenderás en esta unidad?

- Qué son las **expresiones regulares** (regex).
- Cómo se usan en Java con la clase `Pattern` y `Matcher`.
- Validar emails, DNI, contraseñas, etc.
- Buscar y reemplazar textos usando patrones.
- Aplicar regex en condiciones reales.

---

## 🧪 Teoría de Expresiones Regulares

### 🔹 ¿Qué es una expresión regular?

Es una secuencia de caracteres que define un **patrón de búsqueda**.  
Sirve para buscar o validar texto.

Ejemplos típicos:
- Validar si un email es correcto.
- Buscar palabras dentro de un texto.
- Reemplazar números por asteriscos.

---

### 🛠 Caracteres especiales más usados

| Símbolo | Significado                       | Ejemplo        |
|---------|-----------------------------------|----------------|
| `.`     | Cualquier carácter (excepto salto)| `a.c` = `abc`  |
| `*`     | Cero o más repeticiones           | `a*` = `aaaa`  |
| `+`     | Una o más repeticiones            | `a+` = `a`, `aaa` |
| `?`     | Cero o una repetición             | `a?` = `a` o nada |
| `[]`    | Rango de caracteres               | `[abc]` = `a`, `b` o `c` |
| `[^]`   | Negación dentro de rango          | `[^abc]` = cualquier cosa excepto `a`, `b`, `c` |
| `\d`   | Dígito numérico (`0-9`)           | `\d+` = `123` |
| `\w`   | Cualquier letra o número          | `\w+` = `abc123` |
| `^`     | Inicio de línea                   | `^Hola`        |
| `$`     | Fin de línea                      | `mundo$`       |

---

## 🧲 Ejemplo Básico

```java
import java.util.regex.*;

public class RegexEjemplo {
    public static void main(String[] args) {
        String texto = "Mi número es 123456789";
        String regex = "\\d{9}"; // Patrón: exactamente 9 dígitos

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            System.out.println("✅ Número encontrado: " + matcher.group());
        } else {
            System.out.println("❌ No se encontró ningún número");
        }
    }
}
```

---

## 🔍 Validar Email

```java
String email = "ejemplo@email.com";
String regex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";

if (email.matches(regex)) {
    System.out.println("✅ Email válido");
} else {
    System.out.println("❌ Email no válido");
}
```

---

## 🔄 Buscar y Reemplazar

```java
String texto = "Mi contraseña es 12345";
String oculto = texto.replaceAll("\\d", "*");

System.out.println(oculto); // 👉 Mi contraseña es *****
```

---

## 🛡 Validar DNI

```java
String dni = "12345678Z";
String regex = "^[0-9]{8}[A-Z]$";

if (dni.matches(regex)) {
    System.out.println("✅ DNI válido");
} else {
    System.out.println("❌ DNI inválido");
}
```

---

## 🎯 Tareas para el Alumno

- ✅ Valida un número de teléfono español: debe empezar por 6, 7 o 9 y tener 9 cifras.
- ✅ Valida una contraseña que contenga al menos una mayúscula, un número y tenga más de 8 caracteres.
- ✅ Pide al usuario un texto y reemplaza todas las vocales por asteriscos.
- ✅ Usa regex para extraer todas las palabras que empiecen por “a” en una frase.

---

## 📌 Conclusión

Las expresiones regulares te permiten trabajar con texto de manera muy potente. Son esenciales para validar formularios, hacer filtros o transformar datos.  
En esta unidad has aprendido las más usadas y cómo integrarlas en programas Java.

---

📁 Archivo relacionado: `UT11_ExpresionesRegulares.java`

