package med.voll.api.application.validations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.application.domain.DadosConsultaDTO;

@Component
public class HorarioFuncionamentoClinicaValidation implements IConsultaValidation{

    public void validar(DadosConsultaDTO consultaDTO) {
        LocalDateTime dataConsulta = consultaDTO.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if (domingo || antesAbertura || depoisFechamento) {
            throw new RuntimeException("Horário de funcionamento da clínica: Segunda a Sábado das 07:00 às 19:00");
        }
    }
}
