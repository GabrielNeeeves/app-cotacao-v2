package com.main.app_cotacao_v2.model.usuariosModel.roles;

public enum Roles {
    CLIENTE("CLIENTE"),
    FUNCIONARIO("FUNCIONARIO"),
    ADMINISTRADOR("ADMINISTRADOR");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
