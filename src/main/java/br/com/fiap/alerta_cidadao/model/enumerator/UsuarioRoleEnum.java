package br.com.fiap.alerta_cidadao.model.enumerator;

public enum UsuarioRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UsuarioRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
