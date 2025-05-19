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

## Observações

- O projeto está pronto para testes rápidos via Postman, Insomnia ou Swagger UI.
- O banco de dados é resetado sempre que a aplicação é reiniciada (por ser H2 em memória).

---
Desenvolvido por [GuiAparecido](https://github.com/GuiAparecido)
