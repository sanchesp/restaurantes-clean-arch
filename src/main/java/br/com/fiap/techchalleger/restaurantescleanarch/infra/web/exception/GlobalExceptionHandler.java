package br.com.fiap.techchalleger.restaurantescleanarch.infra.web.exception;

import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeJaExisteException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> tratarEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()));
    }

    @ExceptionHandler(EntidadeJaExisteException.class)
    public ResponseEntity<ErroResponse> tratarEntidadeJaExiste(
            EntidadeJaExisteException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResponse(
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage()));
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResponse> tratarRegraNegocio(
            RegraDeNegocioException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroGenerico(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Ocorreu um erro interno na aplicação."));
    }
}