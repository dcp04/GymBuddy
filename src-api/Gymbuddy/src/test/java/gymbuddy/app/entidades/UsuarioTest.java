package gymbuddy.app.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import gymbuddy.app.entities.Rol;
import gymbuddy.app.entities.Usuario;

/**
 * Prueba unitaria para la clase Usuario, que verifica los métodos getter y setter, y el método getAuthorities.
 */
public class UsuarioTest {

    /**
     * Prueba para verificar los métodos getter y setter de la clase Usuario.
     */
    @Test
    public void testGetterAndSetter() {
        // Datos de ejemplo
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.ROL_USER);

        // Creando una instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(firstName);
        usuario.setApellido(lastName);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRoles(roles);

        // Verificando los valores
        assertEquals(id, usuario.getId());
        assertEquals(firstName, usuario.getNombre());
        assertEquals(lastName, usuario.getApellido());
        assertEquals(email, usuario.getEmail());
        assertEquals(password, usuario.getPassword());
        assertEquals(roles, usuario.getRoles());
    }

    /**
     * Prueba para verificar el método getAuthorities de la clase Usuario.
     */
    @Test
    public void testGetAuthorities() {
        // Datos de ejemplo
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.ROL_USER);
        roles.add(Rol.ROL_ADMIN);

        // Creando una instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setRoles(roles);

        // Obteniendo las autoridades
        var authorities = usuario.getAuthorities();

        // Verificando las autoridades
        assertEquals(2, authorities.size());
        assertEquals(true, authorities.contains(new SimpleGrantedAuthority("ROL_USER")));
        assertEquals(true, authorities.contains(new SimpleGrantedAuthority("ROL_ADMIN")));
    }
}
