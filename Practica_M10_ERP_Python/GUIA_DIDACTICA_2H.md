# 📘 Guia Didactica M10 (2 horas)
## Practica ERP en Python: un solo menu de productos + persistencia en archivos

---

## 🧭 1) Contexto y alcance

Centro:
- Generalitat de Catalunya
- Departament d'Educacio i Universitats
- Institut d'Ensenyaments Professionals Provencana
- Grupo: `2o DAM`
- Modulo: `M10`
- Profesor: `Daniel Molla`

Alcance exacto de esta sesion:
- Se trabaja **solo un menu**: productos/cesta.
- Todo se guarda en archivos.
- No hay segundo menu.

Menu del ejercicio:
1. Comprar producto (mostrar stock).
2. Mostrar cesta.
3. Generar factura (vaciar o no la cesta).
4. Salir.

---

## 🎯 2) Que aprendera el alumno

Al final de la clase, el alumno podra:
- Construir un menu en bucle con validaciones.
- Aplicar POO por capas (`MenuTienda`, `ServicioTienda`, `PersistenciaTienda`).
- Guardar y recuperar informacion en JSON/TXT.
- Aplicar reglas de negocio reales (stock, cesta, factura).
- Extender el sistema con nuevas funciones.

---

## 🧱 3) Estructura tecnica que se enseña

Archivos que usa la practica:
- `practica_erp_cesta.py`
- `datos_erp/productos.json`
- `datos_erp/cesta.json`
- `datos_erp/facturas_index.json`
- `datos_erp/facturas/*.txt`
- `datos_erp/facturas/*.json`

Arquitectura (explicacion didactica):
- `PersistenciaTienda`: solo lectura/escritura en archivos.
- `ServicioTienda`: reglas de negocio.
- `MenuTienda`: entrada/salida por consola.

---

## ⏱️ 4) Guion paso a paso (120 minutos)

| Minutos | Docente hace | Alumno hace | Evidencia visible | Que aprende |
|---|---|---|---|---|
| 0-10 | Presenta enunciado y regla de "un menu" | Resume en 2 frases el problema | Explicacion oral breve | Comprension del alcance |
| 10-20 | Explica capas del programa | Ubica clases en el `.py` | Señala clases correctas | Separacion de responsabilidades |
| 20-30 | Explica mapa de archivos | Abre `datos_erp` y revisa ficheros | Identifica cada archivo | Persistencia ERP basica |
| 30-40 | Ejecuta programa con ejemplo corto | Repite ejecucion local | Menu funcionando | Flujo principal |
| 40-55 | Demuestra compra valida e invalida | Prueba ID incorrecto y cantidad negativa | Errores controlados | Validaciones de entrada |
| 55-70 | Explica actualizacion de stock/cesta | Compara JSON antes/despues | Cambios en `productos.json` y `cesta.json` | Trazabilidad de datos |
| 70-85 | Genera factura y explica importes | Factura con y sin vaciado de cesta | TXT/JSON creados | Reglas de facturacion |
| 85-95 | Repite flujo completo rapido | Compra + cesta + factura | Caso completo | Integracion de modulos |
| 95-110 | Lanza retos de ampliacion | Implementa un reto guiado | Nueva funcion funcionando | Pensamiento de mejora |
| 110-120 | Cierre y rubricado | Entrega mini-demo | Checklist completado | Autoevaluacion tecnica |

---

## 🗣️ 5) Script didactico para explicar en clase

Frases cortas para usar como profesor:
- "Primero entendemos el problema, luego tocamos codigo."
- "Si mezclo menu y negocio, despues no puedo escalar facil."
- "Comprar no es solo imprimir: es validar, actualizar y guardar."
- "ERP sin persistencia no existe; por eso revisamos los JSON."
- "Factura es evidencia: debe quedar en archivo siempre."

---

## 🧪 6) Practica guiada (paso a paso en aula)

### Paso 1: Arrancar
1. Abrir terminal en `Practica_M10_ERP_Python`.
2. Ejecutar `python practica_erp_cesta.py`.
3. Verificar que aparece el bloque de enunciado y teoria.

Resultado esperado:
- Se muestra un unico menu con 4 opciones.

### Paso 2: Comprar producto
1. Elegir opcion `1`.
2. Introducir un `ID` valido.
3. Introducir `cantidad` positiva.
4. Confirmar mensaje de compra correcta.

Resultado esperado:
- Baja el stock en `productos.json`.
- Sube la cesta en `cesta.json`.

### Paso 3: Validar errores
1. Repetir opcion `1`.
2. Probar ID inexistente.
3. Probar cantidad negativa.
4. Probar cantidad superior al stock.

Resultado esperado:
- Errores claros y programa sin caerse.

### Paso 4: Mostrar cesta
1. Elegir opcion `2`.
2. Revisar lineas y subtotal.

Resultado esperado:
- Tabla de cesta correcta.

### Paso 5: Generar factura
1. Elegir opcion `3`.
2. Probar respuesta `s` y luego `n` en ejecuciones distintas.
3. Revisar carpeta `datos_erp/facturas`.

Resultado esperado:
- Se crea factura TXT y JSON.
- Se actualiza `facturas_index.json`.

### Paso 6: Cerrar
1. Elegir opcion `4`.
2. Verificar salida limpia.

---

## 🧩 7) Ejercicios didacticos para el alumno

### A) Comprension (10-15 min)
1. Explica que archivo cambia al comprar y por que.
2. Explica que archivo cambia al facturar y por que.
3. Explica la diferencia entre vaciar y no vaciar cesta.

### B) Aplicacion (15-20 min)
1. Haz dos compras del mismo producto.
2. Demuestra que acumula cantidad en una linea de cesta.
3. Enseña stock final correcto.

### C) Ampliacion (20-25 min)
1. Implementar historial de facturas.
2. Implementar eliminacion parcial de producto en cesta.
3. Implementar descuento por volumen.

---

## ✅ 8) Checklist de evaluacion rapida

Marca `OK` o `NO`:
- `OK/NO` Existe un solo menu (productos/cesta).
- `OK/NO` Compra valida descuenta stock.
- `OK/NO` Compra invalida no rompe la ejecucion.
- `OK/NO` Cesta se guarda en archivo.
- `OK/NO` Factura se genera en TXT y JSON.
- `OK/NO` Se actualiza indice de facturas.
- `OK/NO` Codigo separado por capas.

---

## 🧯 9) Errores frecuentes y correccion en clase

Error comun:
- El alumno calcula totales en la capa de persistencia.
Correccion:
- Mover calculo a `ServicioTienda`.

Error comun:
- El alumno modifica stock pero no guarda archivo.
Correccion:
- Verificar llamadas a `guardar_productos`.

Error comun:
- El alumno agrega segundo menu.
Correccion:
- Recordar alcance: solo menu de productos/cesta.

---

## 🏁 10) Cierre de clase y entrega minima

Entrega minima funcional:
1. Programa ejecuta menu 1-4.
2. Compra actualiza stock/cesta.
3. Factura se guarda en archivos.
4. Flujo estable con errores controlados.

Entregable recomendado:
- Captura de consola de una compra.
- Captura de `productos.json` actualizado.
- Captura de una factura generada.

