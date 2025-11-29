package com.main.app_cotacao_v2.service.inventario;

import com.main.app_cotacao_v2.model.inventario.InventarioModel;
import com.main.app_cotacao_v2.repository.inventarioRepository.InventarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioModel> getInventarioByUsuario(Long usuario_id) {
        return inventarioRepository.findByUsuarioId(usuario_id);
    }

    public InventarioModel addItem(Long usuarioId, String itemNome, Integer quantidade) {
        InventarioModel item = new InventarioModel();
        item.setUsuarioId(usuarioId);
        item.setItem_nome(itemNome);
        item.setQuantidade(quantidade);
        return inventarioRepository.save(item);
    }

    public void updateItem(Long id, Long usuarioId) {
        InventarioModel item = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));


    }

    public void deleteItem(Long id, Long usuarioId) {
        InventarioModel item = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        if (!item.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Você não pode excluir itens de outro usuário");
        }
        inventarioRepository.delete(item);
    }
}

