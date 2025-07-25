package com.main.app_cotacao_v2.model.usuariosModel.dto;

import java.math.BigDecimal;

public record FuncionarioPostDto(
        String nome,
        String email,
        String senha,
        BigDecimal salario,
        Integer empresaId,
        Integer escolaId
) {
}
