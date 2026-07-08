package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

}

