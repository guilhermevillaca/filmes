package br.com.villaca.arte.dto.response;

import java.time.Instant;

import br.com.villaca.arte.model.Obra;
import br.com.villaca.arte.model.Usuario;

public record AvaliacaoResponse(Integer id,
        Integer nota,
        String comentario,
        Instant dataAvaliacao,
        Usuario usuario,
        Obra obra) {

}
