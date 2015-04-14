# Fixtube
É um micro-framework para manipulação de templates utilizada para auxiliar no processo de reutilização de fixtures de banco de dados na automação de testes.

Suponha que você tenha que inserir dados em 5 tabelas diferentes, para contextualizar um cenário de testes:
```sql
insert into produto (id, nome, quantidade, preco) values (1, 'Macarrão Fortaleza', 10, 3.40);
```
Com Fixtube, bastaria:
Fixtube.load("pedido");

http://dbunit.sourceforge.net/howto.html
http://archive.oreilly.com/pub/post/dbunit_made_easy.html
http://jyaml.sourceforge.net/yaml4dbunit.html
http://www.h2database.com/html/cheatSheet.html
http://www.h2database.com/html/quickstart.html
