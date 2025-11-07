package br.com.villaca.arte.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.villaca.arte.dto.request.AvaliacaoRequest;
import br.com.villaca.arte.dto.response.AvaliacaoResponse;
import br.com.villaca.arte.model.Avaliacao;

@Mapper(componentModel="spring")
public interface AvaliacaoMapper {

    @Mapping(target = "id", ignore = true)
    Avaliacao toEntity(AvaliacaoRequest avaliacao);

    AvaliacaoResponse toResponseDTO(Avaliacao avaliacao);

    List<AvaliacaoResponse> toResponseList(List<Avaliacao> avaliacaos);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(AvaliacaoRequest dto, @MappingTarget Avaliacao avaliacao);

}
