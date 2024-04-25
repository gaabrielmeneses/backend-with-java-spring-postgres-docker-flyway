package br.com.menesic.GranjaApp.domain.adapters.venda;

import br.com.menesic.GranjaApp.domain.dto.VendaDto;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.cliente.FindClienteUseCase;
import br.com.menesic.GranjaApp.domain.ports.usecase.pato.FindPatoUseCase;
import br.com.menesic.GranjaApp.domain.ports.usecase.venda.CreateVendaUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateVendaUseCaseImpl implements CreateVendaUseCase {

    private final VendaRepository vendaRepository;
    private final FindPatoUseCase findPatoUseCase;
    private final FindClienteUseCase findClienteUseCase;

    @Override
    public void save(VendaDto vendaDto) {
        log.info("[CreateVendaUseCaseImpl] - save");
        Cliente cliente = findClienteUseCase.findByNome(vendaDto.getCliente().getNome()).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado em nossa base de dados!"));
        vendaDto.getPato().forEach(patoDto -> {
            findPatoUseCase.findByNome(patoDto.getNome()).ifPresentOrElse(
                pato -> {
                    pato.setVendido(true);
                    Venda venda = Venda.builderVenda(cliente, pato);
                    vendaRepository.save(venda);
                    log.info("[CreateVendaUseCaseImpl] - save - venda para o cliente: {} SUCCESS ", cliente.getNome());
                },
                () -> {
                    throw new EntityNotFoundException("Pato não encontrado em nossa base de dados!");
                });
        });
    }
}
