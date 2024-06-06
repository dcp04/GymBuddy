package gymbuddy.app.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

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
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellidos;

    /**
     * Correo electrónico del usuario.
     */
    @Column(unique = true)
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;

    private int estatura;

    private double peso;

    /**
     * Roles del usuario.
     */
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Rol.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_rol")
    @Column(name = "RolesUsuario")
    private Set<Rol> rol = new HashSet<>();

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        rol.size();
        return rol.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Set<Rol> getRoles() {
        return rol;
    }

    public void setRoles(Set<Rol> roles) {
        this.rol = roles;
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

    public String getApellido() {
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
