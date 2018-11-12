package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    private String nomeUsuario;

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

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
        nome.setText(usuario.getNome());
        if(usuario.getTipo() == 1) tipo.setText("Cliente");
        else tipo.setText("Fornecedor / Prestador");

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

    @FXML
    void aa(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setVisible(false);
    }

    public void remover(ActionEvent e) {
        Festa festaRemover = tabela.getSelectionModel().getSelectedItem();
        if(festaDAO.remover(festaRemover.getId(), usuario.getId()))
            tabela.setItems(festaDAO.carregaFestas(usuario.getId()));
        else
            JOptionPane.showMessageDialog(null, "Erro ao remover a festa!");
    }
}
