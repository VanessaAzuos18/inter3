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
}
