# Catalog API

Aplicação full stack de catálogo de produtos desenvolvida para praticar a construção de APIs REST com Java e Spring Boot, persistência com JPA/Hibernate e integração com uma interface React.

## Visão geral

O projeto permite consultar e administrar produtos, categorias e usuários. O backend segue uma arquitetura em camadas, separando recursos REST, regras de negócio e acesso a dados.

## Tecnologias

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security e OAuth2/JWT
- Bean Validation
- PostgreSQL e H2
- Maven
- JUnit 5

### Frontend

- React
- TypeScript
- React Router
- Bootstrap e Sass

## Funcionalidades

- CRUD de produtos, categorias e usuários
- Paginação de resultados
- DTOs para transferência de dados
- Validação de dados de entrada
- Tratamento centralizado de exceções
- Relacionamentos entre entidades
- Autenticação e autorização com token JWT
- Perfil de testes com banco H2
- Carga inicial de dados para desenvolvimento

## Arquitetura do backend

```text
resource -> service -> repository -> database
              |
             dto
```

- `resource`: endpoints e respostas HTTP
- `service`: regras de negócio e casos de uso
- `repository`: persistência com Spring Data JPA
- `dto`: contratos de entrada e saída da API
- `entities`: modelo de domínio

## Estrutura do repositório

```text
catalog/
|-- backend/    # API Java e Spring Boot
`-- frontend/   # Interface React e TypeScript
```

## Como executar o backend

### Requisitos

- Java 17
- Maven ou Maven Wrapper

Na pasta `backend`, execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

Por padrão, o projeto utiliza o perfil de testes com banco H2 em memória. A API fica disponível em `http://localhost:8080`.

## Endpoints principais

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/products` | Lista produtos com paginação |
| GET | `/products/{id}` | Consulta um produto |
| POST | `/products` | Cadastra um produto |
| PUT | `/products/{id}` | Atualiza um produto |
| DELETE | `/products/{id}` | Remove um produto |
| GET | `/categories` | Lista categorias |
| GET | `/users` | Lista usuários |

## Testes

Na pasta `backend`, execute:

```bash
./mvnw test
```

Os testes existentes validam o contexto da aplicação e operações de persistência do catálogo.

## Próximas evoluções

- Migrar a segurança para o modelo atual do Spring Security
- Ampliar testes unitários e de integração
- Documentar a API com OpenAPI/Swagger
- Criar ambiente local com Docker Compose
- Adicionar integração contínua com GitHub Actions
- Publicar uma demonstração da aplicação

## Autor

Desenvolvido por [Italo Dias](https://github.com/Italogabrieldias).

- [LinkedIn](https://www.linkedin.com/in/ital0dias/)
