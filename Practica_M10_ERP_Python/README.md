# Práctica Python M10 ERP

## Archivos incluidos

- `practica_erp_cesta.py`: solución base funcional (POO + archivos).
- `practica_erp_cesta_(sencillo).py`: misma practica en version simplificada y mas didactica.
- `practica_erp_biblioteca_(sencillo_plantilla).py`: plantilla guiada de otro tema para que el alumno la construya paso a paso.
- `GUIA_DIDACTICA_2H.md`: guía completa para impartir clase de 2 horas.
- `MASTERCLASS_CONCEPTOS_M10.md`: documento visual con explicación de conceptos y cajas de código.
- `ENUNCIADO_TRADUCIDO_ES.md`: traducción al español del enunciado y alcance.
- `ejecutar_practica.bat`: script de validación + ejecución en Windows.
- `ejecutar_practica_(sencillo).bat`: lanzador de la version sencilla.

## Ejecución rápida

```bash
python practica_erp_cesta.py
```

Version sencilla:

```bash
python "practica_erp_cesta_(sencillo).py"
```

Plantilla guiada (biblioteca):

```bash
python "practica_erp_biblioteca_(sencillo_plantilla).py"
```

En Windows también puedes ejecutar:

```bat
ejecutar_practica.bat
```

## Persistencia (se crea automáticamente)

Al ejecutar, se crea la carpeta `datos_erp` con:
- `productos.json`
- `cesta.json`
- `facturas_index.json`
- `facturas/` (facturas en `.txt` y `.json`)
