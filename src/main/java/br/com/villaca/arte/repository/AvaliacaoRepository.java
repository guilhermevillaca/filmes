package br.com.villaca.arte.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Avaliacao;


import java.util.List;
import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID>{

    Page<Avaliacao> findByObra_Id(UUID idObra, Pageable pageable);

}
