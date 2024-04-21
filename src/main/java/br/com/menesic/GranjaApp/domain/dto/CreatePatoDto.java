package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatoDto {

    @Valid
    private PatoDto pato;
    @Valid
    private PatoDto patoMae;
}
