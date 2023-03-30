/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CompromissoFilterPanel.java
 *
 * Created on Mar 5, 2011, 11:21:39 AM
 */
package vendas.swing.app.contas;

import ritual.util.DateUtils;
import vendas.beans.CompromissoFilter;
import vendas.swing.core.EditPanel;

/**
 *
 * @author sam
 */
public class FechamentoFilterPanel extends EditPanel {

    /** Creates new form CompromissoFilterPanel */
    public FechamentoFilterPanel() {
        initComponents();
    }

    @Override
    public void object2Field(Object obj) {
        CompromissoFilter filter = (CompromissoFilter) obj;
    }

    @Override
    public void field2Object(Object obj) {
        CompromissoFilter filter = (CompromissoFilter) obj;
        try {
        filter.setDtEnd(DateUtils.parse(DateUtils.format(dtEndField.getDate())));
        filter.setDtIni(DateUtils.parse(DateUtils.format(dtIniField.getDate())));
        } catch (Exception e) {

        }
        /*
        if (todosRadio.isSelected()) {
            filter.setSituacao(0);
        }
        if (pendentesRadio.isSelected()) {
            filter.setSituacao(1);
        }
        if (pagosRadio.isSelected()) {
            filter.setSituacao(2);
        }
         * */
         

        StringBuilder filterTitle = new StringBuilder();

        if (dtIniField.getDate() != null) {
            filterTitle.append(getBundle().getString("vencimentosIni"));
            filterTitle.append(" ");
            filterTitle.append(DateUtils.format(dtIniField.getDate()));
            filterTitle.append(getBundle().getString("para"));
            filterTitle.append(DateUtils.format(dtEndField.getDate()));
            filterTitle.append(". ");
        }
        /*
        if (pendentesRadio.isSelected()) {
            filterTitle.append(getBundle().getString("pendentes"));
            filterTitle.append(". ");
        }
        if (pagosRadio.isSelected()) {
            filterTitle.append(getBundle().getString("pagos"));
            filterTitle.append(". ");
        }
         
         */
        filter.setTitle(filterTitle.toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dtIniField = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dtEndField = new com.toedter.calendar.JDateChooser();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel1.setText(bundle.getString("vencimentos")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        dtIniField.setDateFormatString("dd/MM/yyyy"); // NOI18N
        dtIniField.setName("dtIniField"); // NOI18N

        jLabel2.setText(bundle.getString("para")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        dtEndField.setDateFormatString("dd/MM/yyyy"); // NOI18N
        dtEndField.setName("dtEndField"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(dtIniField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dtEndField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(dtEndField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(dtIniField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtEndField;
    private com.toedter.calendar.JDateChooser dtIniField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
