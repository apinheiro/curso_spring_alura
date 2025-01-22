package med.voll.api.infra.factory;

import med.voll.api.application.domain.EnderecoDTO;
import med.voll.api.application.domain.MedicoDTO;
import med.voll.api.application.domain.RegistroMedicoDTO;
import med.voll.api.domain.Endereco;
import med.voll.api.domain.EnderecoBuilder;
import med.voll.api.domain.Medico;
import med.voll.api.domain.MedicoBuilder;

public class MedicoFactory {

    public static MedicoDTO convertToDto(Medico medico){
        return new MedicoDTO(medico.getId(),
                             medico.getNome(), 
                             medico.getEmail(), 
                             medico.getCrm(), 
                             medico.getEspecialidade(), 
                             convertEnderecoDTO(medico.getEndereco()),
                             medico.getTelefone());
    }

    public static RegistroMedicoDTO convertToRegistroMedico(Medico medico){
        return new RegistroMedicoDTO(medico.getNome(), 
                                     medico.getEmail(), 
                                     medico.getCrm(), 
                                     medico.getEspecialidade(), 
                                     medico.getId());
    }

    public static Medico convertFromDto(MedicoDTO medicoDTO) {
        if (medicoDTO == null) return null;

        return new MedicoBuilder()
            .withEndereco(setEndereco(medicoDTO.endereco()))
            .withNome(medicoDTO.nome())
            .withEmail(medicoDTO.email())
            .withCrm(medicoDTO.crm())
            .withEspecialidade(medicoDTO.especialidade())
            .withTelefone(medicoDTO.telefone())
            .build();        
    }

    private static Endereco setEndereco(EnderecoDTO enderecoDTO){
        if (enderecoDTO == null) return null;

        return new EnderecoBuilder()
            .withCep(enderecoDTO.cep())
            .withCidade(enderecoDTO.cidade())
            .withComplemento(enderecoDTO.complemento())
            .withUf(enderecoDTO.uf())
            .withLogradouro(enderecoDTO.logradouro())
            .withNumero(enderecoDTO.numero())
            .withBairro(enderecoDTO.bairro())
            .build();
    }

    private static EnderecoDTO convertEnderecoDTO(Endereco endereco){
        if (endereco == null) return null;

        return new EnderecoDTO(endereco.getCep(), 
                               endereco.getLogradouro(), 
                               endereco.getNumero(), 
                               endereco.getComplemento(), 
                               endereco.getBairro(), 
                               endereco.getCidade(), 
                               endereco.getUf());
    }
}
