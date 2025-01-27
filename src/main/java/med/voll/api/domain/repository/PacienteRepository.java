package med.voll.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Page<Paciente> findAllByAtivo(Pageable pagina, boolean ativo);

}
