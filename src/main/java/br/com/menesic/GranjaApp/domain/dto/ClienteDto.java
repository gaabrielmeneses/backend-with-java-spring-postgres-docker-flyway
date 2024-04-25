package br.com.menesic.GranjaApp.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    @NotBlank(message = "É necessário preencher o nome do cliente no momento do cadastro.")
    private String nome;
    private Boolean desconto;
}
