/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserProfileForm.java
 *
 * Created on Apr 11, 2010, 12:44:09 PM
 */
package vendas.security;

import vendas.entity.User;
import vendas.swing.core.EditPanel;

/**
 *
 * @author sam
 */
public class UserProfileForm extends EditPanel {

    /** Creates new form UserProfileForm */
    public UserProfileForm() {
        initComponents();
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
        userNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        passwdField = new javax.swing.JPasswordField();
        repeatPasswdField = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        emailPasswdField = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        emailServerPortField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        assinaturaTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        serverField = new javax.swing.JTextField();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel1.setText(bundle.getString("userName")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        userNameField.setEditable(false);
        userNameField.setText("jTextField1");
        userNameField.setName("userField"); // NOI18N

        jLabel2.setText(bundle.getString("password")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(bundle.getString("retypePasswd")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        passwdField.setText("jPasswordField1");
        passwdField.setName("passwdField"); // NOI18N

        repeatPasswdField.setText("jPasswordField2");
        repeatPasswdField.setName("retypePasswdField"); // NOI18N

        jLabel4.setText(bundle.getString("email")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        emailField.setText("jTextField1");
        emailField.setName("emailField"); // NOI18N
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel7.setText(bundle.getString("emailPasswd")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        emailPasswdField.setText("jPasswordField1");
        emailPasswdField.setName("emailPasswdField"); // NOI18N

        jLabel8.setText(bundle.getString("emailServerPort")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        emailServerPortField.setText("jTextField1");
        emailServerPortField.setName("emailServerPortField"); // NOI18N

        jLabel5.setText(bundle.getString("assinatura")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        assinaturaTextArea.setColumns(20);
        assinaturaTextArea.setRows(5);
        assinaturaTextArea.setName("assinaturaTextArea"); // NOI18N
        jScrollPane1.setViewportView(assinaturaTextArea);

        jLabel6.setText(bundle.getString("emailServerName")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        serverField.setText("jTextField1");
        serverField.setName("serverField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(passwdField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(repeatPasswdField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(serverField, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(emailPasswdField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailServerPortField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(passwdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repeatPasswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailPasswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailServerPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    @Override
    public void object2Field(Object obj) {
        User user = (User) obj;
        assinaturaTextArea.setText(user.getAssinatura());
        emailField.setText(user.getEmail());
        passwdField.setText(user.getPasswd());
        repeatPasswdField.setText((user.getRepeatPasswd()));
        serverField.setText((user.getServerName()));
        userNameField.setText(user.getUserName());
        emailPasswdField.setText(user.getEmailPasswd());
        Integer port = user.getPort();
        if (port == null)
           emailServerPortField.setText("465");
        else
            emailServerPortField.setText(port.toString());
    }

    @Override
    public void field2Object(Object obj) {
        User user = (User) obj;
        user.setPasswd(new String(passwdField.getPassword()));
        user.setRepeatPasswd(new String(repeatPasswdField.getPassword()));
        user.setAssinatura(assinaturaTextArea.getText());
        user.setEmail(emailField.getText());
        user.setServerName(serverField.getText());
        String passwd = user.getPasswd().toString();
        user.setEmailPasswd(new String(emailPasswdField.getPassword()));
        user.setPort(Integer.decode(emailServerPortField.getText()));
        
        String newPasswd = user.getRepeatPasswd().toString();
        String userName = user.getUserName();
        try {
            user.setRepeatPasswd(Crypt.encrypt(userName, newPasswd));
            user.setPasswd(Crypt.encrypt(userName, passwd));
            //user.setEmailPasswd(Crypt.encrypt());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

/*    @Override
    public boolean entryValidate() {
        String passwd = passwdField.getPassword().toString();
        String repeatpasswd = repeatPasswdField.getPassword().toString();
        if ((passwd == null) || (passwd.length() == 0) || (repeatpasswd == null) || (repeatpasswd.length() == 0))
            return false;
        else
            return true;

    }
*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea assinaturaTextArea;
    private javax.swing.JTextField emailField;
    private javax.swing.JPasswordField emailPasswdField;
    private javax.swing.JTextField emailServerPortField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField passwdField;
    private javax.swing.JPasswordField repeatPasswdField;
    private javax.swing.JTextField serverField;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables


}
