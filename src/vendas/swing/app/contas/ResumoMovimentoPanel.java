/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas.swing.app.contas;

import java.awt.Container;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import ritual.swing.FractionCellRenderer;
import ritual.swing.TApplication;
import ritual.util.NumberUtils;
import vendas.beans.MovimentoFilter;
import vendas.beans.ResumoMovimento;
import vendas.dao.FluxoDao;
import vendas.swing.core.EditPanel;
import vendas.swing.model.ResumoGrupoMovimentoModel;
import vendas.util.Constants;
import vendas.util.EditDialog;
import vendas.util.Reports;

/**
 *
 * @author jaime
 */
public class ResumoMovimentoPanel extends EditPanel {
    List<ResumoMovimento> lista;
    EditDialog edtDlg;

    /**
     * Creates new form ResumoMovimentoPanel
     */
    public ResumoMovimentoPanel() {
        initComponents();
    }

    public EditDialog getEdtDlg() {
        return edtDlg;
    }

    public void setEdtDlg(EditDialog edtDlg) {
        this.edtDlg = edtDlg;
    }
    
    @Override
    public void object2Field(Object obj) {
        MovimentoFilter filtro = (MovimentoFilter) obj;
        setFilter(filtro);
    }

    public void setFilter(MovimentoFilter filtro) {
        FluxoDao dao = (FluxoDao)TApplication.getInstance().lookupService("fluxoDao");
        lista = dao.findResumoGrupoMovimento(filtro);
        jTable1.setModel(new ResumoGrupoMovimentoModel(lista)); 
        jTable1.setDefaultRenderer(BigDecimal.class, new FractionCellRenderer(8, 2, SwingConstants.RIGHT));

        noContaText.setText(filtro.getConta().getNome());
        BigDecimal credito = new BigDecimal(0);
        BigDecimal debito = new BigDecimal(0);
        
        for (ResumoMovimento item : lista) {
            if (item.getValorCredito() != null) {
                credito = credito.add(item.getValorCredito());
            }
            if (item.getValorDebito()!= null) {
                debito = debito.add(item.getValorDebito());
            }
        } 
        
        nuCreditoText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuDebitoText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        
        nuCreditoText.setValue(NumberUtils.format(credito));
        nuDebitoText.setValue(NumberUtils.format(debito));
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
        noContaText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nuCreditoText = new javax.swing.JFormattedTextField();
        nuDebitoText = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel1.setText(bundle.getString("conta")); // NOI18N

        noContaText.setEditable(false);
        noContaText.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText(bundle.getString("totalCredito")); // NOI18N

        jLabel3.setText(bundle.getString("totatDebito")); // NOI18N

        nuCreditoText.setEditable(false);
        nuCreditoText.setColumns(12);
        nuCreditoText.setText("jFormattedTextField1");
        nuCreditoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuCreditoTextActionPerformed(evt);
            }
        });

        nuDebitoText.setEditable(false);
        nuDebitoText.setColumns(12);
        nuDebitoText.setText("jFormattedTextField2");

        jButton1.setText(bundle.getString("print")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noContaText))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(nuCreditoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nuDebitoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noContaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nuCreditoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(nuDebitoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(6, 6, 6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuCreditoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuCreditoTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuCreditoTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        edtDlg.clearAndHide();
        try {
            URL url = getClass().getResource(Constants.JRRESUMOGRUPOMOVIMENTO);
            Reports.showReport("Resumo", "", url, lista); //Reports.showReport(url, model, lista);
        } catch (Exception ex) {
            Logger.getLogger(ResumoMovimentoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField noContaText;
    private javax.swing.JFormattedTextField nuCreditoText;
    private javax.swing.JFormattedTextField nuDebitoText;
    // End of variables declaration//GEN-END:variables
}