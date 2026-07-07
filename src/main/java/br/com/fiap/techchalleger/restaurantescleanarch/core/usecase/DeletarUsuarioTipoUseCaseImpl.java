package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;

public class DeletarUsuarioTipoUseCaseImpl implements DeletarUsuarioTipoUseCase {

    private final UsuarioTipoGateway usuarioTipoGateway;

    public DeletarUsuarioTipoUseCaseImpl(UsuarioTipoGateway usuarioTipoGateway) {
        this.usuarioTipoGateway = usuarioTipoGateway;
    }

    @Override
    public void deletarUsuarioTipo(Long id) {
        usuarioTipoGateway.deletarUsuarioTipo(id);
    }
}

