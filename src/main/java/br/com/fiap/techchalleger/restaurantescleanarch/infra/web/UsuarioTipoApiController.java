
package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.UsuarioTipoController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarUsuarioTipoDto;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.UsuarioTipoJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios-tipos")
public class UsuarioTipoApiController {

    private final UsuarioTipoController usuarioTipoController;

    public UsuarioTipoApiController(UsuarioTipoController usuarioTipoController) {
        this.usuarioTipoController = usuarioTipoController;
    }

    @PostMapping
    public String criar(@RequestBody UsuarioTipoJson usuarioTipoJson) {
        return usuarioTipoController.criarUsuarioTipo(mapToDto(usuarioTipoJson));
    }

    @GetMapping
    public List<UsuarioTipoJson> listar() {
        List<UsuarioTipo> lista = usuarioTipoController.listarUsuarioTipo();

        return lista.stream()
                .map(u -> new UsuarioTipoJson(u.getId(), u.getTipo()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UsuarioTipoJson atualizar(@PathVariable Long id, @RequestBody UsuarioTipoJson usuarioTipoJson) {
        UsuarioTipo updated = usuarioTipoController.atualizarUsuarioTipo(id, mapToDto(usuarioTipoJson));

        return new UsuarioTipoJson(updated.getId(), updated.getTipo());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioTipoController.deletarUsuarioTipo(id);
    }

    private CriarUsuarioTipoDto mapToDto(UsuarioTipoJson usuarioTipoJson) {
        return new CriarUsuarioTipoDto(usuarioTipoJson.getTipo());
    }
}
