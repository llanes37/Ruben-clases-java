# 🎓 Masterclass M10 - Conceptos Clave (ERP en Python)
## Guía visual completa para explicar, practicar y construir

---

## 🧩 1) Qué estamos construyendo

Un programa de consola tipo ERP sencillo, con:
- un único menú,
- reglas de negocio,
- persistencia real en archivos,
- estructura por clases.

Objetivo real de aprendizaje:
- no solo que “funcione”, sino entender **cómo se diseña** un programa mantenible.

---

## 🏗️ 2) Arquitectura en 3 capas (idea principal)

```text
┌──────────────────────────┐
│        MENÚ/UI           │  ← Pide datos, muestra resultados
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│      NEGOCIO/SERVICIO    │  ← Aplica reglas (stock, préstamos, factura...)
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│      PERSISTENCIA        │  ← Lee/escribe JSON/TXT
└──────────────────────────┘
```

Regla de oro:
- El menú **no** decide reglas de negocio.
- La persistencia **no** calcula lógica de negocio.

---

## 📦 3) Modelo de datos (dataclass)

### ¿Qué es?
Una clase para guardar datos de forma clara y limpia.

```python
from dataclasses import dataclass

@dataclass
class Producto:
    id_producto: int
    nombre: str
    precio: float
    stock: int
```

Por qué se usa:
- código más corto,
- más legible para alumno,
- fácil de convertir a JSON.

---

## 💾 4) Persistencia en JSON (base de cualquier mini-ERP)

Patrón recomendado:

```python
def cargar_json(ruta, default):
    try:
        with open(ruta, "r", encoding="utf-8") as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        return default
```

```python
def guardar_json(ruta, data):
    with open(ruta, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
```

Qué enseñar aquí:
- lectura robusta,
- no romper programa si falta archivo,
- formato legible (`indent=2`).

---

## ⚙️ 5) Reglas de negocio (el “corazón”)

Ejemplo: comprar / prestar

```python
if cantidad <= 0:
    raise ValueError("La cantidad debe ser mayor que 0.")

if producto.stock < cantidad:
    raise ValueError("Stock insuficiente.")

producto.stock -= cantidad
```

Qué aprende el alumno:
- validar primero,
- modificar estado después,
- guardar al final.

---

## 🔁 6) Bucle de menú (control de flujo)

```python
while True:
    mostrar_menu()
    opcion = input("Opción: ").strip()
    if opcion == "1":
        flujo_1()
    elif opcion == "2":
        flujo_2()
    elif opcion == "3":
        flujo_3()
    elif opcion == "4":
        break
    else:
        print("Opción no válida")
```

Punto didáctico:
- este patrón se repite en muchísimos programas reales.

---

## 🧪 7) Manejo de errores didáctico

```python
try:
    id_producto = int(input("ID: ").strip())
    cantidad = int(input("Cantidad: ").strip())
    print(servicio.comprar(id_producto, cantidad))
except ValueError as e:
    print(f"Error: {e}")
```

Qué enseñar:
- `try/except` evita caídas,
- mensajes claros ayudan al usuario y al depurado.

---

## 🧾 8) Generación de informe/factura

Idea:
- construir líneas de texto,
- calcular base/IVA/total,
- guardar en archivo.

```python
lineas = []
lineas.append("FACTURA")
lineas.append(f"Fecha: {fecha}")
lineas.append(f"TOTAL: {total:.2f} EUR")

with open(ruta_factura, "w", encoding="utf-8") as f:
    f.write("\n".join(lineas))
```

Valor didáctico:
- evidencia trazable del proceso (archivo final).

---

## 📁 9) Estructura recomendada de carpetas

```text
Practica_M10_ERP_Python/
├─ practica_erp_cesta.py
├─ practica_erp_cesta_(sencillo).py
├─ practica_erp_biblioteca_(sencillo_plantilla).py
├─ GUIA_DIDACTICA_2H.md
└─ datos_.../
   ├─ productos.json / libros.json
   ├─ cesta.json / prestamos.json
   ├─ facturas_index.json
   └─ facturas/ o informes/
```

---

## 🧠 10) Preguntas potentes para clase

1. ¿Qué rompe si mezclo menú y negocio en una sola función gigante?  
2. ¿Por qué guardar en JSON y no solo en variables?  
3. ¿Qué pasa si dos acciones modifican el mismo archivo?  
4. ¿Cómo llevarías este mismo diseño a una app web?

---

## ✅ 11) Checklist de “programa bien hecho”

- [ ] Hay un único menú y cumple el enunciado.
- [ ] Las validaciones están antes de modificar datos.
- [ ] Los datos se guardan siempre en archivos.
- [ ] El código está separado por responsabilidad.
- [ ] Los errores se controlan con mensajes claros.
- [ ] Existe al menos un informe/factura/exportación.

---

## 🚀 12) Siguiente nivel (cuando terminen)

Retos sugeridos:
- historial de operaciones,
- eliminación parcial de ítems,
- descuentos por volumen,
- exportación doble (TXT + JSON),
- mini pruebas automáticas de funciones clave.

---

## 🧾 13) Frase final para alumno

Primero hazlo funcionar simple.  
Después hazlo limpio.  
Después hazlo escalable.

