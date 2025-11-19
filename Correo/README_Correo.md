# 📬 Proyecto: Cliente SMTP Básico en Java

## 🎯 Objetivo
Este mini-proyecto demuestra cómo enviar un correo electrónico conectándote **directamente** a un servidor SMTP usando **sockets** en Java, construyendo manualmente los comandos del protocolo.

Incluye:
- `Cliente.java`: Versión básica comentada paso a paso.
- `ClienteDidactico.java`: Versión mejorada, modular y con validaciones.

## 🧠 Conceptos Clave
| Concepto | Explicación |
|----------|------------|
| SMTP | Protocolo estándar para transferencia de correo entre servidores. |
| Socket TCP | Canal de comunicación confiable entre cliente y servidor. |
| Comandos SMTP | Instrucciones de texto: `HELO`, `MAIL FROM`, `RCPT TO`, `DATA`, `QUIT`. |
| Cabeceras | Metadatos del correo: `From`, `To`, `Subject`, etc. |
| Cuerpo | Contenido real del mensaje tras una línea en blanco. |
| Terminación del cuerpo | Se indica con una línea que contiene solo un punto (`.`). |

## 🔄 Flujo SMTP mínimo (sin autenticación)
1. Servidor responde banner inicial (`220`).
2. Cliente envía `HELO` (o mejor `EHLO`).
3. Servidor responde `250` (OK).
4. `MAIL FROM:<remitente>` → identifica emisor.
5. `RCPT TO:<destinatario>` → identifica receptor.
6. `DATA` → servidor suele responder `354` (esperando mensaje).
7. Se envían cabeceras + línea en blanco + cuerpo.
8. Se finaliza con una línea que contiene solo `.`.
9. Servidor responde `250` (mensaje aceptado).
10. Cliente envía `QUIT` → servidor responde `221`.

## ⚠ Limitaciones de la versión básica
- No maneja TLS (`STARTTLS`).
- No implementa autenticación (`AUTH LOGIN` / `PLAIN`).
- No verifica códigos específicos: solo imprime respuestas.
- Supone que el servidor permite relé (cada vez menos común).

## 🚀 Mejoras implementadas en la versión didáctica
- Métodos reutilizables para enviar comandos y leer respuestas.
- Validación simple del formato de correo con regex.
- Separación clara de responsabilidades.
- Comentarios estructurados estilo *Better Comments*.
- `EHLO` con fallback a `HELO`.

## 🛠 Próximas ampliaciones (pendientes / ideas)
- Implementar `STARTTLS` usando `SSLSocket` o upgrade de canal.
- Añadir autenticación (`AUTH LOGIN`).
- Manejar respuestas multilinea (prefijos `250-` y final `250 `).
- Soporte para múltiples destinatarios y adjuntos (MIME).
- Añadir logs con timestamps y niveles (INFO/WARN/ERROR).

## 📌 Ejecución (Windows PowerShell)
Compila y ejecuta:
```powershell
# Compilar
javac .\src\Cliente.java .\src\ClienteDidactico.java

# Ejecutar versión básica
java -cp .\src Cliente

# Ejecutar versión didáctica
java -cp .\src ClienteDidactico
```

## 🧪 Probando con un servidor local
Puedes usar por ejemplo un contenedor Docker con postfix/openSMTPD o un mock:
```powershell
# Ejemplo (requiere Docker Desktop instalado)
docker run -d --name smtp -p 25:25 namshi/smtp
```
> Nota: Muchos ISP bloquean el puerto 25 saliente; quizá necesites un entorno interno.

## 🔍 Validación de respuesta
La versión didáctica intenta parsear el código numérico inicial (3 dígitos). Ejemplo: `250 OK` → código 250.

## 🧩 Ejemplo de sesión típica
```
220 mail.servidor.com ESMTP Ready
HELO ruben.foo
250 mail.servidor.com Hello
MAIL FROM:<yo@dominio.com>
250 OK
RCPT TO:<destino@dominio.com>
250 Accepted
DATA
354 End data with <CR><LF>.<CR><LF>
From: yo@dominio.com
To: destino@dominio.com
Subject: Prueba

Hola esto es un test.
.
250 Queued as 12345
QUIT
221 Bye
```

## ✅ Resumen
Este proyecto es una puerta de entrada para comprender **cómo se envía un correo “a bajo nivel”** sin depender de librerías como JavaMail. Ideal para reforzar conocimientos de:
- Protocolos de texto.
- Sockets y flujos.
- Manejo de errores y robustez.

Si quieres que añadamos autenticación, STARTTLS o soporte MIME, ¡pídelo y lo ampliamos! ✨
