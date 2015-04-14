# Fixtube
É um micro-framework para manipulação de templates utilizada para auxiliar no processo de reutilização de fixtures de banco de dados na automação de testes.

Suponha que você tenha que inserir dados em 5 tabelas diferentes, para contextualizar um cenário de testes, bastaria colocar todos os comandos dentro de um arquivo de fixture (pedido.fixture):
```sql
insert into vendedor (id, nome) values (1, 'Nome do Vendedor');
insert into cliente (id, nome) values (1, 'Nome do Cliente');
insert into produto (id, nome, quantidade, preco) values (1, 'Macarrão Enrolados', 10, 3.40);
insert into produto (id, nome, quantidade, preco) values (2, 'Sardinha Costa do Sal', 20, 6.50);
insert into pedido (id, id_cliente, data) values (1, 1, '2015-04-14');
insert into item (id, id_pedido, id_produto, quantidade) values (1, 1, 1, 10);
insert into item (id, id_pedido, id_produto, quantidade) values (2, 1, 2, 15);
```
E executar:
```java
Fixtube.load("pedido");
```
http://dbunit.sourceforge.net/howto.html
http://archive.oreilly.com/pub/post/dbunit_made_easy.html
http://jyaml.sourceforge.net/yaml4dbunit.html
http://www.h2database.com/html/cheatSheet.html
http://www.h2database.com/html/quickstart.html
