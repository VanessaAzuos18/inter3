package lafiesta.model.domain;

public class Aluguel {

    private int id;
    private String tipo;
    private String cidade;
    private String nome_festa;
    private String data;
    private String nome_usuario;
    private String tel_usuario;

    public Aluguel(int id, String tipo, String cidade){
        this.setId(id);
        this.setTipo(tipo);
        this.setCidade(cidade);
    }

    public Aluguel(String tipo, String cidade, String nome_festa, String data, String nome_usuario, String tel_usuario){
        this.setTipo(tipo);
        this.setCidade(cidade);
        this.setNome_festa(nome_festa);
        this.setData(data);
        this.setNome_usuario(nome_usuario);
        this.setTel_usuario(tel_usuario);
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

    public String getNome_festa() {
        return nome_festa;
    }

    public void setNome_festa(String nome_festa) {
        this.nome_festa = nome_festa;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getTel_usuario() {
        return tel_usuario;
    }

    public void setTel_usuario(String tel_usuario) {
        this.tel_usuario = tel_usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
