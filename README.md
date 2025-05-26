# Sistema de Agendamento de Consultas

Backend modular desenvolvido em Spring Boot com GraphQL, Kafka e Docker para gerenciar agendamentos, envio de notificações e histórico de pacientes.

---

## 📋 Sumário

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Execução com Docker](#execução-com-docker)
- [Endpoints GraphQL](#endpoints-graphql)
- [Estrutura dos Projetos](#estrutura-dos-projetos)
- [Requisitos](#requisitos)
- [Autores](#autores)

---

## 🧭 Visão Geral

Em um ambiente hospitalar, a gestão eficiente de agendamentos e notificações é essencial para o bom funcionamento da operação e para garantir a presença dos pacientes. Este sistema foi desenvolvido com foco em modularidade, escalabilidade, mensageria assíncrona (via Kafka) e uso de GraphQL para consultas flexíveis.

---

## 🛠 Funcionalidades

- Agendamento de consultas médicas
- Consulta do histórico e próximas consultas de pacientes
- Envio automático de notificações via Kafka
- Consulta de dados via GraphQL
- Separação em serviços independentes: `agendamento-service`, `notificacao-service`

---

## 🧱 Arquitetura

- **Agendamento-service**: Responsável por cadastrar, atualizar e consultar consultas.
- **Notificacao-service**: Consumidor Kafka que recebe eventos de consulta e simula notificações (log).
- Comunicação assíncrona via **Apache Kafka**
- Banco de dados **H2** em memória para desenvolvimento
- Infraestrutura orquestrada com **Docker Compose**

---

## 🚀 Tecnologias Utilizadas

| Tecnologia        | Uso                                      |
|-------------------|-------------------------------------------|
| Spring Boot       | Core da aplicação backend                 |
| Spring GraphQL    | Interface para consultas e mutações       |
| Spring Data JPA   | Acesso a dados relacional via H2          |
| Apache Kafka      | Mensageria assíncrona entre serviços      |
| Docker            | Containerização e orquestração dos serviços |
| Lombok            | Redução de boilerplate em Java            |
| H2 Database       | Banco em memória para desenvolvimento     |

---

## 🐳 Execução com Docker

### Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Passos

1. Clone o repositório:

git clone https://github.com/luizavramos/SistemaDeConsultas.git
cd SistemaDeConsultas

2. Suba os serviços:
docker-compose up --build

3. Acesse:

Agendamento: http://localhost:8081/graphiql

Notificações (sem UI): mensagens no console

Kafka: executado internamente nos containers

### 📌 Consultas GraphQL

#### 🔍 Queries

```graphql
query {
  consultasPorPaciente(pacienteId: "p001") {
    id
    dataHora
  }
}
```

```graphql
query {
  consultasFuturas(pacienteId: "p001") {
    id
    dataHora
  }
}
```

#### 📝 Mutações

```graphql
mutation {
  criarConsulta(input: {
    pacienteId: "p001",
    medicoId: "m001",
    dataHora: "2025-06-15T10:30:00",
    observacoes: "Retorno"
  }) {
    id
  }
}
```

```graphql
mutation {
  atualizarConsulta(id: "1", input: {
    pacienteId: "p001",
    medicoId: "m002",
    dataHora: "2025-06-20T09:00:00",
    observacoes: "Alterado"
  }) {
    id
  }
}
```


<pre lang="markdown"> ### 📁 Estrutura dos Projetos ```plaintext SistemaDeConsultas/ │ ├── agendamento-service/ │ ├── src/ │ │ └── main/ │ │ ├── java/ │ │ │ └── com/tech_challenge/agendamento_service/ │ │ │ ├── model/ │ │ │ ├── service/ │ │ │ ├── repository/ │ │ │ ├── kafka/ │ │ │ └── graphql/resolver/ │ │ └── resources/ │ │ └── application.yml │ └── Dockerfile │ ├── notificacao-service/ │ ├── src/ │ │ └── main/ │ │ ├── java/ │ │ │ └── com/tech_challenge/notificacao_service/ │ │ │ ├── consumer/ │ │ │ ├── model/ │ │ │ └── service/ │ │ └── resources/ │ │ └── application.yml │ └── Dockerfile │ ├── docker-compose.yml └── README.md ``` </pre>

📦 Requisitos
Java 17+

Maven 3.8+

Docker & Docker Compose

👥 Autora
Luiza V. Ramos – RM: 360207

