package br.com.menesic.GranjaApp.common.exception;

import br.com.menesic.GranjaApp.application.config.swagger.APIException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {
    private APIException failedResponse;

    public NotFoundException(ExceptionMessageEnum message, Object... params) {
        super(message.readMessage(params));
        this.failedResponse = APIException.builder()
                .code(message.getCode())
                .message(message.readMessage(params))
                .reason(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
    }
}
