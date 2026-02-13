#!/usr/bin/env python3
"""
===============================================================================
PRACTICA M10 - ERP (PYTHON) | VERSION DIDACTICA EN EL .PY
===============================================================================

CENTRO:
- Generalitat de Catalunya
- Departament d'Educacio i Universitats
- Institut d'Ensenyaments Professionals Provencana
- Grupo: 2o DAM
- Profesor: Daniel Molla

ENUNCIADO TRABAJADO (TRADUCIDO AL ESPANOL):
Realiza un programa en Python, estructurado con objetos, que gestione la cesta
de la compra de una tienda con este menu:
1) Comprar un producto (mostrando productos en stock).
2) Mostrar la cesta de la compra.
3) Generar factura y elegir si vaciar la cesta o seguir comprando.
4) Salir.

REGLAS CLAVE:
- Solo se trabaja este menu de productos/cesta (no hay dos menus).
- En cualquier opcion excepto salir, se vuelve a mostrar el menu.
- Toda la informacion debe guardarse en archivos.

===============================================================================
GUIA RAPIDA DE CLASE (2 HORAS) DENTRO DEL PROPIO .PY
===============================================================================
0:00 - 0:25  Teoria:
  - Separacion de capas: menu, servicio (negocio), persistencia (ficheros).
  - Modelos de datos: Producto e ItemCesta.
  - Flujo: comprar -> guardar -> mostrar -> facturar -> guardar.

0:25 - 1:15  Practica guiada:
  - Probar compra de productos y validaciones.
  - Verificar que se actualiza stock y cesta en JSON.
  - Generar factura y comprobar archivos .txt + .json.

1:15 - 2:00  Ejercicios del alumno:
  - Reto 1: historial de facturas.
  - Reto 2: eliminar producto de cesta.
  - Reto 3: descuentos por volumen.
  (Plantillas al final del archivo, seccion "RETOS DEL ALUMNO")

===============================================================================
BETTER COMMENTS (en VS Code con extension Better Comments):
- # * Explicacion importante de teoria/arquitectura.
- # ? Pregunta guiada para trabajar en clase.
- # ! Punto critico que no se debe romper.
- # TODO: Trabajo del alumno / ampliaciones.

TEORIA AMPLIADA (PARA LEER DIRECTAMENTE EN ESTE .PY):
1) POO APLICADA:
   - Modelo: clases simples de datos (Producto, ItemCesta).
   - Servicio: reglas de negocio (stock, validaciones, factura).
   - Persistencia: entrada/salida de archivos (JSON/TXT).
   - Vista consola: menu e interaccion con input/print.

2) PRINCIPIO DE RESPONSABILIDAD UNICA:
   - Cada clase tiene una responsabilidad principal.
   - Esto facilita mantenimiento, pruebas y ampliaciones.

3) PERSISTENCIA MINIMA DE UN ERP:
   - Catalogo y stock en "productos.json".
   - Estado de compra en "cesta.json".
   - Evidencia de venta en "facturas/*.txt|*.json".
   - Resumen de facturas en "facturas_index.json".

4) FLUJO FUNCIONAL DEL PROGRAMA:
   Comprar -> Validar -> Actualizar stock -> Guardar archivos
   Mostrar cesta -> Leer archivo -> Pintar resumen
   Facturar -> Calcular importes -> Guardar factura -> (vaciar opcional)

5) REGLA DEL ENUNCIADO DEL ALUMNO:
   - SOLO menu de productos/cesta.
   - NO existe segundo menu en esta practica.
===============================================================================
"""

from __future__ import annotations

import json
from dataclasses import asdict, dataclass
from datetime import datetime
from pathlib import Path
from typing import Any

# * IVA fijo para la practica.
IVA = 0.21


@dataclass
class Producto:
    """
    Entidad de negocio: producto del catalogo.
    """
    # * Modelo de producto disponible en catalogo.
    id_producto: int
    nombre: str
    precio: float
    stock: int


@dataclass
class ItemCesta:
    """
    Entidad de negocio: linea de compra dentro de la cesta.
    """
    # * Modelo de linea de compra dentro de la cesta.
    id_producto: int
    nombre: str
    precio_unitario: float
    cantidad: int

    @property
    def subtotal(self) -> float:
        # * Subtotal por linea = precio_unitario x cantidad.
        return self.precio_unitario * self.cantidad


class PersistenciaTienda:
    """
    Responsable de almacenar y recuperar informacion desde archivos.
    """
    # * Capa de persistencia: solo lectura/escritura de archivos.
    # ! No meter logica de negocio aqui (solo guardar/cargar datos).
    def __init__(self, base_dir: Path) -> None:
        self.base_dir = base_dir
        self.productos_file = self.base_dir / "productos.json"
        self.cesta_file = self.base_dir / "cesta.json"
        self.facturas_dir = self.base_dir / "facturas"
        self.indice_facturas_file = self.base_dir / "facturas_index.json"
        self._asegurar_estructura()

    def _asegurar_estructura(self) -> None:
        """
        Crea carpetas y archivos base si no existen.
        """
        # * Crea estructura minima de datos para que el programa siempre arranque.
        self.base_dir.mkdir(parents=True, exist_ok=True)
        self.facturas_dir.mkdir(parents=True, exist_ok=True)

        if not self.productos_file.exists():
            productos_iniciales = [
                Producto(1, "Portatil Lenovo", 699.00, 10),
                Producto(2, "Monitor 24 pulgadas", 149.90, 15),
                Producto(3, "Teclado mecanico", 59.95, 20),
                Producto(4, "Raton inalambrico", 24.50, 25),
                Producto(5, "Disco SSD 1TB", 89.99, 12),
            ]
            self.guardar_productos(productos_iniciales)

        if not self.cesta_file.exists():
            self._escribir_json(self.cesta_file, [])

        if not self.indice_facturas_file.exists():
            self._escribir_json(self.indice_facturas_file, [])

    def _leer_json(self, ruta: Path, default: Any) -> Any:
        """
        Lee JSON de forma segura.
        Si hay error de lectura o formato, devuelve "default".
        """
        # * Lectura robusta: si el JSON esta roto o falla el I/O, devuelve default.
        try:
            with ruta.open("r", encoding="utf-8") as file:
                return json.load(file)
        except (json.JSONDecodeError, OSError):
            return default

    def _escribir_json(self, ruta: Path, data: Any) -> None:
        """
        Escribe datos en JSON con formato legible.
        """
        # * Guardado estandar UTF-8 con indentado para que el alumno lo lea facil.
        with ruta.open("w", encoding="utf-8") as file:
            json.dump(data, file, ensure_ascii=False, indent=2)

    def cargar_productos(self) -> list[Producto]:
        """
        Recupera el catalogo de productos desde archivo.
        """
        data = self._leer_json(self.productos_file, [])
        return [Producto(**item) for item in data]

    def guardar_productos(self, productos: list[Producto]) -> None:
        """
        Guarda el catalogo de productos (incluyendo stock actualizado).
        """
        data = [asdict(p) for p in productos]
        self._escribir_json(self.productos_file, data)

    def cargar_cesta(self) -> list[ItemCesta]:
        """
        Recupera la cesta actual desde archivo.
        """
        data = self._leer_json(self.cesta_file, [])
        return [ItemCesta(**item) for item in data]

    def guardar_cesta(self, cesta: list[ItemCesta]) -> None:
        """
        Guarda la cesta actual en archivo.
        """
        data = [asdict(item) for item in cesta]
        self._escribir_json(self.cesta_file, data)

    def guardar_factura(self, factura: dict[str, Any], texto_factura: str) -> Path:
        """
        Guarda una factura en TXT y JSON y actualiza el indice de facturas.
        """
        # * Guarda factura en doble formato (txt y json) + actualiza indice.
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        numero_factura = f"FAC-{timestamp}"
        ruta_factura_txt = self.facturas_dir / f"{numero_factura}.txt"
        ruta_factura_json = self.facturas_dir / f"{numero_factura}.json"

        factura["numero_factura"] = numero_factura
        factura["archivo_texto"] = str(ruta_factura_txt.name)

        with ruta_factura_txt.open("w", encoding="utf-8") as file:
            file.write(texto_factura)

        self._escribir_json(ruta_factura_json, factura)

        index_data = self._leer_json(self.indice_facturas_file, [])
        index_data.append(
            {
                "numero_factura": numero_factura,
                "fecha": factura["fecha"],
                "total": factura["total"],
                "archivo_texto": ruta_factura_txt.name,
            }
        )
        self._escribir_json(self.indice_facturas_file, index_data)
        return ruta_factura_txt


class ServicioTienda:
    """
    Responsable de aplicar reglas de negocio de la tienda.
    """
    # * Capa de negocio:
    #   valida reglas, actualiza stock, gestiona cesta y compone factura.
    def __init__(self, persistencia: PersistenciaTienda) -> None:
        self.persistencia = persistencia

    def obtener_productos(self) -> list[Producto]:
        """
        Devuelve productos para mostrar en menu.
        """
        return self.persistencia.cargar_productos()

    def obtener_cesta(self) -> list[ItemCesta]:
        """
        Devuelve la cesta actual para visualizar.
        """
        return self.persistencia.cargar_cesta()

    def comprar_producto(self, id_producto: int, cantidad: int) -> str:
        """
        Aplica una compra:
        - valida entrada,
        - valida stock,
        - descuenta stock,
        - acumula en cesta,
        - persiste cambios.
        """
        # ! Regla 1: la cantidad debe ser positiva.
        if cantidad <= 0:
            raise ValueError("La cantidad debe ser mayor que 0.")

        productos = self.persistencia.cargar_productos()
        cesta = self.persistencia.cargar_cesta()

        # * Busca producto por ID.
        producto = next((p for p in productos if p.id_producto == id_producto), None)
        if producto is None:
            raise ValueError("No existe un producto con ese ID.")

        # ! Regla 2: no vender por encima del stock actual.
        if producto.stock < cantidad:
            raise ValueError(
                f"No hay stock suficiente. Stock actual: {producto.stock} unidades."
            )

        # * Si todo es correcto: descontamos stock.
        producto.stock -= cantidad

        # * Acumula cantidades en cesta si ya existe ese producto.
        item = next((i for i in cesta if i.id_producto == id_producto), None)
        if item:
            item.cantidad += cantidad
        else:
            cesta.append(
                ItemCesta(
                    id_producto=producto.id_producto,
                    nombre=producto.nombre,
                    precio_unitario=producto.precio,
                    cantidad=cantidad,
                )
            )

        # ! Persistir SIEMPRE tras una compra valida.
        self.persistencia.guardar_productos(productos)
        self.persistencia.guardar_cesta(cesta)
        return f"Anadido a la cesta: {cantidad} x {producto.nombre}."

    def generar_factura(self, vaciar_cesta: bool) -> tuple[str, Path]:
        """
        Genera factura desde la cesta, la guarda en archivos
        y opcionalmente vacia la cesta.
        """
        cesta = self.persistencia.cargar_cesta()
        if not cesta:
            raise ValueError("La cesta esta vacia. No se puede generar factura.")

        base_imponible = sum(item.subtotal for item in cesta)
        iva = base_imponible * IVA
        total = base_imponible + iva
        fecha = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

        # * Construccion de factura legible para el alumno (archivo TXT).
        lineas = []
        lineas.append("=" * 62)
        lineas.append("FACTURA - PRACTICA M10 ERP")
        lineas.append("=" * 62)
        lineas.append(f"Fecha: {fecha}")
        lineas.append("-" * 62)
        lineas.append("Detalle:")
        for item in cesta:
            lineas.append(
                f"  - {item.nombre} | {item.cantidad} x {item.precio_unitario:.2f} EUR = {item.subtotal:.2f} EUR"
            )
        lineas.append("-" * 62)
        lineas.append(f"Base imponible: {base_imponible:.2f} EUR")
        lineas.append(f"IVA (21%):      {iva:.2f} EUR")
        lineas.append(f"TOTAL:          {total:.2f} EUR")
        lineas.append("=" * 62)

        texto_factura = "\n".join(lineas) + "\n"
        factura_data = {
            "fecha": fecha,
            "items": [asdict(i) for i in cesta],
            "base_imponible": round(base_imponible, 2),
            "iva": round(iva, 2),
            "total": round(total, 2),
            "cesta_vaciada": vaciar_cesta,
        }
        ruta_factura = self.persistencia.guardar_factura(factura_data, texto_factura)

        # * Si el usuario elige vaciar, se limpia la cesta tras facturar.
        if vaciar_cesta:
            self.persistencia.guardar_cesta([])

        return texto_factura, ruta_factura


class MenuTienda:
    """
    Responsable de la interaccion por consola.
    """
    # * Paleta de colores mas "bonita" (azul/verde vivos) para terminal ANSI.
    AZUL = "\033[38;5;39m"
    VERDE = "\033[38;5;42m"
    CELESTE = "\033[38;5;45m"
    AMARILLO = "\033[38;5;220m"
    ROJO = "\033[38;5;196m"
    RESET = "\033[0m"

    def __init__(self, servicio: ServicioTienda) -> None:
        self.servicio = servicio

    def iniciar(self) -> None:
        # * Al arrancar mostramos contexto didactico para el alumno.
        self._mostrar_bloque_enunciado_y_teoria()
        self._mostrar_mapa_archivos_didactico()

        while True:
            self._mostrar_menu()
            opcion = input("Selecciona una opcion: ").strip()

            if opcion == "1":
                self._opcion_comprar()
            elif opcion == "2":
                self._opcion_mostrar_cesta()
            elif opcion == "3":
                self._opcion_generar_factura()
            elif opcion == "4":
                print(f"{self.CELESTE}Saliendo del programa...{self.RESET}")
                break
            else:
                print(f"{self.ROJO}Opcion no valida. Intenta de nuevo.{self.RESET}")

    def _mostrar_bloque_enunciado_y_teoria(self) -> None:
        # * Este bloque deja todo "orientado al .py" como pediste:
        #   enunciado + teoria + instrucciones en pantalla desde el propio codigo.
        print(f"{self.AZUL}{'=' * 78}{self.RESET}")
        print(f"{self.VERDE}ENUNCIADO (M10) - MENU DE PRODUCTOS/CARRITO{self.RESET}")
        print(f"{self.AZUL}{'=' * 78}{self.RESET}")
        print("1) Comprar producto (mostrar stock)")
        print("2) Mostrar cesta")
        print("3) Generar factura (vaciar o seguir comprando)")
        print("4) Salir")
        print("Regla: todos los datos se guardan en archivos.")
        print("Importante: este programa usa un solo menu (productos/cesta).")
        print()
        print(f"{self.CELESTE}TEORIA RAPIDA:{self.RESET}")
        print("- PersistenciaTienda: archivos JSON/TXT.")
        print("- ServicioTienda: reglas de negocio.")
        print("- MenuTienda: interaccion con usuario.")
        print(f"{self.AZUL}{'-' * 78}{self.RESET}")

    def _mostrar_mapa_archivos_didactico(self) -> None:
        # * Teoria aplicada en runtime: el alumno ve exactamente que archivo
        #   se usa para cada parte del ejercicio.
        persistencia = self.servicio.persistencia
        print(f"{self.CELESTE}MAPA DE ARCHIVOS ERP:{self.RESET}")
        print(f"- Catalogo + stock: {persistencia.productos_file}")
        print(f"- Cesta activa:     {persistencia.cesta_file}")
        print(f"- Facturas (txt):   {persistencia.facturas_dir}")
        print(f"- Indice facturas:  {persistencia.indice_facturas_file}")
        print(f"{self.AZUL}{'-' * 78}{self.RESET}")

    def _mostrar_menu(self) -> None:
        print()
        print(f"{self.AZUL}{'=' * 65}{self.RESET}")
        print(f"{self.VERDE}PRACTICA M10 ERP - MENU DE PRODUCTOS{self.RESET}")
        print(f"{self.AZUL}{'=' * 65}{self.RESET}")
        print("1) Comprar un producto (mostrar stock)")
        print("2) Mostrar cesta de la compra")
        print("3) Generar factura")
        print("4) Salir")
        print(f"{self.AZUL}{'-' * 65}{self.RESET}")

    def _opcion_comprar(self) -> None:
        """
        Flujo didactico de compra:
        1) Mostrar stock.
        2) Pedir ID y cantidad.
        3) Validar y actualizar negocio.
        4) Persistir automaticamente.
        """
        productos = self.servicio.obtener_productos()
        print(f"{self.VERDE}\nPRODUCTOS DISPONIBLES{self.RESET}")
        print("-" * 65)
        print(f"{'ID':<5}{'Producto':<25}{'Precio':<15}{'Stock':<10}")
        print("-" * 65)
        for producto in productos:
            print(
                f"{producto.id_producto:<5}{producto.nombre:<25}{producto.precio:<15.2f}{producto.stock:<10}"
            )
        print("-" * 65)

        try:
            id_producto = int(input("ID del producto a comprar: ").strip())
            cantidad = int(input("Cantidad: ").strip())
            mensaje = self.servicio.comprar_producto(id_producto, cantidad)
            print(f"{self.VERDE}{mensaje}{self.RESET}")
        except ValueError as error:
            print(f"{self.ROJO}Error: {error}{self.RESET}")

    def _opcion_mostrar_cesta(self) -> None:
        """
        Muestra estado actual de la cesta leyendo desde archivo.
        """
        cesta = self.servicio.obtener_cesta()
        if not cesta:
            print(f"{self.AMARILLO}La cesta esta vacia.{self.RESET}")
            return

        print(f"{self.VERDE}\nCESTA ACTUAL{self.RESET}")
        print("-" * 82)
        print(f"{'Producto':<30}{'Cant.':<10}{'P.Unit':<15}{'Subtotal':<15}")
        print("-" * 82)

        total = 0.0
        for item in cesta:
            total += item.subtotal
            print(
                f"{item.nombre:<30}{item.cantidad:<10}{item.precio_unitario:<15.2f}{item.subtotal:<15.2f}"
            )
        print("-" * 82)
        print(f"Total parcial (sin IVA): {total:.2f} EUR")

    def _opcion_generar_factura(self) -> None:
        """
        Genera factura y guarda evidencia en archivos.
        """
        respuesta = input("Deseas vaciar la cesta tras facturar? (s/n): ").strip().lower()
        vaciar = respuesta in {"s", "si"}
        try:
            texto_factura, ruta_factura = self.servicio.generar_factura(vaciar)
            print(f"{self.VERDE}\nFACTURA GENERADA{self.RESET}")
            print(texto_factura)
            print(f"{self.CELESTE}Factura guardada en: {ruta_factura}{self.RESET}")
        except ValueError as error:
            print(f"{self.ROJO}Error: {error}{self.RESET}")


def main() -> None:
    # * Punto de entrada de la practica.
    base_dir = Path(__file__).resolve().parent / "datos_erp"
    persistencia = PersistenciaTienda(base_dir)
    servicio = ServicioTienda(persistencia)
    menu = MenuTienda(servicio)
    menu.iniciar()


# ============================================================================
# RETOS DEL ALUMNO (PARA PRACTICAR DENTRO DEL .PY)
# ============================================================================
# * EJERCICIOS DE COMPRENSION (ANTES DE PROGRAMAR)
# 1) Explica con tus palabras por que "PersistenciaTienda" no debe hacer calculos.
# 2) Indica que archivo cambia cuando compras un producto.
# 3) Indica que archivo cambia cuando generas una factura.
# 4) Que pasaria si "cesta.json" se borra en mitad de la ejecucion?
#
# * EJERCICIOS GUIADOS (DURANTE CLASE)
# 1) Haz 2 compras del mismo producto:
#    - verifica que no se crean 2 lineas nuevas, sino una linea acumulada.
# 2) Intenta comprar cantidad negativa:
#    - observa y explica el ValueError.
# 3) Intenta facturar con cesta vacia:
#    - observa y explica el ValueError.
#
# * EJERCICIOS DE AMPLIACION (PARA LA SEGUNDA HORA)
# TODO Reto 1: Crear funcion para listar historial de facturas desde
#       datos_erp/facturas_index.json y mostrar fecha + total.
#
# TODO Reto 2: Crear funcion para eliminar un producto de la cesta:
#       - pedir ID y cantidad a eliminar
#       - si la cantidad llega a 0, eliminar linea
#       - devolver stock al catalogo
#
# TODO Reto 3: Aplicar descuento por volumen:
#       - si cantidad >= 5 en una linea, aplicar 10% en esa linea
#       - reflejar descuento en factura txt/json
#
# TODO Reto 4: Anadir busqueda de productos por texto:
#       - pedir palabra clave
#       - mostrar coincidencias por nombre
#       - permitir comprar directamente desde resultados
#
# TODO Reto 5: Exportar cierre diario:
#       - leer facturas del dia
#       - totalizar ventas e IVA
#       - guardar resumen en "cierre_YYYYMMDD.txt"
#
# ? Pregunta para clase:
#   Si mezclas lectura de input() con logica de negocio en la misma clase,
#   que problema de mantenimiento aparece cuando quieras crear una interfaz web?
#
# ? Pregunta de arquitectura:
#   Que ventaja concreta aporta guardar factura en txt y json a la vez?
# ============================================================================


if __name__ == "__main__":
    main()
