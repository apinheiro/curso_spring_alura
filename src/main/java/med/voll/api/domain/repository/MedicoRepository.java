package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.Medicos;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {    
    
}
