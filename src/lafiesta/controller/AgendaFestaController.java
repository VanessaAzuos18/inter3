package lafiesta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.AluguelDAO;
import lafiesta.model.domain.Aluguel;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgendaFestaController implements Initializable {

    @FXML
    private TableView<Aluguel> tabelaAgenda;
    @FXML
    private TableColumn<Aluguel, String> tipo;
    @FXML
    private TableColumn<Aluguel, String> cidade;
    @FXML
    private TableColumn<Aluguel, String> nome_festa;
    @FXML
    private TableColumn<Aluguel, String> data;
    @FXML
    private TableColumn<Aluguel, String> nome_usuario;
    @FXML
    private TableColumn<Aluguel, String> telefone;
    @FXML
    private Button voltar;

    AluguelDAO aluguelDAO = new AluguelDAO();
    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        cidade.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));
        nome_festa.setCellValueFactory(
                new PropertyValueFactory<>("nome_festa"));
        data.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        nome_usuario.setCellValueFactory(
                new PropertyValueFactory<>("nome_usuario"));
        telefone.setCellValueFactory(
                new PropertyValueFactory<>("tel_usuario"));
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        tabelaAgenda.setItems(aluguelDAO.agendaFestas(usuario.getId()));
    }

    public void handleVoltar() throws IOException {
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

        voltar.getScene().getWindow().hide();
    }
}
