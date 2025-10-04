/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 13: COLECCIONES AVANZADAS EN JAVA (HashSet, HashMap, TreeSet)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

 import java.util.*;

 public class UT13_ColeccionesAvanzadas {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in); // 🛠️ Scanner para entrada por teclado
         int opcion;
 
         // * MENÚ PRINCIPAL PARA ELEGIR LA COLECCIÓN
         do {
             System.out.println("\n📦 MENÚ - COLECCIONES AVANZADAS:");
             System.out.println("1. Demostración con HashSet");
             System.out.println("2. Demostración con HashMap");
             System.out.println("3. Demostración con TreeSet");
             System.out.println("0. Salir");
             System.out.print("👉 Elige una opción: ");
             opcion = sc.nextInt();
 
             switch (opcion) {
                 case 1 -> demoHashSet();  // 🧪 Ejecutar demostración de HashSet
                 case 2 -> demoHashMap();  // 🧪 Ejecutar demostración de HashMap
                 case 3 -> demoTreeSet();  // 🧪 Ejecutar demostración de TreeSet
                 case 0 -> System.out.println("👋 Saliendo...");
                 default -> System.out.println("❌ Opción no válida.");
             }
         } while (opcion != 0); // 🔁 Repetir hasta que el usuario elija salir
 
         sc.close(); // 🚪 Cerrar Scanner
     }
 
     // * 📖 TEORÍA: HashSet
     // ──────────────────────────────────────────────────────────────
     // ? HashSet es una colección que NO permite elementos duplicados.
     // ? No garantiza el orden de los elementos.
     // ? Ideal para almacenar elementos únicos como DNI, emails, etc.
     public static void demoHashSet() {
         System.out.println("\n📦 DEMOSTRACIÓN DE HashSet");
 
         HashSet<String> conjunto = new HashSet<>(); // 📦 Creamos un HashSet para almacenar Strings únicos
 
         // * Añadimos elementos al conjunto
         conjunto.add("Java");
         conjunto.add("Python");
         conjunto.add("C++");
         conjunto.add("Java"); // ❌ Este no se añadirá porque ya existe
 
         // * Recorremos los elementos del HashSet con un bucle for-each
         System.out.println("📋 Elementos del conjunto:");
         for (String lenguaje : conjunto) {
             System.out.println("👉 " + lenguaje);
         }
 
         // ! ✅ TAREA ALUMNO:
         // * Crea tu propio HashSet con nombres de personas y verifica que no haya duplicados.
         // * Muestra cuántos elementos únicos hay con conjunto.size().
     }
 
     // * 📖 TEORÍA: HashMap
     // ──────────────────────────────────────────────────────────────
     // ? HashMap almacena datos como pares clave-valor (key-value).
     // ? Ideal para representar diccionarios o relaciones como nombre → edad.
     public static void demoHashMap() {
         System.out.println("\n📦 DEMOSTRACIÓN DE HashMap");
 
         HashMap<String, Integer> edades = new HashMap<>(); // 🧠 Creamos un HashMap con clave String y valor Integer
 
         // * Añadimos pares clave-valor al mapa
         edades.put("Joaquín", 30);
         edades.put("Ana", 25);
         edades.put("Luis", 40);
 
         // * Recorremos el HashMap con entrySet()
         for (Map.Entry<String, Integer> entry : edades.entrySet()) {
             System.out.println("👤 " + entry.getKey() + " tiene " + entry.getValue() + " años.");
         }
 
         // * Accedemos al valor de una clave específica
         System.out.println("🎯 Edad de Ana: " + edades.get("Ana"));
 
         // ! ✅ TAREA ALUMNO:
         // * Crea un HashMap que almacene nombre de productos y su precio (String → Double).
         // * Muestra solo los productos que cuesten más de 10 euros.
     }
 
     // * 📖 TEORÍA: TreeSet
     // ──────────────────────────────────────────────────────────────
     // ? TreeSet es una colección ordenada que NO permite duplicados.
     // ? Ordena automáticamente los elementos de menor a mayor.
     public static void demoTreeSet() {
         System.out.println("\n📦 DEMOSTRACIÓN DE TreeSet");
 
         TreeSet<Integer> numeros = new TreeSet<>(); // 🔢 Creamos un TreeSet para almacenar enteros ordenados
 
         // * Añadimos varios números (sin duplicados)
         numeros.add(5);
         numeros.add(2);
         numeros.add(10);
         numeros.add(5); // ❌ No se añade por ser duplicado
 
         // * Mostramos los elementos ordenados
         System.out.println("📊 Números ordenados:");
         for (int num : numeros) {
             System.out.println("🔹 " + num);
         }
 
         // * Mostramos el primer y último valor
         System.out.println("📉 Mínimo: " + numeros.first());
         System.out.println("📈 Máximo: " + numeros.last());
 
         // ! ✅ TAREA ALUMNO:
         // * Crea un TreeSet que almacene 5 números pedidos por teclado.
         // * Asegúrate de que no se repitan y muéstralos ordenados.
     }
 }
 