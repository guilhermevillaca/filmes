package br.com.villaca.arte.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="login", unique = true)
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name="dataCadastro")
    private LocalDateTime dataCadastro;
}
