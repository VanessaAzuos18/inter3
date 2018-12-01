package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.ComidaDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.UtensilioDAO;
import lafiesta.model.domain.Bebida;
import lafiesta.model.domain.Comida;
import lafiesta.model.domain.Usuario;
import lafiesta.model.domain.Utensilio;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraBasicaController implements Initializable {
    @FXML
    private ComboBox cbTipoComida;
    @FXML
    private ComboBox cbServido;
    @FXML
    private ComboBox cbTipoBebida;
    @FXML
    private ComboBox cbServidoBebida;
    @FXML
    private ComboBox cbGrupoUtensilio;
    @FXML
    private ComboBox cbTipoUtensilio;
    @FXML
    private Text txtResultado;
    @FXML
    private Label txtComida;
    @FXML
    private Label txtResultadoComida;
    @FXML
    private Label txtBebida;
    @FXML
    private Label txtResultadoBebida;
    @FXML
    private Label txtUtensilio;
    @FXML
    private Label txtResultadoUtensilio;

    private Usuario usuario;

    public void handleCalcular(ActionEvent event) {
        calcularComida(cbTipoComida, cbServido);
        obterCalculoBebida(cbTipoBebida, cbServidoBebida);
        obterCalculoUtensilio(cbGrupoUtensilio, cbTipoUtensilio);
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

    public void handleTipoBebida(ActionEvent e) {
        int index = 9;

        if(index != cbTipoBebida.getSelectionModel().getSelectedIndex()) {
            index = cbTipoBebida.getSelectionModel().getSelectedIndex();
            BebidaDAO bebidaDAO = new BebidaDAO();
            ObservableList<String> tipoBebida = FXCollections.observableArrayList(bebidaDAO.obterTipoBebida(index+1));
            cbServidoBebida.setItems(tipoBebida);
        }
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

    public void handleVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/TelaInicial.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Tela inicial");
        stage.setScene(scene);

        TelaInicialController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        cbTipoBebida.getScene().getWindow().hide();
    }

    private void calcularComida(ComboBox grupoComida, ComboBox tipoComida) {
        Comida comida = new Comida();

        boolean flag = comida.calcularComida(grupoComida.getValue().toString(), tipoComida.getSelectionModel().getSelectedIndex(), usuario.getId(), tipoComida.getValue().toString(), false, comida);

        if(flag) {
            txtResultado.setVisible(true);
            txtComida.setVisible(true);
            txtResultadoComida.setVisible(true);
            txtBebida.setVisible(true);
            txtUtensilio.setVisible(true);

            txtComida.setText(comida.getTipo()+":");
            txtResultadoComida.setText(comida.getQuantidade());

            //Define layout
            txtComida.getWidth();
            //txtResultadoComida.setLayoutX(txtComida.getWidth()+10+txtComida.getLayoutX());

            System.out.println(txtComida.getWidth());

            txtComida.widthProperty().addListener((obs, oldVal, newVal) -> {
                txtResultadoComida.setLayoutX(newVal.doubleValue()+10+txtComida.getLayoutX());
            });
        }
    }

    public void obterCalculoBebida(ComboBox grupoBebida, ComboBox tipoBebida) {
        Bebida bebida = new Bebida();

        boolean flag = bebida.calcularBebida(grupoBebida.getValue().toString(),
                tipoBebida.getSelectionModel().getSelectedIndex(), usuario.getId(),
                tipoBebida.getValue().toString(), false, bebida);

        if(flag) {
            txtResultado.setVisible(true);
            txtComida.setVisible(true);
            txtResultadoComida.setVisible(true);
            txtBebida.setVisible(true);
            txtResultadoBebida.setVisible(true);
            txtUtensilio.setVisible(true);

            txtBebida.setText(bebida.getTipo()+":");
            txtResultadoBebida.setText(bebida.getQuantidade());

            //Define layout
            //txtResultadoComida.setLayoutX(txtComida.getWidth()+10+txtComida.getLayoutX());

            txtBebida.widthProperty().addListener((obs, oldVal, newVal) -> {
                txtResultadoBebida.setLayoutX(newVal.doubleValue()+10+txtBebida.getLayoutX());
            });

            txtResultadoBebida.widthProperty().addListener((obs, oldVal, newVal) -> {
                txtUtensilio.setLayoutX(newVal.doubleValue()+70+txtResultadoBebida.getLayoutX());
            });
        }
    }

    public void obterCalculoUtensilio(ComboBox grupoUtensilio, ComboBox tipoUtensilio) {
        Utensilio utensilio = new Utensilio();

        boolean flag = utensilio.calcularUtensilio(grupoUtensilio.getValue().toString(),
                tipoUtensilio.getSelectionModel().getSelectedIndex(), usuario.getId(),
                tipoUtensilio.getValue().toString(), false, utensilio);

        if(flag) {
            txtResultado.setVisible(true);
            txtComida.setVisible(true);
            txtResultadoComida.setVisible(true);
            txtBebida.setVisible(true);
            txtResultadoBebida.setVisible(true);
            txtUtensilio.setVisible(true);
            txtResultadoUtensilio.setVisible(true);

            txtUtensilio.setText(utensilio.getTipo()+":");
            txtResultadoUtensilio.setText(utensilio.getQuantidade());

            //Define layout
            //txtResultadoComida.setLayoutX(txtComida.getWidth()+10+txtComida.getLayoutX());

            txtResultadoBebida.widthProperty().addListener((obs, oldVal, newVal) -> {
                txtUtensilio.setLayoutX(newVal.doubleValue()+70+txtResultadoBebida.getLayoutX());
            });

            txtUtensilio.widthProperty().addListener((obs, oldVal, newVal) -> {
                txtResultadoUtensilio.setLayoutX(newVal.doubleValue()+50+txtUtensilio.getLayoutX());
                System.out.println(newVal);
            });
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtResultado.setVisible(false);
        txtComida.setVisible(false);
        txtResultadoComida.setVisible(false);
        txtBebida.setVisible(false);
        txtResultadoBebida.setVisible(false);
        txtUtensilio.setVisible(false);
        txtResultadoUtensilio.setVisible(false);

        ComidaDAO comidaDAO = new ComidaDAO();
        ObservableList<String> grupo = FXCollections.observableArrayList(comidaDAO.obterGrupoComidas());
        cbTipoComida.setItems(grupo);

        BebidaDAO bebidaDAO = new BebidaDAO();
        ObservableList<String> grupoBebida = FXCollections.observableArrayList(bebidaDAO.obterGrupoBebida());
        cbTipoBebida.setItems(grupoBebida);

        UtensilioDAO utensilioDAO = new UtensilioDAO();
        ObservableList<String> grupoUtensilio = FXCollections.observableArrayList(utensilioDAO.obterGrupoUtensilio());
        cbGrupoUtensilio.setItems(grupoUtensilio);
    }
}
