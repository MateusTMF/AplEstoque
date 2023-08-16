create table TipoProduto(
    idTipoProduto serial primary key,
    descricao varchar(200)
);

create table Produto(
    idProduto serial primary key,
    nomeProduto varchar(200),
    ultimoPrecoPago integer,
    saldoAtual integer,
    idTipoProduto serial,
    idUnidadeMedida serial,

    constraint fk_tipoProduto foreign key (idTipoProduto) REFERENCES TipoProduto,
    constraint fk_unidadeMedida foreign key (idUnidadeMedida) references UnidadeMedida
);

create table UnidadeMedida(
idUnidadeMedida serial primary key,
    descricao varchar(200),
    sigla varchar (3)
);

create table MovimentoEstoque(
idMovimento serial primary key,
    entradaSaida varchar(20),
    documento varchar (30),
    data varchar (20),
    quantidade integer,
    valorMovimento integer,
    idProduto serial,
    idTipoMovimento serial,
    idFuncionario serial,

    constraint fk_produto foreign key (idProduto)references Produto,
    constraint fk_tipoMovimento foreign key (idTipoMovimento)references TipoMovimento,
    constraint fk_funcionario foreign key (idFuncionario)references Funcionario
);


create table TipoMovimento (
idTipoMovimento serial primary key,
    descricao varchar (20)
);

create table Funcionario (
idFuncionario serial primary key, 
    nomeFuncionario varchar (20)
);


/* Insert intos Funcionarios */
insert into funcionario(idFuncionario, nomeFuncionario) values(1, 'José');
insert into funcionario(idFuncionario, nomeFuncionario) values(2, 'Gabriela');
insert into funcionario(idFuncionario, nomeFuncionario) values(3, 'Lucas');
insert into funcionario(idFuncionario, nomeFuncionario) values(4, 'Roberto');

/* Insert intos Tipo Produto */

insert into tipoproduto(idTipoProduto, descricao) values(1, 'alimentos');
insert into tipoproduto(idTipoProduto, descricao) values(2, 'bebida');
insert into tipoproduto(idTipoProduto, descricao) values(3, 'eletronico');
insert into tipoproduto(idTipoProduto, descricao) values(4, 'Academia');

/* insert intos unidade medida */

Insert into unidademedida(idUnidadeMedida, descricao, sigla) values(1, 'quilograma', 'kg');
Insert into unidademedida(idUnidadeMedida, descricao, sigla) values(2, 'litro', 'ml');
Insert into unidademedida(idUnidadeMedida, descricao, sigla) values(3, 'heartz', 'mhz');
Insert into unidademedida(idUnidadeMedida, descricao, sigla) values(4, 'peso', 'kg');

/* insert into Produto */

insert into produto(idProduto, nomeProduto, ultimoPrecoPago, saldoAtual, idTipoProduto, idUnidadeMedida) values(1, 'arroz', 5.50, 20.00, 1, 1);
insert into produto(idProduto, nomeProduto, ultimoPrecoPago, saldoAtual, idTipoProduto, idUnidadeMedida) values(2, 'whisky', 500.00, 1000.00, 2, 2);
insert into produto(idProduto, nomeProduto, ultimoPrecoPago, saldoAtual, idTipoProduto, idUnidadeMedida) values(3, 'computador i5', 2500.00, 5000.00, 3, 3);
insert into produto(idProduto, nomeProduto, ultimoPrecoPago, saldoAtual, idTipoProduto, idUnidadeMedida) values(4, 'Whey', 50.00, 150.00, 4, 4);

select * from produto;

/* insert into tipo movimento */

insert into tipomovimento(idTipoMovimento, descricao) values (1, 'venda');

select * from tipomovimento;

/* insert into movimento estoque */

insert into movimentoestoque(idMovimento, entradaSaida, documento, data, quantidade, valorMovimento, idProduto, idTipoMovimento, idFuncionario) values (1,'saida', 'nota fiscal', '04/06/2023', 3, 60, 1, 1, 2);
insert into movimentoestoque(idMovimento, entradaSaida, documento, data, quantidade, valorMovimento, idProduto, idTipoMovimento, idFuncionario) values (2,'saida', 'nota fiscal', '04/06/2023', 4, 4000, 2, 1, 3);

select * from movimentoestoque;

create or replace function atualizar_saldo_atual()
returns trigger as $$
begin
	update Produto
	set saldoAtual = saldoAtual + new.quantidade
	where idProduto = new.idProduto;
	return new;
end
$$ language plpgsql;

create trigger atualizar_saldo_trigger
after insert or update on MovimentoEstoque for each row
execute procedure atualizar_saldo_atual();