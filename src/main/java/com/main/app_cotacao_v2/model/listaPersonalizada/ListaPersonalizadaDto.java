package com.main.app_cotacao_v2.model.listaPersonalizada;

import com.main.app_cotacao_v2.model.materialPadrao.MaterialPadraoDto;

import java.util.List;

public record ListaPersonalizadaDto(
        Long clienteId,
        Long alunoId,
        Long listaPadraoId,
        List<MaterialPadraoDto> materiais // JSON em String
) {
}
