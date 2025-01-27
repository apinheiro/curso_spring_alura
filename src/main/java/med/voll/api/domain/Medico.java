package med.voll.api.domain;

import java.sql.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String crm;
    
    @Enumerated(EnumType.STRING)
    private Especialidades especialidade;
    
    @Embedded
    private Endereco endereco ;

    @NotBlank
    private String telefone;

    private boolean ativo;

    private Date dataExclusao;

    public Medico(Long id, String nome, String email, String crm, Especialidades especialidade, Endereco endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ativo = true;
    }


    public void atualizar(Medico medico) {
        this.nome = medico.getNome() == null ? this.nome : medico.getNome();
        this.telefone = medico.getTelefone() == null ? this.telefone : medico.getTelefone();

        if(medico.getEndereco() != null){
            this.endereco.atualizar(medico.getEndereco());
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
