package br.com.menesic.GranjaApp.domain.exception;

public class MensageriaException extends RuntimeException {

    public MensageriaException(String conteudo) {
        super(conteudo);
    }

}