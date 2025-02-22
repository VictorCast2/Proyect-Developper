package com.App.Proyect_Developper.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Rol")
@AllArgsConstructor
@NoArgsConstructor
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "UserId", unique = true, nullable = false)
    private String UserId;

    @Column(name = "Rol", nullable = false)
    private String Rol;

}