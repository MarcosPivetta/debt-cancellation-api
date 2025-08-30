# Debt Cancellation API

API REST para cancelamento de débitos, desenvolvida em Java com Spring Boot.

![Java](https://img.shields.io/badge/Java-21-007396?)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9.6-C71A36?logo=apachemaven)
![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-blue)
![Lombok](https://img.shields.io/badge/Lombok-1.18.38-orange)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0.1-blueviolet)
![Swagger](https://img.shields.io/badge/Swagger-2.0-brightgreen)
![JUnit](https://img.shields.io/badge/JUnit-5.9.3-25A162)
![H2 Database](https://img.shields.io/badge/H2%20Database-2.1.214-0F4B8D?logo=h2database)

## 📌 Funcionalidades
- Cancelamento de débitos por ID

## 📋 Tecnologias Utilizadas
- Java 21+
- Spring Boot
- Maven
- OpenAPI/Swagger

### Estrutura do Projeto
```
src/main/java/com/example/debtcancellation/
├── adapters/in/controller         # Controllers REST e DTOs
├── application/core/domain        # Domínio e modelos
├── application/core/policy        # Políticas de negócio
├── application/core/validation    # Validações
├── application/ports/in           # Ports (interfaces de entrada)
├── application/usecases           # Casos de uso
├── config                        # Configurações Spring
└── DebtCancellationApiApplication.java # Classe principal
```

## 🚀 Como Executar

1. **Pré-requisitos:**
   - Java 17 ou superior
   - Maven 3.8+

2. **Build do projeto:**
   ```sh
   mvn clean install
   ```

3. **Executar a aplicação:**
   ```sh
   mvn spring-boot:run
   ```

4. **Acessar documentação Swagger:**
   - http://localhost:8080/swagger-ui.html

## 🧪 Testes
Para rodar os testes automatizados:
```sh
mvn test
```

## Endpoints Principais
- `POST /api/debitos/cancelar` — Cancela um débito

## Observações
- Certifique-se de atualizar as dependências para evitar vulnerabilidades.
- Para customizações de política de cancelamento, edite as classes em `application/core/policy`.

## Licença
Projeto de exemplo para fins educacionais.

