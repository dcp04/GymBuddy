package gymbuddy.app.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Representa un usuario en el sistema.
 */
@Entity
public class Usuario implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario.
     */
    @NotBlank(message = "El campo no puede estar en blanco")
    @Size(max = 50, message = "El nombre solo puede tener 50 caracteres")
    private String nombre;

    /**
     * Apellido del usuario.
     */
    @NotBlank(message = "El campo no puede estar en blanco")
    @Size(max = 50, message = "Los apellidos solo pueden tener 50 caracteres")
    private String apellidos;

    /**
     * Correo electrónico del usuario.
     */
    @Column(unique = true)
    @NotBlank(message = "El campo no puede estar en blanco")
    @Email(message = "El email debe tener formato de email")
    private String email;

    /**
     * Contraseña del usuario.
     */
    @NotBlank(message = "El campo no puede estar en blanco")
    private String password;

    /**
     * Estatura del usuario.
     */
    @NotBlank(message = "El campo no puede estar en blanco")
    private int estatura;

    /**
     * Peso del usuario.
     */
    @NotBlank(message = "El campo no puede estar en blanco")
    private double peso;

    /**
     * Roles del usuario.
     */
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Rol.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_rol")
    @Column(name = "RolesUsuario")
    private Set<Rol> roles = new HashSet<>();

    @Transactional
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        roles.size();
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getEstatura() {
        return estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
