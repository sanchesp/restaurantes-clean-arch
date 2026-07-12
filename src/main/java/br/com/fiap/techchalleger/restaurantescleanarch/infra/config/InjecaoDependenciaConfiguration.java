
package br.com.fiap.techchalleger.restaurantescleanarch.infra.config;

import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.UsuarioTipoController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.UsuarioController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.RestauranteController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.MenuItemController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioTipoMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.RestauranteMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.MenuItemMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.*;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper.RestauranteEntityMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper.MenuItemEntityMapper;
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

    @Bean
    AtualizarUsuarioUseCase atualizarUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            UsuarioMapper usuarioMapper) {

        return new AtualizarUsuarioUseCaseImpl(
                usuarioGateway,
                usuarioMapper);
    }

    @Bean
    UsuarioController usuarioController(
            AtualizarUsuarioUseCase atualizarUsuarioUseCase) {

        return new UsuarioController(atualizarUsuarioUseCase);
    }

    @Bean
    public UsuarioMapper usuarioMapper() {
        return new UsuarioMapper();
    }

    // ====== RESTAURANTE BEANS ======

    @Bean
    CriarRestauranteUseCase criarRestauranteUseCase(
            RestauranteGateway restauranteGateway,
            UsuarioGateway usuarioGateway) {
        return new CriarRestauranteUseCaseImpl(restauranteGateway, usuarioGateway);
    }

    @Bean
    ListarRestaurantesUseCase listarRestaurantesUseCase(RestauranteGateway restauranteGateway) {
        return new ListarRestaurantesUseCaseImpl(restauranteGateway);
    }

    @Bean
    BuscarRestauranteUseCase buscarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new BuscarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Bean
    AtualizarRestauranteUseCase atualizarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new AtualizarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Bean
    DeletarRestauranteUseCase deletarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Bean
    RestauranteController restauranteController(
            CriarRestauranteUseCase criarRestauranteUseCase,
            ListarRestaurantesUseCase listarRestaurantesUseCase,
            BuscarRestauranteUseCase buscarRestauranteUseCase,
            AtualizarRestauranteUseCase atualizarRestauranteUseCase,
            DeletarRestauranteUseCase deletarRestauranteUseCase,
            RestauranteMapper restauranteMapper,
            RestauranteGateway restauranteGateway) {

        return new RestauranteController(
                criarRestauranteUseCase,
                listarRestaurantesUseCase,
                buscarRestauranteUseCase,
                atualizarRestauranteUseCase,
                deletarRestauranteUseCase,
                restauranteMapper,
                restauranteGateway);
    }

    @Bean
    public RestauranteMapper restauranteMapper() {
        return new RestauranteMapper();
    }

    @Bean
    public RestauranteEntityMapper restauranteEntityMapper() {
        return new RestauranteEntityMapper();
    }

    // ====== MENU ITEM BEANS ======

    @Bean
    CriarMenuItemUseCase criarMenuItemUseCase(
            MenuItemGateway menuItemGateway,
            RestauranteGateway restauranteGateway) {
        return new CriarMenuItemUseCaseImpl(menuItemGateway, restauranteGateway);
    }

    @Bean
    ListarMenuItemsUseCase listarMenuItemsUseCase(MenuItemGateway menuItemGateway) {
        return new ListarMenuItemsUseCaseImpl(menuItemGateway);
    }

    @Bean
    BuscarMenuItemUseCase buscarMenuItemUseCase(MenuItemGateway menuItemGateway) {
        return new BuscarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Bean
    AtualizarMenuItemUseCase atualizarMenuItemUseCase(MenuItemGateway menuItemGateway) {
        return new AtualizarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Bean
    DeletarMenuItemUseCase deletarMenuItemUseCase(MenuItemGateway menuItemGateway) {
        return new DeletarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Bean
    MenuItemController menuItemController(
            CriarMenuItemUseCase criarMenuItemUseCase,
            ListarMenuItemsUseCase listarMenuItemsUseCase,
            BuscarMenuItemUseCase buscarMenuItemUseCase,
            AtualizarMenuItemUseCase atualizarMenuItemUseCase,
            DeletarMenuItemUseCase deletarMenuItemUseCase,
            MenuItemMapper menuItemMapper,
            MenuItemGateway menuItemGateway) {

        return new MenuItemController(
                criarMenuItemUseCase,
                listarMenuItemsUseCase,
                buscarMenuItemUseCase,
                atualizarMenuItemUseCase,
                deletarMenuItemUseCase,
                menuItemMapper,
                menuItemGateway);
    }

    @Bean
    public MenuItemMapper menuItemMapper() {
        return new MenuItemMapper();
    }

    @Bean
    public MenuItemEntityMapper menuItemEntityMapper() {
        return new MenuItemEntityMapper();
    }
}
