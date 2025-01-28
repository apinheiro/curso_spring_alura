package med.voll.api.domain;

import java.sql.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String telefone;

    @NotNull
    private boolean ativo;

    private Date dataExclusao;

    @Embedded
    private Endereco endereco;

    public Paciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
        this(id, nome, email, cpf, telefone, endereco, true, null);
    }

    public Paciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco, Boolean ativo, Date dataExclusao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = ativo;
        this.dataExclusao = dataExclusao;
    }

    public void atualizar(Paciente paciente) {
        this.nome = paciente.getNome() == null ? this.nome : paciente.getNome();
        this.telefone = paciente.getTelefone() == null ? this.telefone : paciente.getTelefone();
        if(paciente.getEndereco() != null){
            this.endereco.atualizar(paciente.getEndereco());
        }
    }

    public void deletar() {
        this.ativo = false;
        this.dataExclusao = new Date(System.currentTimeMillis());
    }

    public void ativar() {
        this.ativo = true;
        this.dataExclusao = null;
    }
}
