package gymbuddy.app.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import gymbuddy.app.dto.response.error.ErrorDetailsResponse;
import gymbuddy.app.error.exception.ExcepcionCampoVacio;
import gymbuddy.app.error.exception.MangaNotFoundException;

/**
 * Manejador global de excepciones para el manejo centralizado de errores.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetailsResponse> handleIllegalArgumentException(IllegalArgumentException ex,
            WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsResponse> handleIMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Error en los campos", fieldError.getDefaultMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MangaNotFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleLibroNotFoundException(MangaNotFoundException ex,
            WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,
            WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Ruta no encontrada",
                ex.getRequestURL());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExcepcionCampoVacio.class)
    public ResponseEntity<ErrorDetailsResponse> handleEmptyFieldException(ExcepcionCampoVacio ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Campos vacíos",
                "El título, autor o ISBN no pueden estar vacíos");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Error interno del servidor",
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetailsResponse> handleAccessDeniedException(AccessDeniedException ex,
            WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Acceso denegado",
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

}
