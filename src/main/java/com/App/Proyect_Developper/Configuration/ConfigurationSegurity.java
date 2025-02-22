package com.App.Proyect_Developper.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class ConfigurationSegurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/AdminHome").hasRole("ADMIN")
                        .requestMatchers("/UserHome").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler()) // 🔥 Redirige según el rol
                        .failureUrl("/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 🔥 Define la URL de logout
                        .logoutSuccessUrl("/login?logout") // Redirige a login después de cerrar sesión
                        .invalidateHttpSession(true) // Invalida completamente la sesión
                        .clearAuthentication(true) // Borra la autenticación actual
                        .permitAll() // Permitir el acceso a logout sin autenticación
                );
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