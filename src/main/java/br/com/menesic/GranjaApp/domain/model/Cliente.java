package br.com.menesic.GranjaApp.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Cliente {
    private UUID id;
    private String nome;
    private Boolean desconto;
}
