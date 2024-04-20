package br.com.menesic.GranjaApp.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum ExceptionMessageEnum {
    ERRO_INESPERADO("ERR-INESP-01", "erro_inesperado"),
    FALHA_VALIDACAO_CAMPO("EXC-VALID-02", "falha_validacao_no_campo"),
    FORMATO_DE_ARQUIVO_INVALIDO("FINV-EXC-00", "formato_de_arquivo_invalido"),
    ELEMENTO_NAO_ENCONTRADO("RELM-EXC-00", "falha_elemento_nao_encontrado"),
    ELEMENTO_JA_EXISTE("RELM-EXC-01", "falha_elemento_ja_cadastrado"),
    DADOS_INVALIDOS_ARQUIVO("DAD-INV-ERR-01", "dados_arquivo_invalidos"),
    ARQUIVO_NAO_ENCONTRADO("ARQ-NAE-ERR-01", "arquivo_nao_encontrado"),
    CONVERSAO_TIPO_ERRO("CONV-TIP-ERR-01", "conversao_tipo_erro"),
    CAMPOS_COM_ERRO_FORMULARIO_ENVIADO("CAM-ERR-FORM-00", "campos_com_erro_formulario_enviado");

    private static MessageSource messageSource;
    private final String code;
    private final String message;

    public String readMessage(Object... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        String message = resourceBundle.getString(this.message);
        return ArrayUtils.isEmpty(params)
                ? message
                : formatPattern(message, params);
    }

    private String formatPattern(String pattern, Object[] stringArguments) {
        String[] placeholders = (pattern + " ").split("\\{\\}");
        return IntStream.range(0, Math.min(stringArguments.length, placeholders.length))
                .mapToObj(i -> placeholders[i] + stringArguments[i])
                .collect(Collectors.joining()) + placeholders[placeholders.length - 1];
    }

}
