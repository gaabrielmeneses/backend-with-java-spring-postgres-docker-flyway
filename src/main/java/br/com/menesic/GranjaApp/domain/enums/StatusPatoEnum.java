package br.com.menesic.GranjaApp.domain.enums;

public enum StatusPatoEnum {
    VENDIDO("Vendido"),
    DISPONIVEL("Disponível");
    private final String status;

    public String getStatus() {
        return status;
    }

    StatusPatoEnum(String status) {
        this.status = status;
    }
}
