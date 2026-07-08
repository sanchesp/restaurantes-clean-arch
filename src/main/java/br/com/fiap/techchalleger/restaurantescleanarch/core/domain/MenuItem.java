package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

public class MenuItem {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivel;
    private String caminhoFoto;
    private Long restauranteId;

    public MenuItem(Long id, String nome, String descricao, Double preco, Boolean disponivel, String caminhoFoto, Long restauranteId) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do item não pode ser nulo ou vazio.");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição do item não pode ser nula ou vazia.");
        }

        if (preco == null || preco <= 0) {
            throw new IllegalArgumentException("Preço do item deve ser maior que zero.");
        }

        if (disponivel == null) {
            throw new IllegalArgumentException("Disponibilidade não pode ser nula.");
        }

        if (caminhoFoto == null || caminhoFoto.isBlank()) {
            throw new IllegalArgumentException("Caminho da foto não pode ser nulo ou vazio.");
        }

        if (restauranteId == null) {
            throw new IllegalArgumentException("ID do restaurante não pode ser nulo.");
        }

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.caminhoFoto = caminhoFoto;
        this.restauranteId = restauranteId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

}

