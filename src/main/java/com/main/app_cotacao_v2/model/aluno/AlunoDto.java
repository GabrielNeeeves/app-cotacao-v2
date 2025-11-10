package com.main.app_cotacao_v2.model.aluno;

import com.main.app_cotacao_v2.model.escola.Escola;

public record AlunoDto(
        Long clienteId,
        Long escolaId,
        String nome,
        String serie,
        String turno,                      // "MATUTINO", "VESPERTINO", "NOTURNO"
        Integer anoLetivo,
        String observacoes
) {
}
