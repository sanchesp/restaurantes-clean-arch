package br.com.fiap.techchalleger.restaurantescleanarch.core.dto;

public class CriarMenuItemDto {

    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivel;
    private String caminhoFoto;
    private Long restauranteId;

    public CriarMenuItemDto(String nome, String descricao, Double preco, Boolean disponivel, String caminhoFoto, Long restauranteId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.caminhoFoto = caminhoFoto;
        this.restauranteId = restauranteId;
    }

    public CriarMenuItemDto() {}

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

