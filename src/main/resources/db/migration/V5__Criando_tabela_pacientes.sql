create table pacientes (
    id bigint primary key auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    cpf varchar(14) not null,
    logradouro varchar(255) not null,
    numero varchar(10) not null,
    complemento varchar(255),
    bairro varchar(255) not null,
    cidade varchar(255) not null,
    uf varchar(2) not null,
    cep varchar(9) not null,
    ativo boolean not null default 1,
    data_exclusao datetime,
    unique (cpf)) engine=InnoDB;