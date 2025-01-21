package med.voll.api.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;   
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.domain.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco{

        
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;


    public Endereco(EnderecoDTO endereco) {
        this.bairro = endereco.bairro();
        this.cep = endereco.cep(); 
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.logradouro = endereco.logradouro();
        this.uf = endereco.uf();
        this.numero = endereco.numero();

    }

}
