package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.ProdutoDAO;
import lafiesta.model.domain.Produto;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroServProdController implements Initializable {

    @FXML
    private ComboBox cbTipoProduto;
    @FXML
    private ComboBox cbProduto;
    @FXML
    private TextField cidade;
    @FXML
    private TextArea obs;
    @FXML
    private Button voltara;

    private Usuario usuario;
    ProdutoDAO produtoDAO = new ProdutoDAO();
    FestaDAO festaDAO = new FestaDAO();

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void handleCadastrar(ActionEvent e) throws IOException {
        int idFesta = festaDAO.buscarId(usuario.getId());
        String tipo = cbTipoProduto.getSelectionModel().getSelectedItem().toString();
        String nomeProduto = cbProduto.getSelectionModel().getSelectedItem().toString();

        Produto produto = new Produto(tipo, nomeProduto, cidade.getText(), obs.getText());
        produto.setIdUsuario(usuario.getId());
        if(produtoDAO.cadastrarProduto(produto)){
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }else JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
    }

    public void handleVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/TelaInicial.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Tela Inicial");
        stage.setScene(scene);

        TelaInicialController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltara.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tipoProduto = FXCollections.observableArrayList(produtoDAO.obterTipoProduto());
        cbTipoProduto.setItems(tipoProduto);
    }

    public void handleProdutos(ActionEvent e) {
        int index = cbTipoProduto.getSelectionModel().getSelectedIndex();
        ObservableList<String> produtos = FXCollections.observableArrayList(produtoDAO.obterProdutos(index+1));
        cbProduto.setItems(produtos);
    }
}