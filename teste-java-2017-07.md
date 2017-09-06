Seleção Desenvolvedor Java - Todos os níveis
===================


Olá, estamos muito felizes pelo seu interesse em fazer parte da nossa equipe de desenvolvimento. Gostaríamos de conhecer um pouco mais das suas habilidades com **Java** e, para isso, preparamos o teste abaixo.

----------


Sobre o teste
-------------

> **:warning::warning::warning:ATENÇÃO:** Atualmente estamos tendo uma taxa de **95%** de reprovação dos candidatos nessa etapa. Os maiores motivos de reprovação para candidatos seniores é a não adequação do código aos princípios SOLID. Lembre-se, não estamos avaliando apenas se o código funciona ou não, também utilizamos CLEAN CODE e princípios SOLID como critérios de validação.


A prova consiste em criar um programa que leia todas as linhas do arquivo CSV ([faça o download aqui](cidades.csv)) e utilize esse arquivo como base para consultas, onde a primeira linha (cabeçalho) contém o nome das propriedades e as linhas subsequentes os valores. Após ler o arquivo, o programa deve começar a ouvir o console a espera dos comandos de consulta. Abaixo segue a lista dos comandos que devem ser interpretados:

- **count** * - escreve no console a contagem total de registros importados (não deve considerar a linha de cabeçalho)
- **count distinct [propriedade]** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter [propriedade] [valor]** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 

> **Restrições:**

> - Não é permitido utilizar bibliotecas e frameworks externos, apenas classes do JDK5 ou superior (exceto JUnit para os testes de unidade e Maven ou Gradle como ferramentas build)
> - Interações devem ser feitas apenas via console (System.in e System.out)
> - Não é permitido utilizar bancos de dados ou outros serviços de armazenamento de dados que não tenham sido desenvolvidos pelo próprio candidato

Sobre a avaliação
-------------

Os critérios de avaliação serão diferentes de acordo com o cargo em que o candidato se inscreveu (Desenvolvedor I, II ou III). 

Para **Desenvolvedor I**:

- Nível de cumprimento dos requisitos
- Abrangência dos testes unitários


Para **Desenvolvedor II**:
 
- Todos os critérios do Desenvolvedor I
- Uso adequado de padrões de projetos
- Flexibilidade do código para futuras evoluções (ex: alterar a fonte de dados de cidades para um arquivo XML, novos comandos)


Para **Desenvolvedor III**:

- Todos os critérios do Desenvolvedor II
- Utilização de princípios SOLID
- Otimizações em relação ao uso de memória


Sobre a entrega
-------------

Deve ser criado um repositório público no GitHub com todo o código do programa e com instruções de como executá-lo e como executar os testes. O link do repositório deve ser enviado via email para talentos@involves.com.br com o título ***"[teste-java-2017-07] Nome do Candidato"*** até a data limite informada no email em que você recebeu o link para esse teste.

