package com.main.app_cotacao_v2.model.usuariosModel.dto;

import java.math.BigDecimal;

public record FuncionarioDto(
        BigDecimal salario,
        Long usuarioId,
        Long empresaId,
        Long escolaId
) {
}
