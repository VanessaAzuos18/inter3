package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Usuario;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    private String nomeUsuario;

    @FXML
    private AnchorPane container;

    @FXML
    private Text nome;
    @FXML
    private Label tipo;
    @FXML
    private AnchorPane menu;
    @FXML
    private Button menuBotao;
    @FXML
    private Text txtSair;
    @FXML
    private TableView<Festa> tabela;
    @FXML
    private TableColumn<Festa, Integer> id;
    @FXML
    private TableColumn<Festa, String> nome_festa;
    @FXML
    private TableColumn<Festa, String> local;
    @FXML
    private TableColumn<Festa, String> data;
    @FXML
    private TableColumn<Festa, Integer> convidados;
    @FXML
    private AnchorPane cliente;
    @FXML
    private AnchorPane fornecedor;

    @FXML
    private Text nomeFornecedor;
    @FXML
    private Label tipoFornecedor;
    @FXML
    private AnchorPane menuFornecedor;
    @FXML
    private TableView<Festa> tabelaFornecedor;
    @FXML
    private TableColumn<Festa, Integer> idFornecedor;
    @FXML
    private TableColumn<Festa, String> nome_festaFornecedor;
    @FXML
    private TableColumn<Festa, String> dataFornecedor;
    @FXML
    private TableColumn<Festa, String> localFornecedor;
    @FXML
    private TableColumn<Festa, Integer> convidadosFornecedor;

    private Usuario usuario;

    FestaDAO festaDAO = new FestaDAO();

    public void setNome(String nomeUsuario){
        this.nome.setText(nomeUsuario);
    }

    public void handleMenu(ActionEvent e) {
        if(menu.isVisible() == false) {
            menu.setVisible(true);
        } else {
            menu.setVisible(false);
        }
    }

    public void handleMenuFornecedor(ActionEvent e) {
        if(menuFornecedor.isVisible() == false) {
            menuFornecedor.setVisible(true);
        } else {
            menuFornecedor.setVisible(false);
        }
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
        if(usuario.getTipo() == 1) {
            montaTelaCliente();
        } else {
            montaTelaFornecedor();
        }
    }

    @FXML
    void aa(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso de confirmação");
        alert.setHeaderText("Você realmente deseja sair do sistema?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            container.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setVisible(false);
        menuFornecedor.setVisible(false);
    }

    public void remover(ActionEvent e) {
        Festa festaRemover = tabela.getSelectionModel().getSelectedItem();
        if(festaDAO.remover(festaRemover.getId(), usuario.getId()))
            tabela.setItems(festaDAO.carregaFestas(usuario.getId()));
        else
            JOptionPane.showMessageDialog(null, "Erro ao remover a festa!");
    }

    @FXML
    void handleListaConvidado(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/ListaConvidados.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Lista de convidados");
        stage.setScene(scene);

        ListaConvidadosController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @FXML
    void handleIniciarFesta(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/IniciarFesta.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Iniciar Festa");
        stage.setScene(scene);

        IniciarFestaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    private void montaTelaCliente() {
        cliente.setVisible(true);
        fornecedor.setVisible(false);
        tipo.setText("Cliente");
        nome.setText(usuario.getNome());

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        nome_festa.setCellValueFactory(
                new PropertyValueFactory<>("nome_festa"));
        data.setCellValueFactory(
                new PropertyValueFactory<>("Data"));
        local.setCellValueFactory(
                new PropertyValueFactory<>("Local"));
        convidados.setCellValueFactory(
                new PropertyValueFactory<>("Convidados"));

        tabela.setItems(festaDAO.carregaFestas(usuario.getId()));
    }

    private void montaTelaFornecedor() {
        cliente.setVisible(false);
        fornecedor.setVisible(true);
        tipoFornecedor.setText("Fornecedor / Prestador");
        nomeFornecedor.setText(usuario.getNome());

        idFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        nome_festaFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("nome_festa"));
        dataFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("Data"));
        localFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("Local"));
        convidadosFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("Convidados"));

        tabelaFornecedor.setItems(festaDAO.carregaFestas(usuario.getId()));
    }
}
