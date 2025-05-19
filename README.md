# Controle de Estoque API

API REST para controle de estoque, desenvolvida como teste para C&A.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de dados:** H2 (em memória)
- **Documentação:** Swagger (OpenAPI)

## Regras de Negócio

- **O preço do produto deve ser maior que R$ 9,90:**  
  Esta validação é feita diretamente nos DTOs de entrada utilizando a anotação `@DecimalMin(value = "9.90", message = "O preço não pode ser menor que R$ 9,90")`.  
  Exemplo no código:
  ```java
  @DecimalMin(value = "9.90", message = "O preço não pode ser menor que R$ 9,90")
  private BigDecimal preco;
  ```
  Isso garante que qualquer requisição para cadastrar ou atualizar o preço de um produto que não atenda a essa regra será rejeitada com erro 400 e mensagem explicativa.

- **Armazenamento da data de alteração:**  
  Toda vez que um produto é alterado (preço ou quantidade), a data da última modificação é registrada automaticamente no banco de dados. Isso permite rastrear quando cada produto foi modificado pela última vez.

## Como rodar a aplicação

1. **Clone o repositório**
   ```
   git clone https://github.com/GuiAparecido/controle-estoque-api.git
   ```
2. **Execute o projeto**
   ```
   ./mvnw spring-boot:run
   ```
   Ou abra no IntelliJ/Eclipse e rode a classe principal.

3. **Acesse o banco H2:**  
   - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL padrão: `jdbc:h2:mem:testdb`

4. **Acesse a documentação Swagger:**  
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints principais

- `POST /estoque` — Cadastro de produto
- `GET /estoque` — Listagem de produtos
- `PUT /estoque/{id}/preco` — Alteração de preço
- `PUT /estoque/{id}/quantidade` — Alteração de quantidade
- `DELETE /estoque/{id}` — Exclusão de produto

## Exemplo de uso no Postman

### 1. Cadastrar produto
- **Endpoint:** `POST http://localhost:8080/estoque`
- **Body (JSON):**
  ```json
  {
    "nome": "Produto Exemplo",
    "preco": 12.50,
    "quantidade": 10
  }
  ```
- **Se enviar `preco` menor que 9.90, receberá:**
  ```json
  {
    "preco": "O preço não pode ser menor que R$ 9,90"
  }
  ```

### 2. Listar produtos
- **Endpoint:** `GET http://localhost:8080/estoque`

### 3. Alterar preço do produto
- **Endpoint:** `PUT http://localhost:8080/estoque/{id}/preco`
- **Body (JSON):**
  ```json
  {
    "preco": 15.00
  }
  ```

### 4. Alterar quantidade do produto
- **Endpoint:** `PUT http://localhost:8080/estoque/{id}/quantidade`
- **Body (JSON):**
  ```json
  {
    "quantidade": 20
  }
  ```

### 5. Excluir produto
- **Endpoint:** `DELETE http://localhost:8080/estoque/{id}`

## Testes e Validações

- **Validação automática:**  
  Todos os campos obrigatórios e regras de negócio (como preço mínimo e quantidade mínima) são validados automaticamente pelo Spring.
- **Mensagens de erro:**  
  Caso a validação falhe, a API retorna um JSON com o motivo do erro e o campo correspondente.

---

## Como foi feito

A API foi desenvolvida utilizando as melhores práticas do ecossistema Spring Boot, buscando clareza, organização e facilidade de manutenção e evolução. O projeto segue uma arquitetura tradicional para aplicações REST em Java, com os seguintes destaques:

### Estrutura do Projeto

- **Baseada em camadas:**  
  O código é organizado separando responsabilidades em camadas distintas:
  - **Controller:** Responsável por expor os endpoints REST, receber e validar as requisições.
  - **Service:** Realiza a lógica de negócio, orquestrando as operações e aplicando regras específicas como validações de preço e quantidade.
  - **Repository:** Camada de acesso ao banco de dados, utilizando Spring Data JPA para facilitar operações CRUD sem necessidade de SQL manual.
  - **Model/Entity:** Define as entidades persistidas, representando os produtos do estoque.

- **DTOs (Data Transfer Objects):**  
  Utilizados para trafegar dados entre as camadas e para garantir que apenas as informações necessárias sejam expostas nas respostas e aceitas nas requisições.

### Principais Funcionalidades Técnicas

- **Validação automática:**  
  Utilização das anotações do Bean Validation (javax.validation), como `@DecimalMin`, para garantir que as regras de negócio (ex: preço mínimo) sejam verificadas antes do processamento. Erros de validação geram respostas claras e amigáveis ao usuário.

- **Auditoria de alterações:**  
  Toda vez que um produto é atualizado (preço ou quantidade), a data da última alteração é registrada automaticamente na entidade. Isso permite o rastreamento histórico de modificações, importante para controle de estoque.

- **Documentação automática:**  
  A API gera sua própria documentação interativa utilizando Swagger (OpenAPI), facilitando testes, integração e entendimento dos contratos disponíveis.

- **Banco H2 em memória:**  
  Escolhido para simplificar a execução e os testes locais, eliminando a necessidade de configuração extra de banco. O banco é resetado a cada reinício da aplicação.

### Boas Práticas e Decisões Adotadas

- **Separaçao de responsabilidades:**  
  Cada camada tem seu papel bem definido, facilitando manutenção e testabilidade.
- **Mensagens de erro claras:**  
  Respostas de erro detalham quais campos estão inválidos e o motivo, ajudando o usuário final.
- **Facilidade para testes:**  
  Toda a API pode ser testada via Postman, Insomnia ou diretamente pela interface Swagger UI.

---

## Observações

- O projeto está pronto para testes rápidos via Postman, Insomnia ou Swagger UI.
- O banco de dados é resetado sempre que a aplicação é reiniciada (por ser H2 em memória).
- **Data de alteração dos produtos:** Sempre que um produto é atualizado, a data da última alteração é salva e pode ser consultada.

---
Desenvolvido por [GuiAparecido](https://github.com/GuiAparecido)
