package com.App.Proyect_Developper.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import static com.App.Proyect_Developper.Services.CustomUserDetailsService.userRepository;

@Data
@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "Nombre", nullable = false)
    private String Nombre;

    @Column(name = "Apellido", nullable = true)
    private String Apellido;

    @Column(name = "Username", unique = true, nullable = false)
    private String Username;

    @Column(name = "Contrasenna", nullable = false)
    private String Contrasenna;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RolesModel> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> (GrantedAuthority) rol::getRol)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public String getPassword() {
        return Contrasenna;
    }

}