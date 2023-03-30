/*
 * LoginForm.java
 *
 * Created on 30 de Agosto de 2008, 15:00
 */

package vendas.swing.app.main;

import vendas.entity.User;
import vendas.swing.core.EditPanel;

/**
 *
 * @author  Sam
 */
public class LoginForm extends EditPanel {

    /** Creates new form LoginForm */
    public LoginForm() {
        initComponents();
    }

    @Override
    public void object2Field(Object obj) {
        
    }
    
    @Override
    public void field2Object(Object obj) {
        User user = (User) obj;
        
        user.setUserName(userName.getText());
        user.setPasswd(passwdField.getPassword().toString());
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
        userName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwdField = new javax.swing.JPasswordField();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel1.setText(bundle.getString("user")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        userName.setName("userName"); // NOI18N

        jLabel2.setText(bundle.getString("password")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        passwdField.setName("passwdField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(passwdField, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passwdField;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables

}
