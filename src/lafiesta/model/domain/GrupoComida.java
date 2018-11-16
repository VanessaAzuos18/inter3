package lafiesta.model.domain;

public class GrupoComida {
    private int id;
    private String nome;

    public GrupoComida() {}

    public GrupoComida(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
