package br.com.fiap.techchalleger.restaurantescleanarch.core.controller;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarRestauranteDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarRestauranteDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.RestauranteMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.CriarRestauranteUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.ListarRestaurantesUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.BuscarRestauranteUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarRestauranteUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.DeletarRestauranteUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;

import java.util.List;

public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final ListarRestaurantesUseCase listarRestaurantesUseCase;
    private final BuscarRestauranteUseCase buscarRestauranteUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;
    private final RestauranteMapper restauranteMapper;
    private final RestauranteGateway restauranteGateway;

    public RestauranteController(CriarRestauranteUseCase criarRestauranteUseCase,
                                ListarRestaurantesUseCase listarRestaurantesUseCase,
                                BuscarRestauranteUseCase buscarRestauranteUseCase,
                                AtualizarRestauranteUseCase atualizarRestauranteUseCase,
                                DeletarRestauranteUseCase deletarRestauranteUseCase,
                                RestauranteMapper restauranteMapper,
                                RestauranteGateway restauranteGateway) {
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.listarRestaurantesUseCase = listarRestaurantesUseCase;
        this.buscarRestauranteUseCase = buscarRestauranteUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.deletarRestauranteUseCase = deletarRestauranteUseCase;
        this.restauranteMapper = restauranteMapper;
        this.restauranteGateway = restauranteGateway;
    }

    public String criarRestaurante(CriarRestauranteDto dto) {
        Restaurante restaurante = restauranteMapper.map(dto);
        return criarRestauranteUseCase.criarRestaurante(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return listarRestaurantesUseCase.listarRestaurantes();
    }

    public Restaurante buscarPorId(Long id) {
        return buscarRestauranteUseCase.buscarPorId(id);
    }

    public Restaurante atualizarRestaurante(Long id, AtualizarRestauranteDto dto) {
        Restaurante restauranteExistente = restauranteGateway.buscarPorId(id);
        Restaurante restaurante = restauranteMapper.map(id, dto, restauranteExistente.getDonoId());
        return atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante);
    }

    public void deletarRestaurante(Long id) {
        deletarRestauranteUseCase.deletarRestaurante(id);
    }

}

