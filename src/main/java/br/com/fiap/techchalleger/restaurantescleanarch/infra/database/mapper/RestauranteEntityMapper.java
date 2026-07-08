package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.RestauranteEntity;

public class RestauranteEntityMapper {

    public RestauranteEntity toEntity(Restaurante restaurante) {
        return new RestauranteEntity(
            restaurante.getId(),
            restaurante.getNome(),
            restaurante.getEndereco(),
            restaurante.getTipoCozinha(),
            restaurante.getHorarioFuncionamento(),
            restaurante.getDonoId()
        );
    }

    public Restaurante toDomain(RestauranteEntity entity) {
        return new Restaurante(
            entity.getId(),
            entity.getNome(),
            entity.getEndereco(),
            entity.getTipoCozinha(),
            entity.getHorarioFuncionamento(),
            entity.getDonoId()
        );
    }

}

