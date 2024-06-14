package gymbuddy.app.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa la solicitud de registro de usuario.
 */
public class SignUpRequest {
    @NotBlank(message = "El campo nombre no puede estar en blanco")
    @Size(max = 50, message = "El nombre solo puede tener 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellidos no puede estar en blanco")
    @Size(max = 50, message = "Los apellidos solo pueden tener 50 caracteres")
    private String apellidos;

    @Column(unique = true)
    @NotBlank(message = "El campo email no puede estar en blanco")
    @Email(message = "El email debe tener formato de email")
    private String email;

    @Min(value = 1, message = "La estatura debe ser mayor que 0")
    private int estatura;

    @Min(value = 1, message = "El peso debe ser mayor o igual a 1")
    private Double peso;

    private String password;

    /**
     * Método getter para obtener el nombre del usuario.
     * 
     * @return String El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método setter para establecer el nombre del usuario.
     * 
     * @param firstName El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  /**
     * Método getter para obtener los apellidos del usuario.
     * 
     * @return String Los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método setter para establecer el apellidos del usuario.
     * 
     * @param apellidos El apellidos a establecer.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * 
     * @return String El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * 
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * 
     * @return String El correo electrónico del usuario.
     */
    public int getEstatura() {
        return estatura;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * 
     * @param email El correo electrónico a establecer.
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * 
     * @return String El correo electrónico del usuario.
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * 
     * @param email El correo electrónico a establecer.
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Método getter para obtener la contraseña del usuario.
     * 
     * @return String La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método setter para establecer la contraseña del usuario.
     * 
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
