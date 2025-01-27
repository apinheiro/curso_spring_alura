package med.voll.api.application.domain;

public record PacienteDTO(Long id, String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {

}
