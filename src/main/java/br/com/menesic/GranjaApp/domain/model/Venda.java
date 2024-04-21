package br.com.menesic.GranjaApp.domain.model;

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
public class Venda {
    private UUID id;
    private Cliente cliente;
    private Pato pato;
    private BigDecimal valor;

    public static Venda builderVenda(Cliente cliente, Pato pato) {
        return builder()
                .valor(cliente.getDesconto() ? pato.getValor().subtract(pato.getValor().multiply(BigDecimal.valueOf(0.20))):pato.getValor())
                .cliente(cliente)
                .pato(pato)
                .build();
    }
}
