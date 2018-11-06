package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    @FXML
    private Button botaoCadastroCliente;
    @FXML
    private Button botaoCadastroFornecedor;
    @FXML
    private ImageView imagemAba1;

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

    public void voltar(MouseEvent mouseEvent) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

        CadastroController teste = new CadastroController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) voltar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }
}
