package br.com.fiap.alerta_cidadao.model;

import br.com.fiap.alerta_cidadao.model.enumerator.TipoCrimeEnum;
import br.com.fiap.alerta_cidadao.model.enumerator.TipoRecursoEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_recurso_plc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recurso {

    @Id
    @Column(name = "id_recurso")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_recurso_plc_seq"
    )
    @SequenceGenerator(
            name = "t_recurso_plc_seq",
            sequenceName = "t_recurso_plc_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "total")
    private Long total;

    @Column(name = "disponivel")
    private Long disponivel;

    @Column(name = "t_localizacao_cep")
    private String cepLocalizacao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tp_recurso", nullable = false)
    private TipoRecursoEnum tipoRecurso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "t_localizacao_cep", referencedColumnName = "cep",
            insertable = false, updatable = false)
    private Localizacao localizacao;
}
