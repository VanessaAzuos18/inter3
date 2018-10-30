package lafiesta.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaInicialController extends Application {

    private String nomeUsuario;

    @FXML
    private Label nome;


    public void setNome(String nomeUsuario){
        this.nome.setText(nomeUsuario);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
}
