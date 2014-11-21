package enums;

public enum TipoItem {

	ACE("Acessório"),
	INS("Instrumento"),
	LIV("Livro"),
	MED("Mídia");

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	private TipoItem(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
}
