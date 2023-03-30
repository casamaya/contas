/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas.swing.app.auxiliar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ritual.swing.TApplication;
import vendas.beans.Aniversariante;
import vendas.beans.AniversarioFilter;
import vendas.dao.UserDao;
import vendas.dao.VendedorDao;
import vendas.entity.ContatoVendedor;
import vendas.entity.User;
import vendas.entity.Vendedor;
import vendas.swing.app.cliente.ContatoVendedorEditPanel;
import vendas.util.EditDialog;
import vendas.util.Messages;

/**
 *
 * @author sam
 */
public class AniversarioController {

    VendedorDao vendedorDao;

    public AniversarioController() {
        vendedorDao = (VendedorDao) TApplication.getInstance().lookupService("vendedorDao");
    }

    public List<Aniversariante> getAniversariantes(AniversarioFilter filter) {
        List<ContatoVendedor> contatosVendedor = vendedorDao.findAniversariantes(filter);
        List<Vendedor> vendedores = vendedorDao.findVendedorAniversariante(filter);
        List<Aniversariante> lista = new ArrayList();
        
        for (ContatoVendedor contato : contatosVendedor) {
            lista.add(convert(contato));
        }
        for (Vendedor contato : vendedores) {
            lista.add(convert(contato));
        }
        
        Collections.sort(lista, new AniversarianteComparator());
        return lista;
    }

    public void remove (Aniversariante contato) throws Exception {
        if (Aniversariante.CONTATOVENDEDOR == contato.getClasse()) {
            vendedorDao.deleteRow(ContatoVendedor.class, contato.getId());
        }
    }
    
    public Aniversariante edit(Aniversariante contato) {
        if (Aniversariante.CONTATOVENDEDOR == contato.getClasse()) {
            contato = contatoVendedorEdit(getContatoVendedor(contato.getId()));
        }

        return contato;
    }

    private Aniversariante contatoVendedorEdit(ContatoVendedor contato) {
        ContatoVendedorEditPanel editPanel = new ContatoVendedorEditPanel();
        EditDialog edtDlg = new EditDialog(TApplication.getInstance().getBundle().getString("editCompradorTitle"));

        edtDlg.setEditPanel(editPanel, !TApplication.getInstance().isGrant("ALTERAR_ANIVERSARIO"));

        while (edtDlg.edit(contato)) {
            try {
                vendedorDao.updateRow(contato);
                return convert(contato);
            } catch (Exception e) {
                Messages.errorMessage(TApplication.getInstance().getBundle().getString("saveErrorMessage"));
            }
        }
        return convert(contato);
    }
    
    private Aniversariante convert(ContatoVendedor contato) {
        Aniversariante aniversariante = new Aniversariante();
        aniversariante.setClasse(Aniversariante.CONTATOVENDEDOR);
        aniversariante.setDtAniver(contato.getAniversario());
        aniversariante.setId(contato.getidContato());
        aniversariante.setMsn(contato.getMsn());
        aniversariante.setNome(contato.getContato());
        aniversariante.setEmail(contato.getMsn());
        aniversariante.setObservacao(contato.getObservacao());
        if ("P".equals(contato.getTipoContatoVendedor())) {
            aniversariante.setTipo("Pessoal");
        } else if ("F".equals(contato.getTipoContatoVendedor())) {
            aniversariante.setTipo("Família");
        } else {
            aniversariante.setTipo("Outros");
        }
        aniversariante.setResponsavel(contato.getVendedor().getNome());
        return aniversariante;
    }
    private Aniversariante convert(Vendedor contato) {
        Aniversariante aniversariante = new Aniversariante();
        UserDao userDao = (UserDao) TApplication.getInstance().lookupService("userDao");
        User user = userDao.getUserByVendedor(contato.getIdVendedor());
        aniversariante.setClasse(Aniversariante.VENDEDOR);
        aniversariante.setDtAniver(contato.getDtAniver());
        aniversariante.setId(contato.getIdVendedor());
        aniversariante.setMsn(user.getEmail());
        aniversariante.setNome(contato.getNome());
        aniversariante.setEmail(user.getEmail());
        aniversariante.setTipo("Vendedor");
        return aniversariante;
    }

    private ContatoVendedor getContatoVendedor(Integer id) {
        try {
            return (ContatoVendedor) vendedorDao.findById(ContatoVendedor.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    
    private Vendedor getVendedor(Integer id) {
        try {
            return (Vendedor) vendedorDao.findById(Vendedor.class, id);
        } catch (Exception e) {
            return null;
        }
    }
}

class AniversarianteComparator implements Comparator {

    public int compare(Object obj1, Object obj2) {
        Aniversariante grupo1 = (Aniversariante) obj1;
        Aniversariante grupo2 = (Aniversariante) obj2;
        if (grupo1.getDtAniver() == null || grupo2.getDtAniver() == null)
            return 0;
        else
            return grupo1.getDtAniver().compareTo(grupo2.getDtAniver());

    }
}
