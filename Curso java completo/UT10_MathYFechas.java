
/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 10: CLASE MATH Y MANEJO DE FECHAS
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/*
 * ******************************************************************************************
 *                        📘 TEORÍA Y CONCEPTOS: MATH Y FECHAS EN JAVA
 * ──────────────────────────────────────────────────────────────────────────────
 * ✅ Aprender a usar la clase Math para realizar operaciones matemáticas comunes.
 * ✅ Conocer cómo trabajar con fechas utilizando LocalDate, LocalTime y LocalDateTime.
 * ✅ Utilizar Calendar y Date para compatibilidad con código antiguo.
 * ✅ Calcular operaciones con PI, potencias, raíces, redondeos, etc.
 * ✅ Formatear fechas y obtener la hora actual.
 ******************************************************************************************
 */

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class UT10_MathYFechas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        menu(sc);

        sc.close();
    }

    // 🔸 MENÚ DE OPCIONES
    public static void menu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n📌 MENÚ PRINCIPAL - UNIDAD 10");
            System.out.println("1. Operaciones matemáticas con Math");
            System.out.println("2. Fechas y horas con LocalDate y Calendar");
            System.out.println("3. Salir");
            System.out.print("👉 Selecciona una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    operacionesMath(sc);
                    break;
                case 2:
                    manejoFechas();
                    break;
                case 3:
                    System.out.println("👋 ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intenta de nuevo.");
            }

        } while (opcion != 3);
    }

    // 🧮 FUNCIONES MATH EN JAVA
    public static void operacionesMath(Scanner sc) {
        System.out.println("\n🔢 FUNCIONES DE LA CLASE MATH");

        // * 📖 TEORÍA: La clase Math contiene métodos estáticos para operaciones matemáticas
        System.out.println("PI: " + Math.PI);  // Valor de PI
        System.out.println("Raíz cuadrada de 25: " + Math.sqrt(25));
        System.out.println("Potencia 2^3: " + Math.pow(2, 3));
        System.out.println("Valor absoluto de -15: " + Math.abs(-15));
        System.out.println("Redondeo de 5.7 -> " + Math.round(5.7));
        System.out.println("Techo (ceil) de 3.1 -> " + Math.ceil(3.1));
        System.out.println("Piso (floor) de 8.9 -> " + Math.floor(8.9));
        System.out.println("Mínimo entre 10 y 5 -> " + Math.min(10, 5));
        System.out.println("Máximo entre 10 y 5 -> " + Math.max(10, 5));

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Crea una función que reciba dos números y devuelva el mayor usando Math.max
        // * Crea una función que reciba un número decimal y devuelva su redondeo, piso y techo.
    }

    // 📅 MANEJO DE FECHAS Y HORAS
    public static void manejoFechas() {
        System.out.println("\n📆 MANEJO DE FECHAS Y HORAS");

        // 📌 LocalDate para fechas actuales
        LocalDate fechaHoy = LocalDate.now();
        System.out.println("📅 Fecha actual: " + fechaHoy);

        // 📌 LocalTime para hora actual
        LocalTime horaActual = LocalTime.now();
        System.out.println("⏰ Hora actual: " + horaActual);

        // 📌 LocalDateTime combina ambas
        LocalDateTime fechaYHora = LocalDateTime.now();
        System.out.println("📆⏰ Fecha y hora actuales: " + fechaYHora);

        // 📌 Formato personalizado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("🧾 Fecha formateada: " + fechaYHora.format(formato));

        // 📌 Uso de Calendar (clase antigua)
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        System.out.println("📅 Fecha desde Calendar: " + dia + "/" + mes + "/" + anio);

        // ! ✅ TAREA PARA EL ALUMNO:
        // * Muestra la hora actual con formato 12h y 24h.
        // * Pide al usuario su fecha de nacimiento y calcula cuántos años tiene.
    }
}
