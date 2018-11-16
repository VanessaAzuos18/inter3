package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FestaDAO {
    private Connection connection;

    public FestaDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public ObservableList<Festa> carregaFestas(int id) {
        ObservableList<Festa> festas = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM festa where id_usuario = " + id + " order by id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                festas.add( new Festa(rset.getInt("id"), rset.getString("nome_festa"),
                        rset.getString("data"),rset.getString("local"), rset.getInt("convidados")));
            }
            return festas;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean remover(int index, int id){
        try {
            String sql = null;

            sql = "delete from festa where id = " + index + " and id_usuario = " + id;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean adicionar(Festa festa, int idUsuario){
        try {
            String sql = null;

            sql = "insert into festa (local, id_usuario, convidados, data, nome_festa) values (\"" + festa.getLocal() + "\", " +
            idUsuario + ", " + festa.getConvidados() + ", \"" + festa.getData() + "\", \"" + festa.getNome_festa() + "\")";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
