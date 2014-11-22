package controller;

import java.util.List;

import model.Media;
import dao.DaoFacade;

public class MediaController {
	
	private List<Media> listaMedia;
	
	public void gravarMedia(Media media) throws Exception{
		DaoFacade.save(media);
	}

	@SuppressWarnings("unchecked")
	public List<Media> listarTodos() throws Exception {
		if(listaMedia != null)
			return listaMedia;
		listaMedia = (List<Media>) DaoFacade.lerTodos(Media.class);
		if(listaMedia.isEmpty())
			throw new Exception("Não há midias cadastrados na base!");
		
		return listaMedia;
	}

	public void deletarMedia(Media Media) throws Exception {		
		DaoFacade.delete(Media);
		listaMedia.remove(Media);
	}

	public void atualizarMedia(Media Media) throws Exception {
		DaoFacade.update(Media);		
	}

}
