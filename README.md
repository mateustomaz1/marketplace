# Marketplace

Este repositório contém um sistema de **Marketplace**, desenvolvido com **Spring Boot** no backend e **Angular** no frontend. O projeto foi estruturado seguindo padrões de arquitetura modernos, garantindo escalabilidade e manutenção eficiente.

## Tecnologias Utilizadas

### Backend:
- **Java 17 ou superior**
- **Spring Boot 3.x**
- **Spring Security** (Autenticação e Autorizacao com JWT)
- **PostgreSQL** (Banco de Dados)
- **JPA/Hibernate** (ORM para manipulação dos dados)
- **Lombok** (Redução de boilerplate)

### Frontend:
- **Angular 17**
- **Bootstrap** (Estilização e responsividade)
- **TypeScript**
- **Axios** (Consumo da API REST)

## Padrões de Projeto Utilizados

- **Arquitetura MVC (Model-View-Controller)**
- **Repository Pattern** (Camada de persistência desacoplada)
- **DTOs (Data Transfer Objects)**
- **Service Layer** (Lógica de negócio separada do Controller)
- **JWT Token Authentication** (Autenticação segura)

## Funcionalidades Principais

- Cadastro e autenticação de usuários (JWT)
- Cadastro, edição e exclusão de serviços
- Pesquisa e listagem de serviços
- Agendamento de serviços entre clientes e prestadores
- Perfil de usuário

## Estrutura do Projeto

```
marketplace/
│-- backend/  # Código do Spring Boot
│   │-- src/main/java/com/marketplace/
│   │   │-- controller/
│   │   │-- service/
│   │   │-- repository/
│   │   │-- model/
│   │-- resources/application.yml  # Configuração do banco
│-- frontend/  # Código do Angular
│   │-- src/
│   │   │-- app/
│   │   │   │-- components/
│   │   │   │-- services/
│-- README.md
```

## Como Rodar o Projeto

### 1. Backend (Spring Boot)
1. Clone o repositório:  
   ```bash
   git clone https://github.com/seuusuario/marketplace.git
   ```
2. Configure o **PostgreSQL** e crie um banco de dados
3. Ajuste as configurações no arquivo `application.yml`  
4. Execute o projeto com Maven:
   ```bash
   mvn spring-boot:run
   ```

### 2. Frontend (Angular)
1. Navegue até o diretório `frontend/` e instale as dependências:
   ```bash
   npm install
   ```
2. Inicie o servidor de desenvolvimento:
   ```bash
   npm install
   ```
3. Acesse `http://localhost:4200/`

## Documentação da API
A documentação da API pode ser acessada através do **Swagger**:
```
http://localhost:8080/swagger-ui.html
```

## Licença
Este projeto está licenciado sob a **MIT License**.

