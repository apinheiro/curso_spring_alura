create table medicos(
id bigint not null auto_increment,
nome varchar(255) not null,
email varchar(255) not null,
crm varchar(50) not null,
especialidade varchar(20) not null,
logradouro varchar(255) not null,
numero varchar(10) not null default 'n/a',
complemento varchar(255) null,
bairro varchar(255) not null,
cidade varchar(150) not null,
uf char(2) not null,
cep varchar(8) null,
primary key (id)) engine=InnoDB