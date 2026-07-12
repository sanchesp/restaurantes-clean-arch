package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioTipoEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.UsuarioTipoRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.UsuarioTipoJson;
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
@DisplayName("UsuarioTipoController - Testes de Integração")
class UsuarioTipoControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioTipoRepository usuarioTipoRepository;

    @BeforeEach
    void setup() {
        usuarioTipoRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar tipo de usuário")
    void deveCriarUsuarioTipo() {

        UsuarioTipoJson dto = novoUsuarioTipo("CLIENTE");

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "/usuarios-tipos",
                        dto,
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CLIENTE", response.getBody());

        assertEquals(1, usuarioTipoRepository.count());
    }

    @Test
    @DisplayName("Deve listar tipos de usuário")
    void deveListarUsuarioTipos() {

        criarTipo("CLIENTE");
        criarTipo("DONO_RESTAURANTE");

        ResponseEntity<UsuarioTipoJson[]> response =
                restTemplate.getForEntity(
                        "/usuarios-tipos",
                        UsuarioTipoJson[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UsuarioTipoJson[] tipos = response.getBody();

        assertNotNull(tipos);
        assertEquals(2, tipos.length);

        assertEquals("CLIENTE", tipos[0].getTipo());
        assertEquals("DONO_RESTAURANTE", tipos[1].getTipo());
    }

    @Test
    @DisplayName("Deve atualizar tipo de usuário")
    void deveAtualizarUsuarioTipo() {

        UsuarioTipoEntity tipo = criarTipo("CLIENTE");

        UsuarioTipoJson update = novoUsuarioTipo("DONO_RESTAURANTE");

        restTemplate.put(
                "/usuarios-tipos/{id}",
                update,
                tipo.getId());

        UsuarioTipoEntity atualizado =
                usuarioTipoRepository.findById(tipo.getId()).orElse(null);

        assertNotNull(atualizado);
        assertEquals("DONO_RESTAURANTE", atualizado.getTipo());
    }

    @Test
    @DisplayName("Deve excluir tipo de usuário")
    void deveDeletarUsuarioTipo() {

        UsuarioTipoEntity tipo = criarTipo("CLIENTE");

        restTemplate.delete(
                "/usuarios-tipos/{id}",
                tipo.getId());

        assertFalse(usuarioTipoRepository.existsById(tipo.getId()));
    }


    // ==========================================================
    // Helpers
    // ==========================================================

    private UsuarioTipoEntity criarTipo(String tipo) {

        return usuarioTipoRepository.save(
                new UsuarioTipoEntity(
                        null,
                        tipo));
    }

    private UsuarioTipoJson novoUsuarioTipo(String tipo) {

        return new UsuarioTipoJson(
                null,
                tipo);
    }

}