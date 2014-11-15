package view.clients;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import model.Cliente;
import services.auxiliarityViews.GerenciamentoServices;
import tableModels.ClienteTableModel;
import controller.ClienteController;

public class GerenciaClienteView extends JInternalFrame implements ActionListener, GerenciamentoServices<Cliente>{

	private static final long serialVersionUID = -7087120418438369171L;
	
	private JPanel painel;
	private JPanel painelBotoes;
	private JScrollPane painelScroll;
	
	private JTable tblClietes;	
	private ClienteTableModel tblModel;
	
	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	
	private ClienteController crtl = new ClienteController();	
	
	
	public GerenciaClienteView() {
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
	 * @author bruno
	 */
	private void setFrame() {
		 this.setTitle("Gerenciador de clientes");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(400,300);
		 this.setVisible(true);		 
		 this.setClosable(true);
		 this.setMaximizable(true);
		 this.setIconifiable(true);
		 this.setResizable(true);		 
	}
	
	/**
	 * Inicializa os componentes da tela
	 * @author bruno
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{		
		carregarTabela();
		btnAlterar  = new JButton("Alterar");
		btnDeletar  = new JButton("Deletar");
		btnCancelar = new JButton("Cancelar");		
		
		painel = new JPanel(new GridLayout(2, 1, 10, 10));
		painelScroll = new JScrollPane(tblClietes);
		painelBotoes = new JPanel(new GridLayout(1, 3, 5, 5));
	}
	

	/**
	 * Carrega a tabela de Clientes
	 * @throws Exception 
	 */
	private void carregarTabela() throws Exception {
		carregarTableModel();			
		tblClietes = new JTable();
		popularTabela();		
	}
	
	/**
	 * Popula a tabela
	 */
	private void popularTabela() {
		tblClietes.setModel(tblModel);
		RowSorter<ClienteTableModel> sorter = new TableRowSorter<ClienteTableModel>(tblModel);
		tblClietes.setRowSorter(sorter);
	}

	/**
	 * Carrega um tableModel
	 * @return 
	 * @throws Exception 
	 */
	private void carregarTableModel() throws Exception{
		List<Cliente> listaClientes = crtl.listarTodos();
		tblModel = new ClienteTableModel(listaClientes);		
	}
	
	
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author bruno
	 */
	private void setLayout(){

		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnDeletar);
		painelBotoes.add(btnCancelar);

		painel.add(painelScroll);
		painel.add(painelBotoes);
		
		this.add(painel);
	}
	
	/**
	 * Adiciona os actions listeners aos bot��es
	 * @author bruno
	 */
	private void setActions(){
		btnAlterar.addActionListener(this);
		btnDeletar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(btnAlterar)){
			
		}else if(src.equals(btnDeletar)){
			deletar();
		}else if(src.equals(btnCancelar)){
			this.dispose();
		}
		
	}
	
	/**
	 * Confirma a deleção de um cliente
	 * @param c
	 * @return
	 */
	private boolean confirmaDeletar(Cliente c) {
		String msg = "Deseja realmente deletar o cliente " + c + " do sistema?";
		int opt = JOptionPane.showConfirmDialog(this, msg);
		System.out.println(opt);
		return opt == 0;
	}

		
	@Override
	public void alterar() {
		// TODO Auto-generated method stub
		
	}

		
	@Override
	public List<Cliente> procurar() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void deletar() {
		int idx = tblClietes.getSelectedRow();
		int col = 1;		
		try {
			if(idx == -1)
				throw new Exception("Nenhum cliente foi selecionado");
			String rg = (String) tblClietes.getValueAt(idx, col);
			Cliente c = tblModel.get(rg);
			if(confirmaDeletar(c)){
				crtl.deletarCliente(c);
				JOptionPane.showMessageDialog(this, "Cliente removido com sucesso");
				carregarTableModel();
				popularTabela();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
			e1.printStackTrace();
		}
		
		
	}


}