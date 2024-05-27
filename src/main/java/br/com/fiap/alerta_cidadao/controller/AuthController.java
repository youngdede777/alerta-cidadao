package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.dto.Input.InputUsuario;
import br.com.fiap.alerta_cidadao.dto.LoginDto;
import br.com.fiap.alerta_cidadao.dto.TokenDto;
import br.com.fiap.alerta_cidadao.dto.UsuarioDto;
import br.com.fiap.alerta_cidadao.model.Usuario;
import br.com.fiap.alerta_cidadao.security.TokenService;
import br.com.fiap.alerta_cidadao.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService service;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public TokenDto login(
            @RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha()
                );

        Authentication auth =
                authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return new TokenDto(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto register(@RequestBody @Valid InputUsuario inputUsuario) {
        UsuarioDto usuarioSalvo = null;
        usuarioSalvo = service.salvarUsuario(inputUsuario);

        return usuarioSalvo;
    }
}
