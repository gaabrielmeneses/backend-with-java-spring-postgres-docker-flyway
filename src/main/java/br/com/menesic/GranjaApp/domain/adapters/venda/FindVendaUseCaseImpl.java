package br.com.menesic.GranjaApp.domain.adapters.venda;

import br.com.menesic.GranjaApp.domain.enums.StatusPatoEnum;
import br.com.menesic.GranjaApp.domain.enums.TipoArquivoEnum;
import br.com.menesic.GranjaApp.domain.enums.TipoClienteEnum;
import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.venda.FindVendaUseCase;
import br.com.menesic.GranjaApp.domain.utils.ExcelExporter;
import br.com.menesic.GranjaApp.domain.utils.ExcelToPdfConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.DocumentFormatException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindVendaUseCaseImpl implements FindVendaUseCase {

    private final VendaRepository vendaRepository;

    @Override
    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    @Override
    public byte[] downloadReport(String tipoArquivo) throws IOException, DocumentFormatException {
        List<Map<String, String>> jsonList = reportData();
        byte[] xls = ExcelExporter.getXlsxReport(jsonList);
        if(TipoArquivoEnum.XLS.getTipoArquivo().equalsIgnoreCase(tipoArquivo)) {
            return xls;
        }
        return ExcelToPdfConverter.convertXlsToPdf(xls);
    }

    private List<Map<String, String>> reportData() {
        List<Map<String, String>> jsonList = new ArrayList<>();
        findAll().stream().forEach(venda -> {
            Map<String, String> json = new HashMap<>();
            json.put("nome", venda.getPato().getNome());
            json.put("status", venda.getPato().getVendido() ? StatusPatoEnum.VENDIDO.getStatus():StatusPatoEnum.DISPONIVEL.getStatus());
            json.put("cliente", venda.getCliente().getNome());
            json.put("tipo_do_cliente", venda.getCliente().getDesconto() ? TipoClienteEnum.COM_DESCONTO.getTipo():TipoClienteEnum.SEM_DESCONTO.getTipo());
            json.put("valor", "R$ " + venda.getValor().toString());
            jsonList.add(json);
        });
        return jsonList;
    }
}
