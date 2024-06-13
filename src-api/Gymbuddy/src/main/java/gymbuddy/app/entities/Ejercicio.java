package gymbuddy.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "ejercicio")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar en blanco")
    @Size(max = 50, message = "El nombre solo puede tener 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo no puede estar en blanco")
    @Size(max = 500, message = "La descripción solo puede tener 500 caracteres")
    private String descripcion;

    @ManyToMany(mappedBy = "ejercicios", fetch = FetchType.EAGER)
    private List<Entrenamiento> entrenamiento = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    @NotBlank(message = "El campo no puede estar en blanco")
    private String imagenUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Entrenamiento> getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(List<Entrenamiento> entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }
}
