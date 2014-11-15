package controller;

import java.util.List;

import model.Cliente;
import dao.DaoFacade;

public class ClienteController {
	
	private List<Cliente> listaClientes;
	
	public void gravarCliente(Cliente cliente) throws Exception{
		DaoFacade.save(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodos() throws Exception {
		if(listaClientes != null)
			return listaClientes;
		listaClientes = (List<Cliente>) DaoFacade.lerTodos(Cliente.class);
		if(listaClientes.isEmpty())
			throw new Exception("Não há clientes cadastrados na base!");
		
		return listaClientes;
	}

	public void deletarCliente(Cliente cli) throws Exception {		
		DaoFacade.delete(cli);
		listaClientes.remove(cli);
	}

	public void atualizarCliente(Cliente cli) throws Exception {
		DaoFacade.update(cli);		
	}

}
