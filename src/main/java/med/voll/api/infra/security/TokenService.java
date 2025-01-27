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
    private static final String ISSUER = "API Voll.med";

    @Value("${app.security.jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
 
            return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getNome())
                .withExpiresAt(dataExpiracao())
                .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar token JWT",ex);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        return JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(ISSUER)
            .build()
            .verify(tokenJWT)
            .getSubject();
    }
}
