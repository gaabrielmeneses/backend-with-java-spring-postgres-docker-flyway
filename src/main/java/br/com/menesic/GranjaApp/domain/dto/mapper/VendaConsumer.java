package br.com.menesic.GranjaApp.domain.dto.mapper;

import br.com.menesic.GranjaApp.domain.enums.StatusPatoEnum;
import br.com.menesic.GranjaApp.domain.enums.TipoClienteEnum;
import br.com.menesic.GranjaApp.domain.model.Venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VendaConsumer implements Consumer<Venda> {

    private List<Map<String, String>> jsonList;

    public VendaConsumer() {
        this.jsonList = new ArrayList<>();
    }

    @Override
    public void accept(Venda venda) {
        Map<String, String> json = new HashMap<>();
        json.put("nome", venda.getPato().getNome());
        json.put("status", venda.getPato().getVendido() ? StatusPatoEnum.VENDIDO.getStatus() : StatusPatoEnum.DISPONIVEL.getStatus());
        json.put("cliente", venda.getCliente().getNome());
        json.put("tipoDoCliente", venda.getCliente().getDesconto() ? TipoClienteEnum.COM_DESCONTO.getTipo() : TipoClienteEnum.SEM_DESCONTO.getTipo());
        json.put("valor", "R$ " + venda.getValor().toString());
        jsonList.add(json);
    }

    public List<Map<String, String>> getJsonList() {
        return jsonList;
    }
}

