package br.com.menesic.GranjaApp.domain.venda;

import br.com.menesic.GranjaApp.domain.adapters.venda.FindVendaUseCaseImpl;
import br.com.menesic.GranjaApp.domain.enums.TipoArquivoEnum;
import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.DocumentFormatException;
import org.jeasy.random.EasyRandom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FindVendaUseCaseImplTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private FindVendaUseCaseImpl findVendaUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        findVendaUseCase = new FindVendaUseCaseImpl(vendaRepository);
    }

    @Test
    public void testFindAll() {
        Venda venda = easyRandom.nextObject(Venda.class);
        when(vendaRepository.findAll()).thenReturn(Collections.singletonList(venda));

        List<Venda> foundVendas = findVendaUseCase.findAll();

        verify(vendaRepository, times(1)).findAll();
        assertEquals(1, foundVendas.size());
        assertEquals(venda, foundVendas.get(0));
    }

    @Test
    public void testDownloadReportXls() throws IOException, DocumentFormatException {
        Venda venda = easyRandom.nextObject(Venda.class);
        List<Venda> vendas = Collections.singletonList(venda);
        when(vendaRepository.findAll()).thenReturn(vendas);

        byte[] report = findVendaUseCase.downloadReport(TipoArquivoEnum.XLS.getTipoArquivo());

        assertNotNull(report);
    }

    @Test
    public void testDownloadReportPdf() throws IOException, DocumentFormatException {
        Venda venda = easyRandom.nextObject(Venda.class);
        List<Venda> vendas = Collections.singletonList(venda);
        when(vendaRepository.findAll()).thenReturn(vendas);

        byte[] report = findVendaUseCase.downloadReport(TipoArquivoEnum.PDF.getTipoArquivo());

        assertNotNull(report);
    }
}