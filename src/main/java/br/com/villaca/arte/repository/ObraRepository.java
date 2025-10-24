package br.com.villaca.arte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Obra;

public interface ObraRepository extends JpaRepository<Obra, Integer>{

}
