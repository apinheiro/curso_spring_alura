package med.voll.api.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.Especialidades;
import med.voll.api.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivo(Pageable pagina, boolean ativo);

    @Query("""
           SELECT m FROM Medico m 
           WHERE m.especialidade = :especialidade 
             AND m.ativo = true
             AND NOT EXISTS (
                  SELECT c.medico 
                  FROM Consulta c 
                  WHERE c.medico = m and c.data = :data)
            ORDER BY RAND() LIMIT 1""")
    Medico encontrarMedicoLivre(Especialidades especialidade, LocalDateTime data);    
    
}
