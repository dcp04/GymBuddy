package gymbuddy.app.controller;

import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.repository.EjercicioRepository;
import gymbuddy.app.service.EjercicioService;
import gymbuddy.app.service.StorageService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
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
    
    @CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/api/ejercicios")
    public ResponseEntity<Ejercicio> createEjercicio(@RequestBody Ejercicio ejercicio) {
        Ejercicio createdEjercicio = ejercicioService.createEjercicio(ejercicio);
        return new ResponseEntity<>(createdEjercicio, HttpStatus.CREATED);
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
        Ejercicio ejercicio = ejercicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Ejercicio not found"));

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