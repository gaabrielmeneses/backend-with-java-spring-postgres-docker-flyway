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
    private Pato mae;

    public static Pato builderMae(String nome) {
        return builder()
                .nome(nome)
                .valor(ValorPatoEnum.VALOR_PATO_MAE.getValor())
                .vendido(false)
                .mae(null)
                .build();
    }

    public static Pato builderFilho(String nome, Pato mae) {
        return builder()
                .nome(nome)
                .valor(ValorPatoEnum.VALOR_PATO_MAE.getValor())
                .vendido(false)
                .mae(mae)
                .build();
    }
}
