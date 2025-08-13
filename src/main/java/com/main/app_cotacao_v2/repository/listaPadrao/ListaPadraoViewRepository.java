package com.main.app_cotacao_v2.repository.listaPadrao;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaPadraoViewRepository extends JpaRepository<ListaPadraoView, Long> {

    List<ListaPadraoView> findByEscolaNome(String escolaNome);
}
