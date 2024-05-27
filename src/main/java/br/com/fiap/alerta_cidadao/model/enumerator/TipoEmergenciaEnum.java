package br.com.fiap.alerta_cidadao.model.enumerator;

public enum TipoEmergenciaEnum {

    INCENDIO(0, "Incêndio"),
    INVASAO(1, "Invasão"),
    DESASTRE(2, "Desastre");

    private Integer codigo;

    private String nomeTipo;

    TipoEmergenciaEnum(Integer codigo, String nomeTipo) {
        this.codigo = codigo;
        this.nomeTipo = nomeTipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public static TipoEmergenciaEnum fromCodigo(Integer codigo) {
        for (TipoEmergenciaEnum tipo : TipoEmergenciaEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
