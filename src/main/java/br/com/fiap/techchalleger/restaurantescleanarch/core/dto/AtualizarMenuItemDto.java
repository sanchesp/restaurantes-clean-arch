package br.com.fiap.techchalleger.restaurantescleanarch.core.dto;

public class AtualizarMenuItemDto {

    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivel;
    private String caminhoFoto;

    public AtualizarMenuItemDto(String nome, String descricao, Double preco, Boolean disponivel, String caminhoFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.caminhoFoto = caminhoFoto;
    }

    public AtualizarMenuItemDto() {}

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

}

