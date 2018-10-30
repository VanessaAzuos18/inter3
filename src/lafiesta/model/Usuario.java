package lafiesta.model;

public class Usuario {

    private int id;
    private String nome;
    private String nome_usuario;
    private String endereco;
    private String telefone;
    private String senha;
    private String email;
    private int tipo;
    private String cpf;
    private String cnpj;

    public void setUsuario(int id, String nome, String nome_usuario, String endereco, String telefone, String senha, String email, int tipo, String cpf, String cnpj){
        this.id = id;
        this.nome = nome;
        this.nome_usuario = nome_usuario;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    public String getNome(){
        return this.nome;
    }
}
