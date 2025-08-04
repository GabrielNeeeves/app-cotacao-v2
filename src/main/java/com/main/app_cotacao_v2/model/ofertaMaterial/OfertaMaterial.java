package com.main.app_cotacao_v2.model.ofertaMaterial;

import com.main.app_cotacao_v2.model.material.Material;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oferta_material")
public class OfertaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK para Funcionario
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    // FK para Material
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false, precision = 10)
    private Double preco;

    // prazo de entrega em dias
    private Integer prazoEntrega;

    @Column(columnDefinition = "integer default 1")
    private Integer quantidadeMinima = 1;

    private String observacoes;

    public OfertaMaterial(OfertaMaterialDto dto, Funcionario funcionario, Material material) {
        this.funcionario = funcionario;
        this.material = material;
        this.preco = dto.preco();
        this.prazoEntrega = dto.prazoEntrega();
        this.quantidadeMinima = dto.quantidadeMinima() != null ? dto.quantidadeMinima() : 1;
        this.observacoes = dto.observacoes();
    }

    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

