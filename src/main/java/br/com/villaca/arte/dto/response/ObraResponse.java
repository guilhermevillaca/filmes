package br.com.villaca.arte.dto.response;

import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.model.enums.TipoObra;

import java.util.UUID;

public record ObraResponse(
        UUID id,
        String titulo,
        String descricao,
        Integer anoLancamento,
        String imagemUrl,
        TipoObra tipo,
        GeneroResponse genero) {

}
