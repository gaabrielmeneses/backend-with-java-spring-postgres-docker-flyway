package br.com.menesic.GranjaApp.application.rest;

import br.com.menesic.GranjaApp.application.config.swagger.ApiOperation;
import br.com.menesic.GranjaApp.domain.dto.CreatePatoDto;
import br.com.menesic.GranjaApp.domain.dto.PatoDto;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.port.usecase.pato.CreatePatoUseCase;
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

@Tag(name = "Controller Pato")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pato", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatoController {

    private final CreatePatoUseCase createPatoUseCase;
    private final ModelMapper modelMapper;

    @ApiOperation(
            method = "POST",
            description = "Salvar Pato",
            summary = "Salvar Pato e Mae(Pato)"
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PatoDto save(@RequestBody @Valid final CreatePatoDto createPatoDto) {
        return modelMapper.map(createPatoUseCase.save(createPatoDto), PatoDto.class);
    }
}
