package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;

public class AtualizarUsuarioTipoUseCaseImpl implements AtualizarUsuarioTipoUseCase {

    private final UsuarioTipoGateway usuarioTipoGateway;

    public AtualizarUsuarioTipoUseCaseImpl(UsuarioTipoGateway usuarioTipoGateway) {
        this.usuarioTipoGateway = usuarioTipoGateway;
    }

    @Override
    public UsuarioTipo atualizarUsuarioTipo(Long id, UsuarioTipo tipo) {

        usuarioTipoJaExiste(tipo);

        return usuarioTipoGateway.atualizarUsuarioTipo(id, tipo);
    }

    private void usuarioTipoJaExiste(UsuarioTipo tipo) {
        if (usuarioTipoGateway.usuarioTipoJaExiste(tipo)) {
            throw new IllegalArgumentException("O tipo de usuário já existe.");
        }
    }
}

