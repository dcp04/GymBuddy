package gymbuddy.app.controladores.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import gymbuddy.app.controller.AuthenticationController;
import gymbuddy.app.dto.request.SignUpRequest;
import gymbuddy.app.dto.request.SigninRequest;
import gymbuddy.app.dto.response.user.JwtAuthenticationResponse;
import gymbuddy.app.service.AuthenticationService;

/**
 * Clase de prueba para el controlador de autenticación.
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationControladorTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    /**
     * Prueba para verificar el método de registro.
     */
    @Test
    public void testSignup() {
        // Datos de ejemplo para la solicitud de registro
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setNombre("testUser");
        signUpRequest.setPassword("testPassword");
        // Simulando la respuesta del servicio de autenticación
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse("testToken");
        when(authenticationService.signup(signUpRequest)).thenReturn(authenticationResponse);

        // Llamando al método del controlador
        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.signup(signUpRequest);

        // Verificando el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authenticationResponse, response.getBody());
    }

    /**
     * Prueba para verificar el método de inicio de sesión.
     */
    @Test
    public void testSignin() {
        // Datos de ejemplo para la solicitud de inicio de sesión
        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setEmail("testUser");
        signinRequest.setPassword("testPassword");
        // Simulando la respuesta del servicio de autenticación
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse("testToken");
        when(authenticationService.signin(signinRequest)).thenReturn(authenticationResponse);

        // Llamando al método del controlador
        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.signin(signinRequest);

        // Verificando el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authenticationResponse, response.getBody());
    }
}
