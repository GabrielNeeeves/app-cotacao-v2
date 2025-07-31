package com.main.app_cotacao_v2.model.listaPadrao;

import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.materialPadrao.MaterialPadraoDto;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista_padrao")
public class ListaPadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "escola_id")
    private Escola escola_id;

    @Column(nullable = false)
    private Integer ano_letivo;

    @Column(nullable = false, length = 20)
    private String serie;

    @JdbcTypeCode(SqlTypes.JSON) // Informa ao Hibernate para tratar este campo como JSON
    @Column(name = "materiais", columnDefinition = "jsonb", nullable = false) // Mantém a definição da coluna para o DDL
    private List<MaterialPadraoDto> materiais;

    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario_id;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario_id = funcionario;
    }

    public Escola getEscola() {
        return escola_id;
    }

    public void setEscola(Escola escola) {
        this.escola_id = escola;
    }

    public Integer getAno_letivo() {
        return ano_letivo;
    }

    public void setAno_letivo(Integer anoLetivo) {
        this.ano_letivo = anoLetivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public List<MaterialPadraoDto> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialPadraoDto> materiais) {
        this.materiais = materiais;
    }
}

