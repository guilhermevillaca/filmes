package br.com.villaca.arte.service;

import java.util.List;
import java.util.UUID;

import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;
import br.com.villaca.arte.util.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.villaca.arte.dto.mapper.UsuarioMapper;
import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements GenericService<UUID, UsuarioResponse, UsuarioRequest> {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper; 

    public Usuario getByLogin(String login){
        return repository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Este login não existe na base"));
    }

    @Override
    public UsuarioResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com o id " + id));

    }

    @Override
    public List<UsuarioResponse> findAll() {
        var lista = repository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(lista);
    }

    @Override
    public Page<UsuarioResponse> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var entities = repository.findAll(pageable);
        return entities.map(mapper::toResponseDTO);

    }

    @Override
    public UsuarioResponse create(UsuarioRequest request) {
        var entity = mapper.toEntity(request);
        entity.setId(null);
        var salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public UsuarioResponse update(UUID id, UsuarioRequest request) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com o id " + id));

        mapper.updateFromDto(request, entity); // método adicional no mapper
        var salvo = repository.save(entity);
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
