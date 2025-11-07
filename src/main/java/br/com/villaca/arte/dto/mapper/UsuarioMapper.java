package br.com.villaca.arte.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // De request para entidade
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", expression = "java(java.time.Instant.now())")
    Usuario toEntity(UsuarioRequest dto);

    // De entidade para response
    UsuarioResponse toResponseDTO(Usuario usuario);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(UsuarioRequest dto, @MappingTarget Usuario entity);

}