package med.voll.api.application.domain;

import jakarta.validation.constraints.Pattern;

public record EnderecoDTO ( 
    
    String logradouro, 
    
    String numero, 
    
    String complemento, 
    
    String bairro, 
    
    String cidade, 

    String uf, 

    @Pattern(regexp = "\\d{8}")
    String cep){
}
