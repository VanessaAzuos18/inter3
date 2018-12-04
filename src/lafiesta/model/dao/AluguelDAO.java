package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Aluguel;
import lafiesta.model.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AluguelDAO {
    private Connection connection;

    public AluguelDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public ObservableList<Aluguel> obterAlugueis(int idFesta) {
        ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
        try {
            String sql = "select p.id, p.tipo, p.cidade from produto p inner join aluguel a on a.id_produto = p.id where a.id_festa = " + idFesta + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                alugueis.add(new Aluguel(rset.getInt("id"), rset.getString("tipo"),
                        rset.getString("cidade")));
            }
            return alugueis;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int obterTotalAluguel(int idFesta) {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) FROM aluguel where id_festa = " + idFesta + " order by id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                total = rset.getInt(1);
            }
            return total;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean cadastrarAluguel(int idProduto, int idFesta) {
        try {
            String sql = "INSERT INTO aluguel(id_produto, id_festa) values (" + idProduto + ", "  +idFesta + ");";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Aluguel> agendaFestas(int idUsuario) {
        ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
        try {
            String sql = "select p.tipo, p.cidade, f.nome_festa, f.data, u.nome, u.telefone from usuario u, produto p, \n" +
                    "aluguel a inner join festa f on f.id = a.id_festa where a.id_produto = p.id\n" +
                    "and f.id_usuario = u.id and id_fornecedor = " + idUsuario + ";";

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                alugueis.add(new Aluguel(rset.getString("tipo"), rset.getString("cidade"),
                        rset.getString("nome_festa"), rset.getString("data"),
                        rset.getString("nome"), rset.getString("telefone")));
            }
            return alugueis;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
