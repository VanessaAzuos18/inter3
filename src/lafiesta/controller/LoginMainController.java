package lafiesta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.domain.Usuario;
import lafiesta.model.dao.UsuarioDAO;

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
    private Hyperlink recuperarSenha = new Hyperlink();

    @FXML
    public void login(ActionEvent event) throws IOException {
        String loginDigitado = campoLoginEmail.getText();
        String senhaDigitado = campoLoginSenha.getText();

        //Usuario usuario = new Login().verificaUsuario(loginDigitado, senhaDigitado);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean flag = usuarioDAO.verificarUsuario(loginDigitado, senhaDigitado);

        Usuario usuario;

        if(flag) {
            usuario = usuarioDAO.carregarUsuario(loginDigitado, senhaDigitado);
        } else {
            usuario = null;
        }

        if (usuario != null) {
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

            botaoCadastro.getScene().getWindow().hide();
        } else JOptionPane.showMessageDialog(null, "Usuário não encontrado!");

    }

    @FXML
    public void handleBotaoCadastro(ActionEvent event) throws IOException {
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

        CadastroController teste = new CadastroController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();

        botaoCadastro.getScene().getWindow().hide();
    }

    public void handlerecuperarSenha(ActionEvent event) throws IOException {
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

        root = FXMLLoader.load(getClass().getResource("../view/RecuperarSenha.fxml"));

        RecuperarSenhaController teste = new RecuperarSenhaController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Recuperar Senha");
        stage.setScene(scene);
        stage.show();

        botaoCadastro.getScene().getWindow().hide();
    }
}