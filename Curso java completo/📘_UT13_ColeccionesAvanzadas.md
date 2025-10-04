# 📘 UT13 - Colecciones Avanzadas en Java

> 📆 Unidad 13 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprende a manejar listas, mapas, conjuntos y estructuras complejas.

---

## 🧠 ¿Qué aprenderás en esta unidad?

- Usar las principales colecciones de Java: `ArrayList`, `HashMap`, `HashSet` y `TreeSet`.
- Comprender las diferencias entre listas, mapas y conjuntos.
- Aplicar operaciones comunes como: agregar, eliminar, recorrer y ordenar.
- Entender la importancia de `equals()` y `hashCode()` en colecciones.

---

## 📘 Teoría y conceptos

### 🔹 Colecciones en Java

Java ofrece estructuras de datos listas para usar conocidas como **colecciones** que permiten almacenar grupos de objetos.

| Tipo       | Característica Principal          | Implementaciones comunes         |
|------------|-------------------------------------|----------------------------------|
| Lista      | Ordenada, permite elementos repetidos | `ArrayList`, `LinkedList`       |
| Conjunto   | No permite elementos duplicados      | `HashSet`, `TreeSet`            |
| Mapa       | Pares clave-valor                    | `HashMap`, `TreeMap`            |

---

### 📋 ArrayList - Lista dinámica

```java
import java.util.ArrayList;

ArrayList<String> lista = new ArrayList<>();
lista.add("Manzana");
lista.add("Plátano");
lista.add("Naranja");

System.out.println("Elemento en posición 1: " + lista.get(1));
lista.remove("Plátano");
System.out.println("Último elemento: " + lista.get(lista.size() - 1));
```

---

### 💼 HashMap - Diccionario o tabla clave-valor

```java
import java.util.HashMap;

HashMap<String, Integer> edades = new HashMap<>();
edades.put("Ana", 30);
edades.put("Luis", 25);
edades.put("Marta", 40);

System.out.println("Edad de Luis: " + edades.get("Luis"));
edades.remove("Ana");
```

---

### 🔹 HashSet - Conjunto sin duplicados

```java
import java.util.HashSet;

HashSet<String> dias = new HashSet<>();
dias.add("Lunes");
dias.add("Martes");
dias.add("Lunes"); // No se agregará otra vez

System.out.println("Días: " + dias);
```

---

### 🌐 TreeSet - Conjunto ordenado

```java
import java.util.TreeSet;

TreeSet<Integer> numeros = new TreeSet<>();
numeros.add(5);
numeros.add(1);
numeros.add(10);

System.out.println("Ordenado: " + numeros); // [1, 5, 10]
```

---

## 🔍 Equals y HashCode

Cuando trabajamos con conjuntos (`HashSet`) o como claves en mapas (`HashMap`), los objetos deben definir correctamente los métodos `equals()` y `hashCode()`.

```java
public class Persona {
    String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return nombre.equals(persona.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
```

---

## 🎓 Tareas para el Alumno

- ✅ Crear una lista de alumnos, agregar nombres, eliminarlos y mostrarlos ordenados.
- ✅ Crear un mapa con productos y precios. Buscar por clave, eliminar y mostrar todos.
- ✅ Crear un conjunto de DNI sin duplicados. Probar añadir duplicados y mostrar el resultado.
- ✅ Crear un TreeSet de fechas o notas y mostrarlas ordenadas.

---

## 📌 Conclusión

Las colecciones permiten manejar grandes cantidades de datos de forma eficiente y flexible. Usar la estructura correcta mejora el rendimiento y facilita el código. Esta unidad es fundamental para estructuras más complejas en proyectos reales.

