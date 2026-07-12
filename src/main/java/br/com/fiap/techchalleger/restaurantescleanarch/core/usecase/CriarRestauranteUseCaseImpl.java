package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.UsuarioNaoEDonoRestauranteException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioGateway;

public class CriarRestauranteUseCaseImpl implements CriarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public CriarRestauranteUseCaseImpl(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public String criarRestaurante(Restaurante restaurante) {
        validarUsuarioDono(restaurante.getDonoId());
        return restauranteGateway.criarRestaurante(restaurante);
    }

    private void validarUsuarioDono(Long donoId) {
        var usuario = usuarioGateway.buscarPorId(donoId);
        if (usuario == null) {
            throw new EntidadeNaoEncontradaException("Usuário proprietário não encontrado com o ID: " + donoId);
        }
        if (usuario.getUsuarioTipoId() != 2L) {
            throw new UsuarioNaoEDonoRestauranteException();
        }
    }

}

