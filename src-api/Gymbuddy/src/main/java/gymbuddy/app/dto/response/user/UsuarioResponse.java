package gymbuddy.app.dto.response.user;

/**
 * Representa la respuesta de un usuario.
 */
public class UsuarioResponse {

    /**
     * El nombre del usuario.
     */
    private String nombre;

    /**
     * El apellidos del usuario.
     */
    private String apellidos;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * El rol del usuario.
     */
    private String roles;

    /**
     * Construye un objeto UsuarioResponse con la información proporcionada.
     *
     * @param firstName El nombre del usuario.
     * @param lastName  El apellidos del usuario.
     * @param email     El correo electrónico del usuario.
     * @param rol       El rol del usuario.
     */
    public UsuarioResponse(String nombre, String apellidos, String email, String roles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param firstName El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellidos del usuario.
     *
     * @return El apellidos del usuario.
     */
    public String getApellido() {
        return apellidos;
    }

    /**
     * Establece el apellidos del usuario.
     *
     * @param lastName El apellidos del usuario.
     */
    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol() {
        return roles;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String roles) {
        this.roles = roles;
    }
}
