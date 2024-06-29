package albert.dev.ForoHub.infra.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandling {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<EntityNotFoundException> error404(){
            return ResponseEntity.notFound().build();
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<List<ValidationExceptionDTO>> error400(MethodArgumentNotValidException e){
            var error = e.getFieldErrors().stream().map(ValidationExceptionDTO::new).toList();
            return ResponseEntity.badRequest().body(error);
        }
        public record ValidationExceptionDTO(String error, String field){
            public ValidationExceptionDTO(FieldError error){
                this(error.getField(),error.getDefaultMessage());
            }
        }
}
