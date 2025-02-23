package com.App.Proyect_Developper.Configuration;

import com.App.Proyect_Developper.Enum.RolEnum;
import org.springframework.context.annotation.*;
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
                        .requestMatchers("/Api/Auth/Login", "/Api/Auth/Logout").permitAll() // Permite acceso público
                        .requestMatchers("/Css/**", "/Js/**", "/Img/**").permitAll() // Permite acceso público
                        .requestMatchers("/Error/**", "/Error").permitAll()
                        .requestMatchers("/Admin/**").hasRole(RolAdmin.getRolUsuario()) // Requiere rol Admin
                        .requestMatchers("/User/**").hasRole(RolUser.getRolUsuario()) // Requiere rol User
                        .anyRequest().authenticated() // Autenticación para otras rutas
                )
                .formLogin(form -> form
                        .loginPage("/Api/Auth/Login") // Página de inicio de sesión personalizada
                        .failureUrl("/Error") // Redirigir si hay error
                        .defaultSuccessUrl("/Home", true) // Redirigir después de login exitoso
                        .permitAll() // Permitir acceso a la página de login
                )
                .logout(logout -> logout
                        .logoutUrl("/Api/Auth/Logout") // URL de cierre de sesión
                        .logoutSuccessUrl("/Api/Auth/Login?Logout") // Redirige tras cerrar sesión
                        .invalidateHttpSession(true) // Invalida completamente la sesión
                        .clearAuthentication(true) // Borra la autenticación actual
                        .deleteCookies("JSESSIONID") // Borra la cookie de sesión
                        .permitAll() // Permitir acceso a la página de logout
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Crea nueva sesión al autenticarse
                        .invalidSessionUrl("/Api/Auth/Login") // Redirige si la sesión es inválida
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession) // Nueva sesión tras autenticarse
                )
                .httpBasic(Customizer.withDefaults()); // Habilita autenticación básica
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
<<<<<<< HEAD
        return (_, response, authentication) -> {
=======
        // 🔒 Redirige a la página de inicio correspondiente
        return (request, response, authentication) -> {
>>>>>>> 05922b8701497c7ea76992a345ada07dba523eba
            try {
                // 🔒 Redirige a la página de inicio correspondiente
                if (authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals(RolAdmin.getRolUsuario()))) {
                    response.sendRedirect("/AdminHome"); // 🔥 Redirección segura
                } else if (authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals(RolUser.getRolUsuario()))) {
                    response.sendRedirect("/UserHome");  // 🔥 Redirección segura
                } else {
                    response.sendRedirect("/Api/Auth/Login"); // 🔥 Redirección segura
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                response.sendRedirect("/Api/Auth/Login?error=redirect"); // 🔥 Evita error 500
            }
        };
    }

}