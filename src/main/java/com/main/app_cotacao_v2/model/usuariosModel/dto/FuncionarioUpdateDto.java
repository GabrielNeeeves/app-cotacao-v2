package com.main.app_cotacao_v2.model.usuariosModel.dto;

import java.math.BigDecimal;

public record FuncionarioUpdateDto(
        String nome,
        String email,
        String senha,
        BigDecimal salario,
        Long empresaId,
        Long escolaId
) {
}
