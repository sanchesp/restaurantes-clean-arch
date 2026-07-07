package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import java.util.List;

public class ListarUsuarioTipoUseCaseImpl implements ListarUsuarioTipoUseCase {

    private final UsuarioTipoGateway usuarioTipoGateway;

    public ListarUsuarioTipoUseCaseImpl(UsuarioTipoGateway usuarioTipoGateway) {
        this.usuarioTipoGateway = usuarioTipoGateway;
    }

    @Override
    public List<UsuarioTipo> listarUsuarioTipo() {
        return usuarioTipoGateway.listarUsuarioTipo();
    }
}

