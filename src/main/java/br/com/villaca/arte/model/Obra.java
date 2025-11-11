package br.com.villaca.arte.model;

import br.com.villaca.arte.model.enums.TipoObra;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "obra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Obra extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @Column(length = 1000)
    private String descricao;

    private Integer anoLancamento;

    private String imagemUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoObra tipo;

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    private Genero genero;

}
