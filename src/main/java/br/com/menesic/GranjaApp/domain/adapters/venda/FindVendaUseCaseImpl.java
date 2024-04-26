package br.com.menesic.GranjaApp.domain.adapters.venda;

import br.com.menesic.GranjaApp.domain.dto.mapper.VendaConsumer;
import br.com.menesic.GranjaApp.domain.enums.TipoArquivoEnum;
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
        VendaConsumer vendaConsumer = new VendaConsumer();
        findAll().forEach(vendaConsumer);
        List<Map<String, String>> dadosRelatorio = vendaConsumer.getJsonList();
        byte[] xls = ExcelExporter.getXlsxReport(dadosRelatorio);
        log.info("Download do relatório executado com sucesso!");
        return TipoArquivoEnum.XLS.getTipoArquivo().equalsIgnoreCase(tipoArquivo) ? xls : ExcelToPdfConverter.convertXlsToPdf(xls);
    }
}
