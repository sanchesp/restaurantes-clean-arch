package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;

public interface AtualizarRestauranteUseCase {

    Restaurante atualizarRestaurante(Long id, Restaurante restaurante);

}

