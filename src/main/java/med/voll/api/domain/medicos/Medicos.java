package med.voll.api.domain.medicos;

import med.voll.api.domain.DadosEndereco;
import med.voll.api.domain.Especialidades;

public record Medicos(String nome, String email, String crm, Especialidades especialidade, DadosEndereco endereco) {

}
