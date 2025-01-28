package med.voll.api.application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;

import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.factory.PacienteFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> getMethodName(@PageableDefault(sort="nome") Pageable pagina) {
        var pacientes = pacienteRepository.findAllByAtivo(pagina, true)
                        .map(PacienteFactory::convertToDto);
        return ResponseEntity.ok(pacientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDTO> deletar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.deletar();

        return ResponseEntity.ok(PacienteFactory.convertToDto(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getMethodName(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(PacienteFactory.convertToDto(paciente));
    }
}
