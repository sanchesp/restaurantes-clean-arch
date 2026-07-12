package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;

public class DeletarRestauranteUseCaseImpl implements DeletarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public DeletarRestauranteUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public void deletarRestaurante(Long id) {
        validarRestauranteExiste(id);
        restauranteGateway.deletarRestaurante(id);
    }

    private void validarRestauranteExiste(Long id) {
        var restaurante = restauranteGateway.buscarPorId(id);
        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException("Restaurante não encontrado com o ID: " + id);
        }
    }

}

