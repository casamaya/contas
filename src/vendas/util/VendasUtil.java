/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ritual.swing.ListComboBoxModel;

import ritual.swing.TApplication;

import vendas.dao.PlanoDao;

import vendas.dao.VendedorDao;

import vendas.entity.Conta;

import vendas.entity.Vendedor;

/**
 *
 * @author Sam
 */
public class VendasUtil {

    //static String todosString = TApplication.getInstance().getResourceString("comboTodos");
    static String todosString = " -- Selecione -- ";

    private VendasUtil() {
    }

    

    public static ListComboBoxModel getAPagarListModel() {
        List<Conta> lista = new ArrayList<>();
        PlanoDao plano = (PlanoDao)TApplication.getInstance().lookupService("planoDao");
        try {
            Conta conta = new Conta();
            conta.setNome(todosString);
            lista.add(conta);
            lista.addAll(plano.findAPagar(TApplication.getInstance().getUser().getIdvendedor()));
        } catch (Exception e) {
        }
        return new ListComboBoxModel(lista);
    }
    public static ListComboBoxModel getAPagarMasterListModel() {
        List lista = new ArrayList();
        PlanoDao plano = (PlanoDao)TApplication.getInstance().lookupService("planoDao");
        try {
            Conta conta = new Conta();
            conta.setNome(todosString);
            lista.add(conta);
            lista.addAll(plano.findAPagarMaster(TApplication.getInstance().getUser().getIdvendedor()));
        } catch (Exception e) {
        }
        return new ListComboBoxModel(lista);
    }

    public static ListComboBoxModel getAReceberListModel() {
        List lista = new ArrayList();
        PlanoDao plano = (PlanoDao)TApplication.getInstance().lookupService("planoDao");
        try {
            Conta conta = new Conta();
            conta.setNome(todosString);
            lista.add(conta);
            lista.addAll(plano.findAReceber(TApplication.getInstance().getUser().getIdvendedor()));
        } catch (Exception e) {
        }
        return new ListComboBoxModel(lista);
    }
    public static ListComboBoxModel getAReceberMasterListModel() {
        List lista = new ArrayList();
        PlanoDao plano = (PlanoDao)TApplication.getInstance().lookupService("planoDao");
        try {
            Conta conta = new Conta();
            conta.setNome(todosString);
            lista.add(conta);
            lista.addAll(plano.findAReceberMaster(TApplication.getInstance().getUser().getIdvendedor()));
        } catch (Exception e) {
        }
        return new ListComboBoxModel(lista);
    }

    public static ListComboBoxModel getVendedoresListModel() {
        VendedorDao vendedorDao = (VendedorDao) TApplication.getInstance().lookupService("vendedorDao");
        List lista = new ArrayList();
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(todosString);
        lista.add(vendedor);
        lista.addAll(vendedorDao.findAllAtivos());
        return new ListComboBoxModel(lista);
    }
}

