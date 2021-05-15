/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.anuncios.dao;

import br.com.anuncios.beans.Anuncios;
import br.com.anuncios.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe contendo os métodos para intermediar a aplicação e a tabela anuncios
 * do banco de dados MySQL.
 *
 * @author Eliaquim S. Macedo.
 */
public class AnunciosDAO {

    private Conexao conexao;
    private Connection conn;

    //construtor da classe AnunciosDAO
    public AnunciosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    /**
     * Classe para validação da data inserida nos formulários.
     *
     * @author Eliaquim S. Macedo.
     */
    public class DateValidator {

        /**
         * Método para validação da data inserida nos formulários.
         *
         * @author Eliaquim S. Macedo.
         * @param date Recebe um valor do tipo string como data no formato
         * "dd/MM/yyyy".
         * @return Retorna "true" caso a data fornecida seja válida, caso
         * contrário retorna "false".
         */
        public boolean isValid(String date) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate d = LocalDate.parse(date, formatter);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * Método para inserir dados na tabela anuncios do banco de dados MySQL.
     *
     * @author Eliaquim S. Macedo.
     * @param anuncio Recebe como parâmetro um objeto do tipo Anuncios com os
     * valores do formulário de cadastro.
     */
    public void inserir(Anuncios anuncio) {
        String sql = "insert into ANUNCIOS (NOME_ANUNCIO,NOME_CLIENTE,DATA_INICIO,DATA_FIM,VLR_INVESTIDO_DIA) values (?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, anuncio.getNOME_ANUNCIO());
            stmt.setString(2, anuncio.getNOME_CLIENTE());
            stmt.setString(3, anuncio.getDATA_INICIO());
            stmt.setString(4, anuncio.getDATA_FIM());
            stmt.setFloat(5, anuncio.getVLR_INVESTIDO_DIA());
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para buscar no banco de dados o registro de um anúncio utilizando
     * parte do nome do anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param nomeanuncio Recebe um valor do tipo string contendo o nome do
     * anúncio ou parte dele.
     * @return Retorna um objeto do tipo Anuncios contendo os dados encontrados
     * no banco de dados, caso contrário retorna nulo.
     */
    public Anuncios consultar(String nomeanuncio) {
        String sql = "select * from ANUNCIOS where NOME_ANUNCIO like ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nomeanuncio + "%");
            ResultSet rs = stmt.executeQuery();
            Anuncios anuncio = new Anuncios();
            rs.first();
            anuncio.setID_ANUNCIO(rs.getInt("ID_ANUNCIO"));
            anuncio.setNOME_ANUNCIO(rs.getString("NOME_ANUNCIO"));
            anuncio.setNOME_CLIENTE(rs.getString("NOME_CLIENTE"));
            anuncio.setDATA_INICIO(rs.getString("DATA_INICIO"));
            anuncio.setDATA_FIM(rs.getString("DATA_FIM"));
            anuncio.setVLR_INVESTIDO_DIA(rs.getFloat("VLR_INVESTIDO_DIA"));
            return anuncio;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método para editar os dados cadastrados de um anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param anuncio Recebe como parâmetro um objeto do tipo Anuncios com os
     * valores do formulário de cadastro.
     */
    public void editar(Anuncios anuncio) {
        String sql = "update ANUNCIOS set NOME_ANUNCIO = ?, NOME_CLIENTE = ?, DATA_INICIO = ?, DATA_FIM =?, VLR_INVESTIDO_DIA = ? where ID_ANUNCIO = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, anuncio.getNOME_ANUNCIO());
            stmt.setString(2, anuncio.getNOME_CLIENTE());
            stmt.setString(3, anuncio.getDATA_INICIO());
            stmt.setString(4, anuncio.getDATA_FIM());
            stmt.setFloat(5, anuncio.getVLR_INVESTIDO_DIA());
            stmt.setInt(6, anuncio.getID_ANUNCIO());
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar anúncio: " + e);
        }
    }

    /**
     * Método para excluir um anúncio do banco de dados.
     *
     * @author Eliaquim S. Macedo.
     * @param id_anuncio Recebe um valor do tipo int contendo o número do ID a
     * ser excluido.
     */
    public void excluir(int id_anuncio) {
        String sql = "delete from ANUNCIOS where ID_ANUNCIO = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id_anuncio);
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Método para listar todos os registros filtrados da tabela anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @param nomecliente Parâmetro que recebe um valor do tipo String contendo
     * o nome do cliente.
     * @param datainicio Parâmetro que recebe um valor do tipo String contendo a
     * data início.
     * @param datafim Parâmetro que recebe um valor do tipo String contendo a
     * data fim.
     * @return Retorna um objeto do tipo lista contendo os valores buscados da
     * tabela de anuncios do bd MySQL, caso contrário retorna nulo.
     */
    public List<Anuncios> getAnuncios(String nomecliente, String datainicio, String datafim) {
        String sql = "select * from ANUNCIOS where NOME_CLIENTE like ? and STR_TO_DATE(DATA_INICIO,'%d/%m/%Y') >= STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(DATA_FIM,'%d/%m/%Y') <= STR_TO_DATE(?,'%d/%m/%Y')";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nomecliente + "%");
            stmt.setString(2, datainicio);
            stmt.setString(3, datafim);
            ResultSet rs = stmt.executeQuery();
            List<Anuncios> listaAnuncios = new ArrayList<>();
            while (rs.next()) {
                Anuncios anuncio = new Anuncios();
                anuncio.setID_ANUNCIO(rs.getInt("ID_ANUNCIO"));
                anuncio.setNOME_ANUNCIO(rs.getString("NOME_ANUNCIO"));
                anuncio.setNOME_CLIENTE(rs.getString("NOME_CLIENTE"));
                anuncio.setDATA_INICIO(rs.getString("DATA_INICIO"));
                anuncio.setDATA_FIM(rs.getString("DATA_FIM"));
                anuncio.setVLR_INVESTIDO_DIA(rs.getFloat("VLR_INVESTIDO_DIA"));
                listaAnuncios.add(anuncio);
            }
            return listaAnuncios;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Método para buscar a quantidade total de cliente e anúncios, e também a
     * soma total do valor total investido, diretamente do banco de dados MySQL.
     *
     * @author Eliaquim S. Macedo.
     * @param nomecliente Parâmetro que recebe um valor do tipo String contendo
     * o nome do cliente.
     * @param datainicio Parâmetro que recebe um valor do tipo String contendo a
     * data início.
     * @param datafim Parâmetro que recebe um valor do tipo String contendo a
     * data fim.
     * @return Retorna um objeto do tipo lista contendo os valores buscados da
     * tabela de anuncios do bd MySQL, caso contrário retorna nulo.
     */
    public Anuncios getTotais(String nomecliente, String datainicio, String datafim) {
        String sql = "SELECT COUNT(DISTINCT NOME_ANUNCIO) AS QTD_ANUNCIOS,\n"
                + "COUNT(DISTINCT NOME_CLIENTE) AS QTD_CLIENTE,\n"
                + "SUM((datediff(STR_TO_DATE(DATA_FIM,'%d/%m/%Y'),STR_TO_DATE(DATA_INICIO,'%d/%m/%Y')) +1) * VLR_INVESTIDO_DIA) AS VLR_TOTAL_INVESTIDO\n"
                + "FROM ANUNCIOS\n"
                + "WHERE NOME_CLIENTE LIKE ?\n"
                + "AND STR_TO_DATE(DATA_INICIO,'%d/%m/%Y') >= STR_TO_DATE(?,'%d/%m/%Y')\n"
                + "AND STR_TO_DATE(DATA_FIM,'%d/%m/%Y') <= STR_TO_DATE(?,'%d/%m/%Y')";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nomecliente + "%");
            stmt.setString(2, datainicio);
            stmt.setString(3, datafim);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            Anuncios total = new Anuncios();
            total.setQTD_ANUNCIOS(rs.getInt("QTD_ANUNCIOS"));
            total.setQTD_CLIENTE(rs.getInt("QTD_CLIENTE"));
            total.setVLR_TOTAL_INVESTIDO(rs.getFloat("VLR_TOTAL_INVESTIDO"));
            return total;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
