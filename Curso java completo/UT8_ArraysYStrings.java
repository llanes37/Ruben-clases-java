/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 8: ARRAYS + STRINGS
 *  🔐 REPOSITORIO PRIVADO PARA USO EDUCATIVO
 ******************************************************************************************/

 import java.util.Arrays;
 import java.util.Scanner;
 
 public class UT8_ArraysYStrings {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // 🔷 MENÚ PRINCIPAL PARA EJECUTAR EJERCICIOS
         int opcion = -1;
         do {
             System.out.println("\n🧭 MENÚ UNIDAD 8: ARRAYS + STRINGS");
             System.out.println("1️⃣ - Array de palabras");
             System.out.println("2️⃣ - Buscar palabra");
             System.out.println("3️⃣ - Frase a Array con split()");
             System.out.println("4️⃣ - Ordenar palabras");
             System.out.println("0️⃣ - Salir");
             System.out.print("👉 Elige una opción: ");
             opcion = sc.nextInt();
             sc.nextLine(); // Limpiar buffer
 
             switch (opcion) {
                 case 1 -> arrayDePalabras(sc);
                 case 2 -> buscarPalabra(sc);
                 case 3 -> fraseASplit(sc);
                 case 4 -> ordenarPalabras(sc);
                 case 0 -> System.out.println("🚪 Saliendo del programa...");
                 default -> System.out.println("❌ Opción inválida.");
             }
         } while (opcion != 0);
 
         sc.close();
     }
 
     // 🔹 EJERCICIO 1: ARRAY DE PALABRAS
     public static void arrayDePalabras(Scanner sc) {
         /*
          * 📖 TEORÍA:
          * - Un array puede almacenar Strings como cualquier otro tipo de dato.
          * - Se accede igual que un array de enteros.
          */
         System.out.println("\n📌 EJERCICIO 1: Array de palabras");
 
         String[] palabras = new String[3];
 
         // ? Pedimos al usuario ingresar palabras
         for (int i = 0; i < palabras.length; i++) {
             System.out.print("🔤 Introduce la palabra " + (i + 1) + ": ");
             palabras[i] = sc.nextLine();
         }
 
         // ? Mostramos el contenido del array
         System.out.println("🧾 Palabras ingresadas: " + Arrays.toString(palabras));
 
         // ✅ TAREA ALUMNO: modifica el array para que tenga 5 palabras y muestra solo aquellas que tengan más de 5 letras.
     }
 
     // 🔹 EJERCICIO 2: BUSCAR UNA PALABRA
     public static void buscarPalabra(Scanner sc) {
         /*
          * 📖 TEORÍA:
          * - Podemos buscar palabras en un array usando un bucle y el método equalsIgnoreCase().
          * - Usamos una variable booleana para indicar si se encuentra o no.
          */
         System.out.println("\n📌 EJERCICIO 2: Buscar palabra en un array");
 
         String[] animales = {"perro", "gato", "loro", "pez"};
         System.out.print("🔎 ¿Qué animal deseas buscar? ");
         String buscar = sc.nextLine();
 
         boolean encontrado = false;
         for (String animal : animales) {
             if (animal.equalsIgnoreCase(buscar)) {
                 encontrado = true;
                 break;
             }
         }
 
         if (encontrado) {
             System.out.println("✅ El animal está en la lista.");
         } else {
             System.out.println("❌ El animal NO está en la lista.");
         }
 
         // ✅ TAREA ALUMNO: Haz que el usuario introduzca los animales en lugar de estar predefinidos.
     }
 
     // 🔹 EJERCICIO 3: FRASE A ARRAY USANDO SPLIT
     public static void fraseASplit(Scanner sc) {
         /*
          * 📖 TEORÍA:
          * - El método `split(" ")` convierte una cadena en un array separando por espacios.
          * - Esto es útil para analizar palabras individuales dentro de un texto.
          */
         System.out.println("\n📌 EJERCICIO 3: Convertir frase en array con split");
 
         System.out.print("✍️ Escribe una frase: ");
         String frase = sc.nextLine();
 
         String[] palabras = frase.split(" "); // ? Separar por espacios
 
         System.out.println("🧾 Palabras encontradas: " + Arrays.toString(palabras));
         System.out.println("🔢 Total de palabras: " + palabras.length);
 
         // ✅ TAREA ALUMNO: Modifica el código para ignorar mayúsculas y contar cuántas veces aparece la palabra "java"
     }
 
     // 🔹 EJERCICIO 4: ORDENAR ALFABÉTICAMENTE
     public static void ordenarPalabras(Scanner sc) {
         /*
          * 📖 TEORÍA:
          * - La clase `Arrays` ofrece métodos útiles como `sort()` para ordenar elementos.
          * - Funciona tanto con números como con texto.
          */
         System.out.println("\n📌 EJERCICIO 4: Ordenar palabras alfabéticamente");
 
         System.out.print("🔢 ¿Cuántas palabras vas a ingresar? ");
         int cantidad = sc.nextInt();
         sc.nextLine(); // Limpiar buffer
 
         String[] palabras = new String[cantidad];
         for (int i = 0; i < cantidad; i++) {
             System.out.print("Palabra " + (i + 1) + ": ");
             palabras[i] = sc.nextLine();
         }
 
         Arrays.sort(palabras); // ? Ordenamos
         System.out.println("📚 Palabras ordenadas: " + Arrays.toString(palabras));
 
         // ✅ TAREA ALUMNO: Agrega opción para mostrar la palabra más corta y la más larga.
     }
 }
 