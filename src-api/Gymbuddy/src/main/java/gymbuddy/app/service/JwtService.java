package gymbuddy.app.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz que define los servicios relacionados con JWT.
 */
public interface JwtService {
    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token JWT.
     */
    String extractUserName(String token);

    /**
     * Genera un token JWT para el usuario.
     *
     * @param userDetails Los detalles del usuario.
     * @return El token JWT generado.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Verifica si un token JWT es válido para un usuario.
     *
     * @param token       El token JWT a verificar.
     * @param userDetails Los detalles del usuario.
     * @return true si el token es válido, false de lo contrario.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
