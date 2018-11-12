package lafiesta.model.domain;

public class Festa {

    private int id;
    private String nome_festa;
    private String data;
    private String local;
    private int convidados;

    public Festa(int id, String nome_festa, String data, String local, int convidados){
        this.setId(id);
        this.setNome_festa(nome_festa);
        this.setData(data);
        this.setLocal(local);
        this.setConvidados(convidados);
    }


    public String getNome_festa() {
        return nome_festa;
    }

    public void setNome_festa(String nome_festa) {
        this.nome_festa = nome_festa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getConvidados() {
        return convidados;
    }

    public void setConvidados(int convidados) {
        this.convidados = convidados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
