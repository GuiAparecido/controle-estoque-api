create table estoque(
    id bigserial not null,
    nome varchar (255) not null,
    data_criacao timestamp not null default current_timestamp,
    data_atualizacao timestamp default current_timestamp,
    preco decimal (10,2) not null,
    quantidade bigint not null,

    constraint pk_estoque primary key (id)
);