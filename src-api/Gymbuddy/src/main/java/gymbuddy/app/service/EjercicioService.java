package gymbuddy.app.service;

import gymbuddy.app.entities.Ejercicio;
import java.util.List;

public interface EjercicioService {
    List<Ejercicio> getAllEjercicios();
    Ejercicio getEjercicioById(Long id);
    Ejercicio createEjercicio(Ejercicio ejercicio);
    Ejercicio updateEjercicio(Long id, Ejercicio ejercicio);
    void deleteEjercicio(Long id);
}
