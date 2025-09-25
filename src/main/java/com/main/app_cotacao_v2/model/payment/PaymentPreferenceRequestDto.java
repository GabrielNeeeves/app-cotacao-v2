package com.main.app_cotacao_v2.model.payment;

import java.math.BigDecimal;

public class PaymentPreferenceRequestDto {
    private String titulo;
    private int quantidade;
    private BigDecimal preco;

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
