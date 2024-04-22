package br.com.menesic.GranjaApp.application.rest;

import br.com.menesic.GranjaApp.application.config.swagger.APIOperation;
import br.com.menesic.GranjaApp.domain.dto.ClienteDto;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.ports.usecase.cliente.CreateClienteUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "Endpoint Cliente")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    private final CreateClienteUseCase createClienteUseCase;
    private final ModelMapper modelMapper;

    @APIOperation(
            method = "POST",
            description = "Salvar Cliente",
            summary = "Salvar Cliente"
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID save(@RequestBody @Valid final ClienteDto clienteDto) {
        return createClienteUseCase.save(modelMapper.map(clienteDto, Cliente.class)).getId();
    }
}
