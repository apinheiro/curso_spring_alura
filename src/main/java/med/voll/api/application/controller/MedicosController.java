package med.voll.api.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.application.domain.RegistroMedicoDTO;
import med.voll.api.domain.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.infra.factory.MedicoFactory;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Adiciona um novo médico ao sistema.
     * @param MedicoDTO
     */
    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDTO> adicionar(@RequestBody @Valid MedicoDTO dadosCadastroMedico, UriComponentsBuilder  uriBuilder) {
        Medico medico = MedicoFactory.convertFromDto(dadosCadastroMedico);

        medicoRepository.save(medico);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(MedicoFactory.convertToDto(medico));
    }
    
    /**
     * Lista todos os médicos cadastrados no sistema
     * O retono é uma lista paginada de MedicoDTO, sem a informação do endereço e telefone.
     * 
     * @param pagina
     * @return Page<MedicoDTO>
     */
    @GetMapping
    public ResponseEntity<Page<RegistroMedicoDTO>> listar(@PageableDefault(sort={"nome"}) Pageable pagina) {
        var retorno = medicoRepository.findAllByAtivo(pagina, true)
                  .map(MedicoFactory::convertToRegistroMedico);
        return ResponseEntity.ok(retorno);

    }

    /**
     * Atualiza os dados de um médico cadastrado no sistema.
     * 
     * O cadastro do sistema não atualiza e-mail, CRM e telefone do médico.
     * @param id
     * @param MedicoDTO dadosCadastroMedico
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoDTO> atualizar(@PathVariable String id, @RequestBody @Valid MedicoDTO dadosCadastroMedico) {
        var medico = medicoRepository.getReferenceById(Long.parseLong(id));
        medico.atualizar(MedicoFactory.convertFromDto(dadosCadastroMedico));

        return ResponseEntity.ok(MedicoFactory.convertToDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable String id) {
        var medico = medicoRepository.getReferenceById(Long.parseLong(id));
        medico.deletar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> detalhar(@PathVariable String id) {
        var medico = medicoRepository.getReferenceById(Long.parseLong(id));
        return ResponseEntity.ok(MedicoFactory.convertToDto(medico));
    }
    
}
