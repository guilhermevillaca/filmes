package br.com.villaca.arte.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.villaca.arte.dto.mapper.GeneroMapper;
import br.com.villaca.arte.dto.request.GeneroRequest;
import br.com.villaca.arte.dto.response.GeneroResponse;
import br.com.villaca.arte.repository.GeneroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository repository;    
    private final GeneroMapper mapper;

    public List<GeneroResponse> listarTodos() {
        var obras = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(obras);
    }

    public GeneroResponse getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));
    }

    public GeneroResponse salvar(GeneroRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public GeneroResponse atualizar(Integer id, GeneroRequest dto) {
        var obra = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));

        mapper.updateFromDto(dto, obra); // método adicional no mapper
        var salvo = repository.save(obra);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Obra não encontrada com o id " + id);
        }
        repository.deleteById(id); // se der FK, o Handler pega
    }

}
