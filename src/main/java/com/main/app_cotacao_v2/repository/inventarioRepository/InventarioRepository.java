package com.main.app_cotacao_v2.repository.inventarioRepository;

import com.main.app_cotacao_v2.model.inventario.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventarioRepository extends JpaRepository<InventarioModel, Long> {

    List<InventarioModel> findByUsuarioId(Long usuario_id);
}

