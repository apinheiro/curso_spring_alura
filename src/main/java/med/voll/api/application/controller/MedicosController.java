package med.voll.api.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.domain.repository.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import med.voll.api.domain.Medicos;


@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository medicoRepository;
    
    @PostMapping
    public void adicionar(@RequestBody MedicoDTO dadosCadastroMedico) {
        medicoRepository.save(new Medicos(dadosCadastroMedico));
    }
    
}
