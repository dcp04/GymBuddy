package gymbuddy.app.controller;

import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.service.EjercicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping
    public ResponseEntity<List<Ejercicio>> getAllEjercicios() {
        List<Ejercicio> ejercicios = ejercicioService.getAllEjercicios();
        return new ResponseEntity<>(ejercicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> getEjercicioById(@PathVariable("id") Long id) {
        Ejercicio ejercicio = ejercicioService.getEjercicioById(id);
        if (ejercicio != null) {
            return new ResponseEntity<>(ejercicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ejercicio> createEjercicio(@RequestBody Ejercicio ejercicio) {
        Ejercicio createdEjercicio = ejercicioService.createEjercicio(ejercicio);
        return new ResponseEntity<>(createdEjercicio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> updateEjercicio(@PathVariable("id") Long id, @RequestBody Ejercicio ejercicio) {
        Ejercicio updatedEjercicio = ejercicioService.updateEjercicio(id, ejercicio);
        if (updatedEjercicio != null) {
            return new ResponseEntity<>(updatedEjercicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable("id") Long id) {
        ejercicioService.deleteEjercicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
