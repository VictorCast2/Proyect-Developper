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

    @Column(name = "UserId")
    @ManyToOne @JoinColumn(name = "UserId", nullable = false)
    private String UserId;

    @Column(name = "Rol", nullable = false)
    private String Rol;

}