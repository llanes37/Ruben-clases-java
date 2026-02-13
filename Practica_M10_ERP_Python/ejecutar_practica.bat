@echo off
setlocal

echo ===============================================
echo PRACTICA M10 ERP - PYTHON
echo Validando sintaxis y ejecutando programa...
echo ===============================================

python -m py_compile practica_erp_cesta.py
if errorlevel 1 (
  echo [ERROR] Hay errores de sintaxis en practica_erp_cesta.py
  exit /b 1
)

python practica_erp_cesta.py

endlocal
