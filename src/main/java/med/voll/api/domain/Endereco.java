package med.voll.api.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;   
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco{

    @NotBlank
    private String logradouro;

    private String numero;
    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotBlank
    private String cep;

    public void atualizar(Endereco endereco) {
        this.logradouro = endereco.getLogradouro() == null ? this.logradouro : endereco.getLogradouro();
        this.numero = endereco.getNumero() == null ? this.numero : endereco.getNumero();
        this.complemento = endereco.getComplemento() == null ? this.complemento : endereco.getComplemento();
        this.bairro = endereco.getBairro() == null ? this.bairro : endereco.getBairro();
        this.cidade = endereco.getCidade() == null ? this.cidade : endereco.getCidade();
        this.uf = endereco.getUf() == null ? this.uf : endereco.getUf();
        this.cep = endereco.getCep() == null ? this.cep : endereco.getCep();
    }

}
