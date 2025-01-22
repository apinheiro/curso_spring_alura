package med.voll.api.infra.factory;

import med.voll.api.application.domain.EnderecoDTO;
import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.application.domain.RegistroMedicoDTO;
import med.voll.api.domain.Endereco;
import med.voll.api.domain.EnderecoBuilder;
import med.voll.api.domain.Medico;
import med.voll.api.domain.MedicoBuilder;

public class MedicoDTOFactory {
    public static MedicoDTO convertToDto(Medico medico){
        return new MedicoDTO(medico.getNome(), 
                             medico.getEmail(), 
                             medico.getCrm(), 
                             medico.getEspecialidade(), null, null);
    }

    public static Medico convertFromDto(MedicoDTO medicoDTO){
        return new MedicoBuilder()
                .setNome(medicoDTO.nome())
                .setEmail(medicoDTO.email())
                .setCrm(medicoDTO.crm())
                .setEspecialidade(medicoDTO.especialidade())
                .setEndereco(setEndereco(medicoDTO.endereco()))
                .setTelefone(medicoDTO.telefone())
                .build();
    }

    public static RegistroMedicoDTO convertToRegistroMedico(Medico medico){
        return new RegistroMedicoDTO(medico.getNome(), 
                                     medico.getEmail(), 
                                     medico.getCrm(), 
                                     medico.getEspecialidade(), 
                                     medico.getId());
    }
       
    private static Endereco setEndereco(EnderecoDTO enderecoDTO){
        return new EnderecoBuilder()
                .setCep(enderecoDTO.cep())
                .setCidade(enderecoDTO.cidade())
                .setComplemento(enderecoDTO.complemento())
                .setUf(enderecoDTO.uf())
                .setLogradouro(enderecoDTO.logradouro())
                .setNumero(enderecoDTO.numero())
                .build();
    }
}
