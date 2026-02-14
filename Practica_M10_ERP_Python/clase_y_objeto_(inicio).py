#!/usr/bin/env python3
"""
===============================================================================
INICIO PYTHON - CLASES Y OBJETOS (MUY SENCILLO)
===============================================================================

OBJETIVO:
Aprender desde cero:
1) Que es una clase
2) Que es un objeto
3) Que son los atributos
4) Que son los metodos

Pensado para ciclo DAM (inicio de POO).

----------------------------------------------------------------------------
RESUMEN TEORICO RAPIDO
----------------------------------------------------------------------------
- Clase: "plantilla" o "molde" para crear objetos.
- Objeto: una instancia real creada desde una clase.
- Atributos: datos del objeto (ej: nombre, edad).
- Metodos: acciones del objeto (ej: presentarse, cumplir_anios).
===============================================================================
"""

# * Better Comments (VS Code + extension):
# * Verde -> explicacion importante
# ? Azul  -> pregunta para pensar
# ! Rojo  -> punto clave que no debes olvidar
# TODO    -> ejercicio para practicar


class Color:
    """Colores ANSI para terminal."""

    AZUL = "\033[38;5;39m"
    VERDE = "\033[38;5;42m"
    CELESTE = "\033[38;5;45m"
    AMARILLO = "\033[38;5;220m"
    ROJO = "\033[38;5;196m"
    RESET = "\033[0m"


class Alumno:
    """
    CLASE (molde).

    Atributos:
    - nombre (str)
    - edad (int)
    - curso (str)

    Metodos:
    - presentarse()
    - cumplir_anios()
    - cambiar_curso(nuevo_curso)
    """

    def __init__(self, nombre: str, edad: int, curso: str) -> None:
        # * __init__ se ejecuta al crear un objeto.
        self.nombre = nombre
        self.edad = edad
        self.curso = curso

    def presentarse(self) -> str:
        # * Metodo: comportamiento del objeto.
        return f"Hola, soy {self.nombre}, tengo {self.edad} años y estoy en {self.curso}."

    def cumplir_anios(self) -> None:
        # * Este metodo modifica un atributo.
        self.edad += 1

    def cambiar_curso(self, nuevo_curso: str) -> None:
        self.curso = nuevo_curso


def mostrar_titulo() -> None:
    print(f"{Color.AZUL}{'=' * 74}{Color.RESET}")
    print(f"{Color.VERDE}CLASES Y OBJETOS - GUIA INICIAL MUY SENCILLA{Color.RESET}")
    print(f"{Color.AZUL}{'=' * 74}{Color.RESET}")
    print("Qué vas a ver ahora:")
    print("1) Crear objetos")
    print("2) Leer atributos")
    print("3) Ejecutar métodos")
    print(f"{Color.AZUL}{'-' * 74}{Color.RESET}")


def demo_clase_y_objeto() -> None:
    # ! PUNTO CLAVE: aqui creamos OBJETOS a partir de la CLASE Alumno.
    alumno1 = Alumno("Lucia", 19, "1º DAM")
    alumno2 = Alumno("Mario", 21, "2º DAM")

    print(f"{Color.CELESTE}OBJETOS CREADOS:{Color.RESET}")
    print(f"- alumno1 -> {alumno1.presentarse()}")
    print(f"- alumno2 -> {alumno2.presentarse()}")

    print(f"\n{Color.CELESTE}LECTURA DE ATRIBUTOS:{Color.RESET}")
    print(f"Nombre de alumno1: {alumno1.nombre}")
    print(f"Edad de alumno1: {alumno1.edad}")
    print(f"Curso de alumno1: {alumno1.curso}")

    print(f"\n{Color.CELESTE}USO DE METODOS:{Color.RESET}")
    print("Acción: alumno1 cumple años...")
    alumno1.cumplir_anios()
    print(f"Nueva edad de alumno1: {alumno1.edad}")

    print("Acción: alumno2 cambia de curso...")
    alumno2.cambiar_curso("3º DAM (prácticas)")
    print(f"Nuevo curso de alumno2: {alumno2.curso}")


def mini_ejercicios() -> None:
    print(f"\n{Color.AMARILLO}MINI EJERCICIOS (para alumno):{Color.RESET}")
    print("1) Crea un objeto Alumno llamado alumno3 con tus datos.")
    print("2) Muestra alumno3.presentarse().")
    print("3) Llama a alumno3.cumplir_anios() y vuelve a mostrar la edad.")
    print("4) Cambia su curso con cambiar_curso(...).")
    print(
        "5) (Extra) Crea una nueva clase llamada Profesor con atributos y método presentarse()."
    )

    # TODO: Descomenta y completa este bloque para practicar.
    # alumno3 = Alumno("TU_NOMBRE", 18, "1º DAM")
    # print(alumno3.presentarse())
    # alumno3.cumplir_anios()
    # print("Edad actualizada:", alumno3.edad)
    # alumno3.cambiar_curso("2º DAM")
    # print("Nuevo curso:", alumno3.curso)


def cierre_teorico() -> None:
    print(f"\n{Color.VERDE}RESUMEN FINAL:{Color.RESET}")
    print("- Clase = plantilla.")
    print("- Objeto = instancia real creada desde una clase.")
    print("- Atributos = datos del objeto.")
    print("- Métodos = acciones del objeto.")
    print(f"{Color.AZUL}{'=' * 74}{Color.RESET}")
    # ? Pregunta para clase:
    # ? ¿Qué ventaja tiene crear una clase en lugar de usar variables sueltas?


def main() -> None:
    mostrar_titulo()
    demo_clase_y_objeto()
    mini_ejercicios()
    cierre_teorico()


if __name__ == "__main__":
    main()
