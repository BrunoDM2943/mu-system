package view.clients;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;

public class GerenciaClienteView extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = -7087120418438369171L;
	
	private JPanel painel;
	private JPanel painelBotoes;
	private JScrollPane painelScroll;
	
	private JTable tblClietes;	
	private DefaultTableModel tblModel;
	
	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	
	private ClienteController crtl = new ClienteController();
	
	
	public GerenciaClienteView() {
		inicializar();
		setLayout();
		setActions();
		setFrame();
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
	 */
	private void inicializar(){
		tblClietes = new JTable();
		tblModel = crtl.getTableModel();
		try {
			tblModel = crtl.carregarTabela(tblModel);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		tblClietes.setModel(tblModel);
		
		btnAlterar  = new JButton("Alterar");
		btnDeletar  = new JButton("Deletar");
		btnCancelar = new JButton("Cancelar");		
		
		painel = new JPanel(new GridLayout(2, 1, 10, 10));
		painelScroll = new JScrollPane(painel);
		painelBotoes = new JPanel(new GridLayout(1, 3, 5, 5));
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

		painel.add(tblClietes);
		painel.add(painelBotoes);
		
		this.add(painelScroll);
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
		// TODO Auto-generated method stub
		
	}

}