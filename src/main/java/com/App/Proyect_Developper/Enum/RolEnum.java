package com.App.Proyect_Developper.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RolEnum {
    Administrador ("Admin"),
    User ("User");
    private String RolUsuario;
}