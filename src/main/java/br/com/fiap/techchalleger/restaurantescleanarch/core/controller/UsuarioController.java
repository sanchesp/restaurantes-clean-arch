package br.com.fiap.techchalleger.restaurantescleanarch.core.controller;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarUsuarioTipoDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarUsuarioUseCase;

public class UsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public UsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
    }

    public Usuario atualizarUsuarioTipo(Long id, AtualizarUsuarioTipoDto dto) {
        return atualizarUsuarioUseCase.atualizarUsuarioTipo(id, dto);
    }
}