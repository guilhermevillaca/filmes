package br.com.villaca.arte.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.villaca.arte.dto.mapper.UsuarioMapper;
import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper; 

    public List<UsuarioResponse> listarTodos() {
        var lista = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(lista);
    }

    public UsuarioResponse getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com o id " + id));
    }

    public UsuarioResponse salvar(UsuarioRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public UsuarioResponse atualizar(Integer id, UsuarioRequest dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com o id " + id));

        mapper.updateFromDto(dto, entity); // método adicional no mapper
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Obra não encontrada com o id " + id);
        }
        repository.deleteById(id); // se der FK, o Handler pega
    }

}
