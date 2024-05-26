package gymbuddy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gymbuddy.app.entities.Usuario;

/**
 * Repositorio para la entidad Usuario que gestiona las operaciones de base de datos relacionadas con los usuarios.
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Busca un usuario por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findById(Long id);

    /**
     * Comprueba si existe un usuario con el correo electrónico especificado.
     *
     * @param email El correo electrónico a comprobar.
     * @return true si existe un usuario con el correo electrónico especificado, false de lo contrario.
     */
    Boolean existsByEmail(String email);
}
