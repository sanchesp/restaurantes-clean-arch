package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.RestauranteController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarRestauranteDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarRestauranteDto;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.RestauranteJson;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteApiController {

    private final RestauranteController restauranteController;

    public RestauranteApiController(RestauranteController restauranteController) {
        this.restauranteController = restauranteController;
    }

    @PostMapping
    public String criar(@RequestBody RestauranteJson restauranteJson) {
        return restauranteController.criarRestaurante(mapToCriarDto(restauranteJson));
    }

    @GetMapping
    public List<RestauranteJson> listar() {
        List<Restaurante> lista = restauranteController.listarRestaurantes();
        return lista.stream()
                .map(this::mapToJson)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RestauranteJson buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteController.buscarPorId(id);
        return mapToJson(restaurante);
    }

    @PutMapping("/{id}")
    public RestauranteJson atualizar(@PathVariable Long id, @RequestBody RestauranteJson restauranteJson) {
        Restaurante updated = restauranteController.atualizarRestaurante(id, mapToAtualizarDto(restauranteJson));
        return mapToJson(updated);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        restauranteController.deletarRestaurante(id);
    }

    private CriarRestauranteDto mapToCriarDto(RestauranteJson json) {
        return new CriarRestauranteDto(
            json.getNome(),
            json.getEndereco(),
            json.getTipoCozinha(),
            json.getHorarioFuncionamento(),
            json.getDonoId()
        );
    }

    private AtualizarRestauranteDto mapToAtualizarDto(RestauranteJson json) {
        return new AtualizarRestauranteDto(
            json.getNome(),
            json.getEndereco(),
            json.getTipoCozinha(),
            json.getHorarioFuncionamento()
        );
    }

    private RestauranteJson mapToJson(Restaurante restaurante) {
        return new RestauranteJson(
            restaurante.getId(),
            restaurante.getNome(),
            restaurante.getEndereco(),
            restaurante.getTipoCozinha(),
            restaurante.getHorarioFuncionamento(),
            restaurante.getDonoId()
        );
    }

}

