package com.main.app_cotacao_v2.model.inventario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventario_id;

    @Column(name = "usuario_id", nullable = false) // Maps this field to the 'usuario_id' column
    private Long usuarioId; // Renamed to standard Java camelCase

    @Column(nullable = false)
    private String item_nome;

    private Integer quantidade;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getItem_nome() {
        return item_nome;
    }

    public void setItem_nome(String item_nome) {
        this.item_nome = item_nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

