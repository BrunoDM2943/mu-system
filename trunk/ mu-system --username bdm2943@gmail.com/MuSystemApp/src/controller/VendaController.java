package controller;

import java.util.List;

import model.Venda;
import dao.DaoFacade;

public class VendaController {
	
	private List<Venda> listaVenda;
	
	public void gravarVenda(Venda venda) throws Exception{
		DaoFacade.save(venda);
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listarTodos() throws Exception {
		if(listaVenda != null)
			return listaVenda;
		listaVenda = (List<Venda>) DaoFacade.lerTodos(Venda.class);
		if(listaVenda.isEmpty())
			throw new Exception("Não há vendas cadastradas na base!");
		
		return listaVenda;
	}

	public void deletarVenda(Venda venda) throws Exception {		
		DaoFacade.delete(venda);
		listaVenda.remove(venda);
	}

	public void atualizarVenda(Venda venda) throws Exception {
		DaoFacade.update(venda);		
	}

}