package lafiesta.model.domain;

import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;

public class Bebida {
    private int id;
    private String grupo;
    private String tipo;
    private int idFesta;
    private int idFornecedor;
    private String quantidade;

    public Bebida() {}

    public Bebida(String grupo, String tipo, String quantidade) {
        this.grupo = grupo;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public boolean calcularBebida(String grupoBebida, int tipoComidaIndex, int idUsuario, String tipoComidaValue) {
        double total = 0;
        String grandeza = null;

        BebidaDAO bebidaDAO = new BebidaDAO();
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();

        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(idUsuario);

        int verifica = bebidaDAO.verificarBebida(idUsuario, idFesta, tipoComidaValue);

        if (grupoBebida.equals("ALCOOLICAS") && tipoComidaIndex == 0) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[0] * 1000;
            grandeza = "ml";
        } else if (grupoBebida.equals("ALCOOLICAS") && (tipoComidaIndex == 1 || tipoComidaIndex == 2)) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) / 20.0;
            total *= 1000;
            grandeza = "ml";
        }

        if (grupoBebida.equals("REFRIGERANTES")) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 500;
            grandeza = "ml";
        }

        if (grupoBebida.equals("SUCOS")) {
            total = (Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) - convidadoDAO.contarPessoasGrupo(idUsuario)[2]) * 500;
            total += convidadoDAO.contarPessoasGrupo(idUsuario)[2] * 300;
            grandeza = "ml";
        }

        if(grupoBebida == "AGUAS" && tipoComidaIndex == 0) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 100;
            grandeza = "ml";
        } else if (grupoBebida.equals("AGUAS") && tipoComidaIndex == 1) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 300;
            grandeza = "ml";
        }

        if (grupoBebida.equals("COFFEE BREAK") && (tipoComidaIndex == 0 || tipoComidaIndex == 2)) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[2] * 200;
            grandeza = "ml";
        } else if (grupoBebida.equals("COFFEE BREAK") && (tipoComidaIndex == 1 || tipoComidaIndex == 3)) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[2] * 100;
            grandeza = "ml";
        }

        total = Math.ceil(total);

        if (verifica == 0) {
            Bebida bebida = new Bebida();
            bebida.setTipo(tipoComidaValue);
            bebida.setGrupo(grupoBebida);
            bebida.setIdFesta(idFesta);
            bebida.setQuantidade(total + grandeza);

            boolean sucesso = bebidaDAO.cadastrarBebida(bebida);

            if(sucesso) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdFesta() {
        return idFesta;
    }

    public void setIdFesta(int idFesta) {
        this.idFesta = idFesta;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
