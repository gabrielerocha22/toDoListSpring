# Projeto ToDo List
Este projeto consiste em um sistema simples de gerenciamento de tarefas (ToDo List) desenvolvido em Spring Boot. Ele permite adicionar, editar, excluir e listar tarefas, utilizando um banco de dados SQL Server hospedado no Azure.

# Requisitos
- Java 17 ou superior
- Spring Boot
- SQL Server (local ou Azure)
- Maven
- Git
- IDE (IntelliJ, Eclipse, ou qualquer outra que você preferir)

# Tecnologias Utilizadas
- Back-end: Spring Boot (Java)
- Banco de Dados: SQL Server
- Migrations: Flyway
- Gerenciamento de Dependências: Maven
- Controle de Versão: Git (GitHub)

# Configuração do Banco de Dados
## Passo 1: Criar o banco de dados no Azure (ou local)
### Azure SQL Server:
- Vá até o portal do Azure.
- Crie um banco de dados SQL na sua conta do Azure.
- Certifique-se de permitir conexões externas (como o IP da sua máquina).

### Banco de Dados Local:
- Instale o SQL Server Express ou a versão que preferir.
 Crie um banco de dados para este projeto.

## Passo 2: Configuração do arquivo application.properties
### No seu arquivo src/main/resources/application.properties, configure as informações de conexão do banco de dados:


```bash 
spring.datasource.url=jdbc:sqlserver://<HOST>:1433;databaseName=<DB_NAME>;
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver ```

 
# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

### Substitua os valores de <HOST>, <DB_NAME>, <DB_USERNAME> e <DB_PASSWORD> com as configurações do seu banco de dados (seja no Azure ou local).

## Passo 3: Scripts de Migração
### Os scripts de migração estão localizados na pasta src/main/resources/db/migration. O arquivo de migração inicial será o responsável por criar a tabela de tarefas.

- Exemplo de script de migração (V1__Create_Task_Table.sql):

```bash
CREATE TABLE tasks (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at DATETIME DEFAULT GETDATE(),
    status VARCHAR(50) NOT NULL
);
```
## Ao rodar o projeto pela primeira vez, o Flyway irá automaticamente executar os scripts de migração para configurar o banco de dados.

## Passo 4: Inicializando o Banco de Dados
### Após configurar as propriedades acima e ter o banco de dados disponível, execute a aplicação. O Flyway irá criar automaticamente a tabela tasks na sua base de dados.

# Rodando o Projeto
## Passo 1: Clonar o Repositório
- Clone o repositório para sua máquina local:

``` bash
git clone https://github.com/usuario/to-do-list.git
cd to-do-list
``` 

## Passo 2: Compilar e Rodar o Projeto
- Execute o seguinte comando para compilar e rodar o projeto:

```bash
mvn spring-boot:run
A aplicação estará disponível em http://localhost:8080.
```

### Passo 3: Testando os Endpoints
## A API disponibiliza os seguintes endpoints para interação com as tarefas:

### 1. POST /tasks
- Cria uma nova tarefa.

- Exemplo de Body:

``` bash
{
  "title": "Tarefa 1",
  "description": "Descrição da Tarefa 1",
  "status": "Pendente"
}
``` 
### Resposta:
```bash
{
  "id": 1,
  "title": "Tarefa 1",
  "description": "Descrição da Tarefa 1",
  "status": "Pendente"
}
```
### 2. GET /tasks
- Lista todas as tarefas.

### Resposta:

``` bash
[
  {
    "id": 1,
    "title": "Tarefa 1",
    "description": "Descrição da Tarefa 1",
    "status": "Pendente"
  }
]
```

### 3. PUT /tasks/{id}
- Atualiza uma tarefa existente.

- Exemplo de Body:

``` bash
{
  "title": "Tarefa Atualizada",
  "description": "Descrição da Tarefa Atualizada",
  "status": "Concluída"
}
```
- Resposta:

``` bash
{
  "id": 1,
  "title": "Tarefa Atualizada",
  "description": "Descrição da Tarefa Atualizada",
  "status": "Concluída"
}
```

### 4. DELETE /tasks/{id}
- Deleta uma tarefa.

- Resposta:

``` bash
{
  "message": "Tarefa deletada com sucesso."
}
```
# Observações
- O projeto usa Flyway para gerenciar migrações do banco de dados, garantindo que as tabelas sejam criadas de forma automatizada.
- Todos os dados são armazenados em um banco de dados SQL Server, que pode ser configurado localmente ou no Azure.
## Considerações Finais
- O projeto foi desenvolvido de forma a ser fácil de configurar e executar.
- O banco de dados pode ser configurado tanto localmente quanto na nuvem (Azure).
- Não há dependências externas para rodar a aplicação.
