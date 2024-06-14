package gymbuddy.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gymbuddy.app.entities.Dificultad;
import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Rol;
import gymbuddy.app.entities.Usuario;
import gymbuddy.app.repository.EjercicioRepository;
import gymbuddy.app.repository.EntrenamientoRepository;
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
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

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

            Ejercicio ejercicio1 = new Ejercicio();
            ejercicio1.setNombre("Press Banca");
            ejercicio1.setDescripcion(
                    "El press de banca es un ejercicio de levantamiento de pesas que se centra en el desarrollo de los músculos pectorales mayores, los tríceps y los deltoides anteriores.");
            ejercicio1.setCreador(usuario2);
            ejercicio1.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/PressBancaBarra.png");
            ejercicioRepository.save(ejercicio1);

            Ejercicio ejercicio2 = new Ejercicio();
            ejercicio2.setNombre("Sentadilla");
            ejercicio2.setDescripcion(
                    "La sentadilla es un ejercicio de entrenamiento de fuerza que trabaja los músculos de las piernas, incluyendo los cuádriceps, los isquiotibiales y los glúteos.");
            ejercicio2.setCreador(usuario1);
            ejercicio2.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/Sentadilla.png");
            ejercicioRepository.save(ejercicio2);

            Ejercicio ejercicio3 = new Ejercicio();
            ejercicio3.setNombre("Peso Muerto");
            ejercicio3.setDescripcion(
                    "El peso muerto es un ejercicio de levantamiento de pesas que se centra en el desarrollo de los músculos de la espalda baja, los glúteos y los isquiotibiales.");
            ejercicio3.setCreador(usuario1);
            ejercicio3.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/PesoMuerto.png");
            ejercicioRepository.save(ejercicio3);

            Ejercicio ejercicio4 = new Ejercicio();
            ejercicio4.setNombre("Press Militar");
            ejercicio4.setDescripcion(
                    "El press militar es un ejercicio de levantamiento de pesas que se enfoca en el desarrollo de los músculos deltoides y los tríceps.");
            ejercicio4.setCreador(usuario1);
            ejercicio4.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/PressHombro.png");
            ejercicioRepository.save(ejercicio4);

            Ejercicio ejercicio5 = new Ejercicio();
            ejercicio5.setNombre("Flexiones");
            ejercicio5.setDescripcion(
                    "Las flexiones son un ejercicio de peso corporal que trabaja los músculos del pecho, los tríceps y los hombros.");
            ejercicio5.setCreador(usuario2);
            ejercicio5.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/Flexiones.png");
            ejercicioRepository.save(ejercicio5);

            Ejercicio ejercicio6 = new Ejercicio();
            ejercicio6.setNombre("Plancha");
            ejercicio6.setDescripcion(
                    "La plancha es un ejercicio isométrico que se centra en el fortalecimiento del núcleo, incluyendo los músculos abdominales y la espalda baja.");
            ejercicio6.setCreador(usuario2);
            ejercicio6.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/ejercicios/Plancha.png");
            ejercicioRepository.save(ejercicio6);

            Entrenamiento entrenamiento3 = new Entrenamiento();
            entrenamiento3.setNombre("Entrenamiento Explosivo");
            entrenamiento3.setDificultad(Dificultad.DIFICIL);
            entrenamiento3.setCreador(usuario1);
            entrenamiento3.getEjercicios().add(ejercicio1);
            entrenamiento3.getEjercicios().add(ejercicio2);
            entrenamiento3.getEjercicios().add(ejercicio3);
            entrenamiento3.getEjercicios().add(ejercicio4); 
            entrenamiento3.getEjercicios().add(ejercicio5); 
            entrenamiento3.getEjercicios().add(ejercicio6); 
            entrenamiento3.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/entrenamientos/entrenamientoExplosivo.jpeg");
            entrenamientoRepository.save(entrenamiento3);
            

            Entrenamiento entrenamiento2 = new Entrenamiento();
            entrenamiento2.setNombre("Entrenamiento de Resistencia");
            entrenamiento2.setDificultad(Dificultad.FACIL);
            entrenamiento2.setCreador(usuario2);
            entrenamiento2.getEjercicios().add(ejercicio4); 
            entrenamiento2.getEjercicios().add(ejercicio5); 
            entrenamiento2.getEjercicios().add(ejercicio6); 
            entrenamiento2.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/entrenamientos/entrenamientoResistencia.jpeg");
            entrenamientoRepository.save(entrenamiento2);

            Entrenamiento entrenamiento1 = new Entrenamiento();
            entrenamiento1.setNombre("Entrenamiento De Fuerza");
            entrenamiento1.setDificultad(Dificultad.MODERADO);
            entrenamiento1.setCreador(usuario2);
            entrenamiento1.getEjercicios().add(ejercicio1);
            entrenamiento1.getEjercicios().add(ejercicio2);
            entrenamiento1.getEjercicios().add(ejercicio3);
            entrenamiento1.setImagenUrl("http://gymbuddy.us-east-1.elasticbeanstalk.com/media/entrenamientos/entrenamientoFuerza.jpeg");
            entrenamientoRepository.save(entrenamiento1);

            } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir al guardar los usuarios
        }
    }
}
