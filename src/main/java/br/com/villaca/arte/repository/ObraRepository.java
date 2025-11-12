package br.com.villaca.arte.repository;

import br.com.villaca.arte.model.enums.TipoObra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Obra;

import java.util.UUID;

public interface ObraRepository extends JpaRepository<Obra, UUID>{

    Page<Obra> findByTipo(TipoObra tipo, Pageable pageable);

    Page<Obra> findByGenero_Id(UUID genero_id, Pageable pageable);

}
