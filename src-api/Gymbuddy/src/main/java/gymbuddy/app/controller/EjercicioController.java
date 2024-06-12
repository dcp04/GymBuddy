package gymbuddy.app.controller;

import gymbuddy.app.entities.Dificultad;
import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Usuario;
import gymbuddy.app.repository.EjercicioRepository;
import gymbuddy.app.service.EjercicioService;
import gymbuddy.app.service.StorageService;
import gymbuddy.app.service.UsuarioService;
import gymbuddy.app.dto.EjercicioDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
@AllArgsConstructor
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private final StorageService storageService;

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/ejercicios")
    public List<EjercicioDTO> getAllEjercicios() {
        return ejercicioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/ejercicios/{id}")
    public EjercicioDTO getEjercicioById(@PathVariable Long id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejercicio not found"));
        return convertToDTO(ejercicio);
    }

    private EjercicioDTO convertToDTO(Ejercicio ejercicio) {
        EjercicioDTO dto = new EjercicioDTO();
        dto.setId(ejercicio.getId());
        dto.setNombre(ejercicio.getNombre());
        dto.setDescripcion(ejercicio.getDescripcion());
        dto.setImagenUrl(ejercicio.getImagenUrl());
        return dto;
    }

    @PostMapping(path = "/api/ejercicios", consumes = "multipart/form-data")
    public ResponseEntity<Ejercicio> crearEjercicio(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("creador") Long creadorId,
            @RequestParam("imagenUrl") MultipartFile imagenUrl) {

        String imageUrl = null;
        if (!imagenUrl.isEmpty()) {
            String path = storageService.store(imagenUrl);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            imageUrl = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/media/ejercicios/")
                    .path(path)
                    .toUriString();
        }

        Usuario creador = usuarioService.getUsuarioById(creadorId);

        Ejercicio nuevoEjercicio = new Ejercicio();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);
        nuevoEjercicio.setCreador(creador);
        nuevoEjercicio.setImagenUrl(imageUrl);

        Ejercicio ejercicioCreado = ejercicioService.createEjercicio(nuevoEjercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicioCreado);
    }

    @PutMapping("/api/ejercicios/{id}")
    public ResponseEntity<Ejercicio> updateEjercicio(@PathVariable("id") Long id, @RequestBody Ejercicio ejercicio) {
        Ejercicio updatedEjercicio = ejercicioService.updateEjercicio(id, ejercicio);
        if (updatedEjercicio != null) {
            return new ResponseEntity<>(updatedEjercicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/ejercicios/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable("id") Long id) {
        ejercicioService.deleteEjercicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/media/upload/ejercicios/{id}")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long id) {
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/ejercicios/")
                .path(path)
                .toUriString();

        // Fetch the Ejercicio entity
        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejercicio not found"));

        // Update the imagenUrl attribute
        ejercicio.setImagenUrl(url);

        // Save the updated entity
        ejercicioRepository.save(ejercicio);

        return Map.of("url", url);
    }

    @GetMapping("/media/ejercicios/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
}