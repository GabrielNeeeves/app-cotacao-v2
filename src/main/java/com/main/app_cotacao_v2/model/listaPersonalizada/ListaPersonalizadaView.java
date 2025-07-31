package com.main.app_cotacao_v2.model.listaPersonalizada;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "vw_listas_personalizadas")
@Entity
@Getter
@Setter
public class ListaPersonalizadaView {

    @Id
    @Column(name = "lista_personalizada_id")
    private Long lista_personalizada_id;

    @Column(name = "cliente_id")
    private Long cliente_id;

    @Column(name = "aluno_id")
    private Long aluno_id;

    @Column(name = "aluno_nome")
    private String aluno_nome;

    @Column(name = "lista_padrao_id")
    private Long lista_padrao_id;

    @Column(name = "ano_letivo")
    private Integer ano_letivo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "materiais")
    private String materiais;

}
