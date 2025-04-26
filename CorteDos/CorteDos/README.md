# 📌 CarsMovies API

Este proyecto proporciona una API REST para gestionar información sobre películas de autos. A continuación, se detallan los endpoints disponibles, la forma de consumo y uso, así como las tecnologías utilizadas.

---

## 🚀 Endpoints Disponibles

### **1\. Obtener lista de películas**

- **Método:** GET  
- **URL:** `{{hostname}}/api/v1/carsmovies?page=0&size=5&sort=carMovieYear,desc`
- **Descripción:** Obtiene una lista paginada de películas de autos.
- **Ejemplo de respuesta:**

```json
[
  {
    "id": "123",
    "carMovieName": "Fast & Furious",
    "carMovieYear": "2001",
    "duration": 106
  }
]
```

---

### **2\. Obtener película específica**

- **Método:** GET  
- **URL:** `{{hostname}}/api/v1/carsmovies/{id}`
- **Descripción:** Obtiene la información de una película específica mediante su ID.
- **Ejemplo de respuesta:**

```json
{
  "id": "123",
  "carMovieName": "Fast & Furious",
  "carMovieYear": "2001",
  "duration": 106
}
```

---

### **3\. Crear nueva película**

- **Método:** POST  
- **URL:** `{{hostname}}/api/v1/carsmovies`
- **Cuerpo JSON:**

```json
{
  "carMovieName": "Speed Racer",
  "carMovieYear": "2008",
  "duration": 135
}
```

- **Ejemplo de respuesta:**

```json
{
  "id": "456",
  "carMovieName": "Speed Racer",
  "carMovieYear": "2008",
  "duration": 135
}
```

---

### **4\. Actualizar película**

- **Método:** PUT  
- **URL:** `{{hostname}}/api/v1/carsmovies/{id}`
- **Cuerpo JSON:**

```json
{
  "carMovieName": "Speed Racer",
  "carMovieYear": "2008",
  "duration": 140
}
```

---

### **5\. Eliminar película**

- **Método:** DELETE  
- **URL:** `{{hostname}}/api/v1/carsmovies/{id}`

---

## 🔧 Uso y Consumo

### **1\. Configurar Variables**
Antes de consumir la API, asegúrate de actualizar la variable `{{hostname}}` con la URL de tu servidor.

Ejemplo:
```
BASE_URL=https://mi-api.com
```

### **2\. Realizar Solicitudes con cURL**

#### Obtener todas las películas:
```sh
curl -X GET "{{hostname}}/api/v1/carsmovies?page=0&size=5&sort=carMovieYear,desc"
```

#### Crear una película:
```sh
curl -X POST "{{hostname}}/api/v1/carsmovies"      -H "Content-Type: application/json"      -d '{"carMovieName": "Speed Racer", "carMovieYear": "2008", "duration": 135}'
```

#### Actualizar una película:
```sh
curl -X PUT "{{hostname}}/api/v1/carsmovies/{id}"      -H "Content-Type: application/json"      -d '{"carMovieName": "Speed Racer", "carMovieYear": "2008", "duration": 140}'
```

#### Eliminar una película:
```sh
curl -X DELETE "{{hostname}}/api/v1/carsmovies/{id}"
```

---

## 🛠 Tecnologías Utilizadas

- **Spring Boot** - Framework para construir aplicaciones web en Java.
- **Spring Boot Actuator** - Herramienta para monitorear y gestionar la aplicación.
- **Spring Data JPA** - Persistencia de datos con JPA.
- **H2 Database** - Base de datos en memoria para desarrollo y pruebas.
- **Lombok** - Reducción de código repetitivo en Java.
- **Jackson Databind** - Serialización y deserialización de JSON.
- **Jakarta Validation** - Validaciones para modelos de datos.
- **Maven** - Herramienta de gestión de dependencias y construcción del proyecto.
- **Java 17** - Versión del lenguaje de programación.

---

## 📌 Notas Finales

- Usa Postman para probar los endpoints y verificar respuestas.
- Asegúrate de actualizar la variable `{{hostname}}` con la URL correcta de tu API.
- Si encuentras algún problema, revisa la documentación o consulta al equipo de desarrollo.

---

### ✨ ¡Gracias por usar CarsMovies API! 🚗🎬
