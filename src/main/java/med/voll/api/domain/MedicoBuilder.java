package med.voll.api.domain;

public class MedicoBuilder {
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidades especialidade;
    private Endereco endereco;
    private String telefone;

    public MedicoBuilder withId(Long id) {
        this.id = id == null ? this.id : id;
        return this;
    }

    public MedicoBuilder withNome(String nome) {
        this.nome = nome == null ? this.nome : nome;
        return this;
    }

    public MedicoBuilder withEmail(String email) {
        this.email = email == null ? this.email : email;
        return this;
    }

    public MedicoBuilder withCrm(String crm) {
        this.crm = crm == null ? this.crm : crm;
        return this;
    }

    public MedicoBuilder withEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade == null ? this.especialidade : especialidade;
        return this;
    }

    public MedicoBuilder withEndereco(Endereco endereco) {
        this.endereco = endereco == null ? this.endereco : endereco;
        return this;
    }

    public MedicoBuilder withTelefone(String telefone) {
        this.telefone = telefone == null ? this.telefone : telefone;
        return this;
    }

    public MedicoBuilder from(Medico medico) {
        return new MedicoBuilder()
            .withId(medico.getId())
            .withNome(medico.getNome())
            .withEmail(medico.getEmail())
            .withCrm(medico.getCrm())
            .withEspecialidade(medico.getEspecialidade())
            .withEndereco(medico.getEndereco())
            .withTelefone(medico.getTelefone());
    }

    public Medico build() {
        return new Medico(id, nome, email, crm, especialidade, endereco, telefone);
    }
}