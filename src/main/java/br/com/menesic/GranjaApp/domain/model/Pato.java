package br.com.menesic.GranjaApp.domain.model;

import br.com.menesic.GranjaApp.domain.enums.ValorPatoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pato {
    private UUID id;
    private String nome;
    private BigDecimal valor;
    private Boolean vendido;
    private Boolean mae;

    public static Pato builderMae(String nome, Boolean vendido) {

        return builder()
                .nome(nome)
                .valor(ValorPatoEnum.VALOR_PATO_MAE.getValor())
                .vendido(vendido)
                .mae(true)
                .build();
    }

    public static Pato builderFilho(String nome, Boolean vendido) {
        return builder()
                .nome(nome)
                .valor(ValorPatoEnum.VALOR_PATO_FILHO.getValor())
                .vendido(vendido)
                .mae(false)
                .build();
    }
}
