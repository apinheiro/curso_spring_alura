package med.voll.api.application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.application.domain.PacienteDTO;
import med.voll.api.domain.Paciente;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.factory.PacienteFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTO> adicionar(@RequestBody @Valid PacienteDTO pacienteDto, UriComponentsBuilder  uriBuilder){
        Paciente paciente = PacienteFactory.convertFromDto(pacienteDto);

        pacienteRepository.save(paciente);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(PacienteFactory.convertToDto(paciente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PacienteDTO pacienteDto) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizar(PacienteFactory.convertFromDto(pacienteDto));

        return ResponseEntity.ok(PacienteFactory.convertToDto(paciente));
    }

}
