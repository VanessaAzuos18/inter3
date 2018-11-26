package lafiesta.model.domain;

public class Produto {

    private int id;
    private String tipo;
    private String produto;
    private String cidade;
    private String observacao;
    private int idFesta;
    private int idUsuario;

    public Produto() {}

    public Produto(String tipo, String produto, String cidade, String observacao) {
        this.setTipo(tipo);
        this.setProduto(produto);
        this.setCidade(cidade);
        this.setObservacao(observacao);
    }

    public Produto(int id, String tipo, String observacao, String cidade) {
        this.setId(id);
        this.setTipo(tipo);
        this.setObservacao(observacao);
        this.setCidade(cidade);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdFesta() {
        return idFesta;
    }

    public void setIdFesta(int idFesta) {
        this.idFesta = idFesta;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
