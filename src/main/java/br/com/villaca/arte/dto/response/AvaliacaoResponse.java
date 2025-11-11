package br.com.villaca.arte.dto.response;

import java.time.Instant;
import java.util.UUID;

import br.com.villaca.arte.model.Obra;

public record AvaliacaoResponse(UUID id,
                                Double nota,
                                String comentario,
                                Instant dataAvaliacao,
                                UsuarioResponse usuario,
                                ObraResponse obra) {

}
