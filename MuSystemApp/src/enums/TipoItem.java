package enums;

import model.Acessorio;
import model.Instrumento;
import model.Livro;
import model.Media;
import model.Comercializavel;

public enum TipoItem {

	ACE("Acessório", Acessorio.class),
	INS("Instrumento", Instrumento.class),
	LIV("Livro", Livro.class),
	MED("Mídia", Media.class);

	private String tipo;
	private Class<? extends Comercializavel> clazz;

	public String getTipo() {
		return tipo;
	}
	
	public Class<? extends Comercializavel> getClazz() {
		return clazz;
	}

	private TipoItem(String tipo, Class<? extends Comercializavel> clazz) {
		this.tipo = tipo;
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
}
