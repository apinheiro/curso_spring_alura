package med.voll.api.infra.factory;

import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.domain.Medicos;

public class MedicoDTOFactory {
    public static MedicoDTO create(Medicos medico){
        return new MedicoDTO(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), null, null);
    }
}
