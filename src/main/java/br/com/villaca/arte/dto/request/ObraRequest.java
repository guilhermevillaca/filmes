package br.com.villaca.arte.dto.request;

import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.model.enums.TipoObra;


public record ObraRequest(    
    String titulo,    
    String descricao,
    Integer anoLancamento,
    String imagemUrl,
    TipoObra tipo,    
    Genero genero
) {

}
