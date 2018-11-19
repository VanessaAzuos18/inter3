package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.UtensilioDAO;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Usuario;
import lafiesta.model.domain.Utensilio;

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

    private Usuario usuario;


    public void handleAvancar(ActionEvent e) {

    }

    public void handleVoltar(ActionEvent e) {

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
