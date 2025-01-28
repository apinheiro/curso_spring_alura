package med.voll.api.application.domain;

import med.voll.api.domain.Especialidades;

public record RegistroMedicoDTO(String nome,String email,String crm,Especialidades especialidade,Long medicoId) {

}
