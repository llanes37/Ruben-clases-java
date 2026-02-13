#!/usr/bin/env python3
"""
===============================================================================
PRACTICA M10 ERP - CESTA (SENCILLO)
===============================================================================

ENUNCIADO (claro y corto)
Crear un programa en Python para gestionar la cesta de compra de una tienda.
Debe tener SOLO este menu:
1) Comprar producto (mostrar stock)
2) Mostrar cesta
3) Generar factura (preguntar si vaciar cesta)
4) Salir

Condiciones:
- Solo hay un menu (productos/cesta).
- Despues de cada opcion (menos salir), vuelve al menu.
- Toda la informacion se guarda en archivos.

-------------------------------------------------------------------------------
TEORIA RAPIDA
-------------------------------------------------------------------------------
Arquitectura minima:
- Modelo: Producto (datos de cada producto).
- Clase principal: TiendaCestaSencilla (reglas + archivos + menu).

Ficheros usados (persistencia):
- datos_erp_sencillo/productos.json
- datos_erp_sencillo/cesta.json
- datos_erp_sencillo/facturas/factura_*.txt
- datos_erp_sencillo/facturas_index.json

-------------------------------------------------------------------------------
ATRIBUTOS Y METODOS (para explicar en clase)
-------------------------------------------------------------------------------
Clase Producto:
- id_producto: int
- nombre: str
- precio: float
- stock: int

Clase TiendaCestaSencilla:
Atributos:
- base_dir
- productos_file
- cesta_file
- facturas_dir
- index_file

Metodos:
- _crear_estructura_inicial()
- _cargar_json(ruta, default)
- _guardar_json(ruta, data)
- cargar_productos(), guardar_productos(productos)
- cargar_cesta(), guardar_cesta(cesta)
- comprar(id_producto, cantidad)
- mostrar_cesta()
- generar_factura(vaciar_cesta)
- ejecutar_menu()

-------------------------------------------------------------------------------
OBJETIVO DIDACTICO
-------------------------------------------------------------------------------
Esta version es intencionalmente simple: menos codigo, mas legible y muy
comentada para que el alumno pueda entender y extender.
===============================================================================
"""

from __future__ import annotations

import json
from dataclasses import asdict, dataclass
from datetime import datetime
from pathlib import Path
from typing import Any


# * Constante de negocio facil de localizar para el alumno.
IVA = 0.21


@dataclass
class Producto:
    # * Modelo de datos de cada producto.
    id_producto: int
    nombre: str
    precio: float
    stock: int


class TiendaCestaSencilla:
    # * Clase unica de trabajo: aqui vive casi toda la practica.
    # ! Mantiene el ejercicio simple para 2o DAM.
    def __init__(self, base_dir: Path) -> None:
        self.base_dir = base_dir
        self.productos_file = self.base_dir / "productos.json"
        self.cesta_file = self.base_dir / "cesta.json"
        self.facturas_dir = self.base_dir / "facturas"
        self.index_file = self.base_dir / "facturas_index.json"
        self._crear_estructura_inicial()

    def _crear_estructura_inicial(self) -> None:
        # * Crea carpetas/archivos al primer arranque.
        self.base_dir.mkdir(parents=True, exist_ok=True)
        self.facturas_dir.mkdir(parents=True, exist_ok=True)

        if not self.productos_file.exists():
            productos_iniciales = [
                Producto(1, "Portatil basico", 599.0, 8),
                Producto(2, "Monitor 24", 149.0, 12),
                Producto(3, "Teclado", 29.9, 20),
                Producto(4, "Raton", 19.9, 30),
            ]
            self.guardar_productos(productos_iniciales)

        if not self.cesta_file.exists():
            self._guardar_json(self.cesta_file, [])

        if not self.index_file.exists():
            self._guardar_json(self.index_file, [])

    def _cargar_json(self, ruta: Path, default: Any) -> Any:
        # * Lectura robusta para evitar que el programa se rompa.
        try:
            with ruta.open("r", encoding="utf-8") as file:
                return json.load(file)
        except (OSError, json.JSONDecodeError):
            return default

    def _guardar_json(self, ruta: Path, data: Any) -> None:
        with ruta.open("w", encoding="utf-8") as file:
            json.dump(data, file, ensure_ascii=False, indent=2)

    def cargar_productos(self) -> list[Producto]:
        data = self._cargar_json(self.productos_file, [])
        return [Producto(**item) for item in data]

    def guardar_productos(self, productos: list[Producto]) -> None:
        self._guardar_json(self.productos_file, [asdict(p) for p in productos])

    def cargar_cesta(self) -> list[dict[str, Any]]:
        # * Cesta simple en diccionarios para no complicar al alumno.
        return self._cargar_json(self.cesta_file, [])

    def guardar_cesta(self, cesta: list[dict[str, Any]]) -> None:
        self._guardar_json(self.cesta_file, cesta)

    def comprar(self, id_producto: int, cantidad: int) -> str:
        # ! Reglas minimas: cantidad > 0, producto existe, stock suficiente.
        if cantidad <= 0:
            raise ValueError("La cantidad debe ser mayor que 0.")

        productos = self.cargar_productos()
        cesta = self.cargar_cesta()

        producto = next((p for p in productos if p.id_producto == id_producto), None)
        if producto is None:
            raise ValueError("ID de producto no valido.")
        if producto.stock < cantidad:
            raise ValueError(f"Stock insuficiente. Disponible: {producto.stock}")

        producto.stock -= cantidad

        item = next((i for i in cesta if i["id_producto"] == id_producto), None)
        if item:
            item["cantidad"] += cantidad
        else:
            cesta.append(
                {
                    "id_producto": producto.id_producto,
                    "nombre": producto.nombre,
                    "precio_unitario": producto.precio,
                    "cantidad": cantidad,
                }
            )

        self.guardar_productos(productos)
        self.guardar_cesta(cesta)
        return f"Compra realizada: {cantidad} x {producto.nombre}"

    def mostrar_cesta(self) -> str:
        cesta = self.cargar_cesta()
        if not cesta:
            return "La cesta esta vacia."

        total = 0.0
        lineas = ["Cesta actual:"]
        for item in cesta:
            subtotal = item["precio_unitario"] * item["cantidad"]
            total += subtotal
            lineas.append(
                f"- {item['nombre']} | {item['cantidad']} x {item['precio_unitario']:.2f} = {subtotal:.2f} EUR"
            )
        lineas.append(f"Total sin IVA: {total:.2f} EUR")
        return "\n".join(lineas)

    def generar_factura(self, vaciar_cesta: bool) -> Path:
        cesta = self.cargar_cesta()
        if not cesta:
            raise ValueError("No se puede facturar porque la cesta esta vacia.")

        base = sum(item["precio_unitario"] * item["cantidad"] for item in cesta)
        iva = base * IVA
        total = base + iva
        fecha = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

        lineas = [
            "==================================================",
            "FACTURA - PRACTICA CESTA (SENCILLO)",
            "==================================================",
            f"Fecha: {fecha}",
            "Detalle:",
        ]
        for item in cesta:
            subtotal = item["precio_unitario"] * item["cantidad"]
            lineas.append(
                f"- {item['nombre']} | {item['cantidad']} x {item['precio_unitario']:.2f} = {subtotal:.2f} EUR"
            )
        lineas.extend(
            [
                "--------------------------------------------------",
                f"Base imponible: {base:.2f} EUR",
                f"IVA (21%):      {iva:.2f} EUR",
                f"TOTAL:          {total:.2f} EUR",
                "==================================================",
            ]
        )

        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        ruta_factura = self.facturas_dir / f"factura_{timestamp}.txt"
        with ruta_factura.open("w", encoding="utf-8") as file:
            file.write("\n".join(lineas) + "\n")

        index_data = self._cargar_json(self.index_file, [])
        index_data.append(
            {"fecha": fecha, "archivo": ruta_factura.name, "total": round(total, 2)}
        )
        self._guardar_json(self.index_file, index_data)

        if vaciar_cesta:
            self.guardar_cesta([])

        return ruta_factura

    def ejecutar_menu(self) -> None:
        # ? Para clase: pedir al alumno que explique este while.
        while True:
            print("\n=== MENU CESTA (SENCILLO) ===")
            print("1) Comprar producto")
            print("2) Mostrar cesta")
            print("3) Generar factura")
            print("4) Salir")
            opcion = input("Elige opcion: ").strip()

            if opcion == "1":
                self._flujo_comprar()
            elif opcion == "2":
                print(self.mostrar_cesta())
            elif opcion == "3":
                self._flujo_factura()
            elif opcion == "4":
                print("Fin del programa.")
                break
            else:
                print("Opcion no valida.")

    def _flujo_comprar(self) -> None:
        productos = self.cargar_productos()
        print("\nProductos disponibles:")
        for p in productos:
            print(f"{p.id_producto}) {p.nombre} - {p.precio:.2f} EUR - stock {p.stock}")

        try:
            id_producto = int(input("ID del producto: ").strip())
            cantidad = int(input("Cantidad: ").strip())
            print(self.comprar(id_producto, cantidad))
        except ValueError as error:
            print(f"Error: {error}")

    def _flujo_factura(self) -> None:
        respuesta = input("Vaciar cesta despues de facturar? (s/n): ").strip().lower()
        vaciar = respuesta in {"s", "si"}
        try:
            ruta = self.generar_factura(vaciar)
            print(f"Factura guardada en: {ruta}")
        except ValueError as error:
            print(f"Error: {error}")


def main() -> None:
    # * Entrada principal de la version sencilla.
    base_dir = Path(__file__).resolve().parent / "datos_erp_sencillo"
    app = TiendaCestaSencilla(base_dir)
    app.ejecutar_menu()


# =============================================================================
# RETOS DEL ALUMNO (opcional)
# =============================================================================
# TODO 1: Crear opcion para ver historial de facturas leyendo facturas_index.json.
# TODO 2: Permitir eliminar unidades de un producto de la cesta.
# TODO 3: Aplicar descuento del 10% si cantidad >= 5 en la factura.
# =============================================================================

if __name__ == "__main__":
    main()
