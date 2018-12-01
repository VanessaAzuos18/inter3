package lafiesta.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lafiesta.model.database.BDFabricaConexao;
import lafiesta.model.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() {
        connection = BDFabricaConexao.getConnection();
    }

    public List<String> obterTipoProduto() {
        List<String> tipoProduto = new ArrayList<String>();
        tipoProduto.add("PRODUTO");
        tipoProduto.add("SERVIÇO");
        return tipoProduto;
    }

    public List<String> obterProdutos(int idGrupo) {
        List<String> tipoBebida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM tipo_produto WHERE id_grupo = " + idGrupo + ";";
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

    public boolean cadastrarProduto(Produto produto) {
        try {
            String sql = "INSERT INTO produto(id_fornecedor, tipo, observacao, cidade) values (\"" + produto.getIdUsuario() + "\", \"" + produto.getProduto() + "\", \"" + produto.getObservacao() + "\", \"" + produto.getCidade() + "\");";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Produto> obterMeusProdutosServicos(int idUsuario) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM produto where id_fornecedor = " + idUsuario + " order by id;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                produtos.add(new Produto(rset.getInt("id"), rset.getString("tipo"),
                        rset.getString("observacao"),rset.getString("cidade")));
            }
            return produtos;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean remover(int index, int id){
        try {
            String sql = null;

            sql = "delete from produto where id = " + index + " and id_fornecedor = " + id;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Produto> obterProdutos(String categoria, String cidade) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        try {
            String sql = "select p.id, p.tipo, p.cidade, u.nome from produto p inner join usuario u on p.id_fornecedor = u.id where p.cidade = '"
                    + cidade + "' and p.categoria = '" + categoria + "';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                produtos.add(new Produto(rset.getInt("id"), rset.getString("tipo"),
                        rset.getString("cidade"),rset.getString("nome"), "busca"));
            }
            return produtos;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ObservableList<Produto> buscarProdutos(String categoria, String cidade) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        try {
            String sql = "select p.id, p.tipo, p.cidade, u.nome from produto p inner join usuario u on p.id_fornecedor = u.id where p.tipo = '" + categoria + "' and p.cidade = '" + cidade + "'";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                produtos.add(new Produto(rset.getInt("id"), rset.getString("tipo"),
                        rset.getString("cidade"),rset.getString("nome"), "busca"));
            }
            return produtos;
        } catch(SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<String> obterCategorias() {
        List<String> tipoBebida = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM tipo_produto;";
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
}
