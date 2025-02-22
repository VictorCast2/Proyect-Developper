package com.App.Proyect_Developper.Configuration;

import com.App.Proyect_Developper.Enum.RolEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class ConfigurationSegurity {
    public RolEnum RolAdmin = RolEnum.Administrador;
    public RolEnum RolUser = RolEnum.User;

    /**
     * Configura las reglas de seguridad de la aplicación.
     * @param http La configuración de seguridad HTTP.
     * @return El filtro de seguridad configurado.
     * @throws Exception Si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securedFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/Login", "/Error", "/Logout").permitAll() // Permite acceso público
                        .requestMatchers("/Css/**", "/Js/**", "/Img/**").permitAll() // Permite acceso público
                        .requestMatchers("/Public/**").permitAll() // Permite acceso público
                        .requestMatchers("/Admin/**").hasRole(RolAdmin.getRolUsuario()) // Requiere rol Admin
                        .requestMatchers("/User/**").hasRole(RolUser.getRolUsuario()) // Requiere rol User
                        .anyRequest().authenticated() // Autenticación para otras rutas
                )
                .formLogin(form -> form
                        .loginPage("/Login") // Página de inicio de sesión personalizada
                        .failureUrl("/Error") // Redirigir si hay error
                        .defaultSuccessUrl("/Home", true) // Redirigir después de login exitoso
                        .permitAll() // Permitir acceso a la página de login
                )
                .logout(logout -> logout
                        .logoutUrl("/Logout") // URL de cierre de sesión
                        .logoutSuccessUrl("/Login?Logout") // Redirige tras cerrar sesión
                        .invalidateHttpSession(true) // Invalida completamente la sesión
                        .clearAuthentication(true) // Borra la autenticación actual
                        .deleteCookies("JSESSIONID") // Borra la cookie de sesión
                        .permitAll() // Permitir acceso a la página de logout
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Crea nueva sesión al autenticarse
                        .invalidSessionUrl("/Login") // Redirige si la sesión es inválida
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession) // Nueva sesión tras autenticarse
                )
                .httpBasic(Customizer.withDefaults()); // Habilita autenticación básica
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            try {
                if (authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                    response.sendRedirect("/AdminHome"); // 🔥 Redirección segura
                } else {
                    response.sendRedirect("/UserHome");
                }
            } catch (Exception e) {
                e.printStackTrace(); // 🛠 Imprime errores en consola
                response.sendRedirect("/login?error=redirect"); // 🔥 Evita error 500
            }
        };
    }

}