package enums;

public enum Especialidade {

	PER("Percussão"),
	SOP("Sopro"),
	ACU("Acústico"),
	ELE("Elétrico");

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	private Especialidade(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
