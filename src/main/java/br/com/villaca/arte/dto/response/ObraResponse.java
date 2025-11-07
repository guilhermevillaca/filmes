package br.com.villaca.arte.dto.response;

import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.model.enums.TipoObra;

public record ObraResponse(
        Integer id,
        String titulo,
        String descricao,
        Integer anoLancamento,
        String imagemUrl,
        TipoObra tipo,
        Genero genero) {

}
