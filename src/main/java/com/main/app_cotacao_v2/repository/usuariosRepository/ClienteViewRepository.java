package com.main.app_cotacao_v2.repository.usuariosRepository;

import com.main.app_cotacao_v2.model.usuariosModel.ClienteView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteViewRepository extends JpaRepository<ClienteView, Long> {
}
