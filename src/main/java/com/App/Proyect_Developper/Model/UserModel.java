package com.App.Proyect_Developper.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Data
@Entity
@Table(name = "Users")
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "RolesModel", joinColumns = @JoinColumn(name = "UserId"))
    @Column(name = "Roles")
    private Set<String> Roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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