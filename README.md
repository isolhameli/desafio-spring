# Desafio Spring Boot - Israel Solha

Projeto do Bootcamp da DigitalHouse para preparação da turma de Software Engeineers do Mercado Livre.

### Credenciais do Banco de Dados H2 já populado

URL: http://localhost:8080/h2-console  
Driver class: org.h2.Driver  
JBCD URL: jdbc:h2:./h2-bootcamp  
username: mercadolibre  
password: (vazio / em branco)

### Documentação da API no Swagger

http://localhost:8080/swagger-ui.html#/

O arquivo de banco de dados se encontra na raiz do projeto: <b>h2-bootcamp.mv.db</b>
e pode ser deletado caso queira resetar o banco de dados.

O arquivo <b>Israel Solha - Desafio Spring.postman_collection.json</b> contem uma coleção que
pode ser importada para o Postman com todos os endpoints já organizados para mais fácil testagem.

O único endpoint diferente da documentação é o de cadastro de usuários:

http://localhost:8080/users/create

Que recebe dois parâmetros:

{"userName": "israelsolha", "seller": true}

Nesse caso, todos os vendedores são clientes, mas nem todo cliente é vendedor.

## Dados presentes no banco

### Users

| ID  | JOIN_DATE  | USERNAME     |  SELLER |
| --- |:----------:|--------------|:-------:|
| 1   | 2021-06-08 | felipe213    | true    |
| 2   | 2021-06-08 | amanda_lima  | true    |
| 3   | 2021-06-08 | israels      | false   |
| 4   | 2021-06-08 | matheusfelipe| false   |
| 5   | 2021-06-08 | armandoj     | false   |
| 11  | 2021-06-08 | carlos23     | false   |

Nota: Na verdade, como Seller herda de User, há uma tabela apenas com as propriedades gerais de User e
outra com as específicas de Seller, mas nesse caso, para facilitar leitura, foi apresentado da forma
como está acima

### Followers

| USER_ID  | SELLER_ID  |
| -------- | ---------- |
| 3        | 1          |
| 4        | 1          |
| 4        | 2          |
| 5        | 1          |

Nota: Qualquer User é capaz de seguir, mas apenas Sellers podem ser seguidos

### POSTS

| ID  | CATEGORY  | DATE      |  DISCOUNT |  HAS_PROMO | PRICE | PRODUCT_ID | SELLER_ID |
| --- |-----------|:---------:|-----------|:----------:|-------|------------|-----------|
| 6   | 120       | 2021-05-05| null      | false      |2800.69|  1         | 1         |
| 7   | 100       | 2021-06-05| null      | false      |1050.69|  2         | 1         |
| 8   | 59        | 2021-06-07| null      | false      |8.69   |  3         | 2         |
| 9   | 100       | 2021-06-06| 0.2       | true       |1500.50|  4         | 2         |
| 10  | 80        | 2021-06-03| 0.1       | true       |450.50 |  5         | 2         |


Adicionais:

- Tratamento de exceções com mensagem personalizadas avisando todos os campos que estão errados e como deveriam estar
- Sort ASC e DESC sendo feito em todas as propriedades das Responses feito de forma dinâmica. Ex: price_asc ou price_desc 
no endpoint de listagem de produtos.  
- Validação de duas camadas, tanto nos DTOs de Request como na Entidade, garantindo integridade dos dados durante todo
o fluxo da aplicação.
- Validações como userName único, para não registrar dois usuários com o mesmo userName.
- Anotações Customizadas como a @LocalDateCheck utilizada na data dos posts, para receber datas utilizando qualquer formato,
além de ter um maior controle do lançamento da exceção e agrupamento dos erros do que simplesmente utilizar um @JsonFormat.
- Utilização de JsonViews para gerenciar que propriedades estarão visíveis em cada endpoint
- Otimização de queries utilizando JPA Derived Queries, fazendo todas as buscas direto do banco, e puxando sempre que possível
os dados de maneira LAZY, para aumentar a eficiência do sistema
  

Qualquer dúvida, sugestão, comentários, favor entrar em contato:
israel.solha@mercadolivre.com

