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
}