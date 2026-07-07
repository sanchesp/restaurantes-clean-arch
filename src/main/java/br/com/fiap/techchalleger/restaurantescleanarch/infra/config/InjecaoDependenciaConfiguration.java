
package br.com.fiap.techchalleger.restaurantescleanarch.infra.config;

import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.UsuarioTipoController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioTipoMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.CriarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.CriarUsuarioTipoUseCaseImpl;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.ListarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.ListarUsuarioTipoUseCaseImpl;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarUsuarioTipoUseCaseImpl;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.DeletarUsuarioTipoUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.DeletarUsuarioTipoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjecaoDependenciaConfiguration {

    @Bean
    CriarUsuarioTipoUseCase criarUsuarioTipoUseCase(
            UsuarioTipoGateway usuarioTipoGateway) {

        return new CriarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Bean
    ListarUsuarioTipoUseCase listarUsuarioTipoUseCase(UsuarioTipoGateway usuarioTipoGateway) {
        return new ListarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Bean
    AtualizarUsuarioTipoUseCase atualizarUsuarioTipoUseCase(UsuarioTipoGateway usuarioTipoGateway) {
        return new AtualizarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Bean
    DeletarUsuarioTipoUseCase deletarUsuarioTipoUseCase(UsuarioTipoGateway usuarioTipoGateway) {
        return new DeletarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Bean
    UsuarioTipoController usuarioTipoController(
            CriarUsuarioTipoUseCase criarUsuarioTipoUseCase,
            ListarUsuarioTipoUseCase listarUsuarioTipoUseCase,
            AtualizarUsuarioTipoUseCase atualizarUsuarioTipoUseCase,
            DeletarUsuarioTipoUseCase deletarUsuarioTipoUseCase,
            UsuarioTipoMapper usuarioTipoMapper) {

        return new UsuarioTipoController(
                criarUsuarioTipoUseCase,
                listarUsuarioTipoUseCase,
                atualizarUsuarioTipoUseCase,
                deletarUsuarioTipoUseCase,
                usuarioTipoMapper);
    }

    @Bean
    public UsuarioTipoMapper usuarioTipoMapper() {
        return new UsuarioTipoMapper();
    }
}
