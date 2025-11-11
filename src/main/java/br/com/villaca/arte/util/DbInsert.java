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

        //Inserindo Generos
        List<Genero> generos = Arrays.asList(
                new Genero(null, "Ação"),
                new Genero(null, "Aventura"),
                new Genero(null, "Comédia"),
                new Genero(null, "Drama"),
                new Genero(null, "Terror"),
                new Genero(null, "Suspense"),
                new Genero(null, "Romance"),
                new Genero(null, "Ficção Científica"),
                new Genero(null, "Fantasia"),
                new Genero(null, "Animação"),
                new Genero(null, "Musical"),
                new Genero(null, "Documentário"),
                new Genero(null, "Biografia"),
                new Genero(null, "Guerra"),
                new Genero(null, "Policial"),
                new Genero(null, "Crime"),
                new Genero(null, "Mistério"),
                new Genero(null, "Histórico"),
                new Genero(null, "Esporte"),
                new Genero(null, "Família"),
                new Genero(null, "Infantil"),
                new Genero(null, "Western"),
                new Genero(null, "Noir"),
                new Genero(null, "Erótico"),
                new Genero(null, "LGBTQIA+"),
                new Genero(null, "Religioso"),
                new Genero(null, "Experimental"),
                new Genero(null, "Curta-metragem"),
                new Genero(null, "Reality Show"),
                new Genero(null, "Talk Show"),
                new Genero(null, "Culinária"),
                new Genero(null, "Moda"),
                new Genero(null, "Viagem"),
                new Genero(null, "Natureza"),
                new Genero(null, "Artes Marciais"),
                new Genero(null, "Super-herói"),
                new Genero(null, "Apocalíptico"),
                new Genero(null, "Pós-apocalíptico"),
                new Genero(null, "Cyberpunk"),
                new Genero(null, "Steampunk"),
                new Genero(null, "Magia"),
                new Genero(null, "Escolar"),
                new Genero(null, "Slice of Life"),
                new Genero(null, "Isekai"),
                new Genero(null, "Mecha"),
                new Genero(null, "Horror Psicológico"),
                new Genero(null, "Thriller"),
                new Genero(null, "Crime Organizado"),
                new Genero(null, "Detetive"),
                new Genero(null, "Mistério Sobrenatural")
        );
        generoRepository.saveAll(generos);
        List<Genero> generosAll = generoRepository.findAll();
        Lorem lorem = new LoremIpsum().getInstance();

        //Inserindo Obra
        Obra obra1 = new Obra(
                null,
                "Duro de Matar",
                lorem.getParagraphs(1, 2),
                1988,
                "https://br.web.img2.acsta.net/medias/nmedia/18/92/25/88/20188922.jpg",
                TipoObra.FILME,
                generosAll.get(new Random().nextInt(generosAll.size())));
        obraRepository.save(obra1);

        //inserindo Obras aleatórias

        Random random = new Random();
        TipoObra[] tipos = TipoObra.values();
        for (int i = 0; i <= 100; i++) {
            //gerando ano randomico
            int ano = 1960 + random.nextInt(2025 - 1960 + 1);
            TipoObra tipoAleatorio = tipos[random.nextInt(tipos.length)];
            Obra obra = new Obra(
                    null,
                    lorem.getTitle(2),
                    lorem.getParagraphs(1, 2),
                    ano,
                    "https://picsum.photos/id/" + random.nextInt(50) + "/400/800",
                    tipoAleatorio,
                    generos.get(new Random().nextInt(generos.size())));
            obraRepository.save(obra);
        }
        List<Obra> obras = obraRepository.findAll();

        //usando lorem
        //Inserindo avaliações

        for (int j = 0; j < 1500; j++) {
            double nota = 1.0 + new Random().nextDouble() * (10.0 - 1.0);
            Avaliacao avaliacao1 = new Avaliacao(null,
                    Math.round(nota * 100) / 100.0,
                    lorem.getParagraphs(1, 3),
                    Instant.now(), usuario1, obras.get(new Random().nextInt(obras.size())));
            avaliacaoRepository.save(avaliacao1);

        }


    }

}
