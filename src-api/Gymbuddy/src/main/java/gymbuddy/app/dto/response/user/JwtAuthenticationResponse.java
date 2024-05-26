package gymbuddy.app.dto.response.user;

/**
 * Representa una respuesta de autenticación JWT que contiene un token.
 */
public class JwtAuthenticationResponse {

    /**
     * El token JWT.
     */
    private String token;

    /**
     * Construye un JwtAuthenticationResponse con el token especificado.
     *
     * @param token El token JWT.
     */
    public JwtAuthenticationResponse(String token) {
        this.token = token;
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

    /**
     * Proporciona un constructor para construir objetos JwtAuthenticationResponse.
     *
     * @return Una instancia de JwtAuthenticationResponseBuilder.
     */
    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    /**
     * Clase constructora para construir objetos JwtAuthenticationResponse.
     */
    public static class JwtAuthenticationResponseBuilder {

        /**
         * El token JWT.
         */
        private String token;

        /**
         * Establece el token JWT.
         *
         * @param token El token JWT.
         * @return La instancia de JwtAuthenticationResponseBuilder.
         */
        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * Construye un objeto JwtAuthenticationResponse con los parámetros establecidos.
         *
         * @return El objeto JwtAuthenticationResponse construido.
         */
        public JwtAuthenticationResponse build() {
            return new JwtAuthenticationResponse(token);
        }
    }
}
