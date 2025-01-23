package med.voll.api.infra.exception;

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
  
    private record ValidationError(String campo, String mensagem) {}
}
