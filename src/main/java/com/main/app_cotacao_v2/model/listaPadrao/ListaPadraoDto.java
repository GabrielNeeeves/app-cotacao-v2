package com.main.app_cotacao_v2.model.listaPadrao;

import com.main.app_cotacao_v2.model.material.MaterialDto;
import com.main.app_cotacao_v2.model.material.MaterialPadraoDto;

import java.util.List;

public record ListaPadraoDto(
        Long funcionario_id,
        Long escola_id,
        Integer ano_letivo,
        String serie,
        List<MaterialPadraoDto> materiais
) {
}
