package lafiesta.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.domain.Convidado;
import lafiesta.model.domain.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaConvidadosController implements Initializable {
    @FXML
    private TextField nome;
    @FXML
    private TextField idade;
    @FXML
    private ComboBox sexo;
    @FXML
    private TableView<Convidado> tabela;
    @FXML
    private TableColumn<Convidado, String> colunaNome;
    @FXML
    private TableColumn<Convidado, Integer> colunaIdade;
    @FXML
    private TableColumn<Convidado, String> colunaSexo;

    private Usuario usuario;

    public void handleAdicionar(ActionEvent e) {

        if(nome.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("O campo nome está vazio");
            Optional<ButtonType> result = alert.showAndWait();
        } else if(idade.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("O campo idade está vazio");
            Optional<ButtonType> result = alert.showAndWait();
        } else if(sexo.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("O campo sexo está vazio");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            try {
                Integer.parseInt(idade.getText());

                Convidado convidado = new Convidado();
                convidado.setNome(nome.getText());
                convidado.setIdade(Integer.parseInt(idade.getText()));
                convidado.setSexo(sexo.getValue().toString());
                convidado.setIdUsuario(usuario.getId());

                ConvidadoDAO convidadoDAO = new ConvidadoDAO();
                if(convidadoDAO.cadastrarConvidado(convidado)) {
                    tabela.setItems(convidadoDAO.carregarConvidados(usuario.getId()));
                    System.out.println("HOMEM: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[0]);
                    System.out.println("MULHER: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[1]);
                    System.out.println("CRIANCA: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[2]);
                } else {
                    System.out.println("Falha ao cadastrar o convidado");
                }

                nome.setText("");
                idade.setText("");
                sexo.getSelectionModel().clearSelection();
            } catch(NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText("Digite uma idade valida");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    public void handleVoltar(ActionEvent e) throws IOException{
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

        nome.getScene().getWindow().hide();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        ConvidadoDAO convidadoDAO = new ConvidadoDAO();
        tabela.setItems(convidadoDAO.carregarConvidados(usuario.getId()));

        System.out.println("HOMEM: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[0]);
        System.out.println("MULHER: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[1]);
        System.out.println("CRIANCA: " + convidadoDAO.contarPessoasGrupo(usuario.getId())[2]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexo.setItems(FXCollections.observableArrayList("Feminino", "Masculino"));
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
    }
}
