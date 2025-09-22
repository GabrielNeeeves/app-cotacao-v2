package com.main.app_cotacao_v2.model.listaPadrao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Table(name = "vw_listas_padrao_completas")
@Entity
@Getter
@Setter
@Immutable
public class ListaPadraoView {

    @Id
    @Column(name = "lista_padrao_id")
    private Long listaPadraoId;

    @Column(name = "funcionario_id")
    private Long funcionarioId;

    @Column(name = "funcionario_nome")
    private String funcionarioNome;

    @Column(name = "escola_id")
    private Long escolaId;

    @Column(name = "escola_nome")
    private String escolaNome;

    @Column(name = "ano_letivo")
    private Integer anoLetivo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "materiais")
    private String materiais;

    public Long getListaPadraoId() {
        return listaPadraoId;
    }

    public void setListaPadraoId(Long listaPadraoId) {
        this.listaPadraoId = listaPadraoId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public Long getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(Long escolaId) {
        this.escolaId = escolaId;
    }

    public String getEscolaNome() {
        return escolaNome;
    }

    public void setEscolaNome(String escolaNome) {
        this.escolaNome = escolaNome;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }
}
