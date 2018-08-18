package lafiesta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;

public class LoginMainController {
    @FXML
    private TextField campoLoginEmail;
    @FXML
    private TextField campoLoginSenha;
    @FXML
    private Button botaoCadastro;

    @FXML
    public void login(ActionEvent event) {
        String loginDigitado = campoLoginEmail.getText();
        String senhaDigitado = campoLoginSenha.getText();
        String login = "teste";
        String senha = "teste";

        if(loginDigitado.equals(login) && senhaDigitado.equals(senha)) {
            System.out.println("LOGIN SUCESSO");
            JOptionPane.showMessageDialog(null, "Login com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Login errado");
        }
    }

    @FXML
    public void handleBotaoCadastro (ActionEvent event) throws IOException{
        /*
        Stage stage;
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../view/Cadastro.fxml"));

        Scene scene = new Scene(root);

        stage = (Stage) botaoCadastro.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        */

        Parent root;

        root = FXMLLoader.load(getClass().getResource("../view/Cadastro.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();

        botaoCadastro.getScene().getWindow().hide();
    }
}
