package br.com.villaca.arte.dto.request;

import java.time.Instant;

public record AvaliacaoRequest(
        Double nota,
        String comentario,
        Instant dataAvaliacao,
        UsuarioRequest usuario,
        ObraRequest obra) {

}
