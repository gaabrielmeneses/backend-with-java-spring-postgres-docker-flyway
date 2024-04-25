package br.com.menesic.GranjaApp.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatoDto {

    @NotBlank(message = "É necessário preencher o nome do pato no momento do cadastro.")
    private String nome;
    private BigDecimal valor;
    private Boolean vendido;
    private PatoDto mae;
}
