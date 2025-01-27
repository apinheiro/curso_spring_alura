package med.voll.api.application.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Especialidades;

public record DadosConsultaDTO(
    Long id,

    Long idMedico,

    @NotNull
    Long idPaciente,

    @NotNull
    @Future
    LocalDateTime data,

    Especialidades especialidade
) {

}
