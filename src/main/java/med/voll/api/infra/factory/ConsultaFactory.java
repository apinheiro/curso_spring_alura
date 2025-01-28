package med.voll.api.infra.factory;

import med.voll.api.application.domain.DadosConsultaDTO;
import med.voll.api.domain.Consulta;

public class ConsultaFactory {

    public static DadosConsultaDTO criarConsultaDTO(Consulta consulta) {
        return new DadosConsultaDTO(consulta.getId(),
                                    consulta.getMedico().getId(),
                                    consulta.getPaciente().getId(),
                                    consulta.getData(),
                                    consulta.getMedico().getEspecialidade());
    }
}
