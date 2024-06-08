package gymbuddy.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gymbuddy.app.entities.Rol;
import gymbuddy.app.entities.Usuario;
import gymbuddy.app.repository.UserRepository;

/**
 * Esta clase se encarga de inicializar datos de demostración para la
 * aplicación.
 */
@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * 
     * @param args Argumentos de línea de comandos.
     * @throws Exception Si ocurre algún error durante la inicialización de datos.
     */
    @Override
    public void run(String... args) throws Exception {

        try {

            // Usuario 3 - Rol ENTRENADOR
            Usuario usuario3 = new Usuario();
            usuario3.setNombre("Javier");
            usuario3.setApellidos("Pintado");
            usuario3.setEmail("javier.pintado@gmail.com");
            usuario3.setPassword(passwordEncoder.encode("javier1234"));
            usuario3.setEstatura(177);
            usuario3.setPeso(77.0);
            usuario3.getRoles().add(Rol.ROL_ENTRENADOR);
            usuarioRepository.save(usuario3);

            // Usuario 2 - Rol ADMIN
            Usuario usuario2 = new Usuario();
            usuario2.setNombre("David");
            usuario2.setApellidos("Castro Portero");
            usuario2.setEmail("david.castro@gmail.com");
            usuario2.setPassword(passwordEncoder.encode("david1234"));
            usuario2.setEstatura(175);
            usuario2.setPeso(70.0);
            usuario2.getRoles().add(Rol.ROL_ADMIN);
            usuarioRepository.save(usuario2);

            // Usuario 1 - Rol USER
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Abel");
            usuario1.setApellidos("Garcia Sanchez");
            usuario1.setEmail("abel.garcia@gmail.com");
            usuario1.setPassword(passwordEncoder.encode("abel1234"));
            usuario1.setEstatura(183);
            usuario1.setPeso(80.0);
            usuario1.getRoles().add(Rol.ROL_USER);
            usuarioRepository.save(usuario1);

        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir al guardar los usuarios
        }
    }
}
