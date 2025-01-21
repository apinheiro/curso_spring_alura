package med.voll.api.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.domain.medicos.Medicos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @PostMapping
    public void adicionar(@RequestBody Medicos dadosCadastroMedico) {
        System.out.println("Adicionando médico: " + dadosCadastroMedico);

    }
    
}
