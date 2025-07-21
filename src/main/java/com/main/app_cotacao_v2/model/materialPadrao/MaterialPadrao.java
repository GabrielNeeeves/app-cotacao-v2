package com.main.app_cotacao_v2.model.materialPadrao;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material_padrao")
public class MaterialPadrao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lista_padrao_id")
    private ListaPadrao listaPadrao;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(length = 200)
    private String observacoes;
}

