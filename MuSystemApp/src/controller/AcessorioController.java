package controller;

import java.util.List;

import model.Acessorio;
import dao.DaoFacade;

public class AcessorioController {
	
	private List<Acessorio> listaAcessorio;
	
	public void gravarAcessorio(Acessorio Acessorio) throws Exception{
		DaoFacade.save(Acessorio);
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> listarTodos() throws Exception {
		if(listaAcessorio != null)
			return listaAcessorio;
		listaAcessorio = (List<Acessorio>) DaoFacade.lerTodos(Acessorio.class);
		if(listaAcessorio.isEmpty())
			throw new Exception("Não há acessorios cadastrados na base!");
		
		return listaAcessorio;
	}

	public void deletarAcessorio(Acessorio acessorio) throws Exception {		
		DaoFacade.delete(acessorio);
		listaAcessorio.remove(acessorio);
	}

	public void atualizarAcessorio(Acessorio acessorio) throws Exception {
		DaoFacade.update(acessorio);		
	}

}
