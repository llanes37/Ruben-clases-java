# 🧪 Práctica Guiada: Cliente SMTP desde Cero

## 🎯 Objetivo Didáctico
Que el alumno construya paso a paso un cliente SMTP mínimo rellenando huecos en las clases plantilla.

## 📂 Estructura
```
CorreoPractica/
  README_PracticaSMTP.md   ← Este documento de instrucciones
  src/
    ClientePlantilla.java  ← Flujo principal con TODOs
    SMTPUtilsPlantilla.java← Utilidades a completar
```

## 🛠 Herramientas Necesarias
- Java 17+ (recomendado)
- Acceso a un servidor SMTP de pruebas (o Docker con imagen de smtp mock)

## 🧠 Conceptos Clave a Trabajar
- Socket TCP (`Socket`)
- Flujo de entrada/salida (`BufferedReader` / `BufferedWriter`)
- Protocolo SMTP: `EHLO/HELO`, `MAIL FROM`, `RCPT TO`, `DATA`, `QUIT`
- Separación de responsabilidades (métodos utilitarios vs flujo principal)

## 🚶‍♂️ Pasos Progresivos
| Paso | Hito | Archivo | Indicaciones |
|------|------|---------|--------------|
| 1 | Conectar y leer banner | ClientePlantilla.java | Completa creación de socket y lee la primera línea |
| 2 | Enviar EHLO | ClientePlantilla.java | Implementa método sendCommand en utils y úsalo |
| 3 | Pedir datos usuario | ClientePlantilla.java | Añade lectura remitente/destinatario/mensaje |
| 4 | MAIL FROM / RCPT TO | ClientePlantilla.java | Usa util para validar respuesta código 250 |
| 5 | DATA y cuerpo | ClientePlantilla.java | Escribir cabeceras + cuerpo y finalizar con '.' |
| 6 | QUIT ordenado | ClientePlantilla.java | Cerrar sesión y mostrar resumen |
| 7 | Validaciones extra | SMTPUtilsPlantilla.java | Email regex sencillo, códigos múltiples |
| 8 | Manejo errores | Ambos | try/catch específico |

## 🧩 Retos Extra (Orden Sugerido)
1. Implementar fallback EHLO → HELO.
2. Añadir validación de email con `Pattern`.
3. Permitir múltiples destinatarios (lista separada por comas).
4. Manejar respuestas multilinea (`250-` ... `250 `).
5. Implementar `STARTTLS` (requiere cambiar a `SSLSocket`).
6. Añadir autenticación `AUTH LOGIN` (Base64 usuario/contraseña).
7. Construir un correo MIME (Subject con UTF-8, adjunto falso).

## 🧪 Cómo Probar
Compilar:
```powershell
javac .\src\SMTPUtilsPlantilla.java .\src\ClientePlantilla.java
```
Ejecutar:
```powershell
java -cp .\src ClientePlantilla
```

## ✅ Criterios de Compleción
- Se envía un correo (el servidor responde 250 tras el cuerpo).
- El programa no lanza excepciones no controladas.
- Los métodos utilitarios están implementados (sin dejar TODOs clave).

## 📝 Consejos
- Implementa primero lo mínimo que haga funcionar la conexión → luego refactoriza.
- Testea cada comando (imprime código recibido) antes de continuar.
- Usa constantes para códigos si quieres más legibilidad.

## ❓ Ayuda Rápida Códigos SMTP
- 220: Servidor listo.
- 250: OK / Aceptado.
- 251: Usuario no local, se entregará.
- 354: Inicia entrada de datos.
- 221: Cierre de conexión.

## 🔄 Iteración
Trabaja en ciclos cortos: implementa un TODO, compila, prueba y sigue.

¡Ahora abre `ClientePlantilla.java` y comienza! 🚀
