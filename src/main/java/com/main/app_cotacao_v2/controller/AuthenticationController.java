package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.infra.security.TokenService;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.dto.*;
import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository userRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto dto) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        // Faz autenticação
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Usuário autenticado
        var usuario = (Usuario) auth.getPrincipal();

        // Gera token JWT
        var token = tokenService.generateToken(usuario);

        // Extrai a ROLE do usuário
        String role = usuario.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_CLIENTE");

        // Busca IDs específicos conforme o tipo de usuário
        Long clienteId = clienteRepository.findByUsuarioId(usuario.getId())
                .map(Cliente::getId)
                .orElse(null);

        Long funcionarioId = funcionarioRepository.findByUsuarioId(usuario.getId())
                .map(Funcionario::getId)
                .orElse(null);

        // Retorna tudo junto
        return ResponseEntity.ok(
                new LoginResponseDto(
                        token,
                        role,
                        clienteId,
                        funcionarioId
                )
        );
    }

    // REGISTRAR CLIENTE
    @PostMapping("/register/cliente")
    public ResponseEntity registerCliente(@RequestBody ClienteDto dto) {
        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        this.clienteRepository.cadastrarClienteComUsuario(
                dto.nome(),
                dto.email(),
                encryptedPassword,
                dto.alunoId()
        );
        return ResponseEntity.ok().build();
    }

    // REGISTRAR FUNCIONARIO
    @PostMapping("/register/funcionario")
    public ResponseEntity registerFuncionario(@RequestBody FuncionarioPostDto dto) {

        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        funcionarioRepository.cadastrarFuncionario(
                dto.nome(),
                dto.email(),
                encryptedPassword,
                dto.salario(),
                dto.empresaId(),
                dto.escolaId()
        );
        return ResponseEntity.ok().build();
    }

    //REGISTER ADMINISTRADOR
    @PostMapping("/register/admin")
    public ResponseEntity registerAdmin(@RequestBody AdminPostDto dto) {
        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        this.userRepo.cadastrarUsuario(
                dto.nome(),
                dto.email(),
                encryptedPassword,
                Roles.ADMINISTRADOR.name()
        );
        return ResponseEntity.ok().build();
    }

}
