package com.main.app_cotacao_v2.model.usuariosModel.dto;

public record LoginResponseDto(
        String token,
        String role,
        Long clienteId,
        Long funcionarioId,
        Long empresaId,
        Long escolaId,
        String empresa,
        String escola
) {
}
