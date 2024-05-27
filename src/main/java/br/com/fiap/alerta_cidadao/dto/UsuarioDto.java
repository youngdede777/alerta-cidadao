package br.com.fiap.alerta_cidadao.dto;


import br.com.fiap.alerta_cidadao.model.Usuario;
import br.com.fiap.alerta_cidadao.model.enumerator.UsuarioRoleEnum;

public record UsuarioDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRoleEnum role
) {

    public UsuarioDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
