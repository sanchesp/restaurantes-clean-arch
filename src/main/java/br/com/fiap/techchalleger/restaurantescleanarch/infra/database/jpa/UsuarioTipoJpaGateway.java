package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioTipoEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.UsuarioTipoRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper.UsuarioTipoEntityMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Component
public class UsuarioTipoJpaGateway implements UsuarioTipoGateway {

    private final UsuarioTipoRepository usuarioTipoRepository;
    private final UsuarioTipoEntityMapper usuarioTipoEntityMapper;

    public UsuarioTipoJpaGateway(UsuarioTipoRepository usuarioTipoRepository,
                                 UsuarioTipoEntityMapper usuarioTipoEntityMapper) {
        this.usuarioTipoRepository = usuarioTipoRepository;
        this.usuarioTipoEntityMapper = usuarioTipoEntityMapper;
    }


    @Override
    public String criarUsuarioTipo(UsuarioTipo tipo) {
        try {

            UsuarioTipoEntity usuarioTipoEntity= usuarioTipoEntityMapper.toEntity(tipo);

            return usuarioTipoRepository.save(usuarioTipoEntity).getTipo();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tipo de usuário", e);
        }
    }

    @Override
    public List<UsuarioTipo> listarUsuarioTipo() {
        return usuarioTipoRepository.findAll()
                .stream()
                .map(usuarioTipoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioTipo atualizarUsuarioTipo(Long id, UsuarioTipo tipo) {
        try {
            Optional<UsuarioTipoEntity> optional = usuarioTipoRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Tipo de usuário não encontrado: " + id);
            }

            UsuarioTipoEntity entity = optional.get();
            entity.setTipo(tipo.getTipo());

            UsuarioTipoEntity saved = usuarioTipoRepository.save(entity);

            return usuarioTipoEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tipo de usuário", e);
        }
    }

    @Override
    public void deletarUsuarioTipo(Long id) {
        try {
            usuarioTipoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tipo de usuário", e);
        }
    }

    @Override
    public boolean usuarioTipoJaExiste(UsuarioTipo tipo) {
        try {
            return usuarioTipoRepository.existsByTipoIgnoreCase(tipo.getTipo());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar existência do tipo de usuário", e);
        }
    }
}
