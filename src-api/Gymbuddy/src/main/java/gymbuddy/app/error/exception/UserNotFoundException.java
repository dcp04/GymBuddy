package gymbuddy.app.error.exception;

/**
 * Excepción lanzada cuando no se encuentra un usuario.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Construye una nueva UserNotFoundException con el mensaje de error especificado.
     *
     * @param message El mensaje de error.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
