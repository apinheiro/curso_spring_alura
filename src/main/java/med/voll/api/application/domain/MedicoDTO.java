package med.voll.api.application.domain;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Especialidades;

public record MedicoDTO(
    String nome, 
    
    @Email
    String email, 

    @Pattern(regexp = "\\d{4,6}")
    String crm, 
    
    @UpperCase
    Especialidades especialidade, 
    
    @Valid
    EnderecoDTO endereco,
    
    String telefone) {

}
