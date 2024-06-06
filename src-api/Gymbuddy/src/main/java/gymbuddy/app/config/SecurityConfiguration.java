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
                .requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasAuthority(Rol.ROL_ADMIN.toString())
                .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .cors(withDefaults -> {})
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
