package gymbuddy.app.dto.request;

/**
 * Clase que representa la solicitud de registro de usuario.
 */
public class SignUpRequest {
    private String nombre; // Nombre del usuario
    private String apellido; // Apellido del usuario
    private String email; // Correo electrónico del usuario
    private int estatura;
    private Double peso;
    private String password; // Contraseña del usuario

    /**
     * Método getter para obtener el nombre del usuario.
     * @return String El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método setter para establecer el nombre del usuario.
     * @param firstName El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método getter para obtener el apellido del usuario.
     * @return String El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método setter para establecer el apellido del usuario.
     * @param lastName El apellido a establecer.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * @return String El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * @return String El correo electrónico del usuario.
     */
    public int getEstatura() {
        return estatura;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * @param email El correo electrónico a establecer.
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * @return String El correo electrónico del usuario.
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * @param email El correo electrónico a establecer.
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Método getter para obtener la contraseña del usuario.
     * @return String La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método setter para establecer la contraseña del usuario.
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
