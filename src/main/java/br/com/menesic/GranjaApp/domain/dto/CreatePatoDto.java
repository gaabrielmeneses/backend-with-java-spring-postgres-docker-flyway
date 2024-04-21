package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatoDto {

    private PatoDto pato;
    private PatoDto patoMae;
}
