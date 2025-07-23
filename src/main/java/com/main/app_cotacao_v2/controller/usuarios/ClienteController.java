package com.main.app_cotacao_v2.controller.usuarios;

import com.main.app_cotacao_v2.model.usuariosModel.ClienteView;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteViewRepository clienteViewRepository;

    //GET
    @GetMapping
    public List<ClienteView> getAllClientesDetalhes() {
        return clienteViewRepository.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteView> getClienteById(@PathVariable Long id) {
        Optional<ClienteView> cliente = clienteViewRepository.findById(id);
        return ResponseEntity.ok(cliente.get());
    }

}
