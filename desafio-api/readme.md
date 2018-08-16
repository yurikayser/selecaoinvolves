Você vai precisar para executar o teste:

- Eclipse
- Maven
- Postman (ou qualquer outro client http)

Você deve fazer o download do repositório e iniciar o desenvolvimento a partir do exemplo criado.

Para rodar o exemplo, você deve executar os seguintes comandos na raiz do projeto:

- `mvn clean install`
- `mvn tomcat7:run` 

Feito isso, dê start na aplicação. Para garantir que esteja tudo ok, faça uma requisição `GET` para o seguinte endereço: `http://localhost:8080/greeting`.

O desafio consiste na criação de um CRUD via API para fornecedores e produtos. **Não é necessário adicionar um banco de dados na aplicação, somente o que será avaliado são as regras de negócio.** Quando precisar simular uma chamada para o banco de dados, deixe um comentário no código, ou apenas chame uma classe qualquer, vazia, ou que retorne o que você precise.

Duas entidades vão existir na aplicação:

- Fornecedor
  - ID
  - Nome
  - Lista de produtos
  
- Produto
  - ID
  - Nome


Seguem as funcionalidades esperadas:

- Deve ser possível adicionar um fornecedor
  - Não deve ser permitido adicionar um fornecedor sem nome
  - Não deve ser permitido adicionar um fornecedor sem produtos
- Deve ser possível adicionar um produto
  - Não deve ser permitido adicionar um produto sem nome
- Deve ser possível buscar todos os fornecedores
- Deve ser possível buscar um único fornecedor fornecendo o ID
- Deve ser possível remover um fornecedor



