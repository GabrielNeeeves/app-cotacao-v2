package com.main.app_cotacao_v2.model.listaPadrao;

import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import com.main.app_cotacao_v2.service.escola.EscolaService;
import jakarta.persistence.*;
import lombok.*;

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
    private String serie;                       // 1° A, 2°C, etc

    public ListaPadrao(ListaPadraoDto dto, FuncionarioRepository funcionarioRepository, EscolaService escolaService) {
        funcionario_id = funcionarioRepository.findById(dto.funcionario_id()).get();
        escola_id = escolaService.getEscolaById(dto.escola_id());
        ano_letivo = dto.ano_letivo();
        serie = dto.serie();
    }

    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Funcionario funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public Escola getEscola_id() {
        return escola_id;
    }

    public void setEscola_id(Escola escola_id) {
        this.escola_id = escola_id;
    }

    public Integer getAno_letivo() {
        return ano_letivo;
    }

    public void setAno_letivo(Integer ano_letivo) {
        this.ano_letivo = ano_letivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
