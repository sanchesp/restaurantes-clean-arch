package br.com.fiap.techchalleger.restaurantescleanarch.core.exception;


public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}