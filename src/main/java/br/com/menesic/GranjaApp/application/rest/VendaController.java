package br.com.menesic.GranjaApp.application.rest;

import br.com.menesic.GranjaApp.application.config.swagger.ApiOperation;
import br.com.menesic.GranjaApp.domain.dto.FindAllVendaDto;
import br.com.menesic.GranjaApp.domain.dto.VendaDto;
import br.com.menesic.GranjaApp.domain.ports.usecase.venda.CreateVendaUseCase;
import br.com.menesic.GranjaApp.domain.ports.usecase.venda.FindVendaUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Controller Pato")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/venda", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendaController {

    private final CreateVendaUseCase createVendaUseCase;
    private final FindVendaUseCase findVendaUseCase;
    private final ModelMapper modelMapper;

    @ApiOperation(
            method = "POST",
            description = "Registrar venda",
            summary = "Registrar venda"
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid final VendaDto vendaDto) {
        createVendaUseCase.save(vendaDto);
    }

    @ApiOperation(
            method = "POST",
            description = "Buscar todas as vendas",
            summary = "Buscar todas as vendas"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<FindAllVendaDto> findAll() {
        return List.of(modelMapper.map(findVendaUseCase.findAll(), FindAllVendaDto[].class));
    }
}
