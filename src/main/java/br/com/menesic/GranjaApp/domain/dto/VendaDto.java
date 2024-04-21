package br.com.menesic.GranjaApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDto {
    private ClienteDto cliente;
    private List<PatoDto> pato;
}
