package com.main.app_cotacao_v2.model.listaPersonalizada;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, columnDefinition = "jsonb")
    private String materiais; // JSON personalizado

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

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }
}
