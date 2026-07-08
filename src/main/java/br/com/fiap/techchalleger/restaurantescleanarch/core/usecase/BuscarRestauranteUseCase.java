package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;

public interface BuscarRestauranteUseCase {

    Restaurante buscarPorId(Long id);

}

