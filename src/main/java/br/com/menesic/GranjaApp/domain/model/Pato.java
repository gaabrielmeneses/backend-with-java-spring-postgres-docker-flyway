package br.com.menesic.GranjaApp.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Pato {
    private UUID id;
    private String nome;
    private BigDecimal valor;
    private Boolean vendido;
    private Boolean mae;
}
