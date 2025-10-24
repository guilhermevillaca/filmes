package br.com.villaca.arte.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.model.Obra;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.GeneroRepository;
import br.com.villaca.arte.repository.ObraRepository;
import br.com.villaca.arte.repository.UsuarioRepository;

public class DbInsert implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    ObraRepository obraRepository;

    @Override
    public void run(String... args) throws Exception {

        //Inserindo Usuário
        Usuario usuario1 = new Usuario(null, 
        "Guilherme", 
        "guidvillaca@gmail.com",
        "123", 
        LocalDateTime.now());
        usuarioRepository.save(usuario1);

        //Inserindo Genero
        Genero genero1 = new Genero(null, "Ação");
        generoRepository.save(genero1);
        Genero genero2 = new Genero(null, "Comédia");
        generoRepository.save(genero2);
        Genero genero3 = new Genero(null, "Drama");
        generoRepository.save(genero3);

        //Inserindo com List
        Genero genero4 = new Genero(null, "Suspense");
        Genero genero5 = new Genero(null, "Terror");
        Genero genero6 = new Genero(null, "Animação");
        List<Genero> generos = Arrays.asList(genero4, genero5, genero6);
        generoRepository.saveAll(generos);


        //Inserindo Obra
        //Obra obra1 = new Obra(null, null, null, null, null, null, null)


        
    }

}
