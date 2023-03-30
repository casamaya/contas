/**
 * ClienteContatoModel.java
 * 
 * Created on 30/06/2007, 12:12:18
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendas.swing.model;

import java.util.List;
import vendas.dao.BancoDao;
import ritual.swing.TApplication;
import vendas.entity.Banco;
import vendas.swing.core.BaseTableModel;

/**
 *
 * @author Sam
 */
public class ContaEmpresaModel extends BaseTableModel {
    
    public static final int BANCO = 0;
    public static final int AGENCIA = 1;
    public static final int CONTA = 2;

    private BancoDao bancoDao = (BancoDao)TApplication.getInstance().lookupService("bancoDao");

    public ContaEmpresaModel() throws Exception {
        super();
    }
    
    public ContaEmpresaModel(List values) {
        super(values);
    }
    
    @Override
    public void setColumns() {
        addColumn("Banco");
        addColumn("Agência");
        addColumn("Conta-corrente");
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }
    
    @Override
    public Class getColumnClass(int column) {
        Class aClass = null;
        switch (column) {
        case BANCO: aClass = String.class; break;
        case AGENCIA: aClass = String.class; break;
        case CONTA: aClass = String.class; break;
        }
        return aClass;
    }
}
