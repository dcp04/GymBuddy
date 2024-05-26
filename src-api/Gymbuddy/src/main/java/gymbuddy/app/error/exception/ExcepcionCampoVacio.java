package gymbuddy.app.error.exception;

/**
 * Excepción lanzada cuando se encuentra un campo vacío.
 */
public class ExcepcionCampoVacio extends RuntimeException {
    private String field;

    /**
     * Construye una nueva ExcepcionCampoVacio con el nombre del campo y el mensaje de error especificados.
     *
     * @param field   El nombre del campo.
     * @param message El mensaje de error.
     */
    public ExcepcionCampoVacio(String field, String message) {
        super(message);
        this.field = field;
    }

    /**
     * Obtiene el nombre del campo.
     *
     * @return El nombre del campo.
     */
    public String getField() {
        return field;
    }
}
