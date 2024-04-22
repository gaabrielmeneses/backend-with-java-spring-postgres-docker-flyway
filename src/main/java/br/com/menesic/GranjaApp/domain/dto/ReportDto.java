package br.com.menesic.GranjaApp.domain.dto;

import br.com.menesic.GranjaApp.domain.enums.StatusPatoEnum;
import br.com.menesic.GranjaApp.domain.enums.TipoClienteEnum;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.model.Pato;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDto {
    private String nome;
    private String  status;
    private String cliente;
    private String tipoCliente;
    private String valor;


    public static ReportDto builderLineReport(Cliente cliente, Pato pato, String valor) {
        return builder()
                .nome(pato.getNome())
                .status(pato.getVendido() ? StatusPatoEnum.VENDIDO.getStatus():StatusPatoEnum.DISPONIVEL.getStatus())
                .cliente(cliente.getNome())
                .tipoCliente(cliente.getDesconto() ? TipoClienteEnum.COM_DESCONTO.getTipo():TipoClienteEnum.SEM_DESCONTO.getTipo())
                .valor(valor)
                .build();
    }
}
