package gymbuddy.app.dto.response.error;

import java.util.Date;

/**
 * Clase que representa una respuesta detallada de error.
 */
public class DetailsResponse {
    private Date timestamp; // Marca de tiempo del error
    private String message; // Mensaje de error
    private String details; // Detalles adicionales del error

    /**
     * Constructor de la clase.
     * @param timestamp La marca de tiempo del error.
     * @param message El mensaje de error.
     * @param details Los detalles adicionales del error.
     */
    public DetailsResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * Método getter para obtener la marca de tiempo del error.
     * @return Date La marca de tiempo del error.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Método setter para establecer la marca de tiempo del error.
     * @param timestamp La marca de tiempo a establecer.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Método getter para obtener el mensaje de error.
     * @return String El mensaje de error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Método setter para establecer el mensaje de error.
     * @param message El mensaje de error a establecer.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Método getter para obtener los detalles adicionales del error.
     * @return String Los detalles adicionales del error.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Método setter para establecer los detalles adicionales del error.
     * @param details Los detalles adicionales del error a establecer.
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
