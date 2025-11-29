package com.main.app_cotacao_v2.controller.inventario;

import com.main.app_cotacao_v2.model.inventario.InventarioModel;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.service.inventario.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<InventarioModel> getInventario(@AuthenticationPrincipal UserDetails userDetails) {
        Long usuarioId = resolveUsuarioId(userDetails);
        return inventarioService.getInventarioByUsuario(usuarioId);
    }

    @PostMapping
    public InventarioModel addItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody InventarioModel itemRequest) {
        Long usuarioId = resolveUsuarioId(userDetails);
        return inventarioService.addItem(usuarioId, itemRequest.getItem_nome(), itemRequest.getQuantidade());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        Long usuarioId = resolveUsuarioId(userDetails);
        inventarioService.deleteItem(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        Long usuarioId = resolveUsuarioId(userDetails);
        inventarioService.deleteItem(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    private Long resolveUsuarioId(UserDetails userDetails) {
        if (userDetails instanceof Usuario usuario) {
            return usuario.getId(); // sua entidade deve ter getId()
        }
        throw new IllegalStateException("Principal não é do tipo Usuario");
    }
}

