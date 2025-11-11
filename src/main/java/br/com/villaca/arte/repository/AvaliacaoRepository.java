package br.com.villaca.arte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Avaliacao;

import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID>{

}
