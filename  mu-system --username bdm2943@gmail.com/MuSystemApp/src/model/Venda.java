package model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	private List<Item> itens;
	
	private Cliente cliente;
	
	private float total = 0f;
	
	public Venda() {
		 itens = new ArrayList<Item>();
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addItem(Item e){
		itens.add(e);		
	}
	
	public boolean remItem(Comercializavel e){
		return itens.remove(e);
	}
	
	public double somar(){
		itens.forEach(e -> total += e.getTotalItem());		
		return total;		
	}
}
