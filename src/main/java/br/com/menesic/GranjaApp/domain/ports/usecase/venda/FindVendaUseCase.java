package br.com.menesic.GranjaApp.domain.ports.usecase.venda;

import br.com.menesic.GranjaApp.domain.model.Venda;

import java.io.IOException;
import java.util.List;

public interface FindVendaUseCase {
    List<Venda> findAll();

    byte[] downloadReport(String tipoArquivo) throws IOException;
}
