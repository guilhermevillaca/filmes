package br.com.villaca.arte.dto.request;

import br.com.villaca.arte.model.enums.PerfilUsuario;

import java.time.Instant;

public record UsuarioRequest(
        String nome,
        String email,
        String login,
        String senha,
        Instant dataCadastro,
        PerfilUsuario perfil) {
}
