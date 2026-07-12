package br.com.fiap.techchalleger.restaurantescleanarch.core.exception;

public class EntidadeJaExisteException extends RuntimeException {

    public EntidadeJaExisteException(String mensagem) {
        super(mensagem);
    }
}