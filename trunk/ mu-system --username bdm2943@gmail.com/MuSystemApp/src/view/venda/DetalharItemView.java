package view.venda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Venda;
import view.GUIModels.tableModels.ItemTableModel;

public class DetalharItemView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Venda venda = null;
	
	private JPanel painel;
	private JPanel painelRodape;
	private JScrollPane painelScroll;
	
	private JTable tblItem;	
	private ItemTableModel itemModel;
	
	private JButton btnCancelar;
	
	private JLabel lbSoma;
	
	
	public DetalharItemView(Venda venda){
		this.venda = venda;
		try{
			inicializar();
			setFrame();
			setLayout();
			setActions();
			setFrame();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
			this.dispose();
		}	
	}
	
	/**
	 * Configuar o frame da janela
	 * @author bruno
	 */
	private void setFrame() {
		 this.setTitle("Historico de Vendas");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(480,200);
		 this.setVisible(true);		 
		 this.setResizable(true);		 
	}
	
	/**
	 * Inicializa os componentes da tela
	 * @author bruno
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{		
		carregarTabela();
		
		btnCancelar  = new JButton("Cancelar");	
		lbSoma = new JLabel("Total = R$" + venda.somar());
		
		painel = new JPanel(new BorderLayout(10, 10));
		painelScroll = new JScrollPane(tblItem);
		painelRodape = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
		
		
	}
	
	/**
	 * Carrega a tabela de Vendas
	 * @throws Exception 
	 */
	private void carregarTabela() throws Exception {
		carregarTableModel();			
		tblItem = new JTable();
		popularTabela();		
	}
	
	/**
	 * Popula a tabela
	 */
	private void popularTabela() {
		tblItem.setModel(itemModel);
	}

	/**
	 * Carrega um tableModel
	 * @return 
	 * @throws Exception 
	 */
	private void carregarTableModel() throws Exception{
		itemModel = new ItemTableModel(venda.getItens());		
	}
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author bruno
	 */
	private void setLayout(){
		painelRodape.add(btnCancelar);
		painelRodape.add(lbSoma);

		painel.add(painelScroll, BorderLayout.CENTER);
		painel.add(painelRodape, BorderLayout.SOUTH);
		
		this.add(painel);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author bruno
	 */
	private void setActions(){
		btnCancelar.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(btnCancelar)){
			this.dispose();
		}
	}

}
