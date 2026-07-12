package br.com.fiap.techchalleger.restaurantescleanarch.core.exception;


public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}