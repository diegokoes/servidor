package es.daw.productosrestcontroller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String message;
    // Poner un Map con campo-valor
    private Map<String,String> details;
    private LocalDateTime timeStamp;




}
