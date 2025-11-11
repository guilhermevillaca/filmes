package br.com.villaca.arte.model;

import java.time.Instant;
import java.util.UUID;

import br.com.villaca.arte.model.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="login", unique = true)
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name="dataCadastro")
    private Instant dataCadastro;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;


}
