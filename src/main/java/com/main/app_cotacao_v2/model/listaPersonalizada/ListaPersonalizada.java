package com.main.app_cotacao_v2.model.listaPersonalizada;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.material.MaterialDto;
import com.main.app_cotacao_v2.model.material.MaterialPadraoDto;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista_personalizada")
public class ListaPersonalizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lista_padrao_id")
    private ListaPadrao listaPadrao;

    @JdbcTypeCode(SqlTypes.JSON) // Informa ao Hibernate para tratar este campo como JSON
    @Column(name = "materiais", columnDefinition = "jsonb", nullable = false) // Mantém a definição da coluna para o DDL
    private List<MaterialDto> materiais;

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public ListaPadrao getListaPadrao() {
        return listaPadrao;
    }

    public void setListaPadrao(ListaPadrao listaPadrao) {
        this.listaPadrao = listaPadrao;
    }

    public List<MaterialDto> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialDto> materiais) {
        this.materiais = materiais;
    }
}
