package med.voll.api.infra.exception;

public class MedicoNotFoundException extends Exception {
    public MedicoNotFoundException(String message) {
        super(message);
    }

    public MedicoNotFoundException(){
        super("Médico não encontrado");
    }

}
