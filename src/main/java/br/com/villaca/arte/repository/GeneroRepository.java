package br.com.villaca.arte.repository;

import br.com.villaca.arte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villaca.arte.model.Genero;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface GeneroRepository extends JpaRepository<Genero, UUID>{

}
