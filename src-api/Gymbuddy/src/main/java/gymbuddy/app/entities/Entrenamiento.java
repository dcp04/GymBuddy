package gymbuddy.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "entrenamiento")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar en blanco")
    @Size(max = 50, message = "El nombre solo puede tener 50 caracteres")
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ejercicio_entrenamiento", joinColumns = @JoinColumn(name = "entrenamiento_id"), inverseJoinColumns = @JoinColumn(name = "ejercicio_id"))
    @JsonBackReference
    private List<Ejercicio> ejercicios = new ArrayList<>();

    @NotBlank(message = "El campo no puede estar en blanco")
    private String imagenUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_entrenamiento", joinColumns = @JoinColumn(name = "entrenamiento_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuariosApuntados = new ArrayList<>();

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

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<Usuario> getUsuariosApuntados() {
        return usuariosApuntados;
    }

    public void setUsuariosApuntados(List<Usuario> usuariosApuntados) {
        this.usuariosApuntados = usuariosApuntados;
    }
}
