package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Convidado;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvidadoDAO {
    private Connection connection;

    public ConvidadoDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public boolean cadastrarConvidado(Convidado convidado) {
        try {
            CallableStatement stmt = connection.prepareCall("{call cadastrarConvidado(?, ?, ?, ?)}");
            stmt.setString(1, convidado.getNome());
            stmt.setInt(2, convidado.getIdade());
            stmt.setString(3, convidado.getSexo());
            stmt.setInt(4, convidado.getIdUsuario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Convidado> carregarConvidados(int id) {
        ObservableList<Convidado> convidados = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM convidado where id_usuario = " + id + " order by id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                convidados.add(new Convidado(rset.getString("nome"), rset.getInt("idade"),
                        rset.getString("sexo")));
            }
            return convidados;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String carregarTotalConvidados(int id) {
        try {
            String sql = "SELECT count(*) as total FROM convidado where id_usuario = " + id + " order by id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                String total = String.valueOf(rset.getInt("total"));
                return total;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }
        return "0";
    }

    public int[] contarPessoasGrupo(int idUsuario) {
        int[] totalPessoasGrupo = new int[3];
        try {
            CallableStatement stmt = connection.prepareCall("{call contarPessoasGrupo(?, ?, ?, ?)}");
            stmt.setInt(1,idUsuario);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.execute();
            totalPessoasGrupo[0] = stmt.getInt(2);
            totalPessoasGrupo[1] = stmt.getInt(3);
            totalPessoasGrupo[2] = stmt.getInt(4);

            return totalPessoasGrupo;
        } catch(SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
