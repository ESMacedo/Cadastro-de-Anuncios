/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.anuncios.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe Conexao, contem o método "getConexao" para estabelecer conexão com o
 * banco de dados MySQL.
 *
 * @author Eliaquim S. Macedo.
 */
public class Conexao {

    /**
     * Método para estabeler a conexão com o banco de dados e retornar a
     * instância da conexão.
     *
     * author Eliaquim S. Macedo.
     *
     * @return Retorna a instância de conexão caso não aconteça nenhum erro,
     * caso contrátio retorna nulo e exibe a mensagem de erro.
     */
    public Connection getConexao() {
        // tenta estabelecer a conexão, se der erro, exibi uma mensagem de erro
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_anuncios?serverTimezone=UTC", // string de conexão
                    "root", // usuário do mysql
                    "1234"); // senha do mysql
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}
