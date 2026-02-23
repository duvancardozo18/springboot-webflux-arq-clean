# Spring Boot WebFlux - DDD con Arquitectura Hexagonal

Aplicación reactiva desarrollada con Spring Boot WebFlux implementando Domain-Driven Design (DDD) y Arquitectura Hexagonal.


## Requisitos

- Java 17 o superior
- Maven 3.6+
- MongoDB 4.x o superior

---

## Ejecución con Docker

### Usando Docker Compose (Recomendado)

La forma más rápida de ejecutar la aplicación junto con MongoDB:

```bash
# Construir y levantar todos los servicios
docker-compose up -d

# Ver los logs
docker-compose logs -f

La aplicación estará disponible en `http://localhost:8080` y MongoDB en `localhost:27017`.



---

## Arquitectura

El proyecto implementa **Arquitectura Hexagonal** (Ports & Adapters) con **DDD**:

```
hexagonal/
├── domain/                      # Capa de Dominio (Lógica de Negocio)
│   ├── entities/               # Entidades del dominio
│   ├── valueobjects/           # Objetos de valor
│   ├── repository/             # Puertos (interfaces)
│   └── usecases/               # Casos de uso
│
└── infrastructure/             # Capa de Infraestructura (Adaptadores)
    ├── controller/             # Adaptadores de entrada (API REST)
    ├── persistence/            # Adaptadores de salida (MongoDB)
    ├── dto/                    # Objetos de transferencia de datos
    ├── mapper/                 # Mappers DTO <-> Domain
    └── config/                 # Configuración
```

## Configuración

La aplicación se ejecuta por defecto en el puerto `8080` y se conecta a una base de datos MongoDB local:

```yaml
server:
  port: 8080
  
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/webflux_hex_db
```

## Ejecución

```bash
# Usando Maven Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# Usando Maven Wrapper (Linux/Mac)
./mvnw spring-boot:run

# O usando Maven instalado
mvn spring-boot:run
```

### API Endpoints - Productos
 
Base URL: `http://localhost:8080`

#### Crear un producto
```http
POST /api/productos
Content-Type: application/json
```

```
**Body:**
```json
{
  "nombre": "Laptop",
  "descripcion": "Laptop de alto rendimiento",
  "precio": 1500.00
}
```

**Respuesta:**
```json
{
  "id": "507f1f77bcf86cd799439011",
  "nombre": "Laptop",
  "descripcion": "Laptop de alto rendimiento",
  "precio": 1500.00
}
```

---

#### Listar todos los productos
```http
GET /api/productos
```
---

#### Obtener producto por ID
```http
GET /api/productos/{id}
```
---

#### Actualizar un producto
```http
PUT /api/productos/{id}
Content-Type: application/json
```

**Body:**
```json
{
  "nombre": "Laptop Gaming",
  "descripcion": "Laptop gaming de alto rendimiento",
  "precio": 2000.00
}
```

---

#### Eliminar un producto
```http
DELETE /api/productos/{id}
```

#### Buscar productos por nombre
```http
GET /api/productos/buscar?q={nombre}
```
---

### Categorías


#### Crear una categoría
```http
POST /api/categorias
Content-Type: application/json
```

**Body:**
```json
{
  "nombre": "Electrónica",
  "descripcion": "Productos electrónicos"
}
```

---

#### Listar todas las categorías
```http
GET /api/categorias
```
---

#### Obtener categoría por ID
```http
GET /api/categorias/{id}
```
---

#### Actualizar una categoría
```http
PUT /api/categorias/{id}
Content-Type: application/json
```


**Body:**
```json
{
  "nombre": "Electrónica y Tecnología",
  "descripcion": "Productos de electrónica y tecnología"
}
```

---

#### Eliminar una categoría
```http
DELETE /api/categorias/{id}
```
---

### Producto-Categorías (Relaciones)

#### Listar todas las relaciones producto-categoría
```http
GET /api/producto-categorias
```

---

#### Crear una relación producto-categoría
```http
POST /api/producto-categorias
Content-Type: application/json
```


**Body:**
```json
{
  "productoId": "507f1f77bcf86cd799439011",
  "categoriaId": "507f1f77bcf86cd799439021"
}
```

**Respuesta:**
```json
{
  "id": "507f1f77bcf86cd799439031",
  "productoId": "507f1f77bcf86cd799439011",
  "categoriaId": "507f1f77bcf86cd799439021"
}
```

---
