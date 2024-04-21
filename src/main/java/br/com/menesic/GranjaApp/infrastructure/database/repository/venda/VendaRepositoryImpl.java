package br.com.menesic.GranjaApp.infrastructure.database.repository.venda;

import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import br.com.menesic.GranjaApp.infrastructure.database.entity.VendaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VendaRepositoryImpl implements VendaRepository {

    private final VendaDatabaseRepository databaseRepository;
    private final ModelMapper modelMapper;
    @Override
    public Venda save(Venda venda) {
        log.info("[VendaPatoClienteRepositoryImpl] - save - venda - cliente: {} e pato: {}", venda.getCliente().getNome(), venda.getPato().getNome());
        VendaEntity vendaSaved = databaseRepository.save(modelMapper.map(venda, VendaEntity.class));
        return modelMapper.map(vendaSaved, Venda.class);
    }

    @Override
    public List<Venda> findAll() {
        return List.of(modelMapper.map(databaseRepository.findAll(), Venda[].class));
    }
}
