package com.main.app_cotacao_v2.model.ofertaMaterial;

public record OfertaMaterialResponseDto(
        Long id,
        String materialNome,
        Double preco,
        Integer prazoEntrega,
        String observacoes
) {}