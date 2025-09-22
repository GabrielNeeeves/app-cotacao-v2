package com.main.app_cotacao_v2.repository.listaPadrao;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoEscolaExpandidaView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaPadraoEscolaExpandidaViewRepository extends JpaRepository<ListaPadraoEscolaExpandidaView, Long> {


    List<ListaPadraoEscolaExpandidaView> findByEscolaNome(String escolaName);
}
