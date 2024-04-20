package br.com.menesic.GranjaApp.common.exception;

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
public class BadRequestException extends RuntimeException {
    private APIException failedResponse;

    public BadRequestException(ExceptionMessageEnum message, Object... params) {
        super(message.readMessage(params));
        this.failedResponse = APIException.builder()
                .code(message.getCode())
                .message(message.readMessage(params))
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }

    public BadRequestException(ExceptionMessageEnum message, HttpStatus status, Object... params) {
        super(message.readMessage(params));
        this.failedResponse = APIException.builder()
                .code(message.getCode())
                .message(message.readMessage(params))
                .reason(status.getReasonPhrase())
                .build();
    }

}
