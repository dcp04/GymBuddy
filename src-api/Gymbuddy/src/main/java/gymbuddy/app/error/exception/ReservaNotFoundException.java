package gymbuddy.app.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra una reserva.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNotFoundException extends RuntimeException {

    /**
     * Construye una nueva ReservaNotFoundException con el mensaje de error especificado.
     *
     * @param mensaje El mensaje de error.
     */
    public ReservaNotFoundException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye una nueva ReservaNotFoundException con el mensaje de error y la causa especificados.
     *
     * @param mensaje El mensaje de error.
     * @param causa   La causa de la excepción.
     */
    public ReservaNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
