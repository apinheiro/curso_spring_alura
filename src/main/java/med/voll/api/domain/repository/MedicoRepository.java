package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {    
    
}
