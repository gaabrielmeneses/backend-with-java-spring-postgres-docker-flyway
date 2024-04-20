package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatoDto {

    @NotBlank(message = "É necessário indicar o nome do pato no momento do cadastro.")
    private String nome;
    @NotBlank(message = "É necessário indicar o valor do pato no momento do cadastro.")
    private BigDecimal valor;
    private Boolean vendido;
    private Boolean mae;
}
