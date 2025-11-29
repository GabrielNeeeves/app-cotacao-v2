package com.main.app_cotacao_v2.model.empresa;

public record EmpresaDto(
        String nome,
        String endereco,
        String cnpj,
        String telefone
) {
}
