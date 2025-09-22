package com.main.app_cotacao_v2.model.ofertaMaterial;

public record OfertaMaterialDto(
        Long funcionarioId,
        Long materialId,
        Double preco,
        Integer prazoEntrega,
        Integer quantidadeMinima,
        String observacoes
) {
}
