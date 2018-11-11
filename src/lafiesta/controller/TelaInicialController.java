package lafiesta.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.domain.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    private String nomeUsuario;


    @FXML
    private Label nome;
    @FXML
    private AnchorPane menu;
    @FXML
    private Button menuBotao;

    private Usuario usuario;

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
        System.out.println(usuario.getTipo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setVisible(false);
    }

}
