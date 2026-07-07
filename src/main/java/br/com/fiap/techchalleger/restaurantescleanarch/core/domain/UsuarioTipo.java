
package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

public class UsuarioTipo {

    private Long id;
    private String tipo;

    public UsuarioTipo(String tipo) {
        this(null, tipo);
    }

    public UsuarioTipo(Long id, String tipo) {

        if (!tipo.equalsIgnoreCase("CLIENTE")
                && !tipo.equalsIgnoreCase("DONO_RESTAURANTE")) {

            throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
        }

        this.id = id;
        this.tipo = tipo;

    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

}
