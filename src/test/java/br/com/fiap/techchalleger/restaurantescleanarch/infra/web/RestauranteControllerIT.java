package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.RestauranteEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.RestauranteRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.UsuarioRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.RestauranteJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("RestauranteController - Testes de Integração")
class RestauranteControllerIT {

    private static final Long TIPO_DONO_RESTAURANTE = 2L;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioEntity owner;

    @BeforeEach
    void setup() {
        restauranteRepository.deleteAll();
        usuarioRepository.deleteAll();

        owner = criarDono();
    }

    @Test
    @DisplayName("Deve criar restaurante")
    void deveCriarRestaurante() {

        RestauranteJson dto = novoRestauranteJson();

        ResponseEntity<String> response =
                restTemplate.postForEntity("/restaurantes", dto, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("ID:"));

        assertEquals(1, restauranteRepository.count());
    }

    @Test
    @DisplayName("Deve listar restaurantes")
    void deveListarRestaurantes() {

        criarRestaurante("R1");
        criarRestaurante("R2");

        ResponseEntity<RestauranteJson[]> response =
                restTemplate.getForEntity("/restaurantes", RestauranteJson[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RestauranteJson[] restaurantes = response.getBody();

        assertNotNull(restaurantes);
        assertEquals(2, restaurantes.length);
    }

    @Test
    @DisplayName("Deve buscar restaurante por id")
    void deveBuscarRestaurantePorId() {

        RestauranteEntity restaurante = criarRestaurante("Pizzaria");

        ResponseEntity<RestauranteJson> response =
                restTemplate.getForEntity(
                        "/restaurantes/{id}",
                        RestauranteJson.class,
                        restaurante.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RestauranteJson json = response.getBody();

        assertNotNull(json);
        assertEquals(restaurante.getId(), json.getId());
        assertEquals("Pizzaria", json.getNome());
        assertEquals(restaurante.getEndereco(), json.getEndereco());
        assertEquals(restaurante.getTipoCozinha(), json.getTipoCozinha());
        assertEquals(restaurante.getHorarioFuncionamento(), json.getHorarioFuncionamento());
        assertEquals(owner.getId(), json.getDonoId());
    }

    @Test
    @DisplayName("Deve atualizar restaurante")
    void deveAtualizarRestaurante() {

        RestauranteEntity restaurante = criarRestaurante("Antigo");

        RestauranteJson update = new RestauranteJson(
                null,
                "Novo Nome",
                "Nova Rua",
                "Brasileira",
                "11:00-23:00",
                owner.getId());

        restTemplate.put("/restaurantes/{id}", update, restaurante.getId());

        ResponseEntity<RestauranteJson> response =
                restTemplate.getForEntity(
                        "/restaurantes/{id}",
                        RestauranteJson.class,
                        restaurante.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RestauranteJson json = response.getBody();

        assertNotNull(json);
        assertEquals("Novo Nome", json.getNome());
        assertEquals("Nova Rua", json.getEndereco());
        assertEquals("Brasileira", json.getTipoCozinha());
        assertEquals("11:00-23:00", json.getHorarioFuncionamento());
        assertEquals(owner.getId(), json.getDonoId());
    }

    @Test
    @DisplayName("Deve deletar restaurante")
    void deveDeletarRestaurante() {

        RestauranteEntity restaurante = criarRestaurante("A Deletar");

        restTemplate.delete("/restaurantes/{id}", restaurante.getId());

        assertFalse(restauranteRepository.existsById(restaurante.getId()));
    }

    /*
     * Adicione estes testes somente se sua API realmente
     * retornar 404 para recursos inexistentes.
     */

    @Test
    @DisplayName("Deve retornar 404 ao buscar restaurante inexistente")
    void deveRetornar404AoBuscarRestauranteInexistente() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/restaurantes/{id}",
                        String.class,
                        99999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // ======================================================
    // Helpers
    // ======================================================

    private UsuarioEntity criarDono() {

        return usuarioRepository.save(
                new UsuarioEntity(
                        null,
                        "Owner",
                        "owner@test.com",
                        "senha",
                        TIPO_DONO_RESTAURANTE));
    }

    private RestauranteEntity criarRestaurante(String nome) {

        return restauranteRepository.save(
                new RestauranteEntity(
                        null,
                        nome,
                        "Rua A",
                        "Italiana",
                        "10:00-22:00",
                        owner.getId()));
    }

    private RestauranteJson novoRestauranteJson() {

        return new RestauranteJson(
                null,
                "Ristorante",
                "Rua A, 100",
                "Italiana",
                "11:00-23:00",
                owner.getId());
    }
}