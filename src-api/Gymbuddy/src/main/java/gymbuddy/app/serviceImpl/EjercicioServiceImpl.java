package gymbuddy.app.serviceImpl;

import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.repository.EjercicioRepository;
import gymbuddy.app.service.EjercicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjercicioServiceImpl implements EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Override
    public List<Ejercicio> getAllEjercicios() {
        return ejercicioRepository.findAll();
    }

    @Override
    public Ejercicio getEjercicioById(Long id) {
        return ejercicioRepository.findById(id).orElse(null);
    }

    @Override
    public Ejercicio createEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    @Override
    public Ejercicio updateEjercicio(Long id, Ejercicio ejercicio) {
        Ejercicio existingEjercicio = ejercicioRepository.findById(id).orElse(null);
        if (existingEjercicio != null) {
            ejercicio.setId(id);
            return ejercicioRepository.save(ejercicio);
        }
        return null;
    }

    @Override
    public void deleteEjercicio(Long id) {
        ejercicioRepository.deleteById(id);
    }

    @Override
    public List<Ejercicio> getEjerciciosByEntrenamientoId(Long id) {
        return ejercicioRepository.findAllByEntrenamientoId(id);
    }

    @Override
    public List<Ejercicio> getAllEjerciciosById(Iterable<Long> id) {
        return ejercicioRepository.findAllById(id);
    }
}
