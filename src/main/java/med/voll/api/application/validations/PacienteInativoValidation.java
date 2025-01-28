package med.voll.api.application.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.domain.DadosConsultaDTO;
import med.voll.api.domain.repository.PacienteRepository;

@Component
public class PacienteInativoValidation implements IConsultaValidation {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosConsultaDTO consultaDTO){
        var paciente = pacienteRepository.findAtivoById(consultaDTO.idPaciente()); 
        if (paciente == null) {
            throw new RuntimeException("Paciente não encontrado.");
        }
    }
}
