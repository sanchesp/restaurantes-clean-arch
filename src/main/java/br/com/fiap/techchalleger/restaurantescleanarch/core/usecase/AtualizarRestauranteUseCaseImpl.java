package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;

public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public AtualizarRestauranteUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Restaurante atualizarRestaurante(Long id, Restaurante restaurante) {
        validarRestauranteExiste(id);
        return restauranteGateway.atualizarRestaurante(id, restaurante);
    }

    private void validarRestauranteExiste(Long id) {
        Restaurante restaurante = restauranteGateway.buscarPorId(id);
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não encontrado com o ID: " + id);
        }
    }

}

