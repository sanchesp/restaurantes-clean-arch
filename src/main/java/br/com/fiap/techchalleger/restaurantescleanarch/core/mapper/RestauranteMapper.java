package br.com.fiap.techchalleger.restaurantescleanarch.core.mapper;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarRestauranteDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarRestauranteDto;

public class RestauranteMapper {

    public Restaurante map(CriarRestauranteDto dto) {
        return new Restaurante(null, dto.getNome(), dto.getEndereco(), dto.getTipoCozinha(), dto.getHorarioFuncionamento(), dto.getDonoId());
    }

    public Restaurante map(Long id, AtualizarRestauranteDto dto, Long donoId) {
        return new Restaurante(id, dto.getNome(), dto.getEndereco(), dto.getTipoCozinha(), dto.getHorarioFuncionamento(), donoId);
    }

}

