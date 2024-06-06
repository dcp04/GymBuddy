package gymbuddy.app.dto;

import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Usuario;
import lombok.Data;

@Data
public class EjercicioDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Entrenamiento entrenamiento;
    private Usuario creador;
    private String imagenUrl;
}
