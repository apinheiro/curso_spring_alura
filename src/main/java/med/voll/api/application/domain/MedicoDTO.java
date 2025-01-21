package med.voll.api.application.domain;

import med.voll.api.domain.Especialidades;

public record MedicoDTO(String nome, String email, String crm, Especialidades especialidade, EnderecoDTO endereco) {

}
