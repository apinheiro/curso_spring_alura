package med.voll.api.application.validations;

import med.voll.api.application.domain.DadosConsultaDTO;

public interface IConsultaValidation {

    void validar(DadosConsultaDTO consultaDTO); 
}
