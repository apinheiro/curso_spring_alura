package med.voll.api.application.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Especialidades;

public record MedicoDTO(
    Long medicoId,
    
    String nome, 
    
    @Email
    String email, 

    @Pattern(regexp = "\\d{4,6}")
    String crm, 
    
    Especialidades especialidade, 
    
    @Valid
    EnderecoDTO endereco,
    
    String telefone) {

}
