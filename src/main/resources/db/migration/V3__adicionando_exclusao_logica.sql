alter table medicos add column ativo boolean not null default true;
alter table medicos add column data_exclusao timestamp;