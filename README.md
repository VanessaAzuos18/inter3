# LAFIESTA
## Estrutura dos pacotes da aplicação

lafiesta: contém a aplicação

- controller: Contém todos os controladores da view

- model: Contém classes relacionadas ao banco de dados

  - dao: Contém as classes que representam as entidades presentes no banco de dados. Cada classe deve possuir métodos capazes de manipular os dados presentes nas entidades
 
  - database: Contém classes que fazem conexão com o banco

  - domain: Contém classes que possuem métodos getters e setters capazes de definir e retornar valores oriundos da aplicação ou de alguma entidade. Cada classe representa uma entidade do banco

- view: Contém as telas salvas em arquivos fxml

res: contém as imagens utilizadas

