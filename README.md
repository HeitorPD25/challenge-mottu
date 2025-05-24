# Challenge Mottu

Este projeto é uma API REST desenvolvida em Java como parte de um desafio técnico da Mottu. O objetivo é fornecer funcionalidades específicas conforme os requisitos do desafio.

## Desenvolvedores

- Eduardo Eiki de Souza – RM554921  
- Nicollas Frei – RM557647  
- Heitor Duarte – RM558208  

## Tecnologias Utilizadas

- **Java** – Linguagem principal do projeto.  
- **Spring Boot** – Framework para facilitar o desenvolvimento da API.  
- **Maven** – Gerenciador de dependências e build.  
- **Docker** – Para containerização da aplicação.  

## Estrutura do Projeto

```
challenge-mottu/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mottu/
│                   └── ...
├── .gitignore
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
```

## Informações Necessárias para Execução do Projeto

### MotorcycleController

- **URL:** `http://localhost:8080/motorcycles`

- **JSON de exemplo para criar uma moto:**

```json
{
  "license_plate": "ABC-1234",
  "model": "MottuSport",
  "status": "Livre",
  "area": {
    "id": 1
  }
}
```

### AreaController

- **URL:** `http://localhost:8080/areas`

- **JSON de exemplo para criar uma área:**

```json
{
  "name": "Área Verde",
  "patio": {
    "id": 1
  }
}
```

> **Importante:** A área deve ser criada antes da moto devido ao relacionamento entre as entidades.