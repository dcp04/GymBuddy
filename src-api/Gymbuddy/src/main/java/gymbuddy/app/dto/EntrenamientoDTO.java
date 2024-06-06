package gymbuddy.app.dto;

import java.util.List;

import gymbuddy.app.entities.Dificultad;
import gymbuddy.app.entities.Ejercicio;
import lombok.Data;


@Data
public class EntrenamientoDTO {
    private Long id;
    private String nombre;
    private Dificultad dificultad;
    private List<Ejercicio> ejercicios;
    private String imagenUrl;
}
