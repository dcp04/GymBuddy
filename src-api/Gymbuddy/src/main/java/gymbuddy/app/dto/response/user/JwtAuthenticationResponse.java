package gymbuddy.app.dto.response.user;

import java.util.Set;

/**
 * Representa una respuesta de autenticación JWT que contiene un token.
 */
public class JwtAuthenticationResponse {

    /**
     * El token JWT.
     */
    private String token;
    private Set<String> roles;

    /**
     * Construye un JwtAuthenticationResponse con el token especificado.
     *
     * @param token El token JWT.
     */
    public JwtAuthenticationResponse(String token, Set<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    /**
     * Obtiene el token JWT.
     *
     * @return El token JWT.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token JWT.
     *
     * @param token El token JWT.
     */
    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * Creates a new JwtAuthenticationResponseBuilder instance.
     * 
     * @return JwtAuthenticationResponseBuilder instance.
     */
    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    /**
     * Clase constructora para construir objetos JwtAuthenticationResponse.
     */
    public static class JwtAuthenticationResponseBuilder {
        private String token;
        private Set<String> roles;

        /**
         * Sets the token for the response.
         * 
         * @param token The JWT token.
         * @return JwtAuthenticationResponseBuilder instance.
         */
        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * Sets the roles for the response.
         * 
         * @param roles The user roles.
         * @return JwtAuthenticationResponseBuilder instance.
         */
        public JwtAuthenticationResponseBuilder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        /**
         * Builds and returns the JwtAuthenticationResponse instance.
         * 
         * @return JwtAuthenticationResponse instance.
         */
        public JwtAuthenticationResponse build() {
            return new JwtAuthenticationResponse(token, roles);
        }
    }
}
