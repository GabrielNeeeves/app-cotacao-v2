package com.main.app_cotacao_v2.model.ofertaMaterialLista;

import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oferta_material_lista")
public class OfertaMaterialLista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lista de IDs de ofertas
    @ElementCollection
    @CollectionTable(
            name = "oferta_material_lista_ofertas",
            joinColumns = @JoinColumn(name = "lista_id")
    )
    @Column(name = "oferta_material_id")
    private List<Long> ofertaIds = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public List<Long> getOfertaIds() {
        return ofertaIds;
    }

    public void setOfertaIds(List<Long> ofertaIds) {
        this.ofertaIds = ofertaIds;
    }

    public Funcionario getFuncionario_id() {
        return funcionario;
    }

    public void setFuncionario_id(Funcionario funcionario_id) {
        this.funcionario = funcionario_id;
    }
}
