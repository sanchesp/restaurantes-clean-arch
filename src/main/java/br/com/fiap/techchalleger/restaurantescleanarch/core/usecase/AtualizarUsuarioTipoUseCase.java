package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;

public interface AtualizarUsuarioTipoUseCase {

    UsuarioTipo atualizarUsuarioTipo(Long id, UsuarioTipo tipo);

}

