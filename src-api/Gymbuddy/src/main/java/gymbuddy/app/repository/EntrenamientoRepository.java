package gymbuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Usuario;
import java.util.List;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    List<Entrenamiento> findByUsuariosApuntadosContaining(Usuario usuario);
}
