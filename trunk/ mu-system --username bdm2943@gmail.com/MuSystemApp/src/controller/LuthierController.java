package controller;

import java.util.List;

import model.Luthier;
import dao.DaoFacade;

public class LuthierController {
	
	private List<Luthier> listaLuthier;
	
	public void gravarLuthier(Luthier luthier) throws Exception{
		DaoFacade.save(luthier);
	}

	@SuppressWarnings("unchecked")
	public List<Luthier> listarTodos() throws Exception {
		if(listaLuthier != null)
			return listaLuthier;
		listaLuthier = (List<Luthier>) DaoFacade.lerTodos(Luthier.class);
		if(listaLuthier.isEmpty())
			throw new Exception("Não há luthier cadastrado na base!");
		
		return listaLuthier;
	}

	public void deletarLuthier(Luthier lut) throws Exception {		
		DaoFacade.delete(lut);
		listaLuthier.remove(lut);
	}

	public void atualizarLuthier(Luthier lut) throws Exception {
		DaoFacade.update(lut);		
	}

}