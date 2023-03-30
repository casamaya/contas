/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import ritual.swing.BaseFrame;
import ritual.swing.MDIDesktopPane;
import ritual.swing.TApplication;
import ritual.swing.ViewFrame;
import ritual.util.DateUtils;
import vendas.beans.AgendaFilter;
import vendas.dao.UserDao;
import vendas.entity.Perfil;
import vendas.entity.User;
import vendas.security.Crypt;
import vendas.security.EmailProfileForm;
import vendas.security.TrocaSenha;
import vendas.security.UserProfileForm;
import vendas.swing.app.auxiliar.AgendaInternalFrame;
import vendas.swing.app.auxiliar.ParamsHelper;
import vendas.swing.app.contas.ContabilFrame;
import vendas.swing.app.contas.FechamentoGeralView;
import vendas.swing.app.contas.FechamentoGrupoView;
import vendas.swing.app.main.AboutBox;
import vendas.swing.core.TableViewFrame;
import vendas.util.Constants;
import vendas.util.EditDialog;
import vendas.util.Messages;
import vendas.util.ReportViewFrame;
import vendas.util.Reports;

/**
 *
 * @author Sam
 */
public class Main extends SingleFrameApplication {

    private JEditorPane textPane;
    private MDIDesktopPane desktopPane;
    private TApplication app;
    private Logger logger;
    private Senha janela;
    private UserDao ud;

    private javax.swing.Action getAction(String actionName) {

        return getContext().getActionMap().get(actionName);
    }

    private JMenu createMenu(String menuName, String[][] actionNames) {
        JMenu menu = new JMenu();
        JMenuItem menuItem;
        
        menu.setName(menuName);
        menu.setText(menuName);

        for (String[] actionName : actionNames) {
            if (actionName[0].equals("---")) {
                menu.add(new JSeparator());
            } else {
                if ((actionName[1] == null) || (app.isGrant(actionName[1]))) {
                    menuItem = new JMenuItem();
                    menuItem.setAction(getAction(actionName[0]));
                    menu.add(menuItem);
                }
            }
        }
        return menu;
    }

    private JComponent createToolBar() {
        String[] toolbarActionNames = {
            "add",
            "---",
            "open",
            "delete",
            "---",
            "refresh",
            "find",
            "cancel",
            "---",
            "print",
            "---",
            "email"
        };
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        for (String actionName : toolbarActionNames) {
            if (actionName.equals("---")) {
                toolBar.addSeparator();
            } else {
                JButton button = new JButton();
                button.setAction(getAction(actionName));
                //button.setFocusable(false);
                button.setText(null);
                toolBar.add(button);
            }
        }
        toolBar.addSeparator();
        
        return toolBar;
    }

    private JMenuBar createMenuBar() {
        String[][] fileMenuActionNames = {
            {"emailProfile", "CONFIGURAR_EMAIL"},
            {"trocaSenha", "TROCAR_SENHA"},
            {"---", null},
            {"email", "ENVIAR_EMAIL"},
            {"print", null},
            {"---", null},
            {"quit", null}
        };

        String[][] auxiliarMenuActionNames = {
            {"bancos", "BANCOS"},
            {"---", null},
            {"aniversario", "ANIVERSARIOS"},
            {"agenda", "AGENDA"},
            {"correio", "MENSAGENS"}
        };
        String[][] financeiroMenuActionNames = {
            {"planoContas", "PLANO_CONTAS"},
            {"tiposPgto", "TIPOS_DE_PAGAMENTO"},
            {"aPagar", "CONTAS_APAGAR"},
            {"aReceber", "CONTAS_ARECEBER"},
            {"---", null},
            {"grupoMovimento", "GRUPO_MOVIMENTO"},
            {"fluxo", "MOVIMENTO_FINANCEIRO"},
            {"fechamentoGrupo", "FECHAMENTO_GRUPO"},
            {"---", null},
            {"contabil", "CONTABIL"},
            {"fechamentoGeral", "FECHAMENTO_GERAL"}
        };

        String[][] windowMenuActionNames = {{"cascata", null}, {"tile", null}};

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenu("fileMenu", fileMenuActionNames));
        menuBar.add(createMenu("auxiliar", auxiliarMenuActionNames));
        menuBar.add(createMenu("financeiro", financeiroMenuActionNames));
        menuBar.add(createMenu("window", windowMenuActionNames));
        //menuBar.add(createMenu("help", helpMenuActionNames));

        return menuBar;
    }

    private JComponent createMainPanel() {
        textPane = new JTextPane();
        textPane.setName("textPane");
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        desktopPane = new MDIDesktopPane();
        app.setDesktopPane(desktopPane);
        scrollPane.getViewport().add(desktopPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createToolBar(), BorderLayout.NORTH);
        panel.setBorder(new EmptyBorder(0, 2, 2, 2)); // top, left, bottom, right
        return panel;
    }
    
    @Action
    public void fluxo() {
        if (app.getUser().isAdmin() || app.getUser().isVendedor()) {
            newInternalFrame("fluxoInternalFrame");
        }
    }
    
    @Action
    public void grupoMovimento() {
        if (app.getUser().isAdmin() || app.getUser().isVendedor()) {
            newInternalFrame("grupoMovimentoInternalFrame");
        }
    }
    
    @Action
    public void fechamentoGrupo() {
        newInternalFrame(new FechamentoGrupoView());
    }

    @Action
    public void aPagar() {
        newInternalFrame("aPagarInternalFrame");
    }

    @Action
    public void aReceber() {
        newInternalFrame("aReceberInternalFrame");
    }

    @Action
    public void planoContas() {
        newInternalFrame("contasInternalFrame");
    }

    @Action
    public void open() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).view();
        }
    }

    @Action
    public void edit() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).edit();
        }
    }

    @Action
    public void add() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        
        if (frame instanceof ViewFrame) {
            String title = frame.getTitle();
                ((ViewFrame) frame).insert();
        }
    }

    @Action
    public void refresh() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).refresh();
        } else if (frame instanceof TableViewFrame) {
            ((TableViewFrame) frame).refresh();
        }
    }

    @Action
    public void delete() {

        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).remove();
        }
    }

    @Action
    public void print() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).report();
        }
    }

    @Action
    public void find() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).filter();
        }
    }

    @Action
    public void cancel() {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ViewFrame) {
            ((ViewFrame) frame).cancelFilter();
        }
    }

    @Action
    public void email() throws Exception {
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof ReportViewFrame) {
            try {
                ((ReportViewFrame) frame).sendMail();
            } catch (Exception e) {
                logger.error(e);
                Messages.errorMessage("Falha ao enviar mensagem: " + e.getMessage());
            }
        } else {
            Messages.warningMessage(app.getResourceString("gerarRelatorio"));
        }
    }

    @Action
    public void quit() {
        exitApp();
    }
    
    @Action
    public void fechamentoGeral() {
        newInternalFrame(new FechamentoGeralView());
    }

    @Action
    public void user() {
        UserProfileForm form = new UserProfileForm();
        EditDialog edtDlg = new EditDialog(TApplication.getInstance().getBundle().getString("newPassword"));
        User user = (User) TApplication.getInstance().getUser();
        edtDlg.setEditPanel(form);
        while (edtDlg.edit(user)) {
            if (user.getPasswd().equals(user.getRepeatPasswd())) {
                try {
                    ud.updateRow(user);
                    app.setUser(user);
                    break;
                } catch (Exception e) {
                    Messages.errorMessage("Falha ao salvar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha N\u00E3o confere");
            }
        }
    }

    @Action
    public void config() {
        ParamsHelper ph = new ParamsHelper();
        ph.showParams();
    }
    
    @Action
    public void agenda() {
        newInternalFrame("agendaInternalFrame");
    }

    @Action
    public void aniversario() {
        newInternalFrame("aniversarianteInternalFrame");
    }

    @Action
    public void bancos() {
        newInternalFrame("bancoInternalFrame");
    }

    @Action
    public void formasPgto() {
        newInternalFrame("formaPgtoInternalFrame");
    }

    @Action
    public void tiposPgto() {
        newInternalFrame("tipoPgtoFinancInternalFrame");
    }


    @Action
    public void correio() {
        newInternalFrame("correioInternalFrame");
    }

    @Action
    public void contabil() {
        if (app.getUser().isAdmin() || app.getUser().isEscritorio())
            newInternalFrame(new ContabilFrame());
    }

    @Action
    public void cascata() {
        desktopPane.cascadeFrames();
    }

    @Action
    public void tile() {
        desktopPane.tileFrames();
    }

    @Action
    public void about() {
        showAboutBox();
    }

    @Action
    public void trocaSenha() {
        TrocaSenha senha = new TrocaSenha();
        senha.trocaSenha();
    }

    @Action
    public void emailProfile() {
        EmailProfileForm form = new EmailProfileForm();
        EditDialog edtDlg = new EditDialog(TApplication.getInstance().getBundle().getString("emailConfig"));
        User user = (User) TApplication.getInstance().getUser();
        edtDlg.setEditPanel(form);
        while (edtDlg.edit(user)) {
            try {
                ud.updateRow(user);
                app.setUser(user);
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar");
            }
        }
    }

    private void newInternalFrame(final String frameName) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final ViewFrame frame = (ViewFrame) app.lookupService(frameName);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (!app.locateFrame(frame.getTitle())) {
                           desktopPane.add(frame);
                           frame.setLocation(0,0);
                           if ("Movimento financeiro".equals(frame.getTitle())) {
                               frame.setSize(app.getDesktopPane().getBounds().width, app.getDesktopPane().getBounds().height);
                           }
                                   
                        }
                        frame.setVisible(true);
                    }
                });
            }
        }).start();
    }

    private void newInternalFrame(final Class a) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    final BaseFrame frame = (BaseFrame) a.newInstance();
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            desktopPane.add(frame);
                            frame.setLocation(0,0);
                            frame.setVisible(true);
                        }
                    });
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void newInternalFrame(final BaseFrame frame) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!app.locateFrame(frame.getTitle())) {
                    desktopPane.add(frame);
                    frame.setLocation(0,0);
                }
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        frame.setVisible(true);
                    }
                });
            }
        }).start();
    }

    private void exitApp() {
        System.exit(0);
    }

    public void showAboutBox() {
        AboutBox dlg = new AboutBox(getMainFrame());
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getMainFrame().getSize();
        Point loc = getMainFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.pack();
        dlg.setVisible(true);
    }

    private void initApp() {
        ud = (UserDao) TApplication.getInstance().lookupService("userDao");
        try {
            ud.findById(User.class, "USER");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    protected void startup() {
        URL url = getClass().getResource("/vendas/log4j.properties");
        PropertyConfigurator.configure(url);
        app = TApplication.getInstance();
        logger = Logger.getLogger(getClass());
        app.setResourcePath(Constants.BUNDLE);
        app.setLogoPath(Constants.LOGOFILE);
        app.setFactoryPath(Constants.FACTORYXML);
        initApp();

        try {
            for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                //logger.info(laf.getName());
//                if ("mac os x".equals(laf.getName().toLowerCase())) {
                if ("metal".equals(laf.getName().toLowerCase())) {
//                if ("Nimbus".equals(laf.getName().toLowerCase())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        app.setMainFrame(getMainFrame());
        autenticar();
        getMainFrame().setJMenuBar(createMenuBar());
        show(createMainPanel());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(Main.class, args);
    }

    private void autenticar() {
        janela = new Senha(null, "Nome de Usuário e Senha", true);
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.setVisible(true);
    }

    public void verificar(String u, String s) {
        boolean result = true;
        String msg = "Dados Incorretos.";
        if ((u == null || u.length() == 0) || (s == null || s.length() == 0)) {
            msg = "Usuário e senha devem ser informados";
            result = false;
        } else {
            try {
                User user = (User) ud.findById(User.class, u);
                
                if (user == null) {
                    result = false;
                    msg = "Usuário inválido";
                } else if (user.getPasswd() == null) {
                    result = newPassword(user, s);
                }
                if (result && Crypt.encrypt(u, s).equals(user.getPasswd())) {
                    result = true;
                    /*if ("RAQUEL".equals(user.getUserName()) || "KATHERINE".equals(user.getUserName())) {
                    Date d = new Date();
                    int i = DateUtils.getDiaSemana(d);
                    //if (i == 1 || i == 7) {
                    //    msg = "Acesso inválido";
                    //    result = false;
                    //} else {
                    String hora = DateUtils.getHora(d);
                    if (hora.compareTo("08:00") >= 0 && hora.compareTo("18:00") <= 0) {
                    System.out.println("Ok");
                    result = true;
                    } else {
                    msg = "Acesso inválido";
                    result = false;
                    }
                    //}
                    }*/
                    List<Perfil> perfil = user.getPerfis();
                    if ( perfil == null || perfil.isEmpty()) {
                        result = false;
                        msg = "Usuário sem perfil.";
                    }
                    if (result) {
                        janela.setVisible(false);
                        app.setUser(user);
                    }
                } else {
                    msg = "Usuário e/ou Senha inválidos";
                    result = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }
        if (!result) {
            JOptionPane.showMessageDialog(null, msg);
            janela.txtUsuario.requestFocus();
        }

    }

    private boolean newPassword(User user, String s) throws Exception {
        UserProfileForm form = new UserProfileForm();
        boolean result = false;
        EditDialog edtDlg = new EditDialog("Nova senha");
        user.setPasswd(s);
        edtDlg.setEditPanel(form);
        while (edtDlg.edit(user)) {
            if (user.getPasswd().equals(user.getRepeatPasswd())) {
                ud.updateRow(user);
                result = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Senha N\u00E3o confere");
            }
        }
        return result;
    }
    
    private void iniciarMensagem() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    correio();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    Messages.errorMessage("Falha ao iniciar correio");
                }
            }
        }).start();
    }

    private JInternalFrame iniciarAgenda() {
        logger.info("iniciarAgenda");
        AgendaInternalFrame frame = (AgendaInternalFrame) app.lookupService("agendaInternalFrame");
        newInternalFrame(frame);
        AgendaFilter filtro = new AgendaFilter();
        frame.executeFilter(filtro);
        return frame;
    }

    private void showReport(String title, String subTitle, String reportFile, List lista) throws Exception {
        URL url = getClass().getResource(reportFile);
        Map model = app.getDefaultMap(title, subTitle);
        int mes = DateUtils.getMonth(DateUtils.getDate());
        model.put("titles", DateUtils.getLastMonths(mes));
        Reports.showReport(url, model, lista);
    }

    private class Senha extends JDialog {

        JTextField txtUsuario;
        JPasswordField txtSenha;
        JButton entrar, cancelar;

        public Senha(Frame owner, String title, boolean modal) {
            super(owner, title, modal);

            Container tela = getContentPane();

            BorderLayout layout = new BorderLayout();
            tela.setLayout(layout);

            JLabel lblUsuario = new JLabel("Nome do Usuário:");
            JLabel lblSenha = new JLabel("Senha:");
            txtUsuario = new JTextField(10);
            txtSenha = new JPasswordField(10);

            JPanel superior = new JPanel();
            superior.setLayout(new GridLayout(2, 2, 5, 5));
            superior.add(lblUsuario);
            superior.add(txtUsuario);
            superior.add(lblSenha);
            superior.add(txtSenha);

            JPanel superior2 = new JPanel();

            String titulo = "Informe o nome de usuário e Senha";
            Border etched = BorderFactory.createEtchedBorder();
            Border borda = BorderFactory.createTitledBorder(etched, titulo);

            superior2.setBorder(borda);
            superior2.setLayout(new FlowLayout(FlowLayout.LEFT));
            superior2.add(superior);

            Tratador trat = new Tratador();

            entrar = new JButton("Entrar");
            entrar.addActionListener(trat);
            getRootPane().setDefaultButton(entrar);

            cancelar = new JButton("Cancelar");
            cancelar.addActionListener(trat);

            JPanel inferior = new JPanel();
            inferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
            inferior.add(entrar);
            inferior.add(cancelar);

            tela.add(BorderLayout.NORTH, superior2);
            tela.add(BorderLayout.SOUTH, inferior);

            setSize(280, 150);
            setLocationRelativeTo(null);
        }

        private class Tratador implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(txtSenha.getPassword());

                if (e.getSource() == entrar) {
                    verificar(txtUsuario.getText().toUpperCase(), senha);
                } else {
                    System.exit(0);
                }
            }
        }
    }
}
