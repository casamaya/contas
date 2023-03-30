/*
 * EmailPanel.java
 *
 * Created on 3 de Fevereiro de 2008, 18:36
 */

package vendas.util;

import demo.ScrollablePicture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import org.hibernate.Hibernate;
import ritual.swing.TApplication;
import vendas.beans.EmailBean;
import vendas.swing.core.EditPanel;

/**
 *
 * @author  Sam
 */
public class EmailClientePanel extends EditPanel {

    List<byte[]> anexos;
    
    /** Creates new form EmailPanel */
    public EmailClientePanel() {
        initComponents();
        viewButton.setVisible(false);
    }

    public EmailClientePanel(String s) {
        initComponents();
        viewButton.setVisible(false);
    }

    @Override
    public void object2Field(Object obj) {
        EmailBean email = (EmailBean)obj;
        subjectField.setText(email.getSubject());
        textPane.setText(email.getText());
        anexos = new ArrayList<byte[]>();
    }

    @Override
    public void field2Object(Object obj) {
        EmailBean email = (EmailBean)obj;
        email.setSubject(subjectField.getText());
        email.setText(textPane.getText());
        if (!anexos.isEmpty())
            email.setAnexos(anexos);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        subjectField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        anexarButton = new javax.swing.JButton();
        fileLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vendas/resources/Vendas"); // NOI18N
        jLabel2.setText(bundle.getString("assunto")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        subjectField.setName("subjectField"); // NOI18N

        jLabel3.setText(bundle.getString("mensagem")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        textPane.setName("textPane"); // NOI18N
        jScrollPane1.setViewportView(textPane);

        anexarButton.setText(bundle.getString("anexar")); // NOI18N
        anexarButton.setName("anexarButton"); // NOI18N
        anexarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexarButtonActionPerformed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(EmailClientePanel.class);
        fileLabel.setText(resourceMap.getString("fileLabel.text")); // NOI18N
        fileLabel.setName("fileLabel"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendas/resources/Open16.gif"))); // NOI18N
        viewButton.setToolTipText(bundle.getString("abrirFoto")); // NOI18N
        viewButton.setName("viewButton"); // NOI18N
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(subjectField, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(anexarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(anexarButton)
                        .addComponent(fileLabel)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewButton)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void anexarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexarButtonActionPerformed
        // TODO add your handling code here:
                final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            byte[] bFile = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                //convert file into array of bytes
                fileInputStream.read(bFile);
                fileInputStream.close();
                anexos.add(bFile);
                fileLabel.setText(file.getName());
                        //viewButton.setVisible(true);
                fileLabel.invalidate();
            } catch (Exception e) {
                getLogger().error(getBundle().getString("saveErrorMessage"), e);
                Messages.errorMessage(getBundle().getString("saveErrorMessage"));
            }
        }
    }//GEN-LAST:event_anexarButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        anexos = new ArrayList<byte[]>();
        fileLabel.setText("sem anexo");
        viewButton.setVisible(false);
        fileLabel.invalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed

        Image image = null;
        try {
            InputStream is = Hibernate.createBlob(anexos.get(0)).getBinaryStream();
            Reports.showReportStream(is, fileLabel.getText());
        } catch (Exception e) {
            getLogger().error(getBundle().getString("imageErrorMessage"), e);
            Messages.errorMessage(getBundle().getString("imageErrorMessage"));
        }

}//GEN-LAST:event_viewButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anexarButton;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField subjectField;
    private javax.swing.JTextPane textPane;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
    
}
