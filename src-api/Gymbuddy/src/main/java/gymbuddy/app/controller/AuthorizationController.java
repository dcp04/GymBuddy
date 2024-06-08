package gymbuddy.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import gymbuddy.app.dto.response.user.UsuarioResponse;
import gymbuddy.app.entities.Usuario;

/**
 * Controlador para gestionar los recursos protegidos por autorización.
 */
@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@CrossOrigin
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    /**
     * Método para obtener el perfil del usuario autenticado.
     * 
     * @param usuario El usuario autenticado.
     * @return Una respuesta HTTP con los detalles del perfil del usuario.
     */
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> miPerfil(@AuthenticationPrincipal Usuario usuario) {
        logger.info("## AuthorizationController :: miPerfil");

        UsuarioResponse userResponse = new UsuarioResponse(usuario.getNombre(), usuario.getApellidos(),
                usuario.getEmail(), usuario.getRoles().toString());

        return ResponseEntity.ok(userResponse);
    }

}
