/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.anuncios.beans;

/**
 * Classes com os métodos sets e gets da tabela Anuncios e de totais para o
 * relatório.
 *
 * @author Eliaquim S. Macedo.
 */
public class Anuncios {

    private int ID_ANUNCIO;
    private String NOME_ANUNCIO;
    private String NOME_CLIENTE;
    private String DATA_INICIO;
    private String DATA_FIM;
    private float VLR_INVESTIDO_DIA;
    private int QTD_ANUNCIOS;
    private int QTD_CLIENTE;
    private float VLR_TOTAL_INVESTIDO;

    /**
     * Método para pegar o atributo ID_ANUNCIO da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna um valor inteiro do atributo ID_ANUNCIO.
     */
    public int getID_ANUNCIO() {
        return ID_ANUNCIO;
    }

    /**
     * Método para modificar os valores do atributo ID_ANUNCIO.
     *
     * @author Eliaquim S. Macedo.
     * @param ID_ANUNCIO Recebe um valor do tipo int contendo o número do ID do
     * anúncio.
     */
    public void setID_ANUNCIO(int ID_ANUNCIO) {
        this.ID_ANUNCIO = ID_ANUNCIO;
    }

    /**
     * Método para pegar o atributo NOME_ANUNCIO da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna uma String do atributo ID_ANUNCIO.
     */
    public String getNOME_ANUNCIO() {
        return NOME_ANUNCIO;
    }

    /**
     * Método para modificar os valores do atributo NOME_ANUNCIO.
     *
     * @author Eliaquim S. Macedo.
     * @param NOME_ANUNCIO Recebe um valor do tipo String contendo o nome do
     * anúncio.
     */
    public void setNOME_ANUNCIO(String NOME_ANUNCIO) {
        this.NOME_ANUNCIO = NOME_ANUNCIO;
    }

    /**
     * Método para pegar o atributo NOME_CLIENTE da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna uma String do atributo NOME_CLIENTE.
     */
    public String getNOME_CLIENTE() {
        return NOME_CLIENTE;
    }

    /**
     * Método para modificar os valores do atributo NOME_CLIENTE.
     *
     * @author Eliaquim S. Macedo.
     * @param NOME_CLIENTE Recebe um valor do tipo String contendo o nome do
     * cliente.
     */
    public void setNOME_CLIENTE(String NOME_CLIENTE) {
        this.NOME_CLIENTE = NOME_CLIENTE;
    }

    /**
     * Método para pegar o atributo DATA_INICIO da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna uma String como data no formato "dd/MM/yyyy" do atributo
     * DATA_INICIO.
     */
    public String getDATA_INICIO() {
        return DATA_INICIO;
    }

    /**
     * Método para modificar os valores do atributo DATA_INICIO.
     *
     * @author Eliaquim S. Macedo.
     * @param DATA_INICIO Recebe um valor do tipo string como data no formato
     * "dd/MM/yyyy".
     */
    public void setDATA_INICIO(String DATA_INICIO) {
        this.DATA_INICIO = DATA_INICIO;
    }

    /**
     * Método para pegar o atributo DATA_FIM da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna uma String como data no formato "dd/MM/yyyy" do atributo
     * DATA_FIM.
     */
    public String getDATA_FIM() {
        return DATA_FIM;
    }

    /**
     * Método para modificar os valores do atributo DATA_FIM.
     *
     * @author Eliaquim S. Macedo.
     * @param DATA_FIM Recebe um valor do tipo string como data no formato
     * "dd/MM/yyyy".
     */
    public void setDATA_FIM(String DATA_FIM) {
        this.DATA_FIM = DATA_FIM;
    }

    /**
     * Método para pegar o atributo VLR_INVESTIDO_DIA da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna um valor do tipo float do atributo VLR_INVESTIDO_DIA.
     */
    public float getVLR_INVESTIDO_DIA() {
        return VLR_INVESTIDO_DIA;
    }

    /**
     * Método para modificar os valores do atributo VLR_INVESTIDO_DIA.
     *
     * @author Eliaquim S. Macedo.
     * @param VLR_INVESTIDO_DIA Recebe um valor do tipo float.
     */
    public void setVLR_INVESTIDO_DIA(float VLR_INVESTIDO_DIA) {
        this.VLR_INVESTIDO_DIA = VLR_INVESTIDO_DIA;
    }

    /**
     * Método para pegar o atributo QTD_ANUNCIOS da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna um valor do tipo int do atributo QTD_ANUNCIOS.
     */
    public int getQTD_ANUNCIOS() {
        return QTD_ANUNCIOS;
    }

    /**
     * Método para modificar os valores do atributo QTD_ANUNCIOS.
     *
     * @author Eliaquim S. Macedo.
     * @param QTD_ANUNCIOS Recebe um valor do tipo int.
     */
    public void setQTD_ANUNCIOS(int QTD_ANUNCIOS) {
        this.QTD_ANUNCIOS = QTD_ANUNCIOS;
    }

    /**
     * Método para pegar o atributo QTD_CLIENTE da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna um valor do tipo int do atributo QTD_CLIENTE.
     */
    public int getQTD_CLIENTE() {
        return QTD_CLIENTE;
    }

    /**
     * Método para modificar os valores do atributo QTD_CLIENTE.
     *
     * @author Eliaquim S. Macedo.
     * @param QTD_CLIENTE Recebe um valor do tipo int.
     */
    public void setQTD_CLIENTE(int QTD_CLIENTE) {
        this.QTD_CLIENTE = QTD_CLIENTE;
    }

    /**
     * Método para pegar o atributo VLR_TOTAL_INVESTIDO da classe Anuncios.
     *
     * @author Eliaquim S. Macedo.
     * @return Retorna um valor do tipo float do atributo VLR_TOTAL_INVESTIDO.
     */
    public float getVLR_TOTAL_INVESTIDO() {
        return VLR_TOTAL_INVESTIDO;
    }

    /**
     * Método para modificar os valores do atributo VLR_TOTAL_INVESTIDO.
     *
     * @author Eliaquim S. Macedo.
     * @param VLR_TOTAL_INVESTIDO Recebe um valor do tipo float.
     */
    public void setVLR_TOTAL_INVESTIDO(float VLR_TOTAL_INVESTIDO) {
        this.VLR_TOTAL_INVESTIDO = VLR_TOTAL_INVESTIDO;
    }
}
