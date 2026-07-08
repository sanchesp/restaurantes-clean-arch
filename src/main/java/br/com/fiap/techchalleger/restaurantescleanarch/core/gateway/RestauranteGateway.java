package br.com.fiap.techchalleger.restaurantescleanarch.core.gateway;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import java.util.List;

public interface RestauranteGateway {

    String criarRestaurante(Restaurante restaurante);

    List<Restaurante> listarRestaurantes();

    Restaurante buscarPorId(Long id);

    Restaurante atualizarRestaurante(Long id, Restaurante restaurante);

    void deletarRestaurante(Long id);

}

