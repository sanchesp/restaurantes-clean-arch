package br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json;

public class MenuItemJson {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivel;
    private String caminhoFoto;
    private Long restauranteId;

    public MenuItemJson(Long id, String nome, String descricao, Double preco, Boolean disponivel, String caminhoFoto, Long restauranteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.caminhoFoto = caminhoFoto;
        this.restauranteId = restauranteId;
    }

    public MenuItemJson() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

}

