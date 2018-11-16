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
import lafiesta.model.dao.ComidaDAO;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Comida;
import lafiesta.model.domain.GrupoComida;
import lafiesta.model.domain.Usuario;

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

    private Usuario usuario;

    public void handleAvancar(ActionEvent e) {

    }

    public void handleVoltar(ActionEvent e) {

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

    public void handleAdicionar(ActionEvent e) {
        calcularComida(cbTipoComida, cbServido);
    }

    private void calcularComida(ComboBox grupoComida, ComboBox tipoComida) {
        double total = 0;
        String grandeza = null;

        ComidaDAO comidaDAO = new ComidaDAO();
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();

        Usuario usuario = new Usuario();
        usuario.setId(2);

        int verifica = comidaDAO.verificarComida(usuario.getId(), tipoComida.getValue().toString());
        if(grupoComida.getValue().toString().equals("CHURRASCO") && (tipoComida.getSelectionModel().getSelectedIndex() >= 0 && tipoComida.getSelectionModel().getSelectedIndex() <= 4)) {
            //Calculo CARNES HOMENS
            double quantidade = comidaDAO.buscarQuantidadeCarne(usuario.getId()) + 1;
            quantidade = 600 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(usuario.getId())[0];
            total = quantidade;

            //Calculo CARNES MULHERES
            quantidade = comidaDAO.buscarQuantidadeCarne(usuario.getId()) + 1;
            quantidade = 400 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(usuario.getId())[1];
            total += quantidade;

            //Calculo CARNES CRIANÇAS
            quantidade = comidaDAO.buscarQuantidadeCarne(usuario.getId()) + 1;
            quantidade = 200 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(usuario.getId())[2];
            total += quantidade;

            comidaDAO.atualizarQuantiadeCarne(String.valueOf(total), usuario.getId());
            grandeza = "g";
        }

        total = Math.ceil(total);
        if (verifica == 0)
        {
            FestaDAO festaDAO = new FestaDAO();
            Comida comida = new Comida();
            comida.setTipo(cbServido.getValue().toString());
            comida.setGrupo(cbTipoComida.getValue().toString());
            comida.setIdFesta(festaDAO.buscarId(usuario.getId()));
            comida.setQuantidade(total + grandeza);

            boolean sucesso = comidaDAO.cadastrarComida(usuario.getId(), comida);
            if (sucesso) {
                tabela.setItems(comidaDAO.obterComida(usuario.getId()));
                System.out.println("CADASTRADO");
            } else {
                System.out.println("Convidado não foi cadastrado!");
            }
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
}
