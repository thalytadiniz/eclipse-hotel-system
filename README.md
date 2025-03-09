Eclipse Hotel System

O Eclipse Hotel System é uma API desenvolvida em Java utilizando o framework Spring Boot. O sistema foi criado para gerenciar clientes, quartos e reservas de um hotel, oferecendo operações básicas de CRUD (Create, Read, Update, Delete). Além disso, a API inclui funcionalidades extras que vão além do CRUD, trazendo mais eficiência para a gestão hoteleira.
A estrutura do projeto foi pensada para ser clara, modular e de fácil manutenção, seguindo as boas práticas do Spring Boot e do desenvolvimento de APIs.

---

## 2. Requisitos Solicitados e Implementados

### 2.1. Estrutura do Projeto
- **Camadas do Projeto**: O sistema foi estruturado seguindo as boas práticas do Spring Boot, com as seguintes camadas:
  - **Controller**: Responsável por receber as requisições HTTP e retornar as respostas.
  - **Service**: Contém a lógica de negócio do sistema.
  - **Repository**: Faz a comunicação com o banco de dados.
  - **DTO (Data Transfer Object)**: Usado para transferir dados entre as camadas.
  - **Entity**: Representa as tabelas do banco de dados.
  - **Enums**: Define os status de reservas e quartos.
  - **Exceptions**: Tratamento de erros personalizados.

### 2.2. Integração com Banco de Dados
- **Hibernate**: As tabelas do banco de dados são criadas automaticamente pelo Hibernate, com base nas entidades mapeadas (`CustomerHotel`, `RoomHotel`, `Reservation`).
- **Entidades Mapeadas**:
  - `CustomerHotel`: Representa os clientes do hotel.
  - `RoomHotel`: Representa os quartos do hotel.
  - `Reservation`: Representa as reservas feitas pelos clientes.

### 2.3. Endpoints Implementados
- **Customers**:
  - `POST /customers`: Cria um novo cliente.
  - `GET /customers`: Retorna todos os clientes.
  - `GET /customers/{id}`: Retorna um cliente específico pelo ID.
  - `PUT /customers/{id}`: Atualiza um cliente existente.
  - `DELETE /customers/{id}`: Exclui um cliente.

- **Rooms**:
  - `POST /rooms`: Cria um novo quarto.
  - `GET /rooms`: Retorna todos os quartos.
  - `GET /rooms/{id}`: Retorna um quarto específico pelo ID.
  - `PUT /rooms/{id}`: Atualiza um quarto existente.
  - `DELETE /rooms/{id}`: Exclui um quarto.
  - `GET /rooms/occupied`: Retorna todos os quartos ocupados no momento.

- **Reservations**:
  - `POST /reservations`: Cria uma nova reserva.
  - `GET /reservations`: Retorna todas as reservas.
  - `GET /reservations/{id}`: Retorna uma reserva específica pelo ID.
  - `PUT /reservations/{id}`: Atualiza uma reserva existente.
  - `DELETE /reservations/{id}`: Exclui uma reserva.
  - `POST /reservations/{id}/close`: Encerra uma reserva, atualizando seu status para `FINISHED` ou `ABSENCE`.
  - `GET /reservations/by-date-range`: Retorna todas as reservas dentro de um intervalo de datas.
  - `POST /reservations/{id}/cancel`: Cancela uma reserva, atualizando seu status para `CANCELED`.

---

## 3. Gerenciamento de Status de Reservas

### 3.1. Status de Reservas
O sistema implementa o gerenciamento de status de reservas conforme solicitado, com os seguintes valores:

- **SCHEDULED**: Indica que o quarto selecionado está reservado para o período de check-in escolhido.
- **IN_USE**: Indica que o quarto está ocupado no momento.
- **ABSENCE**: Indica que o responsável pela reserva não compareceu ao hotel. Este status é final e não pode ser alterado.
- **FINISHED**: Indica que a reserva foi concluída com sucesso. Este status é final e não pode ser alterado.
- **CANCELED**: Indica que a reserva foi cancelada antes do check-in. Este status é final e não pode ser alterado.

### 3.2. Validações de Status
- **Status Final**: Reservas com status final (`FINISHED`, `ABSENCE`, `CANCELED`) não podem ser alteradas ou excluídas.
- **Transições de Status**: O sistema valida as transições de status para garantir que apenas operações válidas sejam realizadas.

---

## 4. Funcionalidades Adicionais Implementadas

### 4.1. Endpoint para Quartos Ocupados
- Foi adicionado o endpoint `GET /rooms/occupied` para listar os quartos ocupados no momento, o que ajuda na gestão diária do hotel.

### 4.2. Endpoint para Cancelamento de Reservas
- Foi adicionado o endpoint `POST /reservations/{id}/cancel` para cancelar reservas, o que aumenta a flexibilidade do sistema.

### 4.3. Endpoint para Consulta de Reservas por Intervalo de Datas
- Foi adicionado o endpoint `GET /reservations/by-date-range` para consultar reservas em um intervalo de datas, o que facilita a análise de ocupação do hotel.

### 4.4. Cálculo Automático do Preço Total da Reserva
- O sistema calcula automaticamente o preço total da reserva com base no número de dias e no preço do quarto, o que melhora a precisão e a eficiência

---

## 5. Por que a criação de uma API?
- **Automatização**: A API automatiza processos manuais, como a gestão de clientes, quartos e reservas.
- **Escalabilidade**: O sistema é escalável e pode ser facilmente expandido para incluir novas funcionalidades.
- **Eficiência**: A API melhora a eficiência operacional do hotel, reduzindo erros e tempo de processamento.
- **Experiência do Usuário**: Oferece uma experiência mais fluida para os funcionários do hotel, com operações rápidas e intuitivas.
