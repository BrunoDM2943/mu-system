package model;

public class Item {
	
	private Comercializavel comercilizavel;

	private Venda venda;
	
	private int qtd;
	
	private float total;
	
	private int codigo;
	
	public Item(Comercializavel comercializavel) {
		 this.comercilizavel = comercializavel;
	}

	public Comercializavel getComercilizavel() {
		return comercilizavel;
	}

	public void setComercilizavel(Comercializavel comercilizavel) {
		this.comercilizavel = comercilizavel;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getTotalItem() {
		return total;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void somar(){
		total = comercilizavel.getPreco() * qtd;
	}
	
	@Override
	public String toString() {
		return this.comercilizavel.toString();
	}

}
