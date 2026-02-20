# Spring Boot WebFlux - DDD con Arquitectura Hexagonal

Aplicación reactiva desarrollada con Spring Boot WebFlux implementando Domain-Driven Design (DDD) y Arquitectura Hexagonal.


## Requisitos

- Java 17 o superior
- Maven 3.6+
- MongoDB 4.x o superior

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

## API Endpoints

Base URL: `http://localhost:8080`

### Productos

#### Listar todos los productos
```http
GET /api/productos
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/api/productos
```

**Respuesta:**
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "nombre": "Laptop",
    "descripcion": "Laptop de alto rendimiento",
    "precio": 1500.00
  }
]
```

---

#### Obtener producto por ID
```http
GET /api/productos/{id}
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/api/productos/507f1f77bcf86cd799439011
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

#### Crear un producto
```http
POST /api/productos
Content-Type: application/json
```

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop",
    "descripcion": "Laptop de alto rendimiento",
    "precio": 1500.00
  }'
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

#### Actualizar un producto
```http
PUT /api/productos/{id}
Content-Type: application/json
```

**Ejemplo con curl:**
```bash
curl -X PUT http://localhost:8080/api/productos/507f1f77bcf86cd799439011 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop Gaming",
    "descripcion": "Laptop gaming de alto rendimiento",
    "precio": 2000.00
  }'
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

**Ejemplo con curl:**
```bash
curl -X DELETE http://localhost:8080/api/productos/507f1f77bcf86cd799439011
```

---

#### Buscar productos por nombre
```http
GET /api/productos/buscar?q={nombre}
```

**Ejemplo con curl:**
```bash
curl -X GET "http://localhost:8080/api/productos/buscar?q=Laptop"
```

**Respuesta:**
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "nombre": "Laptop",
    "descripcion": "Laptop de alto rendimiento",
    "precio": 1500.00
  },
  {
    "id": "507f1f77bcf86cd799439012",
    "nombre": "Laptop Gaming",
    "descripcion": "Laptop gaming RGB",
    "precio": 2500.00
  }
]
```

---

### Categorías

#### Listar todas las categorías
```http
GET /api/categorias
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/api/categorias
```

**Respuesta:**
```json
[
  {
    "id": "507f1f77bcf86cd799439021",
    "nombre": "Electrónica",
    "descripcion": "Productos electrónicos"
  }
]
```

---

#### Obtener categoría por ID
```http
GET /api/categorias/{id}
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/api/categorias/507f1f77bcf86cd799439021
```

**Respuesta:**
```json
{
  "id": "507f1f77bcf86cd799439021",
  "nombre": "Electrónica",
  "descripcion": "Productos electrónicos"
}
```

---

#### Crear una categoría
```http
POST /api/categorias
Content-Type: application/json
```

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Electrónica",
    "descripcion": "Productos electrónicos"
  }'
```

**Body:**
```json
{
  "nombre": "Electrónica",
  "descripcion": "Productos electrónicos"
}
```

**Respuesta:**
```json
{
  "id": "507f1f77bcf86cd799439021",
  "nombre": "Electrónica",
  "descripcion": "Productos electrónicos"
}
```

---

#### Actualizar una categoría
```http
PUT /api/categorias/{id}
Content-Type: application/json
```

**Ejemplo con curl:**
```bash
curl -X PUT http://localhost:8080/api/categorias/507f1f77bcf86cd799439021 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Electrónica y Tecnología",
    "descripcion": "Productos de electrónica y tecnología"
  }'
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

**Ejemplo con curl:**
```bash
curl -X DELETE http://localhost:8080/api/categorias/507f1f77bcf86cd799439021
```

---

### Producto-Categorías (Relaciones)

#### Listar todas las relaciones producto-categoría
```http
GET /api/producto-categorias
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/api/producto-categorias
```

**Respuesta:**
```json
[
  {
    "id": "507f1f77bcf86cd799439031",
    "productoId": "507f1f77bcf86cd799439011",
    "categoriaId": "507f1f77bcf86cd799439021"
  }
]
```

---

#### Crear una relación producto-categoría
```http
POST /api/producto-categorias
Content-Type: application/json
```

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/api/producto-categorias \
  -H "Content-Type: application/json" \
  -d '{
    "productoId": "507f1f77bcf86cd799439011",
    "categoriaId": "507f1f77bcf86cd799439021"
  }'
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

## Ejemplos de Uso Completo

### Ejemplo 1: Crear un producto con su categoría

1. **Crear una categoría:**
```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Electrónica",
    "descripcion": "Productos electrónicos"
  }'
```

2. **Crear un producto:**
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop Dell XPS 15",
    "descripcion": "Laptop de alto rendimiento con procesador Intel i7",
    "precio": 1899.99
  }'
```

3. **Relacionar producto con categoría:**
```bash
curl -X POST http://localhost:8080/api/producto-categorias \
  -H "Content-Type: application/json" \
  -d '{
    "productoId": "{ID_DEL_PRODUCTO}",
    "categoriaId": "{ID_DE_LA_CATEGORIA}"
  }'
```

### Ejemplo 2: Buscar y actualizar un producto

1. **Buscar producto por nombre:**
```bash
curl -X GET "http://localhost:8080/api/productos/buscar?q=Laptop"
```

2. **Actualizar el producto encontrado:**
```bash
curl -X PUT http://localhost:8080/api/productos/{ID_DEL_PRODUCTO} \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop Dell XPS 15 (2024)",
    "descripcion": "Laptop gaming de última generación",
    "precio": 2199.99
  }'
```


