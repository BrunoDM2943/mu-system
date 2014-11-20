package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Cliente;
import exceptions.BusinessException;

public class ClienteTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -5460736422410533100L;

	private List<Cliente> listaClientes;
	
	String[] colunas = {"Nome","Rg","Endereco",
						"Bairro","cidade","Uf",     
						"Telefone","Email"};
	
	public ClienteTableModel(List<Cliente> listaClientes) {
		this.listaClientes = new ArrayList<Cliente>(listaClientes);
	}
	
	public ClienteTableModel() {
		listaClientes = new ArrayList<Cliente>();
	}

	@Override
	public int getRowCount() {		 
		return listaClientes.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente c = listaClientes.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return c.getNome();
		case 1:			
			return c.getRg();
		case 2:
			return c.getEndereco();
		case 3:
			return c.getBairro();
		case 4:
			return c.getCidade();
		case 5:
			return c.getUf();
		case 6:
			return c.getTelefone();
		case 7:
			return c.getEmail();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return String.class;
		case 1:			
			return String.class;
		case 2:
			return String.class;
		case 3:    
			return String.class;
		case 4:    
			return String.class;
		case 5:    
			return String.class;
		case 6:    
			return String.class;
		case 7:    
			return String.class;
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente c = listaClientes.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				c.setNome(String.valueOf(aValue));
				break;
			case 1:			
				c.setRg(String.valueOf(aValue));
				break;
			case 2:
				c.setEndereco(String.valueOf(aValue));
				break;
			case 3:
				c.setBairro(String.valueOf(aValue));
				break;
			case 4:
				c.setCidade(String.valueOf(aValue));
				break;
			case 5:
				c.setUf(String.valueOf(aValue));
				break;
			case 6:
				c.setTelefone(String.valueOf(aValue));
				break;
			case 7:
				c.setEmail(String.valueOf(aValue));
				break;
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Cliente get(String rg) throws Exception{	
		Cliente cli = new Cliente();
		try {
			cli.setRg(rg);
		} catch (BusinessException e) {
			throw new Exception("Nenhum cliente foi selecionado");
		}
		int idx = listaClientes.indexOf(cli);
		return listaClientes.get(idx);
	}

}
