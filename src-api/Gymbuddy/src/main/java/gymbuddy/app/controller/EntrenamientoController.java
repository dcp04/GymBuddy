package gymbuddy.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.service.EntrenamientoService;

import java.util.List;

@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    @GetMapping
    public ResponseEntity<List<Entrenamiento>> obtenerTodosLosEntrenamientos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerTodosLosEntrenamientos();
        return ResponseEntity.ok(entrenamientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenamiento> obtenerEntrenamientoPorId(@PathVariable(value = "id") Long entrenamientoId) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerEntrenamientoPorId(entrenamientoId);
        return ResponseEntity.ok(entrenamiento);
    }

    @PostMapping
    public ResponseEntity<Entrenamiento> crearEntrenamiento(@RequestBody Entrenamiento entrenamiento) {
        Entrenamiento nuevoEntrenamiento = entrenamientoService.crearEntrenamiento(entrenamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEntrenamiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenamiento> actualizarEntrenamiento(
            @PathVariable(value = "id") Long entrenamientoId,
            @RequestBody Entrenamiento entrenamientoDetails) {
        Entrenamiento entrenamientoActualizado = entrenamientoService.actualizarEntrenamiento(entrenamientoId,
                entrenamientoDetails);
        return ResponseEntity.ok(entrenamientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEntrenamiento(@PathVariable(value = "id") Long entrenamientoId) {
        entrenamientoService.eliminarEntrenamiento(entrenamientoId);
        return ResponseEntity.noContent().build();
    }
}
