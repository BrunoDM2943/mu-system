package controller;

import javax.swing.table.DefaultTableModel;

import model.Cliente;
import tableModels.TableModelFactory;
import dao.DaoFacade;

public class ClienteController {
	
	public void gravarCliente(Cliente cliente) throws Exception{
		DaoFacade.gravar(cliente);
	}

	public DefaultTableModel getTableModel() {
		return TableModelFactory.getTableModel(Cliente.class);
	}

	public DefaultTableModel carregarTabela(DefaultTableModel tblModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
