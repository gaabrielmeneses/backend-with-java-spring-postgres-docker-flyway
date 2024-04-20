package br.com.menesic.GranjaApp.domain.enums;

import java.math.BigDecimal;

public enum ValorPatoEnum {
    VALOR_PATO_FILHO(BigDecimal.valueOf(70.00)),
    VALOR_PATO_MAE(BigDecimal.valueOf(50.00));
    private final BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    ValorPatoEnum(BigDecimal valor) {
        this.valor = valor;
    }
}
