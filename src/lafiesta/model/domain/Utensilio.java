package lafiesta.model.domain;

import lafiesta.model.dao.BebidaDAO;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;
import lafiesta.model.dao.UtensilioDAO;

public class Utensilio {
    private int id;
    private String grupo;
    private String tipo;
    private int idFesta;
    private int idFornecedor;
    private String quantidade;

    public Utensilio() {}

    public Utensilio(String grupo, String tipo, String quantidade) {
        this.setGrupo(grupo);
        this.setTipo(tipo);
        this.setQuantidade(quantidade);
    }

    public boolean calcularUtensilio(String grupoUtensilio, int tipoUtensilioIndex, int idUsuario, String tipoUtensilioValue) {
        double total = 0;
        String grandeza = null;

        UtensilioDAO utensilioDAO = new UtensilioDAO();
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();

        FestaDAO festaDAO = new FestaDAO();
        int idFesta = festaDAO.buscarId(idUsuario);

        int verifica = utensilioDAO.verificarUtensilio(idFesta, tipoUtensilioValue);

        if (grupoUtensilio.equals("COPOS") && (tipoUtensilioIndex >= 0 && tipoUtensilioIndex <= 4)) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 5;
            grandeza = " unidade(s)";
        } else if (grupoUtensilio.equals("COPOS") && (tipoUtensilioIndex >= 5 && tipoUtensilioIndex <= 9)) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 5;
            grandeza = " unidade(s)";
        }

        if (grupoUtensilio.equals("GARFOS") || grupoUtensilio.equals("FACAS") || grupoUtensilio.equals("COLHERES") || grupoUtensilio.equals("PRATOS")) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario));
            total += (total / 100) * 0.2;
            grandeza = " unidade(s)";
        }

        if (grupoUtensilio.equals("PAPEL")) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) * 8;
            grandeza = " unidade(s)";
        }

        if (grupoUtensilio.equals("PALITOS") && tipoUtensilioIndex == 0) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario));
            grandeza = " unidade(s)";
        } else if (grupoUtensilio.equals("PALITOS") && tipoUtensilioIndex == 1) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) / 3.0;
            grandeza = " unidade(s)";
        }

        if (grupoUtensilio.equals("PRATOS") && tipoUtensilioIndex == 2) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario));
            grandeza = " unidade(s)";
        }

        if(grupoUtensilio.equals("CADEIRAS")) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario));
            grandeza = " unidade(s)";
        }

        if(grupoUtensilio.equals("MESAS")) {
            total = Double.parseDouble(convidadoDAO.carregarTotalConvidados(idUsuario)) / 4.0;
            grandeza = " unidade(s)";
        }

        total = Math.ceil(total);

        if (verifica == 0) {
            Utensilio utensilio = new Utensilio();
            utensilio.setTipo(tipoUtensilioValue);
            utensilio.setGrupo(grupoUtensilio);
            utensilio.setIdFesta(idFesta);
            utensilio.setQuantidade(total + grandeza);

            boolean sucesso = utensilioDAO.cadastrarUtensilio(utensilio);

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
