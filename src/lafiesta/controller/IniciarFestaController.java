package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IniciarFestaController implements Initializable {
    @FXML
    private TextField nomeFesta;
    @FXML
    private TextField data;
    @FXML
    private TextField local;
    @FXML
    private TextField numeroConvidados;
    @FXML
    private Button voltara;

    private Usuario usuario;
    private ConvidadoDAO convidadoDAO = new ConvidadoDAO();
    private FestaDAO festaDAO = new FestaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleLocalizar(ActionEvent e) {

    }

    public void handleCadastrar(ActionEvent e) throws IOException {
        Festa festa = new Festa(0, nomeFesta.getText(), data.getText(), local.getText(), Integer.parseInt(numeroConvidados.getText()));

        if(festaDAO.adicionar(festa, usuario.getId())){
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("../view/Comida.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(anchorPane);

            stage.setTitle("Cadastrar Comida");
            stage.setScene(scene);

            ComidaController controller = loader.getController();
            controller.setUsuario(usuario);

            stage.show();

            voltara.getScene().getWindow().hide();
        }
        else JOptionPane.showMessageDialog(null, "Erro ao cadastrar festa!");
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        numeroConvidados.setText((convidadoDAO.carregarTotalConvidados(usuario.getId())));
    }
}
