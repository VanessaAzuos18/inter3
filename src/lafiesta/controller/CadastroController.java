package lafiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lafiesta.model.dao.UsuarioDAO;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private AnchorPane telaCadastroFornecedor;
    @FXML
    private ImageView imagemAba1;
    @FXML
    private TextField nome_cliente;
    @FXML
    private TextField email_cliente;
    @FXML
    private TextField endereco_cliente;
    @FXML
    private TextField telefone_cliente;
    @FXML
    private TextField cpf_cliente;
    @FXML
    private PasswordField senha_cliente;
    @FXML
    private PasswordField confir_senha_cliente;

    @FXML
    private TextField nome_fornec;
    @FXML
    private TextField email_fornec;
    @FXML
    private TextField endereco_fornec;
    @FXML
    private TextField telefone_fornec;
    @FXML
    private TextField cnpj_fornec;
    @FXML
    private PasswordField senha_fornec;
    @FXML
    private PasswordField confir_senha_fornec;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void handleExibeTelaCadastroFornecedor(ActionEvent event) throws IOException {
        telaCadastroCliente.setVisible(false);
        telaCadastroFornecedor.setVisible(true);
//        btnCpf.setVisible(false);
    }

    public void handleExibeTelaCadastroCliente(ActionEvent event) {
        telaCadastroFornecedor.setVisible(false);
        telaCadastroCliente.setVisible(true);
//        btnCpf.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        telaCadastroFornecedor.setVisible(false);
    }

    public void handleVoltarLogin(MouseEvent mouseEvent) throws IOException {
//        Parent root;
//
//        root = FXMLLoader.load(getClass().getResource("../view/LoginMain.fxml"));
//
//        CadastroController teste = new CadastroController();
//
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//
//        stage.setTitle("Cadastro");
//        stage.setScene(scene);
//        stage.show();

        System.out.println("chegou!");
//        voltar.getScene().getWindow().hide();
    }

    public void cadastro(ActionEvent event) {
        String nome = nome_cliente.getText();
        String email = email_cliente.getText();
        String endereco = endereco_cliente.getText();
        String telefone = telefone_cliente.getText();
        String cpf = cpf_cliente.getText();
        String senha = senha_cliente.getText();
        String confSenha = confir_senha_cliente.getText();

        if(!nome.equals("") && !email.equals("") && !endereco.equals("") && !telefone.equals("") && !cpf.equals("") && !senha.equals("") && !confSenha.equals("")){
            if(!senha.equals(confSenha)){
                JOptionPane.showMessageDialog(null, "Senhas Divergentes!");
            }else{
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setCpf(cpf);
                usuario.setSenha(senha);
                usuario.setTipo(1);

                boolean cadastro = usuarioDAO.cadastrar(usuario);

                if(cadastro){
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário!");
                }
            }
        }else JOptionPane.showMessageDialog(null, "Há algum campo vazio!");
    }

    public void cadastroCnpj(ActionEvent event) {
        String nome = nome_fornec.getText();
        String email = email_fornec.getText();
        String endereco = endereco_fornec.getText();
        String telefone = telefone_fornec.getText();
        String cnpj = cnpj_fornec.getText();
        String senha = senha_fornec.getText();
        String confSenha = confir_senha_fornec.getText();

        if(!nome.equals("") && !email.equals("") && !endereco.equals("") && !telefone.equals("") && !cnpj.equals("") && !senha.equals("") && !confSenha.equals("")){
            if(!senha.equals(confSenha)){
                JOptionPane.showMessageDialog(null, "Senhas Divergentes!");
            }else{
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setCpf(cnpj);
                usuario.setSenha(senha);
                usuario.setTipo(2);

                boolean cadastro = usuarioDAO.cadastrar(usuario);

                if(cadastro){
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário!");
                }
            }
        }else JOptionPane.showMessageDialog(null, "Há algum campo vazio!");
    }
}