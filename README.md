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
git clone https://github.com/tuusuario/muebles-sas-prueba.git
cd muebles-sas-prueba
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

## 📂 Estructura del proyecto

```
└── src
    ├── main
    │   ├── java/co/com/bancolombia
    │   │   ├── model                # Entidades y modelos
    │   │   ├── usecase              # Lógica de negocio
    │   │   ├── reactiveweb          # Entrypoints (Handler/Router)
    │   │   └── dynamo               # Driven Adapter
    └── test
        ├── usecase
        ├── dynamo
        └── reactiveweb
```

---

## 🧪 Cobertura mínima del 70%

Se garantiza al menos el 70% de cobertura en pruebas unitarias.

---

## 🤝 Autor

Lina María Guerrero López

---

¡Gracias por revisar este proyecto!

