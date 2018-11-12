package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LocalizarFestaController implements Initializable {
    @FXML
    private TableView tabela;
    @FXML
    private TextField cidade;

    public void handleLocalizar(ActionEvent e) {

    }

    public void handleSelecionar(ActionEvent e) {

    }

    public void handleFecharTela(ActionEvent e) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabela.setPlaceholder(new Label(""));
    }
}
