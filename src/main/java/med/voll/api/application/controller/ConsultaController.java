package med.voll.api.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.application.domain.DadosConsultaDTO;
import med.voll.api.application.service.AgendaDeConsultasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosConsultaDTO> agendar(@RequestBody @Valid DadosConsultaDTO consultaDto) {
        var consulta = agenda.agendar(consultaDto);
        return ResponseEntity.ok(consulta);
    }
    
}
