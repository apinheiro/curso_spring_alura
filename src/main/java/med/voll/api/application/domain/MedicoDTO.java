package med.voll.api.application.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Especialidades;

public record MedicoDTO(
    @NotBlank 
    String nome, 
    
    @NotBlank @Email
    String email, 

    @NotBlank @Pattern(regexp = "\\d{4,6}")
    String crm, 
    
    @NotNull
    Especialidades especialidade, 
    
    @NotNull @Valid
    EnderecoDTO endereco,
    
    @NotBlank
    String telefone) {

}
