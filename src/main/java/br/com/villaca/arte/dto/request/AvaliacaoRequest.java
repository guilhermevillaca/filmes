package br.com.villaca.arte.dto.request;

import java.time.Instant;

import br.com.villaca.arte.model.Obra;
import br.com.villaca.arte.model.Usuario;

public record AvaliacaoRequest(        
        Integer nota,
        String comentario,
        Instant dataAvaliacao,
        Usuario usuario,
        Obra obra) {

}
