package lafiesta.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.ProdutoDAO;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Produto;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarFornecedorPrestadorController implements Initializable {

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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public void handleSelecionar(ActionEvent e) throws IOException {

    }

    public void handleBuscarAuxilio(ActionEvent e) throws IOException {
        String tipo = cbCategoria.getSelectionModel().getSelectedItem().toString();
        tabelaAuxilio.setItems(produtoDAO.buscarProdutos(tipo, textCidade.getText()));


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