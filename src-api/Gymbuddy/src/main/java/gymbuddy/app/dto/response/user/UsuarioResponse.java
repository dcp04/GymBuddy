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
     * El apellido del usuario.
     */
    private String apellido;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * El rol del usuario.
     */
    private String rol;

    /**
     * Construye un objeto UsuarioResponse con la información proporcionada.
     *
     * @param firstName El nombre del usuario.
     * @param lastName  El apellido del usuario.
     * @param email     El correo electrónico del usuario.
     * @param rol       El rol del usuario.
     */
    public UsuarioResponse(String nombre, String apellido, String email, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
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
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param lastName El apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
