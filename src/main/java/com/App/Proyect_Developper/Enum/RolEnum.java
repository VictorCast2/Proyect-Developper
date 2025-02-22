package com.App.Proyect_Developper.Enum;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RolEnum {
    Administrador ("Admin"),
    User ("User");
    private String RolUsuario;
}