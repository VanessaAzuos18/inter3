package lafiesta.model.dao;

import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public boolean verificarUsuario(String nome_usuario, String senha) {
        try {
            String sql = "SELECT * FROM usuario where nome = '" + nome_usuario + "' and senha = '" + senha + "';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            if(!rset.next()) {
                return false;
            } else {
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Usuario carregarUsuario(String nome_usuario, String senha) {
        try {
            String sql = "SELECT * FROM usuario where nome = '" + nome_usuario + "' and senha = '" + senha + "';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            if(!rset.next()) {
                return null;
            } else {
                Usuario usuario = new Usuario();
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setNome_usuario(rset.getString("nome_usuario"));
                usuario.setEndereco(rset.getString("endereco"));
                usuario.setTelefone(rset.getString("telefone"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setEmail(rset.getString("email"));
                usuario.setTipo(rset.getInt("tipo"));
                usuario.setCpf(rset.getString("cpf"));
                usuario.setCnpj(rset.getString("cnpj"));

                return usuario;
            }
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean cadastrar(Usuario usuario){
        try {
            String sql = null;

            if(usuario.getTipo() == 1){
                sql = "insert into usuario (nome, nome_usuario, endereco, telefone, senha, email, tipo, cpf) values (\"" + usuario.getNome() + "\", \"" + usuario.getNome() + "\", \""+
                        usuario.getEndereco() + "\", \"" + usuario.getTelefone() + "\", \"" + usuario.getSenha() + "\", \"" + usuario.getEmail() + "\", " + usuario.getTipo() + ", \"" + usuario.getCpf() + "\");";
            }else if(usuario.getTipo() == 2){
                sql = "insert into usuario (nome, nome_usuario, endereco, telefone, senha, email, tipo, cnpj) values (\"" + usuario.getNome() + "\", \"" + usuario.getNome() + "\", \""+
                        usuario.getEndereco() + "\", \"" + usuario.getTelefone() + "\", \"" + usuario.getSenha() + "\", \"" + usuario.getEmail() + "\", " + usuario.getTipo() + ", \"" + usuario.getCnpj() + "\");";
            }

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean atualizar(Usuario usuario){
        try {
            String sql = null;

            if(usuario.getTipo() == 1){
                sql = "UPDATE usuario SET nome =  \"" + usuario.getNome() + "\", nome_usuario = \"" + usuario.getNome() + "\", endereco = \"" + usuario.getEndereco() +
                      "\", telefone = \"" + usuario.getTelefone() + "\", senha = \"" + usuario.getSenha() + "\", email = \"" + usuario.getEmail() + "\", cpf = \"" +
                      usuario.getCpf() + "\" WHERE id = " + usuario.getId() + ";";
            }else if(usuario.getTipo() == 2){
                sql = "UPDATE usuario SET nome =  \"" + usuario.getNome() + "\", nome_usuario = \"" + usuario.getNome() + "\", endereco = \"" + usuario.getEndereco() +
                        "\", telefone = \"" + usuario.getTelefone() + "\", senha = \"" + usuario.getSenha() + "\", email = \"" + usuario.getEmail() + "\", cnpj = \"" +
                        usuario.getCnpj() + "\" WHERE id = " + usuario.getId() + ";";
            }

            System.out.println(sql);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
