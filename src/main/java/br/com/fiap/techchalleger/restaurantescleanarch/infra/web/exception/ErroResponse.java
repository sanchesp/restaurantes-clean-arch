package br.com.fiap.techchalleger.restaurantescleanarch.infra.web.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        int status,
        String mensagem,
        LocalDateTime timestamp) {

    public ErroResponse(int status, String mensagem) {
        this(status, mensagem, LocalDateTime.now());
    }
}