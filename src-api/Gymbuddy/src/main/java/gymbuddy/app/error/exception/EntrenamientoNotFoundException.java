package gymbuddy.app.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra una reserva.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntrenamientoNotFoundException extends RuntimeException {

    /**
     * Construye una nueva EntrenamientoNotFoundException con el mensaje de error especificado.
     *
     * @param mensaje El mensaje de error.
     */
    public EntrenamientoNotFoundException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye una nueva EntrenamientoNotFoundException con el mensaje de error y la causa especificados.
     *
     * @param mensaje El mensaje de error.
     * @param causa   La causa de la excepción.
     */
    public EntrenamientoNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
