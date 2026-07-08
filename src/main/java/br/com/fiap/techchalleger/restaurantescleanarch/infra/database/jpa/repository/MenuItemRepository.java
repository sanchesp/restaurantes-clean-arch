package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

    List<MenuItemEntity> findByRestauranteId(Long restauranteId);

}

