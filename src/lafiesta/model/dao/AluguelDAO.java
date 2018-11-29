package lafiesta.model.dao;

import lafiesta.model.database.BDFabricaConexao;

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

    public List<String> obterAlugueis() {
        List<String> grupoBebida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM aluguel;";
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
}
