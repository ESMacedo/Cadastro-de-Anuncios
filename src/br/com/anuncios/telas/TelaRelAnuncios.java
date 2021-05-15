/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.anuncios.telas;

import br.com.anuncios.beans.Anuncios;
import br.com.anuncios.dao.AnunciosDAO;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Classe principal da tela de relatório de anúncios.
 *
 * @author Eliaquim S. Macedo.
 */
public class TelaRelAnuncios extends javax.swing.JInternalFrame {

    /**
     * Método para preencher a tabela de relatório de anúncios.
     *
     * @author Eliaquim S. Macedo.
     */
    private void preencheTabela() {
        AnunciosDAO anunciosDAO = new AnunciosDAO();
        DecimalFormat dfReal = new DecimalFormat("R$ #,###.00");
        DecimalFormat df = new DecimalFormat("#,###");
        String nomecliente = txtNomeCliente.getText();
        String datainicio = txtDataInicio.getText();
        String datafim = txtDataFim.getText();

        if (datainicio.isEmpty()) {
            datainicio = "01/01/1900";
        }
        if (datafim.isEmpty()) {
            datafim = "31/12/9999";
        }

        List<Anuncios> listaAnuncios = anunciosDAO.getAnuncios(nomecliente, datainicio, datafim);

        DefaultTableModel tabelaAnuncios = (DefaultTableModel) tblRelAnuncios.getModel();
        //limpando a tabela para preencher com novos dados
        tabelaAnuncios.setNumRows(0);
        // percorrendo a "listaAnuncios" e inserindo na tabelaAnuncios
        for (Anuncios a : listaAnuncios) {
            Object[] obj = new Object[]{
                a.getNOME_ANUNCIO(),
                a.getNOME_CLIENTE(),
                a.getDATA_INICIO(),
                a.getDATA_FIM(),
                dfReal.format(a.getVLR_INVESTIDO_DIA()),
                dfReal.format(valorTotal(a.getDATA_INICIO(), a.getDATA_FIM(), a.getVLR_INVESTIDO_DIA())),
                df.format(Math.round(calcularVisualizacoes(valorTotal(a.getDATA_INICIO(), a.getDATA_FIM(), a.getVLR_INVESTIDO_DIA())))),
                df.format(Math.round(calcularCliques(valorTotal(a.getDATA_INICIO(), a.getDATA_FIM(), a.getVLR_INVESTIDO_DIA())))),
                df.format(Math.round(calcularCompartilhamentos(valorTotal(a.getDATA_INICIO(), a.getDATA_FIM(), a.getVLR_INVESTIDO_DIA()))))
            };
            tabelaAnuncios.addRow(obj);
        }
    }

    /**
     * Método para preencher os campos de totais do relatório de anúncios.
     *
     * @author Eliaquim S. Macedo.
     */
    private void preencheTotais() {
        AnunciosDAO anunciosDAO = new AnunciosDAO();
        DecimalFormat dfReal = new DecimalFormat("R$ #,###.00");
        DecimalFormat df = new DecimalFormat("#,###");
        String nomecliente = txtNomeCliente.getText();
        String datainicio = txtDataInicio.getText();
        String datafim = txtDataFim.getText();

        if (datainicio.isEmpty()) {
            datainicio = "01/01/1900";
        }
        if (datafim.isEmpty()) {
            datafim = "31/12/9999";
        }

        Anuncios total = anunciosDAO.getTotais(nomecliente, datainicio, datafim);

        lblTotalAnuncios.setText(df.format(total.getQTD_ANUNCIOS()));
        lblTotalClientes.setText(df.format(total.getQTD_CLIENTE()));
        lblValorTotalInves.setText(dfReal.format(total.getVLR_TOTAL_INVESTIDO()));
        lblQtdTotalVisu.setText(df.format(Math.round(calcularVisualizacoes(total.getVLR_TOTAL_INVESTIDO()))));
        lblQtdTotalCliques.setText(df.format(Math.round(calcularCliques(total.getVLR_TOTAL_INVESTIDO()))));
        lblQtdTotalCompar.setText(df.format(Math.round(calcularCompartilhamentos(total.getVLR_TOTAL_INVESTIDO()))));
    }

    /**
     * Método para calcular o valor total investido em um anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param datainicio Parâmetro que recebe um valor do tipo String contendo a
     * data início.
     * @param datafim Parâmetro que recebe um valor do tipo String contendo a
     * data fim.
     * @param valordia Parâmetro que recebe um valor do tipo float contendo o
     * valor investido por dia.
     * @return Retorna um valor do tipo float contendo o valor total investido
     * de um anúncio.
     */
    public float valorTotal(String datainicio, String datafim, float valordia) {

        String dtInicio = new String(datainicio);
        String dtFim = new String(datafim);

        int anoInicio = Integer.parseInt(dtInicio.substring(6, 10));
        int mesInicio = Integer.parseInt(dtInicio.substring(3, 5));
        int diaInicio = Integer.parseInt(dtInicio.substring(0, 2));

        int anoFim = Integer.parseInt(dtFim.substring(6, 10));
        int mesFim = Integer.parseInt(dtFim.substring(3, 5));
        int diaFim = Integer.parseInt(dtFim.substring(0, 2));

        LocalDate startDate = LocalDate.of(anoInicio, mesInicio, diaInicio);
        LocalDate endDate = LocalDate.of(anoFim, mesFim, diaFim);

        long dias = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        float valortotalinvestido = valordia * dias;
        return valortotalinvestido;
    }

    /**
     * Método para calcular a quantidade máxima de visualizações de um anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param valor Parâmetro que recebe um valor do tipo float contendo o valor
     * total investido.
     * @return Retorna um valor do tipo float contendo a quantidade máxima de
     * visualizações de um anúncio.
     */
    public static float calcularVisualizacoes(float valor) {
        //Variáveis
        float qtd_pessoas_visual = valor * 30; //variável que recebe a quantidade das primeiras visualizações sem compartilhamento
        float qtd_pessoas_click; //variável que recebe a quantidade de pessoas que clicaram no anúncio
        float qtd_pessoas_compar; //variável que recebe a quantidade de pessoas que compartilharam o anúncio
        float qtd_pessoas_visual_compar; //variável que recebe a quantidade de pessoas visualizaram o anúncio após o mesmo ter sido compartilhado
        float total_visual = 0; //variável que recebe a quantidade total de visualizações

        for (int no_compar = 0; no_compar < 4; no_compar++) {
            qtd_pessoas_click = qtd_pessoas_visual * 0.12f; //cálculo da quantidade de pessoas que clicaram no anúncio
            qtd_pessoas_compar = qtd_pessoas_click * 0.15f; //cálculo da quantidade de pessoas que compartilharam o anúncio
            qtd_pessoas_visual_compar = qtd_pessoas_compar * 40; //cálculo da quantidade de pessoas que visualizaram o anúncio após o mesmo ter sido compartilhado

            total_visual = (no_compar == 0) ? (total_visual + qtd_pessoas_visual + qtd_pessoas_visual_compar) : (total_visual + qtd_pessoas_visual_compar); //Soma os valores das primeiras visualizações (sem compartilhamento) apenas no primeiro loop

            qtd_pessoas_visual = qtd_pessoas_visual_compar; //Após o primeiro loop a quantidade de visualizações passa a ser aquelas obtidas depois de um compartilhamento
        }
        return Math.round(total_visual);
    }

    /**
     * Método para calcular a quantidade máxima de cliques de um anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param valor Parâmetro que recebe um valor do tipo float contendo o valor
     * total investido.
     * @return Retorna um valor do tipo float contendo a quantidade máxima de
     * cliques de um anúncio.
     */
    public static float calcularCliques(float valor) {
        //Variáveis
        float qtd_pessoas_visual = valor * 30; //variável que recebe a quantidade das primeiras visualizações sem compartilhamento
        float qtd_pessoas_click; //variável que recebe a quantidade de pessoas que clicaram no anúncio
        float qtd_pessoas_compar; //variável que recebe a quantidade de pessoas que compartilharam o anúncio
        float qtd_pessoas_visual_compar; //variável que recebe a quantidade de pessoas visualizaram o anúncio após o mesmo ter sido compartilhado
        float total_clique = 0; //variável que recebe a quantidade total de cliques

        for (int no_compar = 0; no_compar < 4; no_compar++) {
            qtd_pessoas_click = qtd_pessoas_visual * 0.12f; //cálculo da quantidade de pessoas que clicaram no anúncio
            qtd_pessoas_compar = qtd_pessoas_click * 0.15f; //cálculo da quantidade de pessoas que compartilharam o anúncio
            qtd_pessoas_visual_compar = qtd_pessoas_compar * 40; //cálculo da quantidade de pessoas que visualizaram o anúncio após o mesmo ter sido compartilhado

            total_clique = total_clique + qtd_pessoas_click; // Somatório de cliques do loops

            qtd_pessoas_visual = qtd_pessoas_visual_compar; //Após o primeiro loop a quantidade de visualizações passa a ser aquelas obtidas depois de um compartilhamento
        }
        return Math.round(total_clique);
    }

    /**
     * Método para calcular a quantidade máxima de compartilhamentos de um
     * anúncio.
     *
     * @author Eliaquim S. Macedo.
     * @param valor Parâmetro que recebe um valor do tipo float contendo o valor
     * total investido.
     * @return Retorna um valor do tipo float contendo a quantidade máxima de
     * compartilhamentos de um anúncio.
     */
    public static float calcularCompartilhamentos(float valor) {
        //Variáveis
        float qtd_pessoas_visual = valor * 30; //variável que recebe a quantidade das primeiras visualizações sem compartilhamento
        float qtd_pessoas_click; //variável que recebe a quantidade de pessoas que clicaram no anúncio
        float qtd_pessoas_compar; //variável que recebe a quantidade de pessoas que compartilharam o anúncio
        float qtd_pessoas_visual_compar; //variável que recebe a quantidade de pessoas visualizaram o anúncio após o mesmo ter sido compartilhado
        float total_compartilhamento = 0; //variável que recebe a quantidade total de compartilhamentos

        for (int no_compar = 0; no_compar < 4; no_compar++) {
            qtd_pessoas_click = qtd_pessoas_visual * 0.12f; //cálculo da quantidade de pessoas que clicaram no anúncio
            qtd_pessoas_compar = qtd_pessoas_click * 0.15f; //cálculo da quantidade de pessoas que compartilharam o anúncio
            qtd_pessoas_visual_compar = qtd_pessoas_compar * 40; //cálculo da quantidade de pessoas que visualizaram o anúncio após o mesmo ter sido compartilhado

            total_compartilhamento = total_compartilhamento + qtd_pessoas_compar;

            qtd_pessoas_visual = qtd_pessoas_visual_compar; //Após o primeiro loop a quantidade de visualizações passa a ser aquelas obtidas depois de um compartilhamento
        }
        return Math.round(total_compartilhamento);
    }

    /**
     * Método construa da classe TelaRelAnuncios, cria um formulário de
     * relatório de anúncios.
     *
     * @author Eliaquim S. Macedo.
     */
    public TelaRelAnuncios() {
        initComponents();
        preencheTabela();
        preencheTotais();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRelAnuncios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataFim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblTotalAnuncios = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalClientes = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblValorTotalInves = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblQtdTotalVisu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblQtdTotalCliques = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblQtdTotalCompar = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Relatório de Anúncios");
        setPreferredSize(new java.awt.Dimension(1059, 598));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Relatório de Anúncios");

        tblRelAnuncios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ANUNCIO", "CLIENTE", "DATA INICIO", "DATA FIM", "VLR INVESTIDO POR DIA", "VLR TOTAL INVESTIDO", "QTD MAX VISUALIZACOES", "QTD MAX CLIQUES", "QTD MAX COMPARTILHAMENTO"
            }
        ));
        jScrollPane1.setViewportView(tblRelAnuncios);

        jLabel2.setText("Nome do Cliente:");

        txtNomeCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNomeClienteCaretUpdate(evt);
            }
        });

        jLabel3.setText("Data Início:");

        txtDataInicio.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDataInicioCaretUpdate(evt);
            }
        });

        jLabel4.setText("Data Fim:");

        txtDataFim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDataFimCaretUpdate(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Total de Anúncios");

        lblTotalAnuncios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalAnuncios.setForeground(new java.awt.Color(0, 0, 153));
        lblTotalAnuncios.setText("valor");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Total de Clientes");

        lblTotalClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalClientes.setForeground(new java.awt.Color(0, 0, 153));
        lblTotalClientes.setText("valor");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Valor Total Investido");

        lblValorTotalInves.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblValorTotalInves.setForeground(new java.awt.Color(0, 0, 153));
        lblValorTotalInves.setText("valor");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Qtd Total de Visualizações");

        lblQtdTotalVisu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQtdTotalVisu.setForeground(new java.awt.Color(0, 0, 153));
        lblQtdTotalVisu.setText("valor");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Qtd Total de Cliques");

        lblQtdTotalCliques.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQtdTotalCliques.setForeground(new java.awt.Color(0, 0, 153));
        lblQtdTotalCliques.setText("valor");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Qtd Total de Compartilhamento");

        lblQtdTotalCompar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQtdTotalCompar.setForeground(new java.awt.Color(0, 0, 153));
        lblQtdTotalCompar.setText("valor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(lblTotalAnuncios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTotalClientes)
                    .addComponent(jLabel6))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(lblValorTotalInves))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(lblQtdTotalVisu))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(lblQtdTotalCliques))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(lblQtdTotalCompar))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(407, 407, 407)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalClientes)
                    .addComponent(lblTotalAnuncios)
                    .addComponent(lblValorTotalInves)
                    .addComponent(lblQtdTotalVisu)
                    .addComponent(lblQtdTotalCliques)
                    .addComponent(lblQtdTotalCompar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26))
        );

        setBounds(0, 0, 1059, 598);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNomeClienteCaretUpdate
        preencheTabela();
        preencheTotais();
    }//GEN-LAST:event_txtNomeClienteCaretUpdate

    private void txtDataInicioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDataInicioCaretUpdate
        preencheTabela();
        preencheTotais();
    }//GEN-LAST:event_txtDataInicioCaretUpdate

    private void txtDataFimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDataFimCaretUpdate
        preencheTabela();
        preencheTotais();
    }//GEN-LAST:event_txtDataFimCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQtdTotalCliques;
    private javax.swing.JLabel lblQtdTotalCompar;
    private javax.swing.JLabel lblQtdTotalVisu;
    private javax.swing.JLabel lblTotalAnuncios;
    private javax.swing.JLabel lblTotalClientes;
    private javax.swing.JLabel lblValorTotalInves;
    private javax.swing.JTable tblRelAnuncios;
    private javax.swing.JTextField txtDataFim;
    private javax.swing.JTextField txtDataInicio;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}
