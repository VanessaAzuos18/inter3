package lafiesta.model;
import lafiesta.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    public Usuario verificaUsuario(String loginDigitado, String senhaDigitado){
        try {
            Connection con = (Connection) BDFabricaConexao.getConnection();
            String sql = "SELECT * FROM usuario where nome = '" + loginDigitado + "' and senha = '" + senhaDigitado + "';";
            Statement stm = (Statement) con.createStatement();
            ResultSet rset = stm.executeQuery(sql);

            if(!rset.next()) return null;
            else {
                Usuario usuario = new Usuario();
                usuario.setUsuario(rset.getInt("id"), rset.getString("nome"), rset.getString("nome_usuario"),
                        rset.getString("endereco"), rset.getString("telefone"), rset.getString("senha"),
                        rset.getString("email"), rset.getInt("tipo"), rset.getString("cpf"), rset.getString("cnpj"));
                return usuario;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
