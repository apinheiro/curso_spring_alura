create table consultas (
    id bigint primary key auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    constraint fk_medico foreign key (medico_id) references medicos(id),
    constraint fk_paciente foreign key (paciente_id) references pacientes(id)
) engine=InnoDB;