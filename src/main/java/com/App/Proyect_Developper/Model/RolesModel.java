package com.App.Proyect_Developper.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Rol")
@AllArgsConstructor
@NoArgsConstructor
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "Rol", nullable = false)
    private String Rol;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserModel usuario;

}