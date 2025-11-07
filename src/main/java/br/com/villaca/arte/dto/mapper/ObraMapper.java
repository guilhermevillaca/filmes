package br.com.villaca.arte.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.model.Obra;

@Mapper(componentModel = "spring")
public interface ObraMapper {

    ObraMapper INSTANCE = Mappers.getMapper(ObraMapper.class);

    // request para entidade
    @Mapping(target = "id", ignore = true)
    Obra toEntity(ObraRequest dto);

    ObraResponse toResponseDTO(Obra obra);

    List<ObraResponse> toResponseList(List<Obra> obras);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(ObraRequest dto, @MappingTarget Obra entity);
}
