package br.com.villaca.arte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.villaca.arte.dto.mapper.ObraMapper;
import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.repository.ObraRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ObraService {

    @Autowired
    ObraRepository obraRepository;
    @Autowired
    ObraMapper mapper;

    public List<ObraResponse> listarTodos() {
        var obras = obraRepository.findAll(Sort.by("id").ascending());
        return mapper.toResponseList(obras);
    }

    public ObraResponse getById(Integer id) {
        return obraRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));
    }

    public ObraResponse salvar(ObraRequest obra) {
        var entity = mapper.toEntity(obra);
        entity.setId(null);
        var salvo = obraRepository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ObraResponse atualizar(Integer id, ObraRequest dto) {
        var obra = obraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com o id " + id));

        mapper.updateFromDto(dto, obra); // método adicional no mapper
        var salvo = obraRepository.save(obra);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Integer id) {
        if (!obraRepository.existsById(id)) {
            throw new EntityNotFoundException("Obra não encontrada com o id " + id);
        }
        obraRepository.deleteById(id); // se der FK, o Handler pega
    }
}
