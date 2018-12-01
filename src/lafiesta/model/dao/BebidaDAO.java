package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BebidaDAO {
    private Connection connection;

    public BebidaDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public List<String> obterGrupoBebida() {
        List<String> grupoBebida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM grupo_bebida;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                grupoBebida.add(rset.getString("nome"));
            }
            return grupoBebida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<String> obterTipoBebida(int idGrupo) {
        List<String> tipoBebida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM tipo_bebida WHERE id_grupo = " + idGrupo + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                tipoBebida.add(rset.getString("nome"));
            }
            return tipoBebida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int verificarBebida(int idUsuario, int idFesta, String tipo) {
        int idComida = 0;

        try {
            String sql = "SELECT * FROM bebida WHERE id_festa = " + idFesta + " AND tipo = '" + tipo +"';";
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

    public boolean cadastrarBebida(Bebida bebida) {
        try {
            String sql = "INSERT INTO bebida(grupo, tipo, id_festa, quantidade) values (\"" + bebida.getGrupo() + "\", \"" + bebida.getTipo() + "\", \"" + bebida.getIdFesta() + "\", \"" + bebida.getQuantidade() + "\");";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Bebida> obterBebida(int idFesta) {
        ObservableList<Bebida> bebida = FXCollections.observableArrayList();
        try {
            String sql = "SELECT c.id, g.nome, c.tipo, c.quantidade FROM bebida c, tipo_bebida t, grupo_bebida g WHERE id_festa = " + idFesta + " AND c.tipo = t.nome AND t.id_grupo = g.id ORDER BY c.id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                bebida.add(new Bebida(rset.getString("nome"), rset.getString("tipo"), rset.getString("quantidade")));
            }
            return bebida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int obterTotalBebida(int idFesta) {
        int quantidadeComida = 0;

        try {
            String sql = "SELECT COUNT(*) FROM bebida WHERE id_festa = " + idFesta + ";";
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
