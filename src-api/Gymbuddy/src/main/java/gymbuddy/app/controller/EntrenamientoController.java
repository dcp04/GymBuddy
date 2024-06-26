package gymbuddy.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gymbuddy.app.entities.Dificultad;
import gymbuddy.app.entities.Ejercicio;
import gymbuddy.app.entities.Entrenamiento;
import gymbuddy.app.entities.Usuario;
import gymbuddy.app.error.exception.EntrenamientoNotFoundException;
import gymbuddy.app.repository.EntrenamientoRepository;
import gymbuddy.app.service.EjercicioService;
import gymbuddy.app.service.EntrenamientoService;
import gymbuddy.app.service.StorageService;
import gymbuddy.app.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
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

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/entrenamientos")
    public ResponseEntity<List<Entrenamiento>> obtenerTodosLosEntrenamientos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerTodosLosEntrenamientos();
        return ResponseEntity.ok(entrenamientos);
    }

    @GetMapping("/api/entrenamientos/{id}")
    public ResponseEntity<Entrenamiento> obtenerEntrenamientoPorId(@PathVariable(value = "id") Long entrenamientoId) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(entrenamientoId)
                .orElseThrow(() -> new EntrenamientoNotFoundException("Ejercicio not found"));

        return ResponseEntity.ok(entrenamiento);
    }

    @PostMapping(path = "/api/entrenamientos", consumes = "multipart/form-data")
    public ResponseEntity<Entrenamiento> crearEntrenamiento(
            @RequestParam("nombre") String nombre,
            @RequestParam("dificultad") Dificultad dificultad,
            @RequestParam("creador") Long creadorId,
            @RequestParam("ejercicios") List<Long> ejerciciosIds,
            @RequestParam("imagenUrl") MultipartFile imagenUrl) {

        String imageUrl = null;
        if (!imagenUrl.isEmpty()) {
            String path = storageService.store(imagenUrl);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            imageUrl = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/media/entrenamientos/")
                    .path(path)
                    .toUriString();
        }

        Usuario creador = usuarioService.getUsuarioById(creadorId);
        List<Ejercicio> ejercicios = ejercicioService.getAllEjerciciosById(ejerciciosIds);

        Entrenamiento nuevoEntrenamiento = new Entrenamiento();
        nuevoEntrenamiento.setNombre(nombre);
        nuevoEntrenamiento.setDificultad(dificultad);
        nuevoEntrenamiento.setCreador(creador);
        nuevoEntrenamiento.setEjercicios(ejercicios);
        nuevoEntrenamiento.setImagenUrl(imageUrl);
        System.out.println("Ejercicios: " + ejerciciosIds);

        Entrenamiento entrenamientoCreado = entrenamientoService.crearEntrenamiento(nuevoEntrenamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenamientoCreado);
    }

    @PostMapping("/api/entrenamientos/{entrenamientoId}/apuntarse")
    public ResponseEntity<?> apuntarseAEntrenamiento(
            @PathVariable Long entrenamientoId,
            @RequestParam Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Entrenamiento entrenamiento = entrenamientoService.obtenerEntrenamientoPorId(entrenamientoId);

        if (usuario == null || entrenamiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o Entrenamiento no encontrado");
        }

        List<Usuario> usuariosApuntados = entrenamiento.getUsuariosApuntados();
        if (!usuariosApuntados.contains(usuario)) {
            usuariosApuntados.add(usuario);
            entrenamiento.setUsuariosApuntados(usuariosApuntados);
            entrenamientoService.updateEntrenamiento(entrenamiento);
            return ResponseEntity.ok("Usuario apuntado al entrenamiento con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya está apuntado a este entrenamiento");
        }
    }

    @PostMapping("/api/entrenamientos/{entrenamientoId}/desapuntarse")
    public ResponseEntity<?> desapuntarseDeEntrenamiento(
            @PathVariable Long entrenamientoId,
            @RequestParam Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Entrenamiento entrenamiento = entrenamientoService.obtenerEntrenamientoPorId(entrenamientoId);

        if (usuario == null || entrenamiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o Entrenamiento no encontrado");
        }

        List<Usuario> usuariosApuntados = entrenamiento.getUsuariosApuntados();
        if (usuariosApuntados.contains(usuario)) {
            usuariosApuntados.remove(usuario);
            entrenamiento.setUsuariosApuntados(usuariosApuntados);
            entrenamientoService.updateEntrenamiento(entrenamiento);
            return ResponseEntity.ok("Usuario desapuntado del entrenamiento con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario no está apuntado a este entrenamiento");
        }
    }

    @GetMapping("/api/entrenamientos/{entrenamientoId}/isApuntado")
    public ResponseEntity<?> isUsuarioApuntado(
            @PathVariable Long entrenamientoId,
            @RequestParam Long usuarioId) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerEntrenamientoPorId(entrenamientoId);
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);

        if (entrenamiento == null || usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o Entrenamiento no encontrado");
        }

        boolean isApuntado = entrenamiento.getUsuariosApuntados().contains(usuario);
        return ResponseEntity.ok(isApuntado);
    }

    @GetMapping("/api/entrenamientos/usuario/{usuarioId}")
    public ResponseEntity<List<Entrenamiento>> getEntrenamientosByUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Entrenamiento> entrenamientos = entrenamientoService.getEntrenamientosByUsuario(usuario);
        return ResponseEntity.ok(entrenamientos);
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
            @PathVariable Long id) {
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/entrenamientos/")
                .path(path)
                .toUriString();

        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenamiento not found"));

        entrenamiento.setImagenUrl(url);
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