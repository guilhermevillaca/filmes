package br.com.villaca.arte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.villaca.arte.dto.mapper.UsuarioMapper;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;

@Service
public class UsuarioService {

     @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper mapper;

    public List<UsuarioResponse> listarTodos(){
        return mapper.toResponseList(usuarioRepository.findAll());
    }

    public UsuarioResponse getById(Integer id){
        return mapper.toResponseDTO(usuarioRepository.getReferenceById(id));
    }

    public UsuarioResponse salvar(Usuario usuario){
        return mapper.toResponseDTO(usuarioRepository.save(usuario));
    }

    public boolean deletar(Integer id){
        if(!usuarioRepository.existsById(id)){
            return false;
        }
        usuarioRepository.deleteById(id);
        return !usuarioRepository.existsById(id);
    }

}
