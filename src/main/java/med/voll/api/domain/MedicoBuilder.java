package med.voll.api.domain;

public class MedicoBuilder {
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidades especialidade;
    private Endereco endereco;
    private String telefone;

    public MedicoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MedicoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public MedicoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public MedicoBuilder setCrm(String crm) {
        this.crm = crm;
        return this;
    }

    public MedicoBuilder setEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public MedicoBuilder setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public MedicoBuilder setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Medico build() {
        return new Medico(id, nome, email, crm, especialidade, endereco, telefone);
    }
}