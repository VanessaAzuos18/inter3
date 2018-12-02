package lafiesta.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.ComidaDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.UtensilioDAO;
import lafiesta.model.domain.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {
    @FXML
    private Button teste;
    @FXML
    private AnchorPane anc;
    @FXML
    private Text vol;
    @FXML
    private TableView<Comida> tabela;
    @FXML
    private TableColumn<Comida, String> colunaGrupoComida;
    @FXML
    private TableColumn<Comida, String> colunaTipoComida;
    @FXML
    private TableColumn<Comida, String> colunaQuantidade;
    @FXML
    private Text textoComida;

    @FXML
    private TableView<Bebida> tabelaBebida;
    @FXML
    private TableColumn<Bebida, String> colunaGrupoBebida;
    @FXML
    private TableColumn<Bebida, String> colunaTipoBebida;
    @FXML
    private TableColumn<Bebida, String> colunaQuantidadeBebida;
    @FXML
    private Text textoBebidas;

    @FXML
    private TableView<Utensilio> tabelaUtensilio;
    @FXML
    private TableColumn<Utensilio, String> colunaGrupoUtensilio;
    @FXML
    private TableColumn<Utensilio, String> colunaTipoUtensilio;
    @FXML
    private TableColumn<Utensilio, String> colunaQuantidadeUtensilio;
    @FXML
    private Text textoUtensilio;

    @FXML
    private Text localFesta;
    @FXML
    private Text totalConvidados;
    @FXML
    private Text dataFesta;

    private Usuario usuario;

    /*public void handleGerarPdf() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.showPrintDialog(teste.getScene().getWindow());
            System.out.println(teste.getScene().getWindow());
            System.out.println(anc);
            job.printPage(anc);
            job.endJob();
        }
    }*/

    public void handleVoltar() {

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        FestaDAO festaDAO = new FestaDAO();

        int idFesta = festaDAO.buscarId(usuario.getId());

        String[] festaEspecifica;

        festaEspecifica = festaDAO.carregarFestaEspecifica(idFesta);

        localFesta.setText(festaEspecifica[1]);
        totalConvidados.setText(festaEspecifica[2]);
        dataFesta.setText(festaEspecifica[0]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaGrupoComida.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoComida.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        Comida comida = new Comida();

        ComidaDAO comidaDAO = new ComidaDAO();

        int totalComida = comidaDAO.obterTotalComida(2);
        /*if(totalComida == 1) {
            tabela.setPrefHeight(60*totalComida);
        } else if(totalComida == 2) {
            tabela.setPrefHeight(46*totalComida);
        } else if(totalComida == 3) {
            tabela.setPrefHeight(41.3*totalComida);
        } else if(totalComida >= 5) {
            tabela.setPrefHeight(37.5*totalComida);
        }*/
        System.out.println(30*totalComida);

        tabela.setItems(comidaDAO.obterComida(2));
        tabela.setFixedCellSize(30);
        tabela.prefHeightProperty().bind(tabela.fixedCellSizeProperty().multiply(Bindings.size(tabela.getItems()).add(0.93)));
        tabela.minHeightProperty().bind(tabela.prefHeightProperty());
        tabela.maxHeightProperty().bind(tabela.prefHeightProperty());

        if(totalComida == 0) {
            tabela.setVisible(false);
            textoComida.setVisible(false);
        }


        colunaGrupoBebida.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoBebida.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidadeBebida.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));

        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(2);
        System.out.println(idFesta);

        BebidaDAO bebidaDAO = new BebidaDAO();


        int totalBebida = bebidaDAO.obterTotalBebida(idFesta);

        if(totalBebida != 0) {
            textoBebidas.setLayoutY(textoComida.getLayoutY()+tabela.getPrefHeight()+40);
            tabelaBebida.setLayoutY(tabela.getLayoutY()+tabela.getPrefHeight()+40);

            tabelaBebida.setItems(bebidaDAO.obterBebida(idFesta));
            tabelaBebida.setFixedCellSize(30);
            tabelaBebida.prefHeightProperty().bind(tabelaBebida.fixedCellSizeProperty().multiply(Bindings.size(tabelaBebida.getItems()).add(0.93)));
            tabelaBebida.minHeightProperty().bind(tabelaBebida.prefHeightProperty());
            tabelaBebida.maxHeightProperty().bind(tabelaBebida.prefHeightProperty());
        } else {
            tabelaBebida.setVisible(false);
            textoBebidas.setVisible(false);
        }


        colunaGrupoUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        colunaTipoUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        colunaQuantidadeUtensilio.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));

        UtensilioDAO utensilioDAO = new UtensilioDAO();


        int totalUtensilio = utensilioDAO.obterTotalUtensilio(idFesta);



        if(totalUtensilio != 0) {
            textoUtensilio.setLayoutY(textoBebidas.getLayoutY()+tabelaBebida.getPrefHeight()+40);
            tabelaUtensilio.setLayoutY(tabelaBebida.getLayoutY()+tabelaBebida.getPrefHeight()+40);

            tabelaUtensilio.setItems(utensilioDAO.obterUtensilio(idFesta));
            tabelaUtensilio.setFixedCellSize(30);
            tabelaUtensilio.prefHeightProperty().bind(tabelaUtensilio.fixedCellSizeProperty().multiply(Bindings.size(tabelaUtensilio.getItems()).add(0.93)));
            tabelaUtensilio.minHeightProperty().bind(tabelaUtensilio.prefHeightProperty());
            tabelaUtensilio.maxHeightProperty().bind(tabelaUtensilio.prefHeightProperty());
        } else {
            tabelaUtensilio.setVisible(false);
            textoUtensilio.setVisible(false);
        }
    }
}

