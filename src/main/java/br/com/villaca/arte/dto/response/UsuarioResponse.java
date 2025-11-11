package br.com.villaca.arte.dto.response;

import br.com.villaca.arte.model.enums.PerfilUsuario;

import java.time.Instant;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String login,
        Instant dataCadastro,
        PerfilUsuario perfil) {

}
