package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import model.Item;
import dao.connection.ConnectionFactory;
import dao.interfaces.ItemDao;

public class ItemDaoImpl implements ItemDao{

	private Connection con;
	
	@Override
	public void save(List<Item> itens) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		
		
	}
	
	private String insertBuilder(){
		StringBuilder builder = new StringBuilder();
		builder.append("insert into ");
		
		return builder.toString().toUpperCase();
	}

	}
