# 📘 UT12 - Ficheros en Java

> 📆 Unidad 12 del Curso de Programación en Java  
> ✍️ Autor: Joaquín Rodríguez Llanes  
> 🔹 Aprende a leer, escribir y gestionar ficheros con Java paso a paso.

---

## 🤔 ¿Por qué trabajar con ficheros?

Muchas aplicaciones necesitan **guardar datos de forma permanente**. Para ello, se usan **ficheros de texto o binarios** donde podemos leer o escribir información.

---

## 🔨 Clases principales para manejo de ficheros

| Clase         | Uso principal                             |
|---------------|---------------------------------------------|
| `File`        | Representa rutas y archivos                |
| `FileReader`  | Leer archivos de texto                     |
| `BufferedReader` | Leer líneas de forma eficiente         |
| `FileWriter`  | Escribir en archivos (sobrescribe)         |
| `BufferedWriter` | Escribir de forma eficiente              |
| `PrintWriter` | Escribir texto fácilmente (como `System.out`) |

---

## 🔹 Leer un fichero línea a línea

```java
import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("datos.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
```

---

## 🔹 Escribir texto en un fichero

```java
import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {
        try {
            FileWriter escritor = new FileWriter("salida.txt");
            escritor.write("Hola, mundo!\n");
            escritor.write("Esta línea se escribe en un archivo.\n");
            escritor.close();
            System.out.println("Fichero escrito con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
```

---

## ✏️ Escribir sin borrar el contenido anterior (modo append)

```java
FileWriter escritor = new FileWriter("salida.txt", true); // true = append
escritor.write("Línea adicional\n");
escritor.close();
```

---

## 🔧 Comprobar si un archivo existe

```java
File archivo = new File("salida.txt");
if (archivo.exists()) {
    System.out.println("El archivo existe.");
} else {
    System.out.println("No se ha encontrado el archivo.");
}
```

---

## 📃 Crear un archivo nuevo si no existe

```java
File archivo = new File("nuevo.txt");
try {
    if (archivo.createNewFile()) {
        System.out.println("Archivo creado correctamente.");
    } else {
        System.out.println("El archivo ya existía.");
    }
} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
}
```

---

## 🔢 Tareas para el alumno

- ✅ Crear un archivo y escribir tu nombre y edad.
- ✅ Leer ese archivo y mostrar su contenido.
- ✅ Crear un programa que reciba líneas por consola y las vaya guardando en un archivo de texto.
- ✅ Crea un sistema de registro de notas por alumno en un archivo.

---

## 👍 Conclusión

Dominar el acceso a ficheros te permite **persistir datos entre ejecuciones**, algo fundamental en el desarrollo de aplicaciones reales. En la siguiente unidad veremos **expresiones regulares** para validar información en estos ficheros.

---

📁 Archivo relacionado: `UT12_Ficheros.java`

