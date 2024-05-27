package br.com.fiap.alerta_cidadao.model;

import br.com.fiap.alerta_cidadao.model.enumerator.TipoEmergenciaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_emergencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Emergencia {

    @Id
    @Column(name = "id_emergencia")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_emergencia_seq"
    )
    @SequenceGenerator(
            name = "t_emergencia_seq",
            sequenceName = "t_emergencia_seq",
            allocationSize = 1
    )
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tp_emergencia", nullable = false)
    private TipoEmergenciaEnum tipoEmergencia;

    @Column(name = "status")
    private Character status;

    @Column(name = "dt_inicio")
    private LocalDate dataInicio;

    @Column(name = "dt_fim")
    private LocalDate dataFim;

    @Column(name = "t_localizacao_cep")
    private String cepLocalizacao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "t_localizacao_cep", referencedColumnName = "cep",
            insertable = false, updatable = false)
    private Localizacao localizacao;
}
