package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import java.util.List;

public class ListarRestaurantesUseCaseImpl implements ListarRestaurantesUseCase {

    private final RestauranteGateway restauranteGateway;

    public ListarRestaurantesUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> listarRestaurantes() {
        return restauranteGateway.listarRestaurantes();
    }

}

