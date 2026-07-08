package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;

public class BuscarRestauranteUseCaseImpl implements BuscarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public BuscarRestauranteUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        Restaurante restaurante = restauranteGateway.buscarPorId(id);
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não encontrado com o ID: " + id);
        }
        return restaurante;
    }

}

