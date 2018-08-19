package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {
    @FXML
    private Button voltar;
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private AnchorPane telaCadastroFornecedor;

    public void handleBotaoVoltar(ActionEvent event) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

        voltar.getScene().getWindow().hide();
    }

    public void handleExibeTelaCadastroFornecedor(ActionEvent event) throws IOException {
        telaCadastroCliente.setVisible(false);
        telaCadastroFornecedor.setVisible(true);
    }

    public void handleExibeTelaCadastroCliente(ActionEvent event) {
        telaCadastroFornecedor.setVisible(false);
        telaCadastroCliente.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        telaCadastroFornecedor.setVisible(false);
    }
}
