package lafiesta.model;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;

/**
 *
 * @author Lucas Tardioli
 */
public class BDFabricaConexao {

    public  static  Connection getConnection(){

        return new BDConexaoMySQL().getConnection();

    }

}
