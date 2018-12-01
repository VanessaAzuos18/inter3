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
import lafiesta.model.dao.UtensilioDAO;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Usuario;
import lafiesta.model.domain.Utensilio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UtensilioController implements Initializable {
    @FXML
    private ComboBox cbGrupoUtensilio;
    @FXML
    private ComboBox cbTipoUtensilio;
    @FXML
    private TableView<Utensilio> tabela;
    @FXML
    private TableColumn<Utensilio, String> colunaGrupoUtensilio;
    @FXML
    private TableColumn<Utensilio, String> colunaTipoUtensilio;
    @FXML
    private TableColumn<Utensilio, String> colunaQuantidadeUtensilio;
    @FXML
    private Button voltara;

    private Usuario usuario;


    public void handleAvancar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/FornecedorPrestador.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Fornecedor Prestador");
        stage.setScene(scene);

        FornecedorPrestadorController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltara.getScene().getWindow().hide();
    }

    public void handleVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Bebida.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Cadastrar Utensilio");
        stage.setScene(scene);

        BebidaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltara.getScene().getWindow().hide();
    }

    public void handleAdicionar(ActionEvent e) {
        obterCalculoComida(cbGrupoUtensilio, cbTipoUtensilio);
    }

    public void handleTipoUtensilio(ActionEvent e) {
        int index = 9;

        if(index != cbGrupoUtensilio.getSelectionModel().getSelectedIndex()) {
            index = cbGrupoUtensilio.getSelectionModel().getSelectedIndex();
            UtensilioDAO utensilioDAO = new UtensilioDAO();
            ObservableList<String> tipoUtensilio = FXCollections.observableArrayList(utensilioDAO.obterTipoUtensilio(index+1));
            cbTipoUtensilio.setItems(tipoUtensilio);
        }
    }

    public void obterCalculoComida(ComboBox grupoUtensilio, ComboBox tipoUtensilio) {
        Utensilio utensilio = new Utensilio();
        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(usuario.getId());

        boolean flag = utensilio.calcularUtensilio(grupoUtensilio.getValue().toString(),
                tipoUtensilio.getSelectionModel().getSelectedIndex(), usuario.getId(),
                tipoUtensilio.getValue().toString());

        if(flag) {
            UtensilioDAO utensilioDAO = new UtensilioDAO();
            tabela.setItems(utensilioDAO.obterUtensilio(idFesta));
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtensilioDAO utensilioDAO = new UtensilioDAO();
        ObservableList<String> grupoUtensilio = FXCollections.observableArrayList(utensilioDAO.obterGrupoUtensilio());
        cbGrupoUtensilio.setItems(grupoUtensilio);

        colunaGrupoUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidadeUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
    }
}
