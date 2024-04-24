package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    @NotBlank(message = "É necessário preencher o nome do cliente no momento do cadastro.")
    private String nome;
    private Boolean desconto;
}
