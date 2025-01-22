package med.voll.api.domain;

public class EnderecoBuilder {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoBuilder setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public EnderecoBuilder setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilder setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoBuilder setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoBuilder setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilder setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public EnderecoBuilder setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Endereco build() {
        return new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
    }

}
