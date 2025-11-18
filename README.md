# Maternidade — MVP

Sistema de cadastro e acompanhamento de pacientes grávidas.

## Stack

- Front-end: Angular 18 + Angular Material
- Back-end: Spring Boot 3 (Java 21), JPA, Validation, Spring Security + JWT
- Banco: PostgreSQL
- Infra: Docker e docker-compose
- Documentação: Swagger/OpenAPI

## Arquitetura

- Angular → API Spring Boot → PostgreSQL
- Containers em rede bridge:
  - `frontend` (Nginx) acessa `api` via proxy `/api`
  - `api` conecta ao `postgres-db` via JDBC (`jdbc:postgresql://postgres-db:5432/maternidade`)

## Funcionalidades (MVP)

- Autenticação e autorização (JWT, RBAC com perfis `ADMIN`, `ATENDENTE`, `PROFISSIONAL_SAUDE`)
- Cadastro de pacientes grávidas
- Acompanhamento de pré-natal: consultas e exames
- Consultas e listagens, exportação CSV
- Administração básica de usuários e perfis (estrutura preparada)
- Cartão da Gestante (documento digital agregando dados da gestação, consultas, risco, exames, vacinação, mãe e bebê)

## Estrutura de diretórios

- `backend/` — Spring Boot
  - `src/main/java/com/clinica/maternidade`
    - `model` (entidades JPA)
    - `repository` (interfaces Spring Data)
    - `service` (regras de negócio)
    - `controller` (REST controllers)
    - `security`, `config`, `dto`
  - `src/main/resources/application.yml`
  - `src/main/resources/db/migration/` (Flyway `V1__init.sql`, `V2__gestante_card.sql`)
- `frontend/` — Angular
  - `src/app/core` (services, guards, interceptor)
  - `src/app/auth` (login)
  - `src/app/pacientes` (lista, detalhes, novo, editar)
  - `src/app/gestacao` (cartão da gestante)
  - `src/app/admin` (usuários)
- `docker-compose.yml`

## Configuração (dev)

### Pré-requisitos

- Docker e Docker Compose
- Portas livres: `80` (frontend), `8080` (API), `5432` (Postgres)

### Subir com Docker

- `docker compose up -d --build`
- Acessos:
  - Frontend: `http://localhost/`
  - Swagger API: `http://localhost:8080/swagger-ui.html`

### Banco de dados

- Postgres no Compose: usuário `app_user`, senha `app_password`, DB `maternidade`
- Flyway aplica migrações `V1` e `V2` na inicialização da API

### Login inicial

- Usuário `admin` com senha `admin123` semeado em `DataSeeder`

## Comandos locais (opcional)

- Backend: `mvn -q -DskipTests package` dentro de `backend/`
- Frontend: `npm install` e `npm run build` dentro de `frontend/`

## Endpoints principais

- Auth: `POST /api/auth/login`, `POST /api/auth/logout`
- Pacientes: `GET/POST/PUT/DELETE /api/pacientes`, `GET /api/pacientes/export`
- Gestação: `GET /api/gestacoes/{id}`, `GET/POST /api/pacientes/{pacienteId}/gestacoes`
- Consultas: `GET/POST /api/gestacoes/{gestacaoId}/consultas`
- Exames: `GET/POST /api/gestacoes/{gestacaoId}/exames`
- Cartão da Gestante: `GET /api/gestacoes/{gestacaoId}/cartao`

## Front-end (rotas)

- `/login`, `/pacientes`, `/pacientes/novo`, `/pacientes/:id`, `/pacientes/:id/editar`
- `/gestacoes/:id/cartao`
- `/admin/usuarios`

## Deploy AWS (ECS/ECR)

- Workflow GitHub Actions: `.github/workflows/deploy.yml`
- Configure secrets no repositório:
  - `AWS_REGION`, `AWS_ROLE_TO_ASSUME`
  - `ECR_REGISTRY`, `ECR_REPOSITORY_BACKEND`, `ECR_REPOSITORY_FRONTEND`
  - `ECS_CLUSTER`, `ECS_SERVICE_BACKEND`, `ECS_SERVICE_FRONTEND`
- Fluxo: build imagens, push para ECR, `ecs update-service --force-new-deployment`
- Banco em produção: preferir RDS PostgreSQL; ajustar `SPRING_DATASOURCE_URL`

## Ambiente

- Variáveis (Compose):
  - `SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/maternidade`
  - `SPRING_DATASOURCE_USERNAME=app_user`
  - `SPRING_DATASOURCE_PASSWORD=app_password`
  - `JWT_SECRET` (defina uma chave segura em produção)

## Testes

- Backend: JUnit/Mockito, exemplos podem ser expandidos em `backend/src/test`
- Validações via `jakarta.validation`

## Observações

- Angular Material com tema em `src/styles.scss` alinhado a tranquilidade/cuidado/acolhimento
- RBAC aplicado com `@PreAuthorize` nos controllers

## Licença

- Defina a licença conforme sua preferência (ex.: MIT)