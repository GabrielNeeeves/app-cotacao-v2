package com.main.app_cotacao_v2.model.materialPersonalizado;

import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material_personalizado")
public class MaterialPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_lista_personalizada")
    private ListaPersonalizada listaPersonalizada;

    @Column(nullable = false, length = 200)
    private String nomeMaterial;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(length = 100)
    private String marcaEscolhida;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

