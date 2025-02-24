package com.App.Proyect_Developper.Services;

import com.App.Proyect_Developper.Model.UserModel;
import com.App.Proyect_Developper.Repository.UserRepository;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando autenticar usuario: " + username);
        UserModel usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado en la base de datos.");
                    return new UsernameNotFoundException("Usuario no encontrado ... ");
                });
        System.out.println("Usuario encontrado: " + usuario.getUsername()+ " ... " );
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRoles()
                        .stream().map(SimpleGrantedAuthority::new) // Convertir a SimpleGrantedAuthority
                        .collect(Collectors.toList()) // Convertir a List
        );
    }

}