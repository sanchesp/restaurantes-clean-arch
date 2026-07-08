package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.RestauranteEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.RestauranteRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper.RestauranteEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestauranteJpaGateway implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper restauranteEntityMapper;

    public RestauranteJpaGateway(RestauranteRepository restauranteRepository,
                                RestauranteEntityMapper restauranteEntityMapper) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteEntityMapper = restauranteEntityMapper;
    }

    @Override
    public String criarRestaurante(Restaurante restaurante) {
        try {
            RestauranteEntity entity = restauranteEntityMapper.toEntity(restaurante);
            RestauranteEntity saved = restauranteRepository.save(entity);
            return "Restaurante criado com sucesso. ID: " + saved.getId();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar restaurante", e);
        }
    }

    @Override
    public List<Restaurante> listarRestaurantes() {
        try {
            return restauranteRepository.findAll()
                    .stream()
                    .map(restauranteEntityMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar restaurantes", e);
        }
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        try {
            Optional<RestauranteEntity> optional = restauranteRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            return restauranteEntityMapper.toDomain(optional.get());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar restaurante por ID", e);
        }
    }

    @Override
    public Restaurante atualizarRestaurante(Long id, Restaurante restaurante) {
        try {
            Optional<RestauranteEntity> optional = restauranteRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Restaurante não encontrado: " + id);
            }

            RestauranteEntity entity = optional.get();
            entity.setNome(restaurante.getNome());
            entity.setEndereco(restaurante.getEndereco());
            entity.setTipoCozinha(restaurante.getTipoCozinha());
            entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());

            RestauranteEntity saved = restauranteRepository.save(entity);

            return restauranteEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar restaurante", e);
        }
    }

    @Override
    public void deletarRestaurante(Long id) {
        try {
            Optional<RestauranteEntity> optional = restauranteRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Restaurante não encontrado: " + id);
            }
            restauranteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar restaurante", e);
        }
    }

}

