package tableModels;

import javax.swing.table.DefaultTableModel;

import model.Cliente;

public class TableModelFactory {
	
	
	public static DefaultTableModel getTableModel(Class<?> clazz){
		if(clazz.equals(Cliente.class))
			return new ClienteTableModel();			
		return new DefaultTableModel();
	}

}
