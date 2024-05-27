package br.com.fiap.alerta_cidadao.model;

import br.com.fiap.alerta_cidadao.model.enumerator.TipoCrimeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_crime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Crime {

    @Id
    @Column(name = "crime_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_crime_seq"
    )
    @SequenceGenerator(
            name = "t_crime_seq",
            sequenceName = "t_crime_seq",
            allocationSize = 1
    )
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tp_crime", nullable = false)
    private TipoCrimeEnum tipoCrime;

    @Column(name = "status_ocorrencia")
    private Character status;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "t_localizacao_cep")
    private String cepLocalizacao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "t_localizacao_cep", referencedColumnName = "cep",
    insertable = false, updatable = false)
    private Localizacao localizacao;

}
