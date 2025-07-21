package com.main.app_cotacao_v2.model.ofertaMaterial;

import com.main.app_cotacao_v2.model.materialPadrao.MaterialPadrao;
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
    @JoinColumn(name = "item_padrao_id")
    private MaterialPadrao itemPadrao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column
    private Integer prazoEntrega; // dias

    @Column
    private Integer quantidadeMinima;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

