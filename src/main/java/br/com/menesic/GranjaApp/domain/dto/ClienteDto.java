package br.com.menesic.GranjaApp.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteDto {
    @NotBlank(message = "É necessário preencher o nome do cliente no momento do cadastro.")
    private String nome;
    private Boolean desconto;
}
