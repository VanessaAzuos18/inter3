package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Utensilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtensilioDAO {
    private Connection connection;

    public UtensilioDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public List<String> obterGrupoUtensilio() {
        List<String> grupoUtensilio = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM grupo_utensilio;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                grupoUtensilio.add(rset.getString("nome"));
            }
            return grupoUtensilio;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<String> obterTipoUtensilio(int idGrupo) {
        List<String> tipoUtensilio = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM tipo_utensilio WHERE id_grupo = " + idGrupo + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                tipoUtensilio.add(rset.getString("nome"));
            }
            return tipoUtensilio;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int verificarUtensilio(int idFesta, String tipo) {
        int idComida = 0;

        try {
            String sql = "SELECT * FROM utensilio WHERE id_festa = " + idFesta + " AND tipo = '" + tipo +"';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            if(rset.next()) {
                idComida = rset.getInt("id");
            }
            return idComida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean cadastrarUtensilio(Utensilio utensilio) {
        try {
            String sql = "INSERT INTO utensilio(grupo, tipo, id_festa, quantidade) values (\"" + utensilio.getGrupo() + "\", \"" + utensilio.getTipo() + "\", \"" + utensilio.getIdFesta() + "\", \"" + utensilio.getQuantidade() + "\");";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Utensilio> obterUtensilio(int idFesta) {
        ObservableList<Utensilio> utensilio = FXCollections.observableArrayList();
        try {
            String sql = "SELECT u.id, g.nome, u.tipo, u.quantidade FROM utensilio u, tipo_utensilio t, grupo_utensilio g WHERE id_festa = " + idFesta + " AND u.tipo = t.nome AND t.id_grupo = g.id ORDER BY u.id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                utensilio.add(new Utensilio(rset.getString("nome"), rset.getString("tipo"), rset.getString("quantidade")));
            }
            return utensilio;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int obterTotalUtensilio(int idFesta) {
        int quantidadeComida = 0;

        try {
            String sql = "SELECT COUNT(*) FROM utensilio WHERE id_festa = " + idFesta + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()) {
                quantidadeComida = rset.getInt(1);
            }

            return quantidadeComida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
