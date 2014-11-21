package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.annotations.DataAccessClass;

@DataAccessClass(daoImpl = "dao.implementation.VendaDaoImpl")
public class Venda {

	private List<Item> itens;
	
	private Cliente cliente;
	
	private float total = 0f;
	
	private int codigo;
	
	private Date dataVenda;
	

	public Venda() {
		 itens = new ArrayList<Item>();
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	
	public float somar(){
		itens.forEach(e -> e.somar());
		itens.forEach(e -> total += e.getTotalItem());
		return total;		
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
}
