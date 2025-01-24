package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.Usuario;

@Service
public class TokenService {

    @Value("${app.security.jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getNome())
                .withExpiresAt(dataExpiracao())
                .sign(algoritimo);
        } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar token JWT",ex);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
