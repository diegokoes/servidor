package es.daw.productosrestcontroller.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(
                "Constraint Error ",
                Map.of("prueba",
                        ex.getMessage()
                        ),
                LocalDateTime.now()
        ));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1 + "; " + msg2
                ));

        ErrorDTO errorDTO = new ErrorDTO(
                "Validation Error",
                errors,
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handlerGenericException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(
                "GENERIC EXCEPTION: Internal Server Error",
                Map.of("error", ex.getMessage()),
                LocalDateTime.now()
        );
        return ResponseEntity.internalServerError().body(errorDTO);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDTO> handlerNumberFormatException(NumberFormatException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
                "NumberFormatException: Internal Server Error",
                Map.of("error", ex.getMessage()),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductoNotFoundException(ProductoNotFoundException ex) {

        return ResponseEntity.notFound().build();
    }
}