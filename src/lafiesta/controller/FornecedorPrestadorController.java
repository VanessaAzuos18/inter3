package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FornecedorPrestadorController implements Initializable {

    @FXML
    private Button buscar;

    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleBuscarFornecPrestador(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/BuscarFornecedorPrestador.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Buscar Auxilio");
        stage.setScene(scene);

        BuscarFornecedorPrestadorController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
