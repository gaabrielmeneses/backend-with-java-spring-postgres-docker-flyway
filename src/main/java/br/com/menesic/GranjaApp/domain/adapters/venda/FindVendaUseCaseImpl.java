package br.com.menesic.GranjaApp.domain.adapters.venda;

import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.venda.FindVendaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public byte[] downloadReport(String tipoArquivo) {
        return null;
    }
}
