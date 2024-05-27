package br.com.fiap.alerta_cidadao.model.enumerator;

public enum TipoRecursoEnum {

    VIATURA(0, "Viatura"),
    HELICOPTERO(1, "Helicóptero"),
    MOTO(2, "Moto");

    private Integer codigo;

    private String nomeTipo;

    TipoRecursoEnum(Integer codigo, String nomeTipo) {
        this.codigo = codigo;
        this.nomeTipo = nomeTipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public static TipoRecursoEnum fromCodigo(Integer codigo) {
        for (TipoRecursoEnum tipo : TipoRecursoEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
