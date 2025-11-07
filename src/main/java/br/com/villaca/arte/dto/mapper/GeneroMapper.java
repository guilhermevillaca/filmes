package br.com.villaca.arte.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.villaca.arte.dto.request.GeneroRequest;
import br.com.villaca.arte.dto.response.GeneroResponse;
import br.com.villaca.arte.model.Genero;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    @Mapping(target = "id", ignore = true)
    Genero toEntity(GeneroRequest genero);

    GeneroResponse toResponseDTO(Genero genero);

    List<GeneroResponse> toResponseList(List<Genero> generos);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(GeneroRequest dto, @MappingTarget Genero entity);

}
