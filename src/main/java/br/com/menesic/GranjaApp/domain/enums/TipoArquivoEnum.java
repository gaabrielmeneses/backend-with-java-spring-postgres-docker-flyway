package br.com.menesic.GranjaApp.domain.enums;

import java.math.BigDecimal;

public enum TipoArquivoEnum {
    XLS("xls"),
    PDF("pdf");
    private final String tipoArquivo;

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    TipoArquivoEnum(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }
}
