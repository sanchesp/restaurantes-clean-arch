package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioTipoEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioTipoEntityMapper {

    public UsuarioTipoEntity toEntity(UsuarioTipo tipo) {
        return new UsuarioTipoEntity(tipo.getId(), tipo.getTipo());
    }

    public UsuarioTipo toDomain(UsuarioTipoEntity entity) {
        return new br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo(entity.getId(), entity.getTipo());
    }
}
