package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeJaExisteException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;

public class CriarUsuarioTipoUseCaseImpl implements CriarUsuarioTipoUseCase {

    private final UsuarioTipoGateway usuarioTipoGateway;

    public CriarUsuarioTipoUseCaseImpl(UsuarioTipoGateway usuarioTipoGateway) {
        this.usuarioTipoGateway = usuarioTipoGateway;
    }

    @Override
    public String criarUsuarioTipo(UsuarioTipo tipo) {

        usuarioTipoJaExiste(tipo);

        return usuarioTipoGateway.criarUsuarioTipo(tipo);
    }

    private void usuarioTipoJaExiste(UsuarioTipo tipo) {
        if (usuarioTipoGateway.usuarioTipoJaExiste(tipo)) {
            throw new EntidadeJaExisteException("O tipo de usuário já existe.");
        }
    }
}
