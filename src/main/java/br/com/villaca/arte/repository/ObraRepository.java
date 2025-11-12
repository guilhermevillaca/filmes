package br.com.villaca.arte.repository;

import br.com.villaca.arte.model.enums.TipoObra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Obra;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ObraRepository extends JpaRepository<Obra, UUID>{

    Page<Obra> findByTipo(TipoObra tipo, Pageable pageable);

    List<Obra> findTop5ByTipo(TipoObra tipo);

    Page<Obra> findByGenero_Id(UUID genero_id, Pageable pageable);

    @Query(value = "SELECT * FROM obra ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
    List<Obra> findRandomObras(int limite);

}
