package br.com.villaca.arte.util;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.villaca.arte.model.enums.PerfilUsuario;
import br.com.villaca.arte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import br.com.villaca.arte.model.Avaliacao;
import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.model.Obra;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.model.enums.TipoObra;
import br.com.villaca.arte.repository.AvaliacaoRepository;
import br.com.villaca.arte.repository.GeneroRepository;
import br.com.villaca.arte.repository.ObraRepository;

@Component
public class DbInsert implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    ObraRepository obraRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //Inserindo Usuário
        Usuario usuario1 = new Usuario(null,
                "Guilherme",
                "guidvillaca@gmail.com",
                "guilherme.villaca",
                passwordEncoder.encode("g123456"),
                Instant.now(),
                PerfilUsuario.ADMIN);

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

        Lorem lorem = new LoremIpsum().getInstance();

        //Inserindo Obra
        Obra obra1 = new Obra(
                null,
                "Duro de Matar",
                lorem.getParagraphs(1, 2),
                1988,
                "https://br.web.img2.acsta.net/medias/nmedia/18/92/25/88/20188922.jpg",
                TipoObra.FILME,
                genero1);
        obraRepository.save(obra1);

        //inserindo Obras aleatórias

        Random random = new Random();
        for (int i = 0; i <= 50; i++) {
            //gerando ano randomico
            int ano = 1960 + random.nextInt(2025 - 1960 + 1);
            Obra obra = new Obra(
                    null,
                    lorem.getTitle(2),
                    lorem.getParagraphs(1, 2),
                    ano,
                    "https://picsum.photos/id/" + random.nextInt(50) + "/400/800",
                    TipoObra.FILME,
                    genero1);
            obraRepository.save(obra);
        }
        List<Obra> obras = obraRepository.findAll();

        //usando lorem
        //Inserindo avaliações
        for(Obra obra: obras){
            for(int j = 0; j < 10; j++){
                Avaliacao avaliacao1 = new Avaliacao(null,
                        10,
                        lorem.getParagraphs(1, 3),
                        Instant.now(), usuario1, obra);
                avaliacaoRepository.save(avaliacao1);

            }
        }


    }

}
