package br.com.villaca.arte.dto.request;

import java.time.Instant;

public record UsuarioRequest(        
        String nome,
        String email,
        String login,
        String senha,
        Instant dataCadastro) {
}
