package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByNome(String username);
}
