package br.com.menesic.GranjaApp.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatoMaeDto {
        @NotNull
        private PatoDto pato;
        @NotNull
        private PatoDto mae;
}
