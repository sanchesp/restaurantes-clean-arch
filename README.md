# Restaurantes Clean Arch

Sistema de gestão de restaurantes desenvolvido para o Tech Challenge — Fase 2 (Pós Tech - Arquitetura e Desenvolvimento Java). Esta fase implementa o cadastro de **Tipos de Usuário**, **Restaurantes** e **Itens do Cardápio**, seguindo os princípios de Clean Architecture.

## Sumário

- [Arquitetura](#arquitetura)
- [Como os requisitos da Fase 2 foram atendidos](#como-os-requisitos-da-fase-2-foram-atendidos)
- [Tecnologias](#tecnologias)
- [Como executar](#como-executar)
- [Endpoints da API](#endpoints-da-api)
- [Testes e cobertura](#testes-e-cobertura)
- [Collection Postman](#collection-postman)

## Arquitetura

O projeto segue **Clean Architecture**, organizado em camadas concêntricas com dependência sempre apontando para dentro (`infra` conhece `core`, `core` não conhece `infra`):

```
src/main/java/br/com/fiap/techchalleger/restaurantescleanarch/
├── core/                            # Regras de negócio — não depende de Spring/JPA
│   ├── domain/                      # Entidades de domínio (Usuario, UsuarioTipo, Restaurante, MenuItem)
│   │                                 com invariantes validadas no construtor
│   ├── usecase/                     # Um caso de uso por operação (Criar/Listar/Buscar/Atualizar/Deletar)
│   ├── controller/                  # Orquestra casos de uso (agnóstico de framework web)
│   ├── gateway/                     # Portas (interfaces) para persistência, implementadas na infra
│   ├── mapper/                      # DTO -> Domínio
│   ├── dto/                         # Entrada dos casos de uso
│   └── exception/                   # Exceções de domínio/negócio
└── infra/                           # Detalhes de implementação (adapters)
    ├── web/                         # Controllers REST (*ApiController), JSON (request/response)
    │   └── exception/                # GlobalExceptionHandler (@RestControllerAdvice)
    ├── database/
    │   ├── jpa/                     # Gateways JPA (implementam as portas do core)
    │   ├── jpa/entity/               # Entidades JPA
    │   ├── jpa/repository/           # Spring Data Repositories
    │   └── mapper/                  # Entidade JPA <-> Domínio
    └── config/                      # Injeção de dependências (wiring manual dos beans)
```

Fluxo de uma requisição: `*ApiController` (infra/web) → `Controller` (core) → `UseCase` (core) → `Gateway` (interface, core) → `*JpaGateway` (infra/database) → JPA Repository → Banco.

As classes de `core` não importam nada de `infra`; a inversão de dependência é feita via `InjecaoDependenciaConfiguration`, que instancia os casos de uso e controllers manualmente (fora do padrão `@Service`/`@Component` do Spring), reforçando o desacoplamento do domínio em relação ao framework.

## Como os requisitos da Fase 2 foram atendidos

| Requisito do PDF | Como foi implementado |
|---|---|
| **Tipo de usuário** — CRUD com campo "Nome do Tipo" e associação com usuário | `UsuarioTipo` é uma entidade de domínio livre (qualquer nome não vazio); CRUD completo em `/usuarios-tipos`. Associação feita via `usuarioTipoId` em `Usuario`, atualizável em `PUT /usuarios/{id}`. |
| **Cadastro de restaurante** — nome, endereço, tipo de cozinha, horário, dono | CRUD completo em `/restaurantes`. `donoId` referencia um `Usuario` existente; a criação valida que o usuário existe e é do tipo `DONO_RESTAURANTE`. |
| **Cadastro de itens do cardápio** — nome, descrição, preço, disponibilidade só no local, caminho da foto | CRUD completo em `/menu-items`, com todos os campos exigidos e vínculo a um `restauranteId`. |
| **Qualidade do código / práticas Spring Boot** | Camadas isoladas, injeção de dependência explícita, DTOs de entrada/saída, tratamento centralizado de erros (`GlobalExceptionHandler`). |
| **Documentação do projeto** | Este README (arquitetura, endpoints, setup). |
| **Collections para teste** | `Restaurantes Clean Arch.postman_collection.json`, cobrindo o CRUD completo dos 4 recursos. |
| **Docker Compose** | `docker-compose.yml` sobe a aplicação Java e o Postgres. |
| **Repositório de código aberto** | Projeto versionado em Git/GitHub. |
| **Clean Architecture** | Ver seção [Arquitetura](#arquitetura). |
| **Cobertura de testes de 80%** | Testes unitários (domínio + casos de uso) e de integração (`*ControllerIT`, ponta a ponta via `TestRestTemplate` contra Postgres real). Gate de cobertura mínima de 80% configurado via JaCoCo (`mvn verify`), cobertura atual acima de 90%. |
| **Vídeo demonstrativo** | Entregue separadamente do repositório (não versionado aqui). |

## Tecnologias

- Java 17
- Spring Boot 3.5 (Web, Data JPA)
- PostgreSQL 17
- JaCoCo (cobertura de testes)
- JUnit 5 + Mockito (testes unitários e de integração)
- Docker / Docker Compose

## Como executar

### Opção 1 — Docker Compose (recomendado)

Sobe a aplicação e o banco de dados juntos:

```bash
docker compose up --build
```

A API fica disponível em `http://localhost:8080`.

### Opção 2 — Localmente com Maven

Pré-requisitos: Java 17, Maven (ou use o wrapper `./mvnw`) e um Postgres acessível em `localhost:5432` (pode ser subido isoladamente com `docker compose up -d postgres`), com as credenciais definidas em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurantes_clean_arch
spring.datasource.username=admin
spring.datasource.password=admin123
```

Para rodar a aplicação:

```bash
./mvnw spring-boot:run
```

O schema é criado/atualizado automaticamente (`spring.jpa.hibernate.ddl-auto=update`).

## Endpoints da API

### Tipos de Usuário — `/usuarios-tipos`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/usuarios-tipos` | Cria um tipo de usuário (`{ "tipo": "CLIENTE" }`) |
| GET | `/usuarios-tipos` | Lista todos os tipos |
| GET | `/usuarios-tipos/{id}` | Busca um tipo por ID |
| PUT | `/usuarios-tipos/{id}` | Atualiza o nome do tipo |
| DELETE | `/usuarios-tipos/{id}` | Remove um tipo |

### Usuários — `/usuarios`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/usuarios` | Cria um usuário (`{ "nome", "email", "senha", "usuarioTipoId" }`) |
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Busca um usuário por ID |
| PUT | `/usuarios/{id}` | Atualiza o tipo do usuário (`{ "usuarioTipoId": 2 }`) |
| DELETE | `/usuarios/{id}` | Remove um usuário |

### Restaurantes — `/restaurantes`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/restaurantes` | Cria um restaurante (`{ "nome", "endereco", "tipoCozinha", "horarioFuncionamento", "donoId" }`) |
| GET | `/restaurantes` | Lista todos os restaurantes |
| GET | `/restaurantes/{id}` | Busca um restaurante por ID |
| PUT | `/restaurantes/{id}` | Atualiza um restaurante |
| DELETE | `/restaurantes/{id}` | Remove um restaurante |

`donoId` precisa referenciar um usuário existente do tipo `DONO_RESTAURANTE` (usuarioTipoId = 2).

### Itens do Cardápio — `/menu-items`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/menu-items` | Cria um item de cardápio (`{ "nome", "descricao", "preco", "disponivel", "caminhoFoto", "restauranteId" }`) |
| GET | `/menu-items` | Lista todos os itens |
| GET | `/menu-items/restaurante/{restauranteId}` | Lista os itens de um restaurante específico |
| GET | `/menu-items/{id}` | Busca um item por ID |
| PUT | `/menu-items/{id}` | Atualiza um item |
| DELETE | `/menu-items/{id}` | Remove um item |

`disponivel` indica se o item só pode ser pedido consumindo no restaurante. `caminhoFoto` é apenas o caminho/URL onde a foto do prato seria armazenada (não há upload real de arquivo, conforme especificado no desafio).

### Tratamento de erros

Erros são retornados em formato padronizado (`{ "status", "mensagem", "timestamp" }`):

| Exceção | Status HTTP |
|---|---|
| `EntidadeNaoEncontradaException` | 404 Not Found |
| `EntidadeJaExisteException` | 409 Conflict |
| `RegraDeNegocioException` (e subclasses, ex. dono inválido) | 400 Bad Request |
| Erro não mapeado | 500 Internal Server Error |

## Testes e cobertura

```bash
# sobe o banco de dados usado pelos testes de integração
docker compose up -d postgres

# executa a suíte completa e aplica o gate de cobertura (mínimo 80%)
./mvnw verify
```

- **Testes unitários**: `core/domain/*Test.java` (invariantes de domínio) e `core/usecase/*ImplTest.java` (regras de negócio, com Mockito).
- **Testes de integração**: `infra/web/*ControllerIT.java`, sobem o contexto Spring completo (`@SpringBootTest`) e exercitam os endpoints via `TestRestTemplate` contra um Postgres real.
- **Cobertura**: relatório HTML gerado em `target/site/jacoco/index.html` após `mvn verify`. O build falha se a cobertura de linha cair abaixo de 80%.

## Collection Postman

Importe `Restaurantes Clean Arch.postman_collection.json` no Postman (ou similar). A variável `base_url` já aponta para `http://localhost:8080`. A collection cobre o CRUD completo dos quatro recursos: tipos de usuário, usuários, restaurantes e itens de cardápio, com cada request contendo test scripts (`pm.test`) de verificação.

As pastas seguem a ordem de dependência entre os recursos (`usuarios-tipos` → `usuarios` → `restaurantes` → `menu-items` → `cleanup`) e os IDs são encadeados automaticamente via variáveis de coleção — não é necessário editar IDs manualmente entre requests. Para rodar a collection inteira (ex. via Collection Runner ou `newman run`), suba o ambiente com banco limpo antes (`docker compose down -v && docker compose up -d --build`), já que a pasta `cleanup` remove os dados criados e o Postgres não reaproveita IDs de registros deletados.
