package gymbuddy.app.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import gymbuddy.app.entities.Rol;
import gymbuddy.app.service.UserService;

/**
 * Clase de configuración para la seguridad de la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    UserService userService;

    /**
     * Configura la cadena de filtros de seguridad.
     * 
     * @param http El objeto HttpSecurity.
     * @return La cadena de filtros de seguridad.
     * @throws Exception Si hay un error en la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/resources/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/api/entrenamientos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/entrenamientos/{id}**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                
                .requestMatchers(HttpMethod.GET, "/api/ejercicios/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/ejercicios/{id}/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                
                .requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers(HttpMethod.POST, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                
                .anyRequest().authenticated())
            .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
            .cors(Customizer.withDefaults())
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configura el codificador de contraseñas.
     * 
     * @return El codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el proveedor de autenticación.
     * 
     * @return El proveedor de autenticación.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura el administrador de autenticación.
     * 
     * @param config La configuración de autenticación.
     * @return El administrador de autenticación.
     * @throws Exception Si hay un error al obtener el administrador de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura el origen de configuración CORS.
     * 
     * @return El origen de configuración CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
