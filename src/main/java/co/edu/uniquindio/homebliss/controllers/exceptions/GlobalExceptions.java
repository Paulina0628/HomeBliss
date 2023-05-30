package co.edu.uniquindio.homebliss.controllers.exceptions;

import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.services.exceptions.AttributeException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDTO> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new
                MessageDTO(HttpStatus.BAD_REQUEST, true, "Datos de autenticación incorrectos") );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MessageDTO> generalException(ValidationException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new
                MessageDTO(HttpStatus.INTERNAL_SERVER_ERROR, true, e.getMessage()) );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO> generalException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new
                MessageDTO(HttpStatus.INTERNAL_SERVER_ERROR, true, e.getMessage()) );
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDTO> accessDeniedException(AccessDeniedException
                                                                    accessDeniedException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new
                MessageDTO(HttpStatus.FORBIDDEN, true, "No se puede acceder a este recurso"));
    }
    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<MessageDTO> throwAttributeException(AttributeException e) {
        return ResponseEntity.badRequest().body(new MessageDTO(HttpStatus.BAD_REQUEST, true,
                e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> validationException(MethodArgumentNotValidException ex){
        List<String> messages = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            messages.add(e.getField()+": "+e.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new MessageDTO(HttpStatus.BAD_REQUEST, true,
                messages.toString()));
    }
}
