package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.dto.Input.InputUsuario;
import br.com.fiap.alerta_cidadao.dto.UsuarioDto;
import br.com.fiap.alerta_cidadao.model.Usuario;
import br.com.fiap.alerta_cidadao.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDto salvarUsuario(InputUsuario inputUsuario) {

        String senhaCriptografada =
                new BCryptPasswordEncoder().encode(inputUsuario.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(inputUsuario, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = repository.save(usuario);

        return new UsuarioDto(usuario);
    }
}
