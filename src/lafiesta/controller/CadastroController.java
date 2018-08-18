package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {
    @FXML
    private Button voltar;

    public void handlerBotaoVoltar(ActionEvent event) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

        voltar.getScene().getWindow().hide();
    }
}
