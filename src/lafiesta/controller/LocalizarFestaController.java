package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LocalizarFestaController implements Initializable {
    @FXML
    private TextField cep;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField complemento;
    @FXML
    private TextField bairro;
    @FXML
    private TextField estado;
    @FXML
    private TextField cidade;
    @FXML
    private Text cepInvalido;
    @FXML
    public String enderecoCompleto;
    @FXML
    private TextField nomeFesta;
    private List<String> elementosCampos;
    private Usuario usuario;


    public void handleLocalizar(ActionEvent e) {
    }

    public void handleSalvarLocal(ActionEvent e) throws IOException{

        if(complemento.getText().equals("")) {
            enderecoCompleto = rua.getText() + " " + numero.getText() + ", " +
                    bairro.getText() + ", " +
                    cidade.getText() + "-" + estado.getText();
        } else {
            enderecoCompleto = rua.getText() + " " + numero.getText() + ", " + complemento.getText() + ", " +
                    bairro.getText() + ", " +
                    cidade.getText() + "-" + estado.getText();
        }

        nomeFesta.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/IniciarFesta.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Iniciar festa");
        stage.setScene(scene);

        IniciarFestaController controller = loader.getController();
        controller.setElementosCampos(elementosCampos);
        controller.setLocal(enderecoCompleto);
        controller.setUsuario(usuario);

        stage.show();

        complemento.getScene().getWindow().hide();
    }

    public void handleFecharTela(ActionEvent e) {

    }

    public void setElementFromIniciarFesta(TextField nomeFesta) {
        this.nomeFesta = nomeFesta;

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setElementosCampos(List<String> lista) {
        elementosCampos = lista;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cepInvalido.setVisible(false);
        cep.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                Festa festa = new Festa();
                try {
                    String endereco = festa.getEndereco(cep.getText());
                    System.out.println(endereco);
                    if(endereco.equals(cep.getText())) {
                        cepInvalido.setVisible(true);
                        cep.getStyleClass().remove("campo-formulario");
                        cep.getStyleClass().add("campo-formulario-erro");
                    } else {
                        cepInvalido.setVisible(false);
                        cep.getStyleClass().remove("campo-formulario-erro");
                        cep.getStyleClass().add("campo-formulario");

                        //Preenche endereco
                        rua.setText(endereco);

                        String bairroFesta = festa.getBairro(cep.getText());
                        bairro.setText(bairroFesta);

                        String uf = festa.getUF(cep.getText());
                        estado.setText(uf);

                        String cidadeFesta = festa.getCidade(cep.getText());
                        cidade.setText(cidadeFesta);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("CAGOU");
                }
            }
        });
    }
}
