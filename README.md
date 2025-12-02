# ArchSoftMeasure

Proyecto para aplicar un modelo de medición de las habilidades técnicas y blandas de un arquitecto de software.

## 📋 Descripción

Este repositorio contiene una aplicación full-stack compuesta por dos proyectos que funcionan en conjunto (cliente-servidor) para proporcionar una solución completa de evaluación de competencias de arquitectos de software:

- **archsoft-backend**: API REST desarrollada con Spring Boot
- **archsoft-frontend**: Aplicación web desarrollada con Angular

## 🏗️ Arquitectura del Proyecto

### Backend (archsoft-backend)

API REST construida con Spring Boot que gestiona la lógica de negocio y el almacenamiento de datos.

#### Tecnologías
- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **ModelMapper 3.1.1** - Mapeo de objetos

#### Características
- API RESTful con endpoints para gestión de:
  - Métricas de medición
  - Escalas de evaluación
  - Preguntas y respuestas
  - Tests de evaluación
  - Resultados de medición
- Base de datos PostgreSQL
- Puerto por defecto: `8080`
- Context path: `/api`

#### Configuración
```properties
Base de datos: PostgreSQL (localhost:5432/archsoftmeasure)
Usuario: postgres
Puerto: 8080
Context path: /api
```

### Frontend (archsoft-frontend)

Aplicación web desarrollada con Angular que proporciona la interfaz de usuario para realizar y visualizar las evaluaciones.

#### Tecnologías
- **Angular 19**
- **TypeScript 5.6.2**
- **RxJS 7.8.0**
- **Angular SSR** - Server-Side Rendering
- **SCSS** - Estilos

#### Componentes principales
- **Landing**: Página de inicio
- **Form**: Formularios de captura de datos
- **Test**: Interfaz para realizar tests de evaluación
- **Search**: Búsqueda de evaluaciones
- **Measurement Results**: Visualización de resultados
- **Info**: Información general del sistema

#### Servicios
- **ApiService**: Comunicación con el backend

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Node.js y npm
- PostgreSQL
- Maven (incluido en el proyecto con Maven Wrapper)

### Backend

1. Navega al directorio del backend:
```bash
cd archsoft-backend
```

2. Configura la base de datos PostgreSQL:
```bash
# Crea la base de datos
createdb archsoftmeasure
```

3. Ajusta las credenciales en `src/main/resources/application.properties` si es necesario

4. Ejecuta el proyecto:
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

El backend estará disponible en `http://localhost:8080/api`

### Frontend

1. Navega al directorio del frontend:
```bash
cd archsoft-frontend
```

2. Instala las dependencias:
```bash
npm install
```

3. Ejecuta el servidor de desarrollo:
```bash
npm start
```

La aplicación estará disponible en `http://localhost:4200`

## 📁 Estructura del Proyecto

```
archsoftmeasure/
├── archsoft-backend/          # API REST con Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── co/unicauca/archsoftmeasure/
│   │   │   │       ├── answer/        # Gestión de respuestas
│   │   │   │       ├── measurement/   # Mediciones y resultados
│   │   │   │       ├── metric/        # Métricas de evaluación
│   │   │   │       ├── question/      # Preguntas de tests
│   │   │   │       ├── scale/         # Escalas de medición
│   │   │   │       ├── test/          # Tests de evaluación
│   │   │   │       └── util/          # Utilidades
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
└── archsoft-frontend/         # Aplicación Angular
    ├── src/
    │   └── app/
    │       ├── form/                  # Formularios
    │       ├── test/                  # Interfaz de tests
    │       ├── search/                # Búsqueda
    │       ├── measurement-results/   # Resultados
    │       ├── landing/               # Página inicial
    │       ├── info/                  # Información
    │       └── service/               # Servicios API
    └── package.json
```

## 🔧 Desarrollo

### Backend
- El proyecto utiliza Spring Boot DevTools para hot reload
- Los tests se ejecutan con: `./mvnw test`
- La base de datos se recrea en cada inicio (ddl-auto=create)

### Frontend
- Modo desarrollo: `npm start`
- Build de producción: `npm run build`
- Tests: `npm test`
- SSR: `npm run serve:ssr:archsoft-frontend`

## 📄 Licencia

Este proyecto está bajo la licencia especificada en el archivo LICENSE.

## 👥 Autores

Juan-MZ
Universidad del Cauca
