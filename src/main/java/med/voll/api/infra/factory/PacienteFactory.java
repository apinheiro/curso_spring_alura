package med.voll.api.infra.factory;

import med.voll.api.application.domain.EnderecoDTO;
import med.voll.api.application.domain.PacienteDTO;
import med.voll.api.application.domain.RegistroPacienteDTO;
import med.voll.api.domain.Endereco;
import med.voll.api.domain.EnderecoBuilder;
import med.voll.api.domain.Paciente;
import med.voll.api.domain.PacienteBuilder;

public class PacienteFactory {

    public static PacienteDTO convertToDto(Paciente paciente){
        return new PacienteDTO(paciente.getId(),
                                paciente.getNome(), 
                                paciente.getEmail(), 
                                paciente.getTelefone(), 
                                paciente.getCpf(), 
                                convertEnderecoDTO(paciente.getEndereco()));
    }

    public static RegistroPacienteDTO convertToRegistroMedico(Paciente paciente){
        return new RegistroPacienteDTO(paciente.getNome(), 
                                    paciente.getEmail(), 
                                    paciente.getCpf(), 
                                    paciente.getId());
    }

    public static Paciente convertFromDto(PacienteDTO pacienteDTO) {
        if (pacienteDTO == null) return null;

        return new PacienteBuilder()
            .withEndereco(setEndereco(pacienteDTO.endereco()))
            .withNome(pacienteDTO.nome())
            .withEmail(pacienteDTO.email())
            .withCpf(pacienteDTO.cpf())
            .withTelefone(pacienteDTO.telefone())
            .withId(pacienteDTO.id())
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