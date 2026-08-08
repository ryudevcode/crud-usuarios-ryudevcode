package crud_usuarios_ryudevcode.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import crud_usuarios_ryudevcode.exception.DuplicateResourceException;

import java.util.HashMap;
import java.util.Map;

// permite manejar excepciones de todos los controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Maneja cuando un recurso no existe
    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ErrorResponse> manejarRecursoNoEncontrado(
            ResourceNotFoundException exception
    ){
        //Creamos la respuesta de error
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        //retornamo el HTTP 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    // Maneja errores producidos por @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresValidacion(
            MethodArgumentNotValidException exception) {

        // Mapa donde guardaremos los errores
        Map<String, String> errores = new HashMap<>();

        // Recorremos todos los errores encontrados
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        // Creamos la respuesta completa
        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("mensaje", "Error de validación");
        respuesta.put("errores", errores);

        // Retornamos HTTP 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }


    //Maneja conflictos cuando un recurso ya existe
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> manejarRecursosDuplicados(
            DuplicateResourceException exception) {
        //Creamos la respuesta de error
       ErrorResponse errorResponse = new ErrorResponse(
               HttpStatus.CREATED.value(),
               exception.getMessage()
       );
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
