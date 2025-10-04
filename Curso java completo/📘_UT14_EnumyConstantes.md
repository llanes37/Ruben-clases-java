# 📘 UT14 - Enum y Constantes en Java

> 📆 Unidad 14 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprende a trabajar con enumeraciones (`enum`) y constantes en Java para organizar mejor tu código.

---

## 🌟 ¿Qué aprenderás en esta unidad?

- Declarar y utilizar **constantes** (`final`).
- Comprender el uso y la sintaxis de las **enumeraciones (enum)**.
- Organizar y estructurar valores fijos con enums.
- Usar enums con `switch` y métodos personalizados.

---

## 🧠 Teoría detallada

### 🔸 ¿Qué es una constante?
Una constante es una variable cuyo valor **no puede cambiarse** una vez asignado.
Se declara con la palabra clave `final` y por convención se escribe en **mayúsculas**:

```java
final double PI = 3.1416;
final int EDAD_MINIMA = 18;
```

Las constantes ayudan a:
- Evitar errores por cambios accidentales de valores clave.
- Mejorar la legibilidad del código.

---

### 🔸 ¿Qué es un `enum`?
Un `enum` (enumeración) es un tipo especial de clase que **representa un conjunto fijo de constantes**.

```java
public enum DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}
```

Un `enum` se puede usar así:

```java
DiaSemana hoy = DiaSemana.LUNES;
System.out.println("Hoy es: " + hoy);
```

---

### 🔸 Ventajas de usar enums

✅ Representan un conjunto de valores **cerrado y controlado**.  
✅ Son más seguros que cadenas de texto (`String`).  
✅ Pueden tener **atributos** y **métodos** dentro del enum.

---

## 🔧 Ejemplos Prácticos

### 🔹 Uso básico de `enum` en `switch`

```java
public enum Estacion {
    PRIMAVERA, VERANO, OTONO, INVIERNO
}

Estacion actual = Estacion.VERANO;

switch (actual) {
    case PRIMAVERA -> System.out.println("🌸 ¡Es primavera!");
    case VERANO -> System.out.println("☀️ ¡Es verano!");
    case OTONO -> System.out.println("🍂 ¡Es otoño!");
    case INVIERNO -> System.out.println("❄️ ¡Es invierno!");
}
```

---

### 🔹 Enum con atributos y método personalizado

```java
public enum Dia {
    LUNES("Inicio de semana"),
    VIERNES("¡Casi fin de semana!"),
    DOMINGO("Día de descanso");

    private String descripcion;

    Dia(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

// Uso:
System.out.println(Dia.DOMINGO.getDescripcion());
```

---

## 💡 Buenas prácticas

- Usa `enum` cuando tengas valores **predecibles y limitados**.
- Las constantes se escriben en **MAYÚSCULAS_CON_GUIONES**.
- Los enums pueden usarse en `switch`, colecciones, y métodos.

---

## 🎯 Tareas para el Alumno

✅ Declara una constante `TASA_IVA` con valor 0.21 y úsala para calcular el precio con IVA.  
✅ Crea un enum `NivelUsuario { BASICO, INTERMEDIO, AVANZADO }` y haz un `switch` que muestre un mensaje según el nivel.  
✅ Añade atributos a ese enum que indiquen la cantidad de cursos disponibles por nivel.  
✅ Usa `values()` para mostrar todos los niveles con su descripción.

---

## 📌 Conclusión

Esta unidad te permite organizar tus datos y constantes de forma clara, robusta y mantenible.  
El uso de `final` y `enum` hace que tu código sea más limpio, seguro y profesional.  
¡Sigue así, ya queda poco para el proyecto final! 💪

