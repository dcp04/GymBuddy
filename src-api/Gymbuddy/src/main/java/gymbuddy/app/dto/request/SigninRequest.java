package gymbuddy.app.dto.request;

/**
 * Clase que representa la solicitud de inicio de sesión.
 */
public class SigninRequest {
    private String email; // Correo electrónico del usuario
    private String password; // Contraseña del usuario

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
