package gymbuddy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.repository.EntrenamientoRepository;
import gymbuddy.app.service.EjercicioService;
import gymbuddy.app.service.EntrenamientoService;
import gymbuddy.app.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EntrenamientoController {

    @Autowired
    private final EntrenamientoService entrenamientoService;

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private final StorageService storageService;

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping("/api/entrenamientos")
    public ResponseEntity<List<Entrenamiento>> obtenerTodosLosEntrenamientos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerTodosLosEntrenamientos();
        return ResponseEntity.ok(entrenamientos);
    }

    @GetMapping("/api/entrenamientos/{id}")
    public ResponseEntity<Entrenamiento> obtenerEntrenamientoPorId(@PathVariable(value = "id") Long entrenamientoId) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerEntrenamientoPorId(entrenamientoId);
        return ResponseEntity.ok(entrenamiento);
    }

    @PostMapping("/api/entrenamientos")
    public ResponseEntity<Entrenamiento> crearEntrenamiento(@RequestBody Entrenamiento entrenamiento) {
        Entrenamiento nuevoEntrenamiento = entrenamientoService.crearEntrenamiento(entrenamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEntrenamiento);
    }

    @PutMapping("/api/entrenamientos/{id}")
    public ResponseEntity<Entrenamiento> actualizarEntrenamiento(
            @PathVariable(value = "id") Long entrenamientoId,
            @RequestBody Entrenamiento entrenamientoDetails) {
        Entrenamiento entrenamientoActualizado = entrenamientoService.actualizarEntrenamiento(entrenamientoId,
                entrenamientoDetails);
        return ResponseEntity.ok(entrenamientoActualizado);
    }

    @DeleteMapping("/api/entrenamientos/{id}")
    public ResponseEntity<?> eliminarEntrenamiento(@PathVariable(value = "id") Long entrenamientoId) {
        entrenamientoService.eliminarEntrenamiento(entrenamientoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/entrenamientos/ejercicios/{id}")
    public List<Ejercicio> getEjerciciosByEntrenamientoId(@PathVariable Long id) {
        return ejercicioService.getEjerciciosByEntrenamientoId(id);
    }

    @PostMapping("/media/upload/entrenamientos/{id}")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile,
            @RequestParam("id") Long id) {
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/entrenamientos/")
                .path(path)
                .toUriString();

        // Fetch the Entrenamiento entity
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenamiento not found"));

        // Update the imagenUrl attribute
        entrenamiento.setImagenUrl(url);
        System.out.println(url);

        // Save the updated entity
        entrenamientoRepository.save(entrenamiento);

        return Map.of("url", url);
    }

    @GetMapping("/media/entrenamientos/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
}
