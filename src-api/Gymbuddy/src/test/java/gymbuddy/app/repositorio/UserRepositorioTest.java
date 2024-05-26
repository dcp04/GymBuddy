package gymbuddy.app.repositorio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import gymbuddy.app.entities.Usuario;
import gymbuddy.app.repository.UserRepository;

/**
 * Pruebas unitarias para el repositorio de usuarios.
 */
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class UserRepositorioTest {

    @MockBean
    private UserRepository userRepository;

    /**
     * Prueba para verificar la búsqueda de un usuario por su dirección de correo electrónico.
     */
    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Usuario user = new Usuario();
        user.setId(1L);
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<Usuario> foundUser = userRepository.findByEmail(email);
        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
    }

    /**
     * Prueba para verificar la búsqueda de un usuario por su ID.
     */
    @Test
    public void testFindById() {
        long userId = 1L;
        Usuario user = new Usuario();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<Usuario> foundUser = userRepository.findById(userId);
        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getId());
    }

    /**
     * Prueba para verificar si un usuario existe por su dirección de correo electrónico.
     */
    @Test
    public void testExistsByEmail() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        boolean exists = userRepository.existsByEmail(email);
        assertTrue(exists);
    }
}
