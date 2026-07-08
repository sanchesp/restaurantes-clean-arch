package br.com.fiap.techchalleger.restaurantescleanarch.core.dto;

public class AtualizarRestauranteDto {

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;

    public AtualizarRestauranteDto(String nome, String endereco, String tipoCozinha, String horarioFuncionamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public AtualizarRestauranteDto() {}

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

}

