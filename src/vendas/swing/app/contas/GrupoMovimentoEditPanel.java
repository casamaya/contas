/*
 * FormaPgtoEditPanel.java
 *
 * Created on 10 de Julho de 2007, 16:57
 */

package vendas.swing.app.contas;

import vendas.entity.GrupoMovimento;
import vendas.swing.core.EditPanel;
import ritual.swing.BoundedPlainDocument;

/**
 *
 * @author  p993702
 */
public class GrupoMovimentoEditPanel extends EditPanel {
    
    /** Creates new form FormaPgtoEditPanel */
    public GrupoMovimentoEditPanel() {
        initComponents();
    }

    @Override
    public void object2Field(Object obj) {
        GrupoMovimento formaPgto = (GrupoMovimento) obj;
        nomeField.setText(formaPgto.getNomeGrupo());
    }

    @Override
    public void field2Object(Object obj) {
        GrupoMovimento formaPgto = (GrupoMovimento) obj;
        formaPgto.setNomeGrupo(nomeField.getText());
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        nomeField = new javax.swing.JFormattedTextField();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel3.setText(bundle.getString("nome")); // NOI18N

        nomeField.setDocument(new BoundedPlainDocument(50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(nomeField, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JFormattedTextField nomeField;
    // End of variables declaration//GEN-END:variables
    
}
