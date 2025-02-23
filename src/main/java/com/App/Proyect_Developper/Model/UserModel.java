package com.App.Proyect_Developper.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;

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

    @Column(name = "Roles", nullable = false)
    @OneToMany(mappedBy = "UserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolesModel> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRol()))
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