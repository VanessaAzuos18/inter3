package lafiesta.model.domain;

import lafiesta.model.dao.ComidaDAO;
import lafiesta.model.dao.ConvidadoDAO;
import lafiesta.model.dao.FestaDAO;

public class Comida {
    private int id;
    private String grupo;
    private String tipo;
    private int idFesta;
    private int idFornecedor;
    private String quantidade;

    public Comida() {}

    public Comida(String grupo, String tipo, String quantidade) {
        this.grupo = grupo;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public boolean calcularComida(String grupoComida, int tipoComida, int idUsuario, String tipoComidaValue, boolean cadastrarComida, Comida com) {
        double total = 0;
        String grandeza = null;

        ComidaDAO comidaDAO = new ComidaDAO();
        ConvidadoDAO convidadoDAO = new ConvidadoDAO();

        int verifica = comidaDAO.verificarComida(idUsuario, tipoComidaValue);
        if(grupoComida.equals("CHURRASCO") && (tipoComida >= 0 && tipoComida <= 4)) {
            //Calculo CARNES HOMENS
            double quantidade = comidaDAO.buscarQuantidadeCarne(idUsuario) + 1;
            quantidade = 600 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(idUsuario)[0];
            total = quantidade;

            //Calculo CARNES MULHERES
            quantidade = comidaDAO.buscarQuantidadeCarne(idUsuario) + 1;
            quantidade = 400 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(idUsuario)[1];
            total += quantidade;

            //Calculo CARNES CRIANÇAS
            quantidade = comidaDAO.buscarQuantidadeCarne(idUsuario) + 1;
            quantidade = 200 / quantidade;
            quantidade *= convidadoDAO.contarPessoasGrupo(idUsuario)[2];
            total += quantidade;

            comidaDAO.atualizarQuantiadeCarne(String.valueOf(total)+"g", idUsuario);
            grandeza = "g";
        } else if(grupoComida.equals("CHURRASCO") && tipoComida == 5) //QUEIJO COALHO
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 5.0;
            grandeza = " pct";
        }
        else if (grupoComida.equals("CHURRASCO") && tipoComida == 6) //PÃO DE ALHO
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 10.0;
            grandeza = " pct";
        }
        else if (grupoComida.equals("CHURRASCO") && tipoComida == 7) // MAIONESE
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 10.0;
            total *= 500;
            grandeza = "g";
        }
        else if (grupoComida.equals("CHURRASCO") && tipoComida == 8) //ARROZ
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 6.0;
            grandeza ="kg";
        }
        else if (grupoComida.equals("CHURRASCO") && tipoComida == 11) //FAROFA
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 10.0;
            grandeza = " pct";
        }
        else if (grupoComida.equals("CHURRASCO") && tipoComida == 12) //PÃO FRANCÊS
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 2;
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("CHURRASCO") && (tipoComida == 9 || tipoComida == 10)) //SALADA
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 150;
            grandeza = "g";
        }

        if (grupoComida.equals("FINGER FOODS") && (tipoComida >= 0 && tipoComida <= 10)){ // SALGADINHOS
            if (comidaDAO.obterTotalComida(idUsuario) == 0)
            {
                total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 15;
            }
            else
            {
                total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 8) / (int) comidaDAO.obterTotalComida(idUsuario);
                comidaDAO.atualizarQuantidadeSalgado(Double.toString(total), idUsuario);
            }
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("FINGER FOODS") && (tipoComida >= 11 && tipoComida <= 13)){ //PASTEL
            if (comidaDAO.obterTotalComida(idUsuario) == 0)
            {
                total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 10;
            }
            else
            {
                total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 2) / (int)comidaDAO.obterTotalComida(idUsuario);
                comidaDAO.atualizarQuantidadePastel(Double.toString(total), idUsuario);
            }
            grandeza = " unidade(s)";
        }

        if (grupoComida.equals("DOCE") && (tipoComida >= 0 && tipoComida <= 6))
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 6);
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("DOCE") && tipoComida == 7) // BOLO FESTA
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 150);
            grandeza = "g";
        }
        else if (grupoComida.equals("DOCE") && tipoComida == 8) // BOLO SIMPLES
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 200);
            grandeza = "g";
        }

        if (grupoComida.equals("FRIOS") && tipoComida == 0) //MUSSARELA
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 75);
            grandeza = "g";
        }
        else if (grupoComida.equals("FRIOS") && tipoComida == 1) // PRESUNTO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 50);
            grandeza = "g";
        }
        else if (grupoComida.equals("FRIOS") && (tipoComida >= 2 && tipoComida <= 4)) //SALAME, MORTADELA, COPA
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 25);
            grandeza = "g";
        }

        if (grupoComida.equals("COFFEE BREAK") && tipoComida == 0) //PÃO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 3);
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 1) //TORRADA
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 2);
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 2) //BOLACHA RECHEADA
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 5.0);
            grandeza = " pct";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 3) //BISCOITO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 150);
            grandeza = "g";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 4) //PÃO DE QUEIJO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 5);
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 5) // REQUEIJÃO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 8.0);
            grandeza = " pote";
        }
        else if (grupoComida.equals("COFFEE BREAK") && tipoComida == 6) // CREPIOCA
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario));
            grandeza = " unidade(s)";
        }

        if (grupoComida.equals("VEGETARIANA") && tipoComida == 0) //TORTA DE LEGUMES
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 6.0);
            grandeza = "kg";
        }
        else if (grupoComida.equals("VEGETARIANA") && (tipoComida == 0 || tipoComida == 1)) //SALGADO, PÃO DE QUEIJO
        {
            total = (Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 5.0);
            grandeza = " unidade(s)";
        }
        else if (grupoComida.equals("VEGETARIANA") && tipoComida == 3) //SALADA
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 150;
            grandeza = "g";
        }

        if (grupoComida.equals("BOTECO") && (tipoComida == 0 || tipoComida == 1)) //POLENTA, MANDIOCA
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 6.0;
            grandeza = "kg";
        }
        else if (grupoComida.equals("BOTECO") && tipoComida == 0) //AMENDOIM
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) * 100;
            grandeza = "g";
        }
        else if (grupoComida.equals("BOTECO") && tipoComida == 0) //TORRESMO
        {
            total = Integer.parseInt(convidadoDAO.carregarTotalConvidados(idUsuario)) / 8.0;
            grandeza = "kg";
        }

        total = Math.ceil(total);
        if (verifica == 0 && cadastrarComida == true)
        {
            FestaDAO festaDAO = new FestaDAO();
            com.setTipo(tipoComidaValue);
            com.setGrupo(grupoComida);
            com.setIdFesta(festaDAO.buscarId(idUsuario));
            com.setQuantidade(total + grandeza);

            boolean sucesso = comidaDAO.cadastrarComida(idUsuario, com);
            if (sucesso) {
                return true;
            } else {
                return false;
            }
        } else {
            if(cadastrarComida == false) {
                com.setTipo(tipoComidaValue);
                com.setGrupo(grupoComida);
                com.setQuantidade(total + grandeza);
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
