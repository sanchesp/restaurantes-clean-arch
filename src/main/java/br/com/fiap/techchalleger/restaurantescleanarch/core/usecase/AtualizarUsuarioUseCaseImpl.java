package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarUsuarioTipoDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioMapper;

public class AtualizarUsuarioUseCaseImpl implements AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper usuarioMapper;

    public AtualizarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway,
                                       UsuarioMapper usuarioMapper) {
        this.usuarioGateway = usuarioGateway;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario atualizarUsuarioTipo(Long id, AtualizarUsuarioTipoDto dto) {

        Usuario usuarioExistente = usuarioGateway.buscarPorId(id);

        if (usuarioExistente == null) {
            throw new EntidadeNaoEncontradaException(
                    "Usuário não encontrado com o ID: " + id);
        }

        Usuario usuarioAtualizado = usuarioMapper.map(
                usuarioExistente.getId(),
                usuarioExistente.getNome(),
                usuarioExistente.getEmail(),
                usuarioExistente.getSenha(),
                dto);

        return usuarioGateway.atualizarUsuarioTipo(id, usuarioAtualizado);
    }
}