package gymbuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import gymbuddy.app.entities.Ejercicio;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {

    @Query("SELECT e FROM Ejercicio e WHERE e.entrenamiento.id = :id")
    List<Ejercicio> findAllByEntrenamientoId(@Param("id") Long id);
}
