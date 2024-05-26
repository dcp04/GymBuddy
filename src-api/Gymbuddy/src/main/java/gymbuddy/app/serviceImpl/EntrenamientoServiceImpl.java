package gymbuddy.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.repository.EntrenamientoRepository;
import gymbuddy.app.service.EntrenamientoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Override
    public Entrenamiento crearEntrenamiento(Entrenamiento entrenamiento) {
        return entrenamientoRepository.save(entrenamiento);
    }

    @Override
    public Entrenamiento obtenerEntrenamientoPorId(Long id) {
        return entrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrenamiento no encontrado con ID: " + id));
    }

    @Override
    public List<Entrenamiento> obtenerTodosLosEntrenamientos() {
        return entrenamientoRepository.findAll();
    }

    @Override
    public Entrenamiento actualizarEntrenamiento(Long id, Entrenamiento entrenamientoDetails) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrenamiento no encontrado con ID: " + id));

        // Actualizar los detalles del entrenamiento con los datos proporcionados en
        // entrenamientoDetails
        entrenamiento.setNombre(entrenamientoDetails.getNombre());
        entrenamiento.setDificultad(entrenamientoDetails.getDificultad());
        // Actualizar otros campos según sea necesario

        return entrenamientoRepository.save(entrenamiento);
    }

    @Override
    public void eliminarEntrenamiento(Long id) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrenamiento no encontrado con ID: " + id));

        entrenamientoRepository.delete(entrenamiento);
    }

}