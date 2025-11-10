package com.main.app_cotacao_v2.model;

public record LoginResponseDto(
        String token,
        String role,
        Long clienteId,
        Long funcionarioId,
        Long adminId
) {}
