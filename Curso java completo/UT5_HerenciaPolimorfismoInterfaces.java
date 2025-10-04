/******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: HERENCIA, POLIMORFISMO E INTERFACES EN JAVA**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender el concepto de herencia en Java y cómo usarla para reutilizar código.**
 * ✅ **Entender el polimorfismo y su importancia en la programación orientada a objetos.**
 * ✅ **Aprender a crear e implementar interfaces en Java.**
 * ✅ **Distinguir entre clases abstractas e interfaces y cuándo utilizarlas.**
 * ✅ **Explorar ejemplos prácticos con herencia, polimorfismo e interfaces.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************/

/*
 * 🧠 **TEORÍA GLOBAL: HERENCIA EN JAVA**
 * ──────────────────────────────────────────────────────────────
 * 
 * 🔵 **¿Qué es la Herencia?**
 *  - Es un mecanismo que permite que una clase (hija) adquiera los atributos y métodos de otra clase (padre).
 *  - La palabra clave `extends` se usa para indicar que una clase hereda de otra.
 * 
 * 🔹 **Ejemplo de herencia:**
 * ```java
 * class Animal {
 *     void hacerSonido() {
 *         System.out.println("El animal hace un sonido.");
 *     }
 * }
 * class Perro extends Animal {
 *     void hacerSonido() {
 *         System.out.println("El perro ladra.");
 *     }
 * }
 * ```
 */

/*
 * 🔵 **¿QUÉ ES EL POLIMORFISMO?**
 * ──────────────────────────────────────────────────────────────
 * - Un mismo método puede tener diferentes implementaciones en distintas clases.
 * - Java permite el polimorfismo mediante la sobrescritura (`@Override`) y la sobrecarga de métodos.
 *
 * 🔹 **Ejemplo de polimorfismo:**
 * ```java
 * class Vehiculo {
 *     void acelerar() {
 *         System.out.println("El vehículo acelera.");
 *     }
 * }
 * class Coche extends Vehiculo {
 *     @Override
 *     void acelerar() {
 *         System.out.println("El coche acelera a 100 km/h.");
 *     }
 * }
 * ```
 */

/*
 * 🔵 **¿QUÉ ES UNA INTERFAZ EN JAVA?**
 * ──────────────────────────────────────────────────────────────
 * - Una interfaz es un conjunto de métodos abstractos que deben ser implementados por una clase.
 * - Se usa la palabra clave `implements` para que una clase implemente una interfaz.
 *
 * 🔹 **Ejemplo de interfaz:**
 * ```java
 * interface Volador {
 *     void volar();
 * }
 * class Pajaro implements Volador {
 *     public void volar() {
 *         System.out.println("El pájaro vuela.");
 *     }
 * }
 * ```
 */

import java.util.ArrayList; 

// * 📖 EJEMPLO DE HERENCIA EN JAVA
// ────────────────────────────────────────────────────────────
// ? Definimos una clase base (superclase)
class Animal {
    String nombre;

    // Constructor
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Método genérico para hacer sonido
    void hacerSonido() {
        System.out.println("El animal hace un sonido.");
    }
}

// ? Clase que hereda de Animal
class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre); // Llamamos al constructor de la superclase
    }

    @Override
    void hacerSonido() {
        System.out.println(nombre + " ladra: ¡Guau guau!");
    }
}

// ? Clase que hereda de Animal
class Gato extends Animal {
    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    void hacerSonido() {
        System.out.println(nombre + " maulla: ¡Miau miau!");
    }
}

// * 📖 EJEMPLO DE INTERFACES EN JAVA
// ────────────────────────────────────────────────────────────
interface Vehiculo {
    void acelerar();
    void frenar();
}

// ? Clase que implementa la interfaz
class Coche implements Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("El coche acelera.");
    }

    @Override
    public void frenar() {
        System.out.println("El coche frena.");
    }
}

// * 📖 EJEMPLO DE POLIMORFISMO EN JAVA
// ────────────────────────────────────────────────────────────
// ? Creamos una lista de animales y ejecutamos su método `hacerSonido()`
public class UT5_HerenciaPolimorfismoInterfaces {
    public static void main(String[] args) {
        // Lista de animales
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Perro("Rex"));
        animales.add(new Gato("Whiskers"));

        System.out.println("🔹 Sonidos de los animales:");
        for (Animal a : animales) {
            a.hacerSonido(); // Aplicación del polimorfismo
        }

        // Probamos la interfaz
        System.out.println(" Probando la interfaz Vehiculo:");
        Coche miCoche = new Coche();
        miCoche.acelerar();
        miCoche.frenar();

        // ! ✅ TAREA PARA EL ALUMNO:
        // * 1️⃣ Crea una clase `Ave` que herede de `Animal` y sobrescriba el método `hacerSonido()`.
        // * 2️⃣ Crea una interfaz `Nadador` con el método `nadar()`, e impleméntala en una clase `Pez`.
        // * 3️⃣ Agrega un nuevo método en `Vehiculo` llamado `repostar()` y modifícalo en `Coche`.
    }
}
