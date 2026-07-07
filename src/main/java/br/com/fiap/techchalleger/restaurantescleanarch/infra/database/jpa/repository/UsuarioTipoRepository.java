
package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioTipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioTipoRepository extends JpaRepository<UsuarioTipoEntity, Long>{

	boolean existsByTipoIgnoreCase(String tipo);

}
