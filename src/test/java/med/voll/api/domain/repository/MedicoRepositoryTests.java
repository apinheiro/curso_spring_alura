package med.voll.api.domain.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.Consulta;
import med.voll.api.domain.Endereco;
import med.voll.api.domain.Especialidades;
import med.voll.api.domain.Medico;
import med.voll.api.domain.MedicoBuilder;
import med.voll.api.domain.Paciente;
import med.voll.api.domain.PacienteBuilder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTests {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    
    @Test
    @DisplayName("Método que retorna null quando não tiver médico disponível na data.")
    void encontrarMedicoLivreRetornaSemMedicoDisponivel(){

        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        montarBaseDeTeste(Especialidades.CARDIOLOGIA, proximaSegundaAs10);

        var medicoLivre = medicoRepository.encontrarMedicoLivre(Especialidades.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    // void escolherMedicoAleatorioLivreNaData(){

    // }

    private void montarBaseDeTeste(Especialidades especialidades, LocalDateTime data){
        var medico = cadastrarMedico(especialidades,"12345678");
        var paciente = cadastrarPaciente();
        cadastrarConsulta(medico, paciente, data);
    
    }

    private Endereco getEndereco(){
        return new Endereco("Rua teste", "123", "", "Bairro teste", "Cidade teste", "SP", "12345678");
    }

    private Medico cadastrarMedico(Especialidades especialidade, String crm){
        var medico = new MedicoBuilder().withCrm(crm)
                                        .withNome("Dr. Fulano")
                                        .withEmail("teste@teste.com")
                                        .withTelefone("999999999")
                                        .withEspecialidade(especialidade)
                                        .withEndereco(getEndereco())
                                        .build();

        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(){
        var paciente = new PacienteBuilder().withNome("Fulano")
                                            .withEmail("teste@teste.com")
                                            .withTelefone("12345678")
                                            .withCpf("12345678901")
                                            .withEndereco(getEndereco())
                                            .build();

        entityManager.persist(paciente);
        return paciente;
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        var consulta = new Consulta(null, medico, paciente, data);
        entityManager.persist(consulta);
    }
}