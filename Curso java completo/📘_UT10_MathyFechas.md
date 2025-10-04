# 📘 UT10 - Clase Math y Fechas en Java

> 📆 Unidad 10 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 En esta unidad aprenderás a trabajar con matemáticas y fechas utilizando las clases `Math`, `LocalDate`, `LocalDateTime` y `Calendar`.

---

## 🚀 Objetivos

- Aprender a utilizar la clase `Math` para realizar operaciones matemáticas.
- Conocer las clases para trabajar con fechas (`LocalDate`, `LocalDateTime`, `Calendar`).
- Aplicar estas clases en ejercicios prácticos y tareas cotidianas.

---

## 🔢 Clase `Math`

La clase `Math` proporciona métodos estáticos para operaciones matemáticas comunes.

### 🔌 Métodos comunes

```java
System.out.println(Math.sqrt(16));        // Raíz cuadrada: 4.0
System.out.println(Math.pow(2, 3));        // Potencia: 8.0
System.out.println(Math.max(5, 10));       // Valor máximo: 10
System.out.println(Math.min(3, 7));        // Valor mínimo: 3
System.out.println(Math.round(4.6));       // Redondeo: 5
System.out.println(Math.random());         // Número aleatorio entre 0.0 y 1.0
System.out.println((int)(Math.random()*10)); // Aleatorio entre 0 y 9
```

---

## 🗓️ Manejo de Fechas - `LocalDate` y `LocalDateTime`

Desde Java 8 se recomienda usar `LocalDate` y `LocalDateTime` (en el paquete `java.time`).

### 🔹 Ejemplo con `LocalDate`

```java
import java.time.LocalDate;

LocalDate hoy = LocalDate.now();
System.out.println("Fecha actual: " + hoy);

LocalDate nacimiento = LocalDate.of(2000, 5, 15);
System.out.println("Nacimiento: " + nacimiento);

System.out.println("Día del mes: " + hoy.getDayOfMonth());
System.out.println("Mes: " + hoy.getMonth());
System.out.println("Año: " + hoy.getYear());
```

### 🔹 Ejemplo con `LocalDateTime`

```java
import java.time.LocalDateTime;

LocalDateTime ahora = LocalDateTime.now();
System.out.println("Fecha y hora actual: " + ahora);
```

---

## 💰 Clase `Calendar`

Aunque `Calendar` es anterior a `LocalDate`, sigue siendo muy utilizada. Se encuentra en el paquete `java.util`.

```java
import java.util.Calendar;

Calendar calendario = Calendar.getInstance();
System.out.println("Año: " + calendario.get(Calendar.YEAR));
System.out.println("Mes: " + (calendario.get(Calendar.MONTH) + 1)); // Empieza desde 0
System.out.println("Día: " + calendario.get(Calendar.DAY_OF_MONTH));
```

---

## 🔧 Tareas para el Alumno

- ✅ Crea un programa que calcule el área de un círculo usando `Math.PI` y `Math.pow()`.
- ✅ Genera 10 números aleatorios entre 1 y 100.
- ✅ Pide al usuario su fecha de nacimiento y calcula cuántos años tiene.
- ✅ Muestra el día de la semana en que nació.
- ✅ Implementa un reloj que muestre la hora actual cada segundo (con bucle).

---

## 📊 Conclusión

Esta unidad te permite realizar operaciones matemáticas más complejas y gestionar fechas y horas de forma moderna. Son herramientas clave para proyectos reales, desde calculadoras hasta sistemas de agenda o control de tiempo.

