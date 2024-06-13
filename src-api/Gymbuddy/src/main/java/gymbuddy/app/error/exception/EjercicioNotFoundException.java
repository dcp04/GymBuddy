package gymbuddy.app.error.exception;

/**
 * Excepción lanzada cuando no se encuentra un manga.
 */
public class EjercicioNotFoundException extends RuntimeException {
    /**
     * Construye una nueva EjercicioNotFoundException con el mensaje de error especificado.
     *
     * @param message El mensaje de error.
     */
    public EjercicioNotFoundException(String message) {
        super(message);
    }
}
