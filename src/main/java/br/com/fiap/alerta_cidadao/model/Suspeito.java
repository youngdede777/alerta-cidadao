package br.com.fiap.alerta_cidadao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_suspeito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Suspeito {

    @Id
    @Column(name = "id_suspeito")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_suspeito_seq"
    )
    @SequenceGenerator(
            name = "t_suspeito_seq",
            sequenceName = "t_suspeito_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private Character genero;

    @Column(name = "dt_nasc")
    private LocalDate dataNascimento;

    @Column(name = "crime_id")
    private Long crimeId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "t_localizacao_cep", referencedColumnName = "cep",
            insertable = false, updatable = false)
    private Localizacao localizacao;
}
