package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.ComidaDAO;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Comida;
import lafiesta.model.domain.GrupoComida;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComidaController implements Initializable {
    @FXML
    private ComboBox cbTipoComida;
    @FXML
    private ComboBox cbServido;
    @FXML
    private TableView<Comida> tabela;
    @FXML
    private TableColumn<Comida, String> colunaGrupoComida;
    @FXML
    private TableColumn<Comida, String> colunaTipoComida;
    @FXML
    private TableColumn<Comida, String> colunaQuantidade;
    @FXML
    private Button voltara;

    private Usuario usuario;

    public void handleAvancar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Bebida.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Cadastrar Bebida");
        stage.setScene(scene);

        BebidaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltara.getScene().getWindow().hide();
    }

    public void handleVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/IniciarFesta.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Iniciar Festa");
        stage.setScene(scene);

        IniciarFestaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltara.getScene().getWindow().hide();
    }

    public void handleTipoComida(ActionEvent e) {
        int index = 9;

        if(index != cbTipoComida.getSelectionModel().getSelectedIndex()) {
            index = cbTipoComida.getSelectionModel().getSelectedIndex();
            ComidaDAO comidaDAO = new ComidaDAO();
            ObservableList<String> tipoComida = FXCollections.observableArrayList(comidaDAO.obterTipoComida(index+1));
            cbServido.setItems(tipoComida);
        }

    }

    public void handleAdicionar(ActionEvent e) { calcularComida(cbTipoComida, cbServido); }

    private void calcularComida(ComboBox grupoComida, ComboBox tipoComida) {
        Comida comida = new Comida();

        boolean flag = comida.calcularComida(grupoComida.getValue().toString(), tipoComida.getSelectionModel().getSelectedIndex(), usuario.getId(), tipoComida.getValue().toString());

        if(flag) {
            ComidaDAO comidaDAO = new ComidaDAO();
            tabela.setItems(comidaDAO.obterComida(usuario.getId()));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComidaDAO comidaDAO = new ComidaDAO();
        ObservableList<String> grupo = FXCollections.observableArrayList(comidaDAO.obterGrupoComidas());
        cbTipoComida.setItems(grupo);

        colunaGrupoComida.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoComida.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
