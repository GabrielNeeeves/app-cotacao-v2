package com.main.app_cotacao_v2.model.listaPadrao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "vw_listas_padrao_completas")
@Entity
@Getter
@Setter
public class ListaPadraoView {

    @Id
    @Column(name = "lista_padrao_id")
    private Long lista_padrao_id;

    @Column(name = "funcionario_id")
    private Long funcionario_id;

    @Column(name = "funcionario_nome")
    private String funcionario_nome;

    @Column(name = "escola_id")
    private Long escola_id;

    @Column(name = "escola_nome")
    private String escola_nome;

    @Column(name = "ano_letivo")
    private Integer ano_letivo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "materiais")
    private String materiais;
}
