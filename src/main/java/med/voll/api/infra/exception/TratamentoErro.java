package med.voll.api.infra.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class TratamentoErro {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> tratarError400(ConstraintViolationException ex) {
        
        var errors = ex.getConstraintViolations().stream()
            .map(violation -> new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()))
            .toArray();
        return ResponseEntity.badRequest().body(errors);
    }
  
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> tratarErrorSql(Exception ex) {

        String mensagem = ex.getMessage().contains("Duplicate entry") ? "Registro duplicado" : "Erro ao inserir registro";

        return ResponseEntity.badRequest().body(new ValidationError("sql", mensagem));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> tratarErroRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ValidationError("runtime error", ex.getMessage()));
    }
    
    private record ValidationError(String campo, String mensagem) {}


}
