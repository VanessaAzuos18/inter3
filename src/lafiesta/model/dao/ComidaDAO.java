package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Comida;
import lafiesta.model.domain.Convidado;
import lafiesta.model.domain.GrupoComida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComidaDAO {
    private Connection connection;

    public ComidaDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public List<String> obterGrupoComidas() {
        List<String> grupoComida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM grupo_comida;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                grupoComida.add(rset.getString("nome"));
            }
            return grupoComida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<String> obterTipoComida(int id) {
        List<String> tipoComida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM tipo_comida WHERE id_grupo = " + id + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                tipoComida.add(rset.getString("nome"));
            }
            return tipoComida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int buscarQuantidadeCarne(int id) {
        FestaDAO festaDAO = new FestaDAO();
        int quantidadeCarne = 0;

        try {
            String sql = "SELECT COUNT(*) FROM comida WHERE id_festa = " + festaDAO.buscarId(id) + " AND (grupo = 'CHURRASCO' AND (tipo LIKE '%CARNE%' OR tipo LIKE '%LINGUI%'));";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()) {
                quantidadeCarne = rset.getInt(1);
            }
            System.out.println(quantidadeCarne);
            return quantidadeCarne;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean atualizarQuantiadeCarne(String quantidade, int id) {
        FestaDAO festaDAO = new FestaDAO();

        try {
            String sql = "UPDATE comida SET quantidade = " + quantidade + " WHERE id_festa = " + festaDAO.buscarId(id) + " AND (grupo = 'CHURRASCO' AND (tipo LIKE '%CARNE%' OR tipo LIKE '%LINGUI%'));";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean cadastrarComida(int id, Comida comida) {
        try {
            String sql = "INSERT INTO comida(grupo, tipo, id_festa, quantidade) values (\"" + comida.getGrupo() + "\", \"" + comida.getTipo() + "\", \"" + comida.getIdFesta() + "\", \"" + comida.getQuantidade() + "\");";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Comida> obterComida(int id) {
        ObservableList<Comida> comida = FXCollections.observableArrayList();
        FestaDAO festaDAO = new FestaDAO();
        try {
            String sql = "SELECT c.id, g.nome, c.tipo, c.quantidade FROM comida c, tipo_comida t, grupo_comida g WHERE id_festa = " + festaDAO.buscarId(id) + " AND c.tipo = t.nome AND t.id_grupo = g.id ORDER BY c.id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                comida.add(new Comida(rset.getString("nome"), rset.getString("tipo"), rset.getString("quantidade")));
            }
            return comida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int verificarComida(int id, String tipo) {
        FestaDAO festaDAO = new FestaDAO();
        int idComida = 0;

        try {
            String sql = "SELECT * FROM comida WHERE id_festa = " + festaDAO.buscarId(id) + " AND tipo = '" + tipo +"';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()) {
                idComida = rset.getInt("id");
            }

            return idComida;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
