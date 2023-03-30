/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TrocaSenhaForm.java
 *
 * Created on Sep 2, 2012, 9:50:13 PM
 */

package vendas.security;

import vendas.entity.User;
import vendas.swing.core.EditPanel;

/**
 *
 * @author sam
 */
public class TrocaSenhaForm extends EditPanel {

    /** Creates new form TrocaSenhaForm */
    public TrocaSenhaForm() {
        initComponents();
    }

    @Override
    public void object2Field(Object obj) {
        User user = (User) obj;
        super.object2Field(obj);
    }

    @Override
    public void field2Object(Object obj) {
        User user = (User) obj;
        user.setPasswd(new String(passwordField.getPassword()));
        user.setNewPasswd(new String(newPassword1Field.getPassword()));
        user.setRepeatPasswd(new String(newPassword2Field.getPassword()));
    }

    @Override
    public boolean entryValidate() {
        String senha = new String(passwordField.getPassword());
        String novaSenha1 = new String(newPassword1Field.getPassword());
        String novaSenha2 = new String(newPassword2Field.getPassword());
        if ((senha == null) || (novaSenha1 == null) || (novaSenha2 == null))
            return false;

        return true;
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
        passwordField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        newPassword1Field = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        newPassword2Field = new javax.swing.JPasswordField();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel1.setText(bundle.getString("senhaAtual")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        passwordField.setName("passwordField"); // NOI18N

        jLabel2.setText(bundle.getString("novaSenha")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        newPassword1Field.setName("newPassword1Field"); // NOI18N

        jLabel3.setText(bundle.getString("repetirNovaSenha")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        newPassword2Field.setName("newPassword2Field"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(newPassword2Field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(66, 66, 66)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(passwordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .add(newPassword1Field, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .add(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newPassword1Field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newPassword2Field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField newPassword1Field;
    private javax.swing.JPasswordField newPassword2Field;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration//GEN-END:variables

}
