package br.com.menesic.GranjaApp.domain.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatoDto {

    @Valid
    private PatoDto pato;
    @Valid
    private PatoDto patoMae;
}
