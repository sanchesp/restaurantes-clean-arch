
package br.com.fiap.techchalleger.restaurantescleanarch.core.controller;

import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarUsuarioTipoDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioTipoMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.CriarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.ListarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.DeletarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;

import java.util.List;

public class UsuarioTipoController {

    private final CriarUsuarioTipoUseCase criarUsuarioTipoUseCase;
    private final ListarUsuarioTipoUseCase listarUsuarioTipoUseCase;
    private final AtualizarUsuarioTipoUseCase atualizarUsuarioTipoUseCase;
    private final DeletarUsuarioTipoUseCase deletarUsuarioTipoUseCase;
    private final UsuarioTipoMapper usuarioTipoMapper;

    public UsuarioTipoController(CriarUsuarioTipoUseCase criarUsuarioTipoUseCase,
                                 ListarUsuarioTipoUseCase listarUsuarioTipoUseCase,
                                 AtualizarUsuarioTipoUseCase atualizarUsuarioTipoUseCase,
                                 DeletarUsuarioTipoUseCase deletarUsuarioTipoUseCase,
                                 UsuarioTipoMapper usuarioTipoMapper) {
        this.criarUsuarioTipoUseCase = criarUsuarioTipoUseCase;
        this.listarUsuarioTipoUseCase = listarUsuarioTipoUseCase;
        this.atualizarUsuarioTipoUseCase = atualizarUsuarioTipoUseCase;
        this.deletarUsuarioTipoUseCase = deletarUsuarioTipoUseCase;
        this.usuarioTipoMapper = usuarioTipoMapper;
    }

    public String criarUsuarioTipo(CriarUsuarioTipoDto dto) {
        var usuarioTipo = usuarioTipoMapper.map(dto);

        return criarUsuarioTipoUseCase.criarUsuarioTipo(usuarioTipo);
    }

    public List<UsuarioTipo> listarUsuarioTipo() {
        return listarUsuarioTipoUseCase.listarUsuarioTipo();
    }

    public UsuarioTipo atualizarUsuarioTipo(Long id, CriarUsuarioTipoDto dto) {
        var usuarioTipo = usuarioTipoMapper.map(dto);
        return atualizarUsuarioTipoUseCase.atualizarUsuarioTipo(id, usuarioTipo);
    }

    public void deletarUsuarioTipo(Long id) {
        deletarUsuarioTipoUseCase.deletarUsuarioTipo(id);
    }

}
