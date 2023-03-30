/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import vendas.beans.EmailBean;
import ritual.swing.TApplication;
import vendas.dao.EmpresaDao;
import vendas.dao.UserDao;
import vendas.entity.Params;
import vendas.entity.User;
import vendas.entity.Vendedor;

/**
 *
 * @author Sam
 */
public class ReportViewFrame extends ViewFrame {

    private JasperPrint jasperPrint;
    private EmailBean email;
    //private Properties props;
    private boolean fornecedor;
    private InputStream customStream;
    private byte[] bytes;
    private Boolean incluirAnexo;

    public ReportViewFrame() {
        super();
        email = (EmailBean) TApplication.getInstance().lookupService("emailBean");
        loadProperties();
    }

    public ReportViewFrame(String title) {
        super(title);
        email = (EmailBean) TApplication.getInstance().lookupService("emailBean");
        email.setSubject(title);
        loadProperties();
    }

    public ReportViewFrame(String title, JasperPrint jasperPrint) {
        super(title);
        email = (EmailBean) TApplication.getInstance().lookupService("emailBean");
        email.setSubject(title);
        this.jasperPrint = jasperPrint;
        loadProperties();
    }

    public Boolean getIncluirAnexo() {
        return incluirAnexo;
    }

    public void setIncluirAnexo(Boolean incluirAnexo) {
        this.incluirAnexo = incluirAnexo;
    }

    public InputStream getCustomStream() {
        return customStream;
    }

    public void setCustomStream(InputStream customStream) {
        this.customStream = customStream;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public EmailBean getEmail() {
        return email;
    }

    public void setEmail(EmailBean email) {
        this.email = email;
    }

    private void loadProperties() {
        User user = (User) TApplication.getInstance().getUser();
        String from = user.getEmail();
        String server = user.getServerName();
        String userName = user.getUserName();

        email.setFrom(from);
        email.setServer(server);
        email.setUser(userName);

        incluirAnexo = false;

    }

    public void setTo(String[] to) {
        email.setTo(to);
    }

    public String retiraEspaco(String nome) {
        return nome.replace(' ', '_');
    }

    public boolean sendMail() throws Exception {
        
        TApplication app = TApplication.getInstance();
        
        String fileName = "venda.pdf";
        
        Params params = getParams();
        
        EmailPanel editPanel = new EmailPanel();
        User user = app.getUser();

        String from = user.getEmail();
        String server = user.getServerName();

        boolean result = false;
        if ((from == null) || (from.length() == 0)) {
            Messages.warningMessage(app.getResourceString("emailConfig"));
            return false;
        }
        if ((server == null) || (server.length() == 0)) {
            Messages.warningMessage(app.getResourceString("emailConfig"));
            return false;
        }
        EditDialog edtDlg = new EditDialog(app.getResourceString("sendEmail"));
        edtDlg.setEditPanel(editPanel);
        UserDao userDao = (UserDao) TApplication.getInstance().lookupService("userDao");

        List<Vendedor> vendedores = new ArrayList<>();
        
        while (edtDlg.edit(email)) {
            if (email.getTo().length == 0) {
                Messages.errorMessage("Inclua um destinatário.");
                continue;
            }
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", server); //server SMTP do GMAIL
            props.put("mail.smtp.auth", "true"); //ativa autenticacao
            props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", user.getPort()); //porta

            SimpleAuth auth = null;
            auth = new SimpleAuth(from, user.getEmailPasswd().toString());
            Session session = Session.getDefaultInstance(props, auth);
            session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email

            //Objeto que contém a mensagem
            Message msg = new MimeMessage(session);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(email.getText() + '\n' + user.getAssinatura());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = null;
            //if (customStream == null) {
            if ((bytes == null) && (customStream == null)) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                JRAbstractExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                exporter.exportReport();
                source = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
            } else if (customStream != null) {
                source = new ByteArrayDataSource(customStream, "application/pdf");
            } else if (bytes != null) {
                source = new ByteArrayDataSource(bytes, "application/pdf");
            }
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            if (email.getIncluiAnexoPadrao() && params.getAnexoPadrao() != null) {
                try {
                        DataSource src = new ByteArrayDataSource(params.getAnexoPadrao(), "application/pdf");
                        messageBodyPart = new MimeBodyPart();
                        messageBodyPart.setDataHandler(new DataHandler(src));
                        messageBodyPart.setFileName("promo.pdf");
                        multipart.addBodyPart(messageBodyPart);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Messages.errorMessage("Falha ao incluir anexo");
                        break;
                    }
            }
            
            msg.setContent(multipart);

            //Setando o destinatário
            for (String s : email.getTo()) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            }
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            
            //Setando a origem do email
            msg.setFrom(new InternetAddress(from));
            //Setando o assunto
            msg.setSubject(email.getSubject());
            //Setando o conteúdo/corpo do email
            //msg.setContent(message, "text/plain");

            //Objeto encarregado de enviar os dados para o email
            Transport tr;
            try {
                tr = session.getTransport("smtp"); //define smtp para transporte

                tr.connect(server, user.getPort(), from, user.getEmailPasswd());
                msg.saveChanges(); // don't forget this
                tr.sendMessage(msg, msg.getAllRecipients());
                tr.close();
                result = true;
                
                Messages.infoMessage("Mensagem enviada.");
                break;
            } catch (Exception e) {
                System.out.println(">> Erro: Envio Mensagem");
                e.printStackTrace();
                throw e;
            }
                //

            //}
            //return result;
        }
        return result;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    private Params getParams() {
        EmpresaDao empresaDao = (EmpresaDao) TApplication.getInstance().lookupService("empresaDao");
        Object o = new Integer(-1);
        Params value = null;
        try {
            value = (Params) empresaDao.findById(Params.class, o);
        } catch (Exception e) {
        }
        return value;

    }   
    
}
