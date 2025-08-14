package com.main.app_cotacao_v2.model.listaPadrao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "vw_lista_padrao_escola_expandida")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListaPadraoEscolaExpandidaView {

    @Id
    @Column(name = "lista_id")
    private Integer listaId;

    @Column(name = "ano_letivo")
    private Integer anoLetivo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "escola_nome")
    private String escolaNome;

    @Column(name = "materiais", columnDefinition = "jsonb")
    private String materiais;
}
