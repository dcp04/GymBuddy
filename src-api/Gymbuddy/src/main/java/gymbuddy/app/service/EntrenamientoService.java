package gymbuddy.app.service;

import java.util.List;

import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Usuario;
import jakarta.persistence.EntityNotFoundException;

public interface EntrenamientoService {

    /**
     * Crea un nuevo entrenamiento.
     *
     * @param entrenamiento El entrenamiento a crear.
     * @return El entrenamiento creado.
     */
    Entrenamiento crearEntrenamiento(Entrenamiento entrenamiento);

    /**
     * Obtiene un entrenamiento por su ID.
     *
     * @param id El ID del entrenamiento a buscar.
     * @return El entrenamiento encontrado.
     * @throws EntityNotFoundException Si no se encuentra el entrenamiento.
     */
    Entrenamiento obtenerEntrenamientoPorId(Long id);

    /**
     * Obtiene todos los entrenamientos.
     *
     * @return Una lista de todos los entrenamientos.
     */
    List<Entrenamiento> obtenerTodosLosEntrenamientos();

    /**
     * Actualiza un entrenamiento existente.
     *
     * @param id                El ID del entrenamiento a actualizar.
     * @param entrenamientoDetails Los detalles del entrenamiento actualizado.
     * @return El entrenamiento actualizado.
     * @throws EntityNotFoundException Si no se encuentra el entrenamiento a actualizar.
     */
    Entrenamiento actualizarEntrenamiento(Long id, Entrenamiento entrenamientoDetails);

    /**
     * Elimina un entrenamiento por su ID.
     *
     * @param id El ID del entrenamiento a eliminar.
     * @throws EntityNotFoundException Si no se encuentra el entrenamiento a eliminar.
     */
    void eliminarEntrenamiento(Long id);

    Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento);

    List<Entrenamiento> getEntrenamientosByUsuario(Usuario usuario);
}