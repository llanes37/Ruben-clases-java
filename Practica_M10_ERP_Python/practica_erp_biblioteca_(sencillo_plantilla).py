#!/usr/bin/env python3
"""
===============================================================================
PRACTICA M10 ERP - BIBLIOTECA (SENCILLO - PLANTILLA GUIADA)
===============================================================================

ENUNCIADO (MUY CLARO):
Desarrolla un programa en Python para gestionar prestamos de una biblioteca.
Debe tener SOLO este menu:

1) Prestar un libro
2) Devolver un libro
3) Mostrar prestamos activos
4) Generar informe de prestamos
5) Salir

REGLAS OBLIGATORIAS:
- Es una practica orientada a objetos.
- Toda la informacion se guarda en archivos JSON.
- En cualquier opcion (excepto salir), se vuelve al menu.
- El programa debe controlar errores de entrada (ID no valido, cantidades, etc.).

OBJETIVO DIDACTICO:
Esta plantilla NO trae la logica final hecha.
Trae la estructura profesional para que el alumno construya:
- modelos,
- persistencia,
- reglas de negocio,
- menu.

-------------------------------------------------------------------------------
QUE DEBE CREAR EL ALUMNO (paso a paso)
-------------------------------------------------------------------------------
PASO 1) Modelos:
- Clase Libro
- Clase Prestamo

PASO 2) Persistencia:
- Cargar/guardar libros en JSON.
- Cargar/guardar prestamos en JSON.

PASO 3) Negocio:
- Prestar (si hay stock disponible).
- Devolver (actualiza stock y elimina/actualiza prestamo).
- Generar informe.

PASO 4) Menu:
- Pedir datos por teclado.
- Llamar a metodos de negocio.
- Mostrar resultados.

-------------------------------------------------------------------------------
ARCHIVOS ESPERADOS (los crea el alumno dentro de la carpeta de datos)
-------------------------------------------------------------------------------
- libros.json
- prestamos.json
- informes/ (txt o json)

IMPORTANTE:
Este programa SI crea la carpeta base de datos, pero la deja vacia a proposito.
El alumno debe crear y poblar los JSON durante la practica.
===============================================================================
"""

from __future__ import annotations

import json
from dataclasses import dataclass, asdict
from datetime import datetime
from pathlib import Path
from typing import Any


# * Better Comments (VS Code):
# * Verde  -> explicacion tecnica importante.
# ? Azul   -> pregunta didactica para el alumno.
# ! Rojo   -> regla critica del ejercicio.
# TODO     -> tarea a implementar por el alumno.


@dataclass
class Libro:
    """
    TODO (Alumno):
    Define los atributos minimos:
    - id_libro: int
    - titulo: str
    - autor: str
    - stock: int
    """

    # TODO: El alumno debe completar estos atributos.
    # Ejemplo:
    # id_libro: int
    # titulo: str
    # autor: str
    # stock: int
    pass


@dataclass
class Prestamo:
    """
    TODO (Alumno):
    Define los atributos minimos:
    - id_libro: int
    - titulo: str
    - alumno: str
    - fecha_prestamo: str
    """

    # TODO: El alumno debe completar estos atributos.
    pass


class BibliotecaApp:
    """
    Clase principal de la practica.

    Estructura esperada:
    - atributos de rutas
    - metodos de persistencia
    - metodos de negocio
    - metodos de menu
    """

    # * Colores ANSI para hacer la experiencia mas clara en consola.
    AZUL = "\033[38;5;39m"
    VERDE = "\033[38;5;42m"
    ROJO = "\033[38;5;196m"
    AMARILLO = "\033[38;5;220m"
    RESET = "\033[0m"

    def __init__(self, base_dir: Path) -> None:
        self.base_dir = base_dir
        self.libros_file = self.base_dir / "libros.json"
        self.prestamos_file = self.base_dir / "prestamos.json"
        self.informes_dir = self.base_dir / "informes"

        # ! Regla pedida: crear carpeta de trabajo pero dejarla vacia.
        # ! No crear JSON automaticamente en esta plantilla.
        self._crear_carpeta_base_vacia()

    def _crear_carpeta_base_vacia(self) -> None:
        # * Crea la carpeta principal y subcarpeta de informes.
        self.base_dir.mkdir(parents=True, exist_ok=True)
        self.informes_dir.mkdir(parents=True, exist_ok=True)

    # -------------------------------------------------------------------------
    # BLOQUE 1 - PERSISTENCIA (a implementar)
    # -------------------------------------------------------------------------
    def cargar_json(self, ruta: Path, default: Any) -> Any:
        """
        TODO (Alumno):
        Implementar lectura JSON segura.
        Si no existe o falla, devolver 'default'.
        """
        # TODO: implementar.
        return default

    def guardar_json(self, ruta: Path, data: Any) -> None:
        """
        TODO (Alumno):
        Implementar escritura JSON.
        """
        # TODO: implementar.
        _ = (ruta, data)

    def cargar_libros(self) -> list[Libro]:
        """
        TODO (Alumno):
        1) Leer libros.json
        2) Convertir a lista de Libro
        """
        # TODO: implementar.
        return []

    def guardar_libros(self, libros: list[Libro]) -> None:
        """
        TODO (Alumno):
        Guardar lista de libros en libros.json
        """
        # TODO: implementar.
        _ = libros

    def cargar_prestamos(self) -> list[Prestamo]:
        """
        TODO (Alumno):
        1) Leer prestamos.json
        2) Convertir a lista de Prestamo
        """
        # TODO: implementar.
        return []

    def guardar_prestamos(self, prestamos: list[Prestamo]) -> None:
        """
        TODO (Alumno):
        Guardar lista de prestamos en prestamos.json
        """
        # TODO: implementar.
        _ = prestamos

    # -------------------------------------------------------------------------
    # BLOQUE 2 - NEGOCIO (a implementar)
    # -------------------------------------------------------------------------
    def prestar_libro(self, id_libro: int, alumno: str) -> str:
        """
        TODO (Alumno):
        Reglas:
        - El libro debe existir.
        - Debe haber stock > 0.
        - Se descuenta stock.
        - Se crea un prestamo con fecha actual.
        - Se guarda todo en JSON.
        """
        # TODO: implementar.
        _ = (id_libro, alumno)
        raise NotImplementedError("Implementa prestar_libro()")

    def devolver_libro(self, id_libro: int, alumno: str) -> str:
        """
        TODO (Alumno):
        Reglas:
        - Debe existir un prestamo activo de ese alumno para ese libro.
        - Se incrementa stock del libro.
        - Se elimina (o marca como devuelto) el prestamo.
        - Se guarda todo en JSON.
        """
        # TODO: implementar.
        _ = (id_libro, alumno)
        raise NotImplementedError("Implementa devolver_libro()")

    def informe_prestamos(self) -> Path:
        """
        TODO (Alumno):
        Generar informe con:
        - fecha/hora
        - total de prestamos activos
        - detalle por linea
        Guardar en carpeta informes/.
        """
        # TODO: implementar.
        raise NotImplementedError("Implementa informe_prestamos()")

    # -------------------------------------------------------------------------
    # BLOQUE 3 - MENU (base funcional, para ampliar por el alumno)
    # -------------------------------------------------------------------------
    def ejecutar_menu(self) -> None:
        self._mostrar_intro()

        while True:
            self._mostrar_menu()
            opcion = input("Selecciona opcion: ").strip()

            if opcion == "1":
                self._flujo_prestar()
            elif opcion == "2":
                self._flujo_devolver()
            elif opcion == "3":
                self._flujo_mostrar_prestamos()
            elif opcion == "4":
                self._flujo_generar_informe()
            elif opcion == "5":
                print(f"{self.AZUL}Saliendo...{self.RESET}")
                break
            else:
                print(f"{self.ROJO}Opcion no valida.{self.RESET}")

    def _mostrar_intro(self) -> None:
        print(f"{self.AZUL}{'=' * 78}{self.RESET}")
        print(
            f"{self.VERDE}PRACTICA BIBLIOTECA (PLANTILLA) - CONSTRUCCION PASO A PASO{self.RESET}"
        )
        print(f"{self.AZUL}{'=' * 78}{self.RESET}")
        print("Esta carpeta de datos esta creada y vacia:")
        print(f"- {self.base_dir}")
        print("Debes crear manualmente libros.json y prestamos.json.")
        print(f"{self.AMARILLO}Tip: empieza con 3 libros de prueba.{self.RESET}")
        print(f"{self.AZUL}{'-' * 78}{self.RESET}")

    def _mostrar_menu(self) -> None:
        print("\n--- MENU BIBLIOTECA ---")
        print("1) Prestar libro")
        print("2) Devolver libro")
        print("3) Mostrar prestamos activos")
        print("4) Generar informe")
        print("5) Salir")

    def _flujo_prestar(self) -> None:
        try:
            id_libro = int(input("ID libro: ").strip())
            alumno = input("Nombre alumno: ").strip()
            print(self.prestar_libro(id_libro, alumno))
        except NotImplementedError as error:
            print(f"{self.AMARILLO}Pendiente: {error}{self.RESET}")
        except ValueError:
            print(f"{self.ROJO}Entrada invalida.{self.RESET}")

    def _flujo_devolver(self) -> None:
        try:
            id_libro = int(input("ID libro: ").strip())
            alumno = input("Nombre alumno: ").strip()
            print(self.devolver_libro(id_libro, alumno))
        except NotImplementedError as error:
            print(f"{self.AMARILLO}Pendiente: {error}{self.RESET}")
        except ValueError:
            print(f"{self.ROJO}Entrada invalida.{self.RESET}")

    def _flujo_mostrar_prestamos(self) -> None:
        try:
            prestamos = self.cargar_prestamos()
            if not prestamos:
                print(f"{self.AMARILLO}No hay prestamos activos.{self.RESET}")
                return

            print(f"{self.VERDE}Prestamos activos:{self.RESET}")
            for prestamo in prestamos:
                # ? Alumno: cuando implementes Prestamo como dataclass,
                # ? este print puede cambiar a formato tabla.
                print(f"- {prestamo}")
        except Exception as error:
            print(f"{self.ROJO}Error al mostrar prestamos: {error}{self.RESET}")

    def _flujo_generar_informe(self) -> None:
        try:
            ruta = self.informe_prestamos()
            print(f"{self.VERDE}Informe creado en: {ruta}{self.RESET}")
        except NotImplementedError as error:
            print(f"{self.AMARILLO}Pendiente: {error}{self.RESET}")


def main() -> None:
    # * Nombre asociado para que el alumno ubique su carpeta de practica.
    base_dir = Path(__file__).resolve().parent / "datos_biblioteca_sencillo_plantilla"
    app = BibliotecaApp(base_dir)
    app.ejecutar_menu()


# -----------------------------------------------------------------------------
# RETOS EXTRA (cuando termine la version base)
# -----------------------------------------------------------------------------
# TODO 1: Validar que "alumno" no este vacio al prestar/devolver.
# TODO 2: Permitir listar libros disponibles con stock.
# TODO 3: Exportar informe en JSON ademas de TXT.
# TODO 4: Anadir fecha prevista de devolucion y alerta de retrasos.
# -----------------------------------------------------------------------------


if __name__ == "__main__":
    main()
