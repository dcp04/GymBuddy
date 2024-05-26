package gymbuddy.app.error.exception;

/**
 * Excepción lanzada cuando no se encuentra un manga.
 */
public class MangaNotFoundException extends RuntimeException {
    /**
     * Construye una nueva MangaNotFoundException con el mensaje de error especificado.
     *
     * @param message El mensaje de error.
     */
    public MangaNotFoundException(String message) {
        super(message);
    }
}
