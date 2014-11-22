package view.venda;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Cliente;
import model.Item;
import model.Venda;
import view.GUIModels.tableModels.ItemTableModel;
import view.item.CadastraItemView;
import controller.ClienteController;
import controller.ItemController;
import controller.VendaController;

public class RealizarVendaView extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 3439220950863868970L;
	
	private JPanel painel;
	private JPanel painelDados;
	private JPanel painelRodape;
	private JScrollPane painelScroll;
	
	private JTable tblItem;	
	private ItemTableModel tblModel;
	
	private JComboBox<Cliente> cbCliente;
	
	private JTextField tfData;
	
	private JLabel lbCliente;
	private JLabel lbData;
	private JLabel lbTotal;
	
	private JButton btnAdicionar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JButton btnFinalizar;

	private float total = 0f;
	
	private static final int COL_CODIGO = 0; 
	
	private ItemController crtl = new ItemController();
	
	
	public RealizarVendaView() {
		try {
			inicializar();
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
	 * @author joão
	 */
	private void setFrame() {
		 this.setTitle("Realizar venda");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(480,360);
		 this.setVisible(true);		 
		 this.setClosable(true);
		 this.setMaximizable(true);
		 this.setIconifiable(true);
		 this.setResizable(true);		 
	}
	
	/**
	 * Inicializa os componentes da tela
	 * @author joão
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{		
		tblItem = new JTable();
		btnAdicionar = new JButton("Adicionar");
		btnDeletar   = new JButton("Deletar");
		btnCancelar  = new JButton("Cancelar");
		btnFinalizar = new JButton("Finalizar");
		
		lbCliente = new JLabel("Cliente");
		lbData    = new JLabel("Data");
		lbTotal   = new JLabel("Total:" + total);
		
		
		tfData = new JFormattedTextField("dd/MM/yyyy");
		
		cbCliente = new JComboBox<Cliente>(new ClienteController().getVetorCliente());
				
		painel = new JPanel(new BorderLayout(10, 10));
		painelDados = new JPanel(new GridLayout(2,2,10,10));
		painelDados.setSize(getWidth(), 20);
		painelScroll = new JScrollPane(tblItem);
		painelRodape = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
	}
	

	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author joão
	 */
	private void setLayout(){

		painelDados.add(lbCliente);
		painelDados.add(cbCliente);
		
		painelDados.add(lbData);
		painelDados.add(tfData);
		
		painelRodape.add(btnAdicionar);
		painelRodape.add(btnDeletar);
		painelRodape.add(btnCancelar);
		painelRodape.add(btnFinalizar);
		painelRodape.add(lbTotal);

		painel.add(painelDados, BorderLayout.NORTH);
		painel.add(painelScroll, BorderLayout.CENTER);
		painel.add(painelRodape, BorderLayout.SOUTH);
		
		this.add(painel);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author joão
	 */
	private void setActions(){
		btnAdicionar.addActionListener(this);
		btnDeletar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnFinalizar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		try {
		if(src.equals(btnAdicionar)){			
			adicionar();			
		}else if(src.equals(btnDeletar)){
			deletar();		
		}else if(src.equals(btnCancelar)){
			this.dispose();
		}else{
			finalizar();
		}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1);
			e1.printStackTrace();
		}
		
	}
	
	private void finalizar() throws Exception {
		Venda venda = new Venda();
		venda.setCliente((Cliente) cbCliente.getSelectedItem());
		venda.setDataVenda(tfData.getText());
		venda.setItens(crtl.listarTodos());
		venda.getItens().forEach(e-> e.setVenda(venda));
		new VendaController().gravarVenda(venda);
		JOptionPane.showMessageDialog(this, "Venda gravada com sucesso!");
	}

	/**
	 * Adicionar um item na tabela
	 * @throws Exception 
	 */
	private void adicionar() throws Exception {
		CadastraItemView view = new CadastraItemView(crtl);
		view.setVisible(true);
		view.setModalityType(ModalityType.APPLICATION_MODAL);
		List<Item> itens = crtl.listarTodos();
		tblModel = new ItemTableModel(itens);
		tblItem.setModel(tblModel);
		setNovoPreco(itens);
	}

	/**
	 * Confirma a deleção de um Item
	 * @param Item
	 * @return
	 */
	private boolean confirmaDeletar(Item Item) {
		String msg = "Deseja realmente deletar o item " + Item + " da venda?";
		int opt = JOptionPane.showConfirmDialog(this, msg);
		System.out.println(opt);
		return opt == 0;
	}
			
	/**
	 * Valida o indice selecionado em uma
	 * linha da tabela
	 * @param idx
	 * @throws Exception 
	 */
	private void validarIndice(int idx) throws Exception {
		if(idx == -1)
			throw new Exception("Nenhum Item foi selecionado");		
	}
		
	public void deletar() throws Exception {
		int idx = tblItem.getSelectedRow();		
		validarIndice(idx);
		int codigo = (int) tblItem.getValueAt(idx, COL_CODIGO);
		Item item = tblModel.get(codigo, idx);
		if(confirmaDeletar(item)){
			crtl.removeItem(item);				
			JOptionPane.showMessageDialog(this, "Item removido com sucesso");
			List<Item> lista = crtl.listarTodos();
			tblModel = new ItemTableModel(lista);
			tblItem.setModel(tblModel);				
			setNovoPreco(lista);
		}						
	}

	private void setNovoPreco(List<Item> lista) {
		float totalItens = 0;
		for(Item item : lista)
			totalItens += item.getTotalItem();
		total = totalItens;
		lbTotal.setText("Total:" + total);
	}


}