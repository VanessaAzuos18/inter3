package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.AluguelDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.ProdutoDAO;
import lafiesta.model.domain.Produto;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarFornecedorController implements Initializable {
    @FXML
    private ComboBox cbCategoria;
    @FXML
    private TableView<Produto> tabelaAuxilio;
    @FXML
    private TableColumn<Produto, String> id;
    @FXML
    private TableColumn<Produto, String> tipo;
    @FXML
    private TableColumn<Produto, String> colCidade;
    @FXML
    private TableColumn<Produto, String> nome;
    @FXML
    private TextField textCidade;

    private Usuario usuario;
    ProdutoDAO produtoDAO = new ProdutoDAO();
    FestaDAO festaDAO = new FestaDAO();
    AluguelDAO aluguelDAO = new AluguelDAO();
    FornecedorPrestadorController fornecedorPrestadorController = new FornecedorPrestadorController();

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public void handleBuscarAuxilio(ActionEvent e) throws IOException {
        String tipo = cbCategoria.getSelectionModel().getSelectedItem().toString();
        tabelaAuxilio.setItems(produtoDAO.buscarProdutos(tipo, textCidade.getText()));
    }

    public void handleVoltar(ActionEvent e) throws IOException{
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

        textCidade.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tipoCategoria = FXCollections.observableArrayList(produtoDAO.obterCategorias());
        cbCategoria.setItems(tipoCategoria);

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        tipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colCidade.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));
        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
    }
}
