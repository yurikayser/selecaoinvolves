Necessário para executar o teste:
- Eclipse
- Maven
- Postman (ou qualquer outro client REST)

Para rodar o projeto:

- Importe o projeto no eclipse
- rode o seguinte comando no terminal, na pasta raiz do projeto:
  -“mvn clean install”
- Execute o projeto no eclipse
- O projeto estará rodando no seguinte endereço:
  - “http://localhost:8090”
- Se tiver problemas com a porta 8090, mude a configuração do sistema no arquivo:
  - “application.properties”
- Para verificar se o projeto está ok, faça um GET para: http://localhost:8090/providers/

Considerações:

- As únicas classe que devem ser alteradas no projeto são a “ProviderControllerImpl” e a “ProviderServiceImpl”
- Os métodos que declaram a API e o DAO não devem ser alterados
- O arquivo “ProviderDao” deve ser desconsiderado. Lá estão escritos códigos que só servem para termos os retornos necessários para o teste da API

Exemplos de requests:

- GET - http://localhost:8090/providers/
- GET - http://localhost:8090/providers/3
- POST - http://localhost:8090/providers
  - Body:
```
{
   "name": "Provider Teste"
}

```
- PUT - http://localhost:8090/providers/1
  - Body:
```
{
   "name": "Provider alterado"
}
```
- DELETE - http://localhost:8090/providers/1

Questões:

- Implemente validações para o método de POST e PUT de Provider
  - Não deve ser possível adicionar um Provider sem nome
  - Não deve ser possível adicionar um Provider sem produtos
  - Não deve ser possível adicionar produtos sem nome em um Provider
- Faça uma validação no serviço do método de “find” para que retorne um status 404 na requisição quando não é encontrado um Provider na base de dados
  - Teste a requisição com o ID 99, o código do DAO retorna nulo para a busca de provider com esse ID.
- Termine de implementar o método “addProductToProvider” fazendo com que seja possível adicionar um produto em um provider já existente.


Dicas:

- Tente utilizar exceções para retornar erros de requisição com mensagens para o usuário final
- Trabalhe com os Status http corretamente no retorno de requisições
  - https://www.smartlabsoftware.com/ref/http-status-codes.htm


 
