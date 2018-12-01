package lafiesta.model.domain;

public class Aluguel {

    private int id;
    private String tipo;
    private String cidade;

    public Aluguel(int id, String tipo, String cidade){
        this.setId(id);
        this.setTipo(tipo);
        this.setCidade(cidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
