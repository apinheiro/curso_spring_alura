package med.voll.api.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.application.domain.RegistroMedicoDTO;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.infra.factory.MedicoFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping
    @Transactional
    public void adicionar(@RequestBody @Valid MedicoDTO dadosCadastroMedico) {
        medicoRepository.save(MedicoFactory.convertFromDto(dadosCadastroMedico));
    }
    
    @GetMapping
    /**
     * Lista todos os médicos cadastrados no sistema
     * O retono é uma lista paginada de MedicoDTO, sem a informação do endereço e telefone.
     * 
     * @param pagina
     * @return Page<MedicoDTO>
     */
    public Page<RegistroMedicoDTO> listar(@PageableDefault(sort={"nome"}) Pageable pagina) {
        return medicoRepository.findAll(pagina)
                  .map(MedicoFactory::convertToRegistroMedico);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable String id, @RequestBody @Valid MedicoDTO dadosCadastroMedico) {
        var medico = medicoRepository.getReferenceById(Long.parseLong(id));
        medico.atualizar(MedicoFactory.convertFromDto(dadosCadastroMedico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable String id) {
        medicoRepository.deleteById(Long.parseLong(id));
    }
}
