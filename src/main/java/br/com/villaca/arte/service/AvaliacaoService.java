package br.com.villaca.arte.service;

import java.util.List;
import java.util.UUID;

import br.com.villaca.arte.util.api.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.villaca.arte.dto.mapper.AvaliacaoMapper;
import br.com.villaca.arte.dto.request.AvaliacaoRequest;
import br.com.villaca.arte.dto.response.AvaliacaoResponse;
import br.com.villaca.arte.repository.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AvaliacaoService implements Service<UUID, AvaliacaoResponse, AvaliacaoRequest> {

    private final AvaliacaoRepository repository;    
    private final AvaliacaoMapper mapper;

    @Override
    public List<AvaliacaoResponse> findAll() {
        var obras = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(obras);
    }

    @Override
    public Page<AvaliacaoResponse> findAllPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities = repository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);
    }

    @Override
    public AvaliacaoResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com o id " + id));
    }

    @Override
    public AvaliacaoResponse create(AvaliacaoRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public AvaliacaoResponse update(UUID id, AvaliacaoRequest dto) {
        var obra = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com o id " + id));

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

    public Page<AvaliacaoResponse> findByObra(UUID obra_id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities =  repository.findByObra_Id(obra_id, pageable);
        return entities.map(mapper::toResponseDTO);
    }

}
