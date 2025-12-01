# springboot-messaging-system
# Spring Boot Messaging System

Sistema de mensageria construído com Spring Boot, RabbitMQ, MongoDB e PostgreSQL. O projeto contém dois serviços: `user` (cadastro de usuários e envio de mensagens) e `notifications` (consumo de mensagens e histórico por usuário). Infraestrutura orquestrada via Docker Compose.

## Visão Geral

- Dois serviços Spring Boot: `user` (porta `8080`) e `notifications` (porta `8081`)
- Integração com RabbitMQ para publicação/consumo de mensagens
- Persistência:
  - `user`: PostgreSQL (mensagens vinculadas ao usuário)
  - `notifications`: MongoDB (histórico de notificações por `userId`)
- Painéis de gerenciamento:
  - RabbitMQ Management: `http://localhost:15672` (user/password)
  - Mongo Express: `http://localhost:8082`

## Tecnologias

- Java `21`
- Spring Boot `4.0.0`
- Spring AMQP (RabbitMQ)
- Spring Data JPA (PostgreSQL) e Spring Data MongoDB (MongoDB)
- Docker e Docker Compose
- Lombok

## Serviços Utilizados

- Docker
- RabbitMQ
- MongoDB
- PostgreSQL

## Pré-requisitos

- `Java 21` instalado (para rodar localmente sem Docker)
- `Docker` e `Docker Compose` instalados
- Portas disponíveis:
  - `8080` (user), `8081` (notifications)
  - `5672` e `15672` (RabbitMQ)
  - `27017` (MongoDB), `8082` (Mongo Express)
  - `5433` (PostgreSQL publicado; interno `5432`)

## Configuração de Ambiente

- Variáveis de ambiente (override dos `application.properties`):
  - RabbitMQ:
    - `SPRING_RABBITMQ_HOST` (padrão `rabbitmq` em Compose, `localhost` fora)
    - `SPRING_RABBITMQ_PORT` (padrão `5672`)
    - `SPRING_RABBITMQ_USERNAME` (padrão `user`)
    - `SPRING_RABBITMQ_PASSWORD` (padrão `password`)
  - MongoDB (`notifications`):
    - `SPRING_DATA_MONGODB_URI` (padrão `mongodb://root:secretpassword@mongodb:27017/notifications?authSource=admin` em Compose)
  - PostgreSQL (`user`):
    - `SPRING_DATASOURCE_URL` (padrão `jdbc:postgresql://postgres:5432/userdb` em Compose; local: `jdbc:postgresql://localhost:5433/userdb`)
    - `SPRING_DATASOURCE_USERNAME` (padrão `postgres`)
    - `SPRING_DATASOURCE_PASSWORD` (padrão `postgres`)

## Subir a Stack (Docker Compose)

```bash
docker compose up -d
```

- Serviços iniciados:
  - `notification-rabbitmq`, `notification-mongodb`, `notification-mongo-express`, `notification-postgres`
  - `user-app` (porta `8080`) e `notifications-app` (porta `8081`)

## Executar Localmente (sem Docker)

- Banco e filas via Docker Compose (opcional):

```bash
docker compose up -d rabbitmq mongodb mongo-express postgres
```

- Aplicações via Maven Wrapper (Windows):

```bash
./user/mvnw.cmd -f user/pom.xml spring-boot:run
./notifications/mvnw.cmd -f notifications/pom.xml spring-boot:run
```

- Aplicações via Maven Wrapper (Linux/Mac):

```bash
./user/mvnw -f user/pom.xml spring-boot:run
./notifications/mvnw -f notifications/pom.xml spring-boot:run
```

## Endpoints Principais

- `user` (porta `8080`)
  - `GET /users` — lista usuários
  - `GET /users/{id}` — detalha usuário
  - `POST /users` — cria usuário
  - `POST /users/{id}/messages` — envia mensagem para o usuário e publica na fila `notifications.queue`

- `notifications` (porta `8081`)
  - `GET /history/{userId}` — retorna lista de notificações associadas ao `userId`

## Fluxo de Mensagens

- `user` salva a mensagem no usuário e publica um objeto `NotificationEvent` em `notifications.queue`
- `notifications` consome a fila, persiste `{id, userId, text}` em MongoDB e expõe o histórico via REST

## Exemplo de Envio de Mensagem

```bash
curl -X POST http://localhost:8080/users/1/messages \
  -H "Content-Type: application/json" \
  -d '{"payload":"Testando envio de mensagem"}'
```

- Ver histórico:

```bash
curl http://localhost:8081/history/1
```

## Observações

- No Docker Compose, utilize nomes de serviços como hosts: `rabbitmq`, `mongodb`, `postgres`
- Fora do Docker, use `localhost` com as portas publicadas
- Credenciais padrão devem ser trocadas em produção

## Versionamento

- `1.0.0`

## Autores

- `https://www.linkedin.com/in/luizfernando-react-java-fullstack/`

Obrigado por visitar e bons códigos!
