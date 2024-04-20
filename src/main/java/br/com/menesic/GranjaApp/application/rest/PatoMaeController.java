package br.com.menesic.GranjaApp.application.rest;

import br.com.menesic.GranjaApp.application.config.swagger.ApiOperation;
import br.com.menesic.GranjaApp.domain.dto.PatoMaeDto;
import br.com.menesic.GranjaApp.domain.model.PatoMae;
import br.com.menesic.GranjaApp.domain.port.usecase.patomae.CreatePatoMaeUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "Endpoint Pato-Mae")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pato-mae", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatoMaeController {

    private final CreatePatoMaeUseCase createPatoMaeUseCase;
    private final ModelMapper modelMapper;

    @ApiOperation(
            method = "POST",
            description = "Salvar Pato e Mae(Pato)",
            summary = "Salvar Pato e Mae(Pato)"
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UUID save(@RequestBody @Valid final PatoMaeDto patoMaeDto) {
        return createPatoMaeUseCase.save(modelMapper.map(patoMaeDto, PatoMae.class)).getId();
    }
}
