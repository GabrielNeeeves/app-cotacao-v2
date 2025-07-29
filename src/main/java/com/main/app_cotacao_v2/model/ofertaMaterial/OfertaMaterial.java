package com.main.app_cotacao_v2.model.ofertaMaterial;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oferta_material")
public class OfertaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lista_padrao_id")
    private ListaPadrao listaPadrao;

    @Column(nullable = false, length = 200)
    private String nomeItem; // Nome do item do JSON ao qual a oferta se refere

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    private Integer prazoEntrega; // em dias

    @Column(nullable = false)
    private Integer quantidadeMinima = 1;

    @Column(columnDefinition = "text")
    private String observacoes;

    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ListaPadrao getListaPadrao() {
        return listaPadrao;
    }

    public void setListaPadrao(ListaPadrao listaPadrao) {
        this.listaPadrao = listaPadrao;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

