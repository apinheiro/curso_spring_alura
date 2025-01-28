package med.voll.api.application.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import med.voll.api.application.domain.DadosConsultaDTO;
import med.voll.api.application.validations.IConsultaValidation;
import med.voll.api.domain.Consulta;
import med.voll.api.domain.Medico;
import med.voll.api.domain.Paciente;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.factory.ConsultaFactory;

@Service
public class AgendaDeConsultasService {
    
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<IConsultaValidation> validadores;

    public DadosConsultaDTO agendar(DadosConsultaDTO consultaDTO)
    {
        Paciente paciente = null;
        Medico medico =  null;

        try{
            medico = escolhaMedico(consultaDTO);
            paciente = pacienteRepository.findById(consultaDTO.idPaciente()).get();

            if (medico == null){
                throw new RuntimeException("Não existe médico disponível para esta data.");
            }
        }catch(NoSuchElementException e){
            throw new RuntimeException("Paciente não encontrado.", e);
        }catch(RuntimeException e){
            throw e;
        }

        validadores.forEach(v -> v.validar(consultaDTO));
        Consulta consulta = new Consulta(null, medico, paciente, consultaDTO.data());
        consultaRepository.save(consulta);
        return ConsultaFactory.criarConsultaDTO(consulta);
    }

    private Medico escolhaMedico(DadosConsultaDTO consulta) throws NoSuchElementException {
        Medico medico = null;
        try{
            medico = medicoRepository.findById(consulta.idMedico()).orElseThrow(Exception::new);
        }catch(Exception e){
            if (consulta.especialidade() != null) {
                medico = medicoRepository.encontrarMedicoLivre(consulta.especialidade(), consulta.data());
            } else {
                throw new RuntimeException("Especialidade não informada.");
            }
        }

        return medico;
    }

}
