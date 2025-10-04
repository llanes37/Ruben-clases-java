/*
 * ******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: CLASES Y OBJETOS EN JAVA**
 * ──────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender la programación orientada a objetos en Java.**
 * ✅ **Crear y utilizar clases en Java.**
 * ✅ **Definir atributos y métodos en una clase.**
 * ✅ **Crear objetos a partir de una clase.**
 * ✅ **Usar constructores para inicializar objetos.**
 * ✅ **Comprender la encapsulación y el uso de getters y setters.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************/

/*
 * 🧠 **TEORÍA GLOBAL: CLASES Y OBJETOS EN JAVA**
 * ────────────────────────────────────────────
 * 
 * 🟢 **¿Qué es una Clase?**
 *  - Una **clase** es una plantilla o modelo que define las características (**atributos**) y
 *    comportamientos (**métodos**) de un objeto.
 * 
 * 🟠 **¿Qué es un Objeto?**
 *  - Un **objeto** es una instancia concreta de una clase, que tiene valores específicos.
 *  - En Java, un objeto se crea con la palabra clave `new`.
 * 
 * 🔹 **Ejemplo de una Clase y su Objeto:**
 * ```java
 * class Coche {
 *     String marca;
 *     int velocidad;
 *     
 *     void acelerar() {
 *         velocidad += 10;
 *     }
 * }
 * 
 * public class Main {
 *     public static void main(String[] args) {
 *         Coche miCoche = new Coche(); // Crear un objeto
 *         miCoche.marca = "Toyota";
 *         miCoche.acelerar();
 *         System.out.println("Velocidad: " + miCoche.velocidad);
 *     }
 * }
 * ```
 */

// Definimos la clase principal
public class UT5_ClasesObjetos {
    public static void main(String[] args) {
        // Crear un objeto de la clase Persona
        Persona persona1 = new Persona("Joaquín", 30);
        persona1.mostrarInformacion(); // Llamamos al método
        
        // Modificamos los atributos con setters
        persona1.setNombre("Ana");
        persona1.setEdad(25);
        
        // Mostramos la información actualizada
        persona1.mostrarInformacion();

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Crea otra clase llamada `Coche` con los atributos `marca`, `modelo` y `velocidad`.
        // * Implementa un método `acelerar()` que incremente la velocidad del coche.
        // * En `main()`, crea un objeto `Coche`, establece valores y prueba el método `acelerar()`.
    }
}

// Definimos la clase Persona
class Persona {
    // * ⚠️ Atributos (variables de instancia)
    private String nombre;
    private int edad;
    
    // * ✅ Constructor (inicializa los atributos al crear el objeto)
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    // * 🛠️ Getters y Setters (Encapsulación: acceso controlado a los atributos)
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        } else {
            System.out.println("⚠️ La edad no puede ser negativa.");
        }
    }
    
    // * ✅ Método para mostrar la información del objeto
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad);
    }
}

/*
 * ⚡ **TAREAS PARA EL ALUMNO:**
 * 1. Implementa la clase `Coche` con los atributos `marca`, `modelo` y `velocidad`.
 * 2. Agrega un método `acelerar()` que incremente la velocidad en 10 unidades.
 * 3. Agrega un método `frenar()` que disminuya la velocidad en 5 unidades.
 * 4. Crea un objeto `Coche`, establece valores y prueba los métodos.
 * 5. Agrega validación para que la velocidad no sea negativa.
 * 
 * 🚀 **¡Explora y experimenta con el código!**
 */
