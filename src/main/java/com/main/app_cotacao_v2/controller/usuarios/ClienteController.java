package com.main.app_cotacao_v2.controller.usuarios;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import com.main.app_cotacao_v2.model.usuariosModel.ClienteView;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
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
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteViewRepository clienteViewRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/me")
    public ResponseEntity<?> getClienteLogado(@AuthenticationPrincipal Usuario usuario) {

        var cliente = clienteRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para este usuário"));

        return ResponseEntity.ok(cliente);
    }

    //GET
    @GetMapping
    public List<ClienteView> getAllClientesDetalhes() {
        return clienteViewRepository.findAll();
    }

    //RETORNAR OS ALUNOS DE UM CLIENTE PELO SEU ID
    @GetMapping("/{id}/alunos")
    public List<Aluno> listar(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return cliente.getAlunos();
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
