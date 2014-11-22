package controller;

import java.util.List;

import model.Fabricante;
import dao.DaoFacade;

public class FabricanteController {
	
	private List<Fabricante> listaFabricantes;
	
	public void gravarFabricante(Fabricante fabricante) throws Exception{
		DaoFacade.save(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> listarTodos() throws Exception {
		if(listaFabricantes != null)
			return listaFabricantes;
		listaFabricantes = (List<Fabricante>) DaoFacade.lerTodos(Fabricante.class);
		if(listaFabricantes.isEmpty())
			throw new Exception("Não há fabricantes cadastrados na base!");
		
		return listaFabricantes;
	}

	public void deletarFabricante(Fabricante fab) throws Exception {		
		DaoFacade.delete(fab);
		listaFabricantes.remove(fab);
	}

	public void atualizarFabricante(Fabricante fab) throws Exception {
		DaoFacade.update(fab);		
	}

}