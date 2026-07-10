package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.MenuItemEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.RestauranteEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.MenuItemRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.RestauranteRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.UsuarioRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.MenuItemJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("MenuItemController - Testes de Integração")
class MenuItemControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private RestauranteEntity restaurante;

    @BeforeEach
    void setup() {
        menuItemRepository.deleteAll();
        restauranteRepository.deleteAll();
        usuarioRepository.deleteAll();

        UsuarioEntity dono = criarDono();
        restaurante = criarRestaurante(dono);
    }

    @Test
    @DisplayName("Deve criar um menu item")
    void deveCriarMenuItem() {

        MenuItemJson dto = novoMenuItemJson();

        ResponseEntity<String> response =
                restTemplate.postForEntity("/menu-items", dto, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("ID:"));

        assertEquals(1, menuItemRepository.count());
    }

    @Test
    @DisplayName("Deve listar todos os menu items")
    void deveListarMenuItems() {

        criarMenuItem("Pizza");
        criarMenuItem("Hambúrguer");

        ResponseEntity<MenuItemJson[]> response =
                restTemplate.getForEntity("/menu-items", MenuItemJson[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        MenuItemJson[] itens = response.getBody();

        assertNotNull(itens);
        assertEquals(2, itens.length);
    }

    @Test
    @DisplayName("Deve listar menu items por restaurante")
    void deveListarMenuItemsPorRestaurante() {

        criarMenuItem("Pizza");
        criarMenuItem("Sushi");

        ResponseEntity<MenuItemJson[]> response =
                restTemplate.getForEntity(
                        "/menu-items/restaurante/{id}",
                        MenuItemJson[].class,
                        restaurante.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        MenuItemJson[] itens = response.getBody();

        assertNotNull(itens);
        assertEquals(2, itens.length);
    }

    @Test
    @DisplayName("Deve buscar menu item por id")
    void deveBuscarMenuItemPorId() {

        MenuItemEntity item = criarMenuItem("Sushi");

        ResponseEntity<MenuItemJson> response =
                restTemplate.getForEntity(
                        "/menu-items/{id}",
                        MenuItemJson.class,
                        item.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        MenuItemJson json = response.getBody();

        assertNotNull(json);
        assertEquals(item.getId(), json.getId());
        assertEquals("Sushi", json.getNome());
        assertEquals(35.50, json.getPreco());
        assertTrue(json.getDisponivel());
        assertEquals(restaurante.getId(), json.getRestauranteId());
    }

    @Test
    @DisplayName("Deve atualizar menu item")
    void deveAtualizarMenuItem() {

        MenuItemEntity item = criarMenuItem("Pizza");

        MenuItemJson update = new MenuItemJson(
                null,
                "Pizza Especial",
                "Massa italiana",
                59.90,
                false,
                "/nova.jpg",
                restaurante.getId());

        restTemplate.put("/menu-items/{id}", update, item.getId());

        ResponseEntity<MenuItemJson> response =
                restTemplate.getForEntity(
                        "/menu-items/{id}",
                        MenuItemJson.class,
                        item.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        MenuItemJson json = response.getBody();

        assertNotNull(json);
        assertEquals("Pizza Especial", json.getNome());
        assertEquals("Massa italiana", json.getDescricao());
        assertEquals(59.90, json.getPreco());
        assertFalse(json.getDisponivel());
        assertEquals("/nova.jpg", json.getCaminhoFoto());
    }

    @Test
    @DisplayName("Deve deletar menu item")
    void deveDeletarMenuItem() {

        MenuItemEntity item = criarMenuItem("Pizza");

        restTemplate.delete("/menu-items/{id}", item.getId());

        ResponseEntity<MenuItemJson> response =
                restTemplate.getForEntity(
                        "/menu-items/{id}",
                        MenuItemJson.class,
                        item.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar 404 ao buscar menu item inexistente")
    void deveRetornar404AoBuscarInexistente() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/menu-items/{id}",
                        String.class,
                        99999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // ============================================================
    // Helpers
    // ============================================================

    private UsuarioEntity criarDono() {

        return usuarioRepository.save(
                new UsuarioEntity(
                        null,
                        "Owner",
                        "owner@test.com",
                        "senha",
                        2L));
    }

    private RestauranteEntity criarRestaurante(UsuarioEntity dono) {

        return restauranteRepository.save(
                new RestauranteEntity(
                        null,
                        "Restaurante",
                        "Rua A",
                        "Italiana",
                        "10:00-22:00",
                        dono.getId()));
    }

    private MenuItemEntity criarMenuItem(String nome) {

        return menuItemRepository.save(
                new MenuItemEntity(
                        null,
                        nome,
                        "Descrição",
                        35.50,
                        true,
                        "/foto.jpg",
                        restaurante.getId()));
    }

    private MenuItemJson novoMenuItemJson() {

        return new MenuItemJson(
                null,
                "Pizza",
                "Saborosa",
                35.50,
                true,
                "/img/pizza.jpg",
                restaurante.getId());
    }
}