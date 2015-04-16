# Fixtube

É um micro-framework para manipulação de templates utilizada para auxiliar no processo de reutilização de fixtures de banco de dados na automação de testes.

Suponha que você tenha que inserir dados em 5 tabelas diferentes, para contextualizar um cenário de testes, bastaria colocar todos os comandos dentro de um arquivo `pedido.fixture`:

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

Repare que algumas linhas do script repetem boa parte do seu conteúdo, sendo um excelente local candidato para eliminação de duplicação de código. Com o `Fixture` podemos:

```sql
insert into vendedor (id, nome) values (1, 'Nome do Vendedor');
insert into cliente (id, nome) values (1, 'Nome do Cliente');
!produto: 1, Macarrão Enrolados, 10, 3.40
!produto: 2, Sardinha Costa do Sal, 20, 6.50
insert into pedido (id, id_cliente, data) values (1, 1, '2015-04-14');
!item: 1, 1, 1, 10
!item: 2, 1, 2, 15
```

E para onde foi o conteúdo das linhas substituídas? Para os arquivos `produto.fixture`

```sql
insert into produto (id, nome, quantidade, preco) values ({0}, {1}, {2}, {3});
```

e `item.fixture`

```sql
insert into item (id, id_pedido, id_produto, quantidade) values ({0}, {1}, {2}, {3});
```

Mas e se eu quiser informar a data do pedido, dinamicamente? Basta alterar a linha do pedido para receber um parâmetro, assim:

```sql
insert into pedido (id, id_cliente, data) values (1, 1, '{0}');
```

E passar a chamar a carregar a fixture assim:

```java
Fixtube.load("pedido", "2015-04-14");
```

