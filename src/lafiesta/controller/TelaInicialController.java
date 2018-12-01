package lafiesta.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.ProdutoDAO;
import lafiesta.model.domain.Festa;
import lafiesta.model.domain.Produto;
import lafiesta.model.domain.Usuario;
import sun.plugin.javascript.navig.Anchor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    private String nomeUsuario;

    @FXML
    private AnchorPane container;

    @FXML
    private Text nome;
    @FXML
    private Label tipo;
    @FXML
    private AnchorPane menu;
    @FXML
    private Button menuBotao;
    @FXML
    private Text txtSair;
    @FXML
    private TableView<Festa> tabela;
    @FXML
    private TableColumn<Festa, Integer> id;
    @FXML
    private TableColumn<Festa, String> nome_festa;
    @FXML
    private TableColumn<Festa, String> local;
    @FXML
    private TableColumn<Festa, String> data;
    @FXML
    private TableColumn<Festa, Integer> convidados;
    @FXML
    private AnchorPane cliente;
    @FXML
    private AnchorPane fornecedor;

    @FXML
    private Text nomeFornecedor;
    @FXML
    private Label tipoFornecedor;
    @FXML
    private AnchorPane menuFornecedor;
    @FXML
    private TableView<Produto> tabelaFornecedor;
    @FXML
    private TableColumn<Produto, Integer> idFornecedor;
    @FXML
    private TableColumn<Produto, String> tipoProduto;
    @FXML
    private TableColumn<Produto, String> observacao;
    @FXML
    private TableColumn<Produto, String> cidade;

    private Usuario usuario;

    FestaDAO festaDAO = new FestaDAO();
    ProdutoDAO produtoDao = new ProdutoDAO();

    public void setNome(String nomeUsuario){
        this.nome.setText(nomeUsuario);
    }

    public void handleMenu(ActionEvent e) {
        if(menu.isVisible() == false) {
            menu.setVisible(true);
        } else {
            menu.setVisible(false);
        }
    }

    public void handleMenuFornecedor(ActionEvent e) {
        if(menuFornecedor.isVisible() == false) {
            menuFornecedor.setVisible(true);
        } else {
            menuFornecedor.setVisible(false);
        }
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
        if(usuario.getTipo() == 1) {
            montaTelaCliente();
        } else {
            montaTelaFornecedor();
        }
    }

    @FXML
    void aa(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso de confirmação");
        alert.setHeaderText("Você realmente deseja sair do sistema?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            container.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setVisible(false);
        menuFornecedor.setVisible(false);
    }

    public void remover(ActionEvent e) {
        if(usuario.getTipo() == 1){
            Festa festaRemover = tabela.getSelectionModel().getSelectedItem();
            if(festaDAO.remover(festaRemover.getId(), usuario.getId()))
                tabela.setItems(festaDAO.carregaFestas(usuario.getId()));
            else
                JOptionPane.showMessageDialog(null, "Erro ao remover a festa!");
        }else{
            Produto produtoRemover = tabelaFornecedor.getSelectionModel().getSelectedItem();
            if(produtoDao.remover(produtoRemover.getId(), usuario.getId()))
                tabelaFornecedor.setItems(produtoDao.obterMeusProdutosServicos(usuario.getId()));
            else
                JOptionPane.showMessageDialog(null, "Erro ao remover o produto/servico!");
        }

    }

    @FXML
    void handleListaConvidado(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/ListaConvidados.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Buscar Auxilio");
        stage.setScene(scene);

        /*WritableImage snapshot = scene.snapshot(null);
        File file = new File("image.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image = Image.getInstance("C:\\Users\\user\\Documents\\Matheus\\IFSP\\Semestre_3\\Interdisciplinar\\inter3\\image.png");
            Document document = new Document(PageSize.A4.rotate(), 80, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\user\\Documents\\Matheus\\IFSP\\Semestre_3\\Interdisciplinar\\inter3\\doc.pdf"));
            document.open();
            //image.scaleAbsolute(630, 300);

            document.add(image);
            document.close();

        } catch(DocumentException de) {

        }*/


        ListaConvidadosController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @FXML
    void handleIniciarFesta(MouseEvent event) throws IOException{
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

        txtSair.getScene().getWindow().hide();
    }

    public void handleCalculadoraBasica(MouseEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/CalculadoraBasica.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Calculadora basica");
        stage.setScene(scene);

        CalculadoraBasicaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @FXML
    public void handleCadastroProdServ(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/CadastroServProd.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Iniciar Festa");
        stage.setScene(scene);

        CadastroServProdController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @FXML
    public void handleAgendaFesta(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/AgendaFesta.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Minha Conta");
        stage.setScene(scene);

        AgendaFestaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    @FXML
    public void handleMinhaConta(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/MinhaConta.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Minha Conta");
        stage.setScene(scene);

        MinhaContaController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        txtSair.getScene().getWindow().hide();
    }

    private void montaTelaCliente() {
        cliente.setVisible(true);
        fornecedor.setVisible(false);
        tipo.setText("Cliente");
        nome.setText(usuario.getNome());

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        nome_festa.setCellValueFactory(
                new PropertyValueFactory<>("nome_festa"));
        data.setCellValueFactory(
                new PropertyValueFactory<>("Data"));
        local.setCellValueFactory(
                new PropertyValueFactory<>("Local"));
        convidados.setCellValueFactory(
                new PropertyValueFactory<>("Convidados"));

        tabela.setItems(festaDAO.carregaFestas(usuario.getId()));
    }

    private void montaTelaFornecedor() {
        cliente.setVisible(false);
        fornecedor.setVisible(true);
        tipoFornecedor.setText("Fornecedor / Prestador");
        nomeFornecedor.setText(usuario.getNome());

        idFornecedor.setCellValueFactory(
                new PropertyValueFactory<>("Id"));
        tipoProduto.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        cidade.setCellValueFactory(
                new PropertyValueFactory<>("Cidade"));
        observacao.setCellValueFactory(
                new PropertyValueFactory<>("observacao"));

        tabelaFornecedor.setItems(produtoDao.obterMeusProdutosServicos(usuario.getId()));
    }
}
