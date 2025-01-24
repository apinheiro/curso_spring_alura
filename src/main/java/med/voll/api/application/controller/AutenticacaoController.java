
package med.voll.api.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.application.domain.AutenticacaoDTO;
import med.voll.api.application.domain.TokenDTO;
import med.voll.api.domain.Usuario;
import med.voll.api.infra.security.TokenService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> efetuarLogin(@RequestBody @Valid AutenticacaoDTO auth) {

        var authToken = new UsernamePasswordAuthenticationToken(auth.login(), auth.senha());

        var autentication = manager.authenticate(authToken);

        String token = tokenService.gerarToken((Usuario) autentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}
