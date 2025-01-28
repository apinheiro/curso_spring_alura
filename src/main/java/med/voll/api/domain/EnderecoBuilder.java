package med.voll.api.domain;

public class EnderecoBuilder {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoBuilder withLogradouro(String logradouro) {
        this.logradouro = logradouro == null ? this.logradouro : logradouro;
        return this;
    }

    public EnderecoBuilder withNumero(String numero) {
        this.numero = numero == null ? this.numero : numero;
        return this;
    }

    public EnderecoBuilder withComplemento(String complemento) {
        this.complemento = complemento == null ? this.complemento : complemento;
        return this;
    }

    public EnderecoBuilder withBairro(String bairro) {
        this.bairro = bairro == null ? this.bairro : bairro;
        return this;
    }

    public EnderecoBuilder withCidade(String cidade) {
        this.cidade = cidade == null ? this.cidade : cidade;
        return this;
    }

    public EnderecoBuilder withUf(String uf) {
        this.uf = uf == null ? this.uf : uf;
        return this;
    }

    public EnderecoBuilder withCep(String cep) {
        this.cep = cep == null ? this.cep : cep;
        return this;
    }

    public Endereco build() {
        return new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
    }

    public EnderecoBuilder from(Endereco endereco) {
        return new EnderecoBuilder()
            .withLogradouro(endereco.getLogradouro())
            .withNumero(endereco.getNumero())
            .withComplemento(endereco.getComplemento())
            .withBairro(endereco.getBairro())
            .withCidade(endereco.getCidade())
            .withUf(endereco.getUf())
            .withCep(endereco.getCep());
    }

}
