@echo off
setlocal

echo ==========================================
echo PRACTICA CESTA (SENCILLO) - PYTHON
echo ==========================================

python -m py_compile "practica_erp_cesta_(sencillo).py"
if errorlevel 1 (
  echo [ERROR] Hay errores de sintaxis.
  exit /b 1
)

python "practica_erp_cesta_(sencillo).py"

endlocal
