package br.com.villaca.arte.service;

import java.util.List;
import java.util.UUID;

import br.com.villaca.arte.util.api.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.villaca.arte.dto.mapper.GeneroMapper;
import br.com.villaca.arte.dto.request.GeneroRequest;
import br.com.villaca.arte.dto.response.GeneroResponse;
import br.com.villaca.arte.repository.GeneroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class GeneroService implements Service<UUID, GeneroResponse, GeneroRequest> {

    private final GeneroRepository repository;    
    private final GeneroMapper mapper;

    @Override
    public List<GeneroResponse> findAll() {
        var obras = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(obras);
    }

    @Override
    public Page<GeneroResponse> findAllPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities = repository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);
    }

    @Override
    public GeneroResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));
    }

    @Override
    public GeneroResponse create(GeneroRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public GeneroResponse update(UUID id, GeneroRequest dto) {
        var obra = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));

        mapper.updateFromDto(dto, obra); // método adicional no mapper
        var salvo = repository.save(obra);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Obra não encontrada com o id " + id);
        }
        repository.deleteById(id); // se der FK, o Handler pega
    }

}
