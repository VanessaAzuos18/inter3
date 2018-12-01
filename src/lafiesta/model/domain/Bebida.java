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

    public boolean calcularBebida(String grupoBebida, int tipoBebidaIndex, int idUsuario, String tipoBebidaValue, boolean cadastrarBebida, Bebida bebida) {
        double total = 0;
        String grandeza = null;

        BebidaDAO bebidaDAO = new BebidaDAO();
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();

        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(idUsuario);

        int verifica = bebidaDAO.verificarBebida(idUsuario, idFesta, tipoBebidaValue);

        if (grupoBebida.equals("ALCOOLICAS") && tipoBebidaIndex == 0) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[0] * 1000;
            grandeza = "ml";
        } else if (grupoBebida.equals("ALCOOLICAS") && (tipoBebidaIndex == 1 || tipoBebidaIndex == 2)) {
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

        if(grupoBebida == "AGUAS" && tipoBebidaIndex == 0) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 100;
            grandeza = "ml";
        } else if (grupoBebida.equals("AGUAS") && tipoBebidaIndex == 1) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 300;
            grandeza = "ml";
        }

        if (grupoBebida.equals("COFFEE BREAK") && (tipoBebidaIndex == 0 || tipoBebidaIndex == 2)) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[2] * 200;
            grandeza = "ml";
        } else if (grupoBebida.equals("COFFEE BREAK") && (tipoBebidaIndex == 1 || tipoBebidaIndex == 3)) {
            total = convidadoDAO.contarPessoasGrupo(idUsuario)[2] * 100;
            grandeza = "ml";
        }

        total = Math.ceil(total);

        if (verifica == 0 && cadastrarBebida == true) {
            bebida.setTipo(tipoBebidaValue);
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
            if(cadastrarBebida == false) {
                bebida.setTipo(tipoBebidaValue);
                bebida.setGrupo(grupoBebida);
                bebida.setIdFesta(idFesta);
                bebida.setQuantidade(total + grandeza);
                return true;
            } else {
                return false;
            }
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
