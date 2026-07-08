package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

public class Restaurante {

    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long donoId;

    public Restaurante(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser nulo ou vazio.");
        }

        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço do restaurante não pode ser nulo ou vazio.");
        }

        if (tipoCozinha == null || tipoCozinha.isBlank()) {
            throw new IllegalArgumentException("Tipo de cozinha não pode ser nulo ou vazio.");
        }

        if (horarioFuncionamento == null || horarioFuncionamento.isBlank()) {
            throw new IllegalArgumentException("Horário de funcionamento não pode ser nulo ou vazio.");
        }

        if (donoId == null) {
            throw new IllegalArgumentException("ID do dono não pode ser nulo.");
        }

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public Long getId() {
        return id;
    }

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

