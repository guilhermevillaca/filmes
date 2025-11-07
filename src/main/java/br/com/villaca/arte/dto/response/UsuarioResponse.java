package br.com.villaca.arte.dto.response;

import java.time.Instant;

public record UsuarioResponse(
        Integer id,
        String nome,
        String email,
        String login,        
        Instant dataCadastro) {

}
