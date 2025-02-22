package com.App.Proyect_Developper.Enum;

public enum RolEnum {

    Administrador ("Admin"),
    User ("User");

    private String RolUsuario;

    RolEnum(String rolUsuario) {
        RolUsuario = rolUsuario;
    }

}