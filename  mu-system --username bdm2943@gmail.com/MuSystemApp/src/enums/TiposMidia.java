package enums;

public enum TiposMidia {
	
	CD("CD"),
	DVD("DVD"),
	BLURAY("BLU-RAY");
	
	private String tipo;
		
	private TiposMidia(String tipo){
		this.tipo = tipo;		
	}
	
	@Override
	public String toString() {
		return tipo;
	}

}
