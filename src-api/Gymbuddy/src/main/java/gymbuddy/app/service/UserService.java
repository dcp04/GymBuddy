package gymbuddy.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import gymbuddy.app.dto.response.user.UsuarioResponse;

/**
 * Interfaz que define los servicios relacionados con los usuarios.
 */
public interface UserService {
    /**
     * Obtiene el servicio de detalles del usuario.
     *
     * @return El servicio de detalles del usuario.
     */
    UserDetailsService userDetailsService();

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Una lista de objetos UsuarioResponse que representan a todos los usuarios.
     */
    List<UsuarioResponse> getAllUsers();
}
