package br.com.fiap.techchalleger.restaurantescleanarch.core.dto;

public class CriarRestauranteDto {

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long donoId;

    public CriarRestauranteDto(String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public CriarRestauranteDto() {}

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Long getDonoId() {
        return donoId;
    }

}

