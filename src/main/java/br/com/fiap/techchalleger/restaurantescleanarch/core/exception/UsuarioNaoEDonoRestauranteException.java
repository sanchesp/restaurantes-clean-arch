package br.com.fiap.techchalleger.restaurantescleanarch.core.exception;

public class UsuarioNaoEDonoRestauranteException extends RegraDeNegocioException {

    public UsuarioNaoEDonoRestauranteException() {
        super("O usuário deve ser do tipo DONO_RESTAURANTE para criar um restaurante.");
    }
}