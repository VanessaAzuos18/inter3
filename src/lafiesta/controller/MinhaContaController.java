package lafiesta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lafiesta.model.dao.UsuarioDAO;
import lafiesta.model.domain.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MinhaContaController implements Initializable {

    @FXML
    private Button voltar;
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private AnchorPane telaCadastroFornecedor;

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

    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void atualizar(){
        if(usuario.getTipo() == 1) {
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
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setEndereco(endereco);
                    usuario.setTelefone(telefone);
                    usuario.setCpf(cpf);
                    usuario.setSenha(senha);

                    boolean cadastro = usuarioDAO.atualizar(usuario);

                    if(cadastro){
                        JOptionPane.showMessageDialog(null, "Dados Atualizado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário!");
                    }
                }
            }else JOptionPane.showMessageDialog(null, "Há algum campo vazio!");
        }else{
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
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setEndereco(endereco);
                    usuario.setTelefone(telefone);
                    usuario.setCnpj(cnpj);
                    usuario.setSenha(senha);

                    boolean cadastro = usuarioDAO.atualizar(usuario);

                    if(cadastro){
                        JOptionPane.showMessageDialog(null, "Dados Atualizado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário!");
                    }
                }
            }else JOptionPane.showMessageDialog(null, "Há algum campo vazio!");
        }
    }

    public void handleVoltar() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/TelaInicial.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Buscar Auxilio");
        stage.setScene(scene);

        TelaInicialController controller = loader.getController();
        controller.setUsuario(usuario);

        stage.show();

        voltar.getScene().getWindow().hide();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if(usuario.getTipo() == 1) {
            telaCadastroFornecedor.setVisible(false);
            telaCadastroCliente.setVisible(true);

            nome_cliente.setText(usuario.getNome());
            email_cliente.setText(usuario.getEmail());
            endereco_cliente.setText(usuario.getEmail());
            telefone_cliente.setText(usuario.getTelefone());
            cpf_cliente.setText(usuario.getCpf());
            senha_cliente.setText(usuario.getSenha());
        } else {
            telaCadastroFornecedor.setVisible(true);
            telaCadastroCliente.setVisible(false);

            nome_fornec.setText(usuario.getNome());
            email_fornec.setText(usuario.getEmail());
            endereco_fornec.setText(usuario.getEmail());
            telefone_fornec.setText(usuario.getTelefone());
            cnpj_fornec.setText(usuario.getCnpj());
            senha_fornec.setText(usuario.getSenha());
        }
    }
}
