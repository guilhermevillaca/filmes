package br.com.villaca.arte.service;

import java.util.List;
import java.util.UUID;

import br.com.villaca.arte.dto.response.AvaliacaoResponse;
import br.com.villaca.arte.model.enums.TipoObra;
import br.com.villaca.arte.util.api.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.villaca.arte.dto.mapper.ObraMapper;
import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.repository.ObraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ObraService implements Service<UUID, ObraResponse, ObraRequest> {

    
    private final ObraRepository repository;    
    private final ObraMapper mapper;

    @Override
    public List<ObraResponse> findAll() {
        var obras = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(obras);
    }

    @Override
    public Page<ObraResponse> findAllPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities = repository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);
    }

    public Page<ObraResponse> findAllByTipo(TipoObra tipo, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities = repository.findByTipo(tipo, pageable);
        return entities.map(mapper::toResponseDTO);
    }

    public Page<ObraResponse> findByGenero(UUID genero_id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities =  repository.findByGenero_Id(genero_id, pageable);
        return entities.map(mapper::toResponseDTO);
    }

    @Override
    public ObraResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));
    }

    @Override
    public ObraResponse create(ObraRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public ObraResponse update(UUID id, ObraRequest dto) {
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
