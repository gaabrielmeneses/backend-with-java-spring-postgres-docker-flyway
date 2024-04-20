package br.com.menesic.GranjaApp.application.config.swagger;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@SuperBuilder
public class APIException {
    @Schema(description = "Indica o status da resposta (sucesso ou erro)")
    private String code;

    @Schema(description = "Razão indicando a causa do resultado esperado")
    private String reason;

    @Schema(description = "Mensagem indicando o resultado da operação, seja sucesso ou erro")
    private String message;

    @Builder.Default
    @Schema(description = "Lista de erros contendo os objetos com linha e a descrição do erro")
    private Collection<APIException> errors = new ArrayList<>();
}