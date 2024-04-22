package br.com.menesic.GranjaApp.domain.enums;

public enum TipoClienteEnum {
    COM_DESCONTO("com Desconto"),
    SEM_DESCONTO("sem Desconto");
    private final String tipo;

    public String getTipo() {
        return tipo;
    }

    TipoClienteEnum(String tipo) {
        this.tipo = tipo;
    }
}
