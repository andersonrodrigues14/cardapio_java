use cardapio;

show tables;

create table item_cardapio (
	id bigint PRIMARY KEY AUTO_INCREMENT,
	nome varchar(100) NOT NULL,
	descricao varchar(1000),
	categoria ENUM('ENTRADAS', 'PRATOS_PRINCIPAIS', 'BEBIDAS', 'SOBREMESAS') NOT NULL,
	preco DECIMAL(9,2) NOT NULL,
	preco_promocional DECIMAL(9,2)
);


INSERT INTO cardapio.item_cardapio (nome, descricao, categoria, preco, preco_promocional) VALUES
	('Refresco do Chaves', 'Suco de limão que parece de tamarindo e tem gosto de groselha.', 'BEBIDAS', 2.99, NULL),
	('Sanduíche de Presunto do Chaves', 'Sanduíche de presunto simples, mas feito com muito amor.', 'PRATOS_PRINCIPAIS', 3.50, 2.99),
	('Torta de Frango da Dona Florinda', 'Torta de frango com recheio cremoso e massa crocante.', 'PRATOS_PRINCIPAIS', 12.99, 10.99),
	('Pipoca do Quico', 'Balde de pipoca preparado com carinho pelo Quico.', 'PRATOS_PRINCIPAIS', 4.99, 3.99),
	('Água de Jamaica', 'Água aromatizada com hibisco e toque de açúcar.', 'BEBIDAS', 2.50, 2.00),
	('Churros do Chaves', 'Churros recheados com doce de leite, clássicos e irresistíveis.', 'SOBREMESAS', 4.99, 3.99);


SELECT * FROM cardapio.item_cardapio;

SELECT COUNT(*) FROM cardapio.item_cardapio;