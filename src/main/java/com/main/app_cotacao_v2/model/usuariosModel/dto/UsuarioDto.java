package com.main.app_cotacao_v2.model.usuariosModel.dto;

import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;

public record UsuarioDto(String nome, String email, String senha, Roles role) {
}
