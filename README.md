# 🪑 Muebles SAS - Prueba Técnica

Este proyecto implementa un microservicio reactivo usando el **Scaffold Clean Architecture de Bancolombia**, orientado al manejo de estadísticas de contacto de clientes, persistiendo la información en **DynamoDB Local**, usando **Spring WebFlux** y **Docker Compose**.

---

## 🛠️ Tecnologías

- Java 17
- Spring WebFlux
- DynamoDB Local
- Docker Compose
- Gradle
- Mockito / JUnit5
- Clean Architecture (Scaffold Bancolombia)

---

## 🚀 Cómo ejecutar el servicio

### Prerrequisitos

- JDK 17+
- Docker y Docker Compose
- Git
- Gradle 7.6+

### Clonar el repositorio

```bash
https://github.com/LinaDev14/Muebles-SAS-
cd scaffold-quick-start
```

### Levantar el entorno con Docker Compose

```bash
docker-compose up -d
```

Esto levantará el contenedor con DynamoDB Local en `localhost:8000`.

---

## ▶️ Ejecutar el servicio

### Desde terminal

```bash
./gradlew bootRun
```

El servicio quedará disponible en:

```
http://localhost:8080/api/stats
```
---

## 🔐 Verificación de Hash

El `hash` debe ser generado como una verificación MD5 del contenido del JSON sin incluir el campo `hash`. El orden de los campos debe ser el mismo.

### ¿Cómo se genera?

```bash
echo -n '{"timestamp":"2025-06-23T18:00:00","totalContactoClientes":250,"motivoReclamo":10,"motivoGarantia":5,"motivoDuda":20,"motivoCompra":200,"motivoFelicitaciones":8,"motivoCambio":7}' | md5sum
```

Este valor se coloca en el campo `"hash"` del request.

---

## ⚠️ Error común con DynamoDB Local

### Error:

```
The Access Key ID or Security Token is Invalid
```

### Causa:

Desde la versión 2.0.0 de DynamoDB Local, las claves de acceso **deben ser alfanuméricas** (sin guiones ni símbolos especiales).

### Solución:

Utiliza claves como estas en tu configuración:

```bash
AWS_ACCESS_KEY_ID=localaccess123
AWS_SECRET_ACCESS_KEY=localsecret123
```

Evita usar caracteres especiales. Si usas Docker, asegúrate de incluir estas variables correctamente o usar `StaticCredentialsProvider` con claves dummy.

---
---

## 📬 Probar el endpoint

Puedes usar Postman o cURL. Aquí un ejemplo con cURL:

```bash
curl --location 'http://localhost:8080/api/stats' --header 'Content-Type: application/json' --data '{
  "timestamp": "2025-06-23T18:00:00",
  "totalContactoClientes": 250,
  "motivoReclamo": 25,
  "motivoGarantia": 10,
  "motivoDuda": 100,
  "motivoCompra": 100,
  "motivoFelicitaciones": 7,
  "motivoCambio": 8,
  "hash": "5484062a4be1ce5645eb414663e14f59"
}'
```

---

## ✅ Ejecutar pruebas unitarias

```bash
./gradlew test
```

Se han cubierto pruebas para:

- Casos de uso (`StatsUseCase`)
- Adaptadores (`StatsRepositoryAdapter`)
- Entrypoints (`StatsHandler`)
- Errores y validaciones

---

## 📁 Estructura de Carpetas

El proyecto sigue el enfoque de Clean Architecture. A continuación, se describe brevemente la organización de carpetas:

- `domain/`: Contiene los modelos y casos de uso puros.
- `infrastructure/`: Define adaptadores (por ejemplo, DynamoDB) y puntos de entrada (como controladores web).
- `deployment/docker`: Configuración para desplegar el entorno local con Docker.
- Archivos `gradle` y scripts para la compilación del proyecto.

📁 scaffold-quick-start
├── 📁 .gradle
├── 📁 .idea
├── 📁 applications
├── 📁 build
├── 📁 build-cache
├── 📁 deployment
│   └── 📁 docker
│       └── 📄 Dockerfile
├── 📁 domain
│   ├── 📁 model                        # Entidades y modelos del dominio
│   └── 📁 usecase                     # Casos de uso del dominio
├── 📁 gradle
├── 📁 infrastructure
│   ├── 📁 driven-adapters             # Adaptadores hacia servicios externos (ej. DynamoDB)
│   ├── 📁 entry-points                # Controladores y manejadores HTTP
│   └── 📁 helpers                     # Clases utilitarias o configuraciones adicionales
├── 📄 .gitignore
├── 📄 build.gradle
├── 📄 gradle.properties
├── 📄 gradlew
├── 📄 gradlew.bat
├── 📄 lombok.config
├── 📄 main.gradle
└── 📄 README.md

---

## 🧪 Cobertura mínima del 70%

Se garantiza al menos el 70% de cobertura en pruebas unitarias.

---

## 🤝 Autor

Lina María Guerrero López

---

¡Gracias por revisar este proyecto!

