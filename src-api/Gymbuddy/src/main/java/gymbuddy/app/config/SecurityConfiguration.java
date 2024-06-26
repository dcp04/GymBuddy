package gymbuddy.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import gymbuddy.app.entities.Rol;
import gymbuddy.app.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/resources/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/media/upload/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/media/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/usuarios/email/**").permitAll()  
                .requestMatchers(HttpMethod.GET, "/api/entrenamientos/{entrenamientoId}/isApuntado").hasAuthority(Rol.ROL_USER.toString())
                .requestMatchers(HttpMethod.POST, "/api/entrenamientos/{entrenamientoId}/apuntarse").hasAuthority(Rol.ROL_USER.toString())
                .requestMatchers(HttpMethod.POST, "/api/entrenamientos/{entrenamientoId}/desapuntarse").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString(), Rol.ROL_USER.toString())
                .requestMatchers(HttpMethod.GET, "/api/entrenamientos/usuario/{usuarioId}").hasAuthority(Rol.ROL_USER.toString())
                .requestMatchers(HttpMethod.GET, "/api/entrenamientos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/entrenamientos/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.GET, "/api/ejercicios/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/ejercicios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.POST, "/media/upload/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/media/upload/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.DELETE, "/media/upload/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.GET, "/media/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasAnyAuthority(Rol.ROL_ADMIN.toString(), Rol.ROL_ENTRENADOR.toString())
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
