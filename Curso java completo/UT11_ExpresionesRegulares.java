/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 11: EXPRESIONES REGULARES
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/*
 * ******************************************************************************************
 *                        📘 TEORÍA Y CONCEPTOS: EXPRESIONES REGULARES EN JAVA
 * ──────────────────────────────────────────────────────────────────────────────
 * ✅ Las expresiones regulares son patrones utilizados para buscar o validar texto.
 * ✅ En Java se usan mediante la clase `Pattern` y `Matcher` del paquete `java.util.regex`.
 * ✅ Sirven para validar correos, teléfonos, contraseñas, formatos, etc.
 * 
 * 🚀 ¡Practica validando cadenas con patrones usando expresiones regulares!
 ******************************************************************************************
 */

 import java.util.Scanner;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class UT11_ExpresionesRegulares {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         System.out.println("📌 UNIDAD 11: EXPRESIONES REGULARES EN JAVA");
 
         // 🔹 Ejemplo 1: Validar un correo electrónico
         validarCorreo(sc);
 
         // 🔹 Ejemplo 2: Validar un número de teléfono
         validarTelefono(sc);
 
         // 🔹 Ejemplo 3: Validar contraseña segura
         validarContrasena(sc);
 
         // 🎯 Ejercicio final para el alumno
         // 🟡 TAREA: Crear una función que valide si una matrícula de coche es válida (formato: 0000XXX)
 
         sc.close();
     }
 
     // 🔵 EJEMPLO 1: Validar correo electrónico
     public static void validarCorreo(Scanner sc) {
         System.out.println("\n📧 EJEMPLO 1: Validación de correo electrónico");
         System.out.print("Introduce un correo: ");
         String correo = sc.nextLine();
 
         // 📌 Patrón de expresión regular para correo
         String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(correo);
 
         if (matcher.matches()) {
             System.out.println("✅ Correo válido.");
         } else {
             System.out.println("❌ Correo inválido.");
         }
 
         // 🟡 TAREA: Modifica el patrón para permitir solo dominios ".com"
     }
 
     // 🔵 EJEMPLO 2: Validar teléfono español (9 dígitos, empieza por 6, 7 o 9)
     public static void validarTelefono(Scanner sc) {
         System.out.println("\n📱 EJEMPLO 2: Validación de número de teléfono");
         System.out.print("Introduce un número de teléfono: ");
         String telefono = sc.nextLine();
 
         String regex = "^[679]\\d{8}$"; // empieza por 6, 7 o 9 y tiene 9 dígitos
         boolean valido = Pattern.matches(regex, telefono);
 
         if (valido) {
             System.out.println("✅ Teléfono válido.");
         } else {
             System.out.println("❌ Teléfono inválido.");
         }
 
         // 🟡 TAREA: Cambia el patrón para que solo valide teléfonos que empiecen por 6
     }
 
     // 🔵 EJEMPLO 3: Validar contraseña fuerte
     public static void validarContrasena(Scanner sc) {
         System.out.println("\n🔐 EJEMPLO 3: Validación de contraseña segura");
         System.out.print("Introduce una contraseña: ");
         String pass = sc.nextLine();
 
         /*
          * ✔ Requisitos:
          * - Al menos 8 caracteres
          * - Al menos una letra mayúscula
          * - Al menos una minúscula
          * - Al menos un número
          * - Al menos un carácter especial
          */
         String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
         boolean segura = Pattern.matches(regex, pass);
 
         if (segura) {
             System.out.println("✅ Contraseña válida y segura.");
         } else {
             System.out.println("❌ Contraseña insegura. Debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.");
         }
 
         // 🟡 TAREA: Crea una función que muestre qué parte está fallando en la contraseña
     }
 }
 