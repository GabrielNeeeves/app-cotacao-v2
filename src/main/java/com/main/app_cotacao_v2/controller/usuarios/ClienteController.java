package com.main.app_cotacao_v2.controller.usuarios;

import com.main.app_cotacao_v2.model.usuariosModel.ClienteView;
import com.main.app_cotacao_v2.model.usuariosModel.dto.ClienteDto;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteViewRepository;
import com.main.app_cotacao_v2.service.usuariosService.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteViewRepository clienteViewRepository;

    @Autowired
    private ClienteService clienteService;

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

    //POST
    @PostMapping
    public ResponseEntity<String> cadastrarClienteComUsuario(@RequestBody ClienteDto dto) {
        return clienteService.cadastrarClienteComUsuario(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long id) {
        try {
            clienteService.deleteClienteById(id);
            return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar cliente: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

}
