create table usuarios(
    id bigint primary key auto_increment not null,
    nome varchar(255) not null,
    senha varchar(255) not null,
    unique(nome)
)