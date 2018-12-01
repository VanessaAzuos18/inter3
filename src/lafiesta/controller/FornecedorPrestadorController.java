package lafiesta.controller;

import javafx.event.ActionEvent;
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
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Aluguel;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class FornecedorPrestadorController implements Initializable {

    @FXML
    private Button buscar;
    @FXML
    private TableView<Aluguel> tabelaAuxilio;
    @FXML
    private TableColumn<Aluguel, Integer> id;
    @FXML
    private TableColumn<Aluguel, String> tipo;
    @FXML
    private TableColumn<Aluguel, String> cidade;
    @FXML
    private Button voltar;

    private Usuario usuario;
    AluguelDAO aluguelDAO = new AluguelDAO();
    FestaDAO festaDAO = new FestaDAO();
    Timer timer = new Timer();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final long SEGUNDOS = (1000 * 5);

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        tipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        cidade.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));

        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                int idFesta = festaDAO.buscarId(usuario.getId());
                tabelaAuxilio.setItems(aluguelDAO.obterAlugueis(idFesta));
            }
        };

        timer.scheduleAtFixedRate(tarefa, 5000, SEGUNDOS);
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

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void voltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Utensilio.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Buscar Auxilio");
        stage.setScene(scene);

        UtensilioController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();
        voltar.getScene().getWindow().hide();
    }
}
