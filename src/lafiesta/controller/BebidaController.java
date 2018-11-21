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
import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BebidaController implements Initializable {
    @FXML
    private ComboBox cbTipoBebida;
    @FXML
    private ComboBox cbServido;
    @FXML
    private TableView<Bebida> tabela;
    @FXML
    private TableColumn<Bebida, String> colunaGrupoBebida;
    @FXML
    private TableColumn<Bebida, String> colunaTipoBebida;
    @FXML
    private TableColumn<Bebida, String> colunaQuantidade;
    @FXML
    private Button voltar;

    private Usuario usuario;

    public void handleAvancar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Utensilio.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Cadastrar Utensilio");
        stage.setScene(scene);

        UtensilioController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltar.getScene().getWindow().hide();
    }

    public void handleVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Comida.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Cadastrar Comida");
        stage.setScene(scene);

        ComidaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltar.getScene().getWindow().hide();
    }

    public void handleAdicionar(ActionEvent e) {
        obterCalculoComida(cbTipoBebida, cbServido);
    }

    public void handleTipoComida(ActionEvent e) {
        int index = 9;

        if(index != cbTipoBebida.getSelectionModel().getSelectedIndex()) {
            index = cbTipoBebida.getSelectionModel().getSelectedIndex();
            BebidaDAO bebidaDAO = new BebidaDAO();
            ObservableList<String> tipoBebida = FXCollections.observableArrayList(bebidaDAO.obterTipoBebida(index+1));
            cbServido.setItems(tipoBebida);
        }
    }

    public void obterCalculoComida(ComboBox grupoBebida, ComboBox tipoBebida) {
        Bebida bebida = new Bebida();
        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(usuario.getId());

        boolean flag = bebida.calcularBebida(grupoBebida.getValue().toString(),
                tipoBebida.getSelectionModel().getSelectedIndex(), usuario.getId(),
                tipoBebida.getValue().toString());

        if(flag) {
            BebidaDAO bebidaDAO = new BebidaDAO();
            tabela.setItems(bebidaDAO.obterBebida(idFesta));
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BebidaDAO bebidaDAO = new BebidaDAO();
        ObservableList<String> grupoBebida = FXCollections.observableArrayList(bebidaDAO.obterGrupoBebida());
        cbTipoBebida.setItems(grupoBebida);

        colunaGrupoBebida.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoBebida.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
    }
}
