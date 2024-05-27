package br.com.fiap.alerta_cidadao.model.enumerator;

public enum TipoCrimeEnum {
    FURTO(0, "Furto"),
    ROUBO(1, "Roubo"),
    HOMICIDIO(2, "Homicidio");

    private Integer codigo;

    private String nomeTipo;

    TipoCrimeEnum(Integer codigo, String nomeTipo) {
        this.codigo = codigo;
        this.nomeTipo = nomeTipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public static TipoCrimeEnum fromCodigo(Integer codigo) {
        for (TipoCrimeEnum tipo : TipoCrimeEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}