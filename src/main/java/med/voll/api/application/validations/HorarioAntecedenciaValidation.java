package med.voll.api.application.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import med.voll.api.application.domain.DadosConsultaDTO;

@Component
public class HorarioAntecedenciaValidation implements IConsultaValidation{

    @Value("${app.consulta.antecedencia}")
    private int minutosAntecedencia;

    public void validar(DadosConsultaDTO consultaDTO) {
        LocalDateTime dataConsulta = consultaDTO.data();
        LocalDateTime dataAtual = LocalDateTime.now();

        if (Duration.between(dataAtual, dataConsulta).toMinutes() < minutosAntecedencia){
            throw new RuntimeException("Consulta deve ser agendada com antecedência de " + minutosAntecedencia + " minutos");
        }
    }
}
