package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarUsuarioTipoDto;

public interface AtualizarUsuarioUseCase {

    Usuario atualizarUsuarioTipo(Long id, AtualizarUsuarioTipoDto dto);

}