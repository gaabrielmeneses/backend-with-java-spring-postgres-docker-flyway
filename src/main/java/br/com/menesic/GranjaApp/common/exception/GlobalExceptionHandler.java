package br.com.menesic.GranjaApp.common.exception;

import br.com.menesic.GranjaApp.application.config.swagger.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageProvider messageProvider;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<APIException> handleException(Throwable throwable) {
        APIException failedResponse = APIException.builder()
                .code("ERROR-500")
                .message(messageProvider.getMessage(ExceptionMessageEnum.ERRO_INESPERADO))
                .reason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), throwable.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedResponse);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<APIException> handlerServerInputException(ServerWebInputException e) {
        APIException failedResponse = APIException.builder()
                .code("SERV-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIException> handlerServerInputException(BadRequestException e) {
        log.error(String.format("Code: %s Message: %s ", e.getFailedResponse().getCode(), e.getFailedResponse().getMessage()));
        return ResponseEntity.badRequest().body(e.getFailedResponse());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIException> handlerServerInputException(NotFoundException e) {
        log.error(String.format("Code: %s Message: %s ", e.getFailedResponse().getCode(), e.getFailedResponse().getMessage()));
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIException> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        APIException failedResponse = APIException.builder()
                .code("HTTP-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<APIException> handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        APIException failedResponse = APIException.builder()
                .code("MISS-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIException> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        APIException failedResponse = APIException.builder()
                .code("METH-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);

    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<APIException> handleJsonProcessingException(JsonProcessingException e) {
        APIException failedResponse = APIException.builder()
                .code("JSON-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIException> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        APIException failedResponse = APIException.builder()
                .code("HTTP-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        APIException failedResponse = APIException.builder()
                .code("METH-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIException> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("");
        APIException failedResponse = APIException.builder()
                .code("CONS-EXC-00")
                .message(message)
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<APIException> SignatureException(SignatureException e) {
        APIException failedResponse = APIException.builder()
                .code("SING-EXC-00")
                .message(e.getMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<APIException> SignatureBindException(BindException e) {
        List<APIException> errorsList = e.getFieldErrors().stream()
                .map(this::buildAPIExceptionFromFieldError)
                .collect(Collectors.toList());

        APIException failedResponse = APIException.builder()
                .code("BIND-EXC-00")
                .message(messageProvider.getMessage(ExceptionMessageEnum.CAMPOS_COM_ERRO_FORMULARIO_ENVIADO))
                .errors(errorsList)
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        log.error(String.format("Code: %s Message: %s ", failedResponse.getCode(), failedResponse.getMessage()));
        return ResponseEntity.badRequest().body(failedResponse);
    }

    private APIException buildAPIExceptionFromFieldError(FieldError fieldError) {
        return APIException.builder()
                .code("BIND-EXC-00")
                .message(fieldError.getDefaultMessage())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }
}
