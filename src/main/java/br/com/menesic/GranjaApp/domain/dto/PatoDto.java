package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatoDto {

    @NotBlank(message = "É necessário preencher o nome do pato no momento do cadastro.")
    private String nome;
    private BigDecimal valor;
    private Boolean vendido;
}
