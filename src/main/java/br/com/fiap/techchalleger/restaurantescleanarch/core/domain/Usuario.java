package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long usuarioTipoId;

    public Usuario(Long id, String nome, String email, String senha, Long usuarioTipoId) {

        if (usuarioTipoId == null) {
            throw new IllegalArgumentException("O ID do tipo de usuário não pode ser nulo.");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.usuarioTipoId = usuarioTipoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Long getUsuarioTipoId() {
        return usuarioTipoId;
    }

}

