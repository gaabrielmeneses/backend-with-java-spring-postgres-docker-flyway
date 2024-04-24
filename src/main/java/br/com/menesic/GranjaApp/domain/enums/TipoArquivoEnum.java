package br.com.menesic.GranjaApp.domain.enums;

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
