package lafiesta.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.domain.Usuario;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class GerarRelatorioController implements Initializable {
    @FXML
    private Button botaoSalvarRelatorio;
    @FXML
    private Text textoInformativo;
    @FXML
    private Text caminhoImagem;

    private Usuario usuario;

    public void handleSalvarRelatorio(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/Relatorio.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);


        WritableImage snapshot = scene.snapshot(null);
        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(usuario.getId());
        File file = new File("relatorio_"+usuario.getNome()+"_"+usuario.getId()+"_"+idFesta+".png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            textoInformativo.setVisible(true);
            caminhoImagem.setVisible(true);
            caminhoImagem.setText("relatorio_"+usuario.getNome()+"_"+usuario.getId()+"_"+idFesta+".png");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void handleTelaInicial(ActionEvent e) throws IOException{
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

        botaoSalvarRelatorio.getScene().getWindow().hide();
    }

    public void handleVoltar(MouseEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/FornecedorPrestadorController.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Fornecedor Prestador");
        stage.setScene(scene);

        FornecedorPrestadorController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        botaoSalvarRelatorio.getScene().getWindow().hide();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textoInformativo.setVisible(false);
        caminhoImagem.setVisible(false);
    }
}
