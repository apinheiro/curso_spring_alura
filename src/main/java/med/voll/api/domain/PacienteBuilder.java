package med.voll.api.domain;

import java.sql.Date;

public class PacienteBuilder {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private boolean ativo;
    private Date dataExclusao;

    public PacienteBuilder withId(Long id) {
        this.id = id == null ? this.id : id;
        return this;
    }

    public PacienteBuilder withNome(String nome) {
        this.nome = nome == null ? this.nome : nome;
        return this;
    }

    public PacienteBuilder withEmail(String email) {
        this.email = email == null ? this.email : email;
        return this;
    }

    public PacienteBuilder withCpf(String cpf) {
        this.cpf = cpf == null ? this.cpf : cpf;
        return this;
    }

    public PacienteBuilder withTelefone(String telefone) {
        this.telefone = telefone == null ? this.telefone : telefone;
        return this;
    }

    public PacienteBuilder withEndereco(Endereco endereco) {
        this.endereco = endereco == null ? this.endereco : endereco;
        return this;
    }

    public PacienteBuilder withAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public PacienteBuilder withDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
        return this;
    }

    public PacienteBuilder from(Paciente paciente) {
        return new PacienteBuilder()
            .withId(paciente.getId())
            .withNome(paciente.getNome())
            .withEmail(paciente.getEmail())
            .withCpf(paciente.getCpf())
            .withTelefone(paciente.getTelefone())
            .withEndereco(paciente.getEndereco())
            .withAtivo(paciente.isAtivo())
            .withDataExclusao(paciente.getDataExclusao());
    }

    public Paciente build() {
        return new Paciente(id, nome, email, cpf, telefone, endereco, ativo, dataExclusao);
    }



}
