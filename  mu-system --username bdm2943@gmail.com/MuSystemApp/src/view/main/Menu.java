package view.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import view.clients.CadastraClienteView;
import view.clients.GerenciaClienteView;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	
	private JDesktopPane desktop    	 = new JDesktopPane();
	
	private JMenuBar  menuBar       	 = new JMenuBar();
	
	private JMenu     menuRegistros 	 = new JMenu("Cadastros");
	private JMenuItem cadastraCliente    = new JMenuItem("Clientes");
	
	private JMenu     menuGerenciamento  = new JMenu("Gerenciamento");
	private JMenuItem gerenciaClientes   = new JMenuItem("Clientes");
	
	private CadastraClienteView cadastraClienteView;
	private GerenciaClienteView gerenciaClienteView;
	

	
	public Menu() {
		inicializar();
		setLayout();
		setActions();
		setFrame();
	}
	
	private void setActions() {
		cadastraCliente.addActionListener(this);
		gerenciaClientes.addActionListener(this);
	}


	/**
	 * Configuar o frame da janela
	 * @author bruno
	 */
	private void setFrame() {
		this.setTitle("Menu");
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setSize(800, 600);		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(desktop);		
		

		
		this.setJMenuBar(menuBar);
		this.setVisible(true);		 
	}
	
	/**
	 * Inicializa os componentes da tela
	 * @author bruno
	 */
	private void inicializar(){
		desktop = new JDesktopPane();        
        
		menuBar = new JMenuBar();            
		                                                
		menuRegistros 	= new JMenu("Cadastros");    
		cadastraCliente = new JMenuItem("Clientes"); 
		                                                
		menuGerenciamento  = new JMenu("Gerenciamento");
		gerenciaClientes   = new JMenuItem("Clientes"); 					
	}
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author bruno
	 */
	private void setLayout(){
		desktop.setVisible(true);
		desktop.setLayout(null);
		
		menuBar.add(menuRegistros);	
		menuBar.add(menuGerenciamento);
		
		menuRegistros.add(cadastraCliente);
		menuGerenciamento.add(gerenciaClientes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();		
		try {
			makesInternalFrame(src);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Cria um frame interno baseado no botão
	 * selecionado
	 * 
	 * @param src
	 * @throws PropertyVetoException 
	 */
	private void makesInternalFrame(Object src) throws PropertyVetoException {
		if(src.equals(cadastraCliente)){				
				cadastraClienteView = new CadastraClienteView();
				desktop.add(cadastraClienteView);
				cadastraClienteView.setVisible(true);
		}else if(src.equals(gerenciaClientes)){				
				gerenciaClienteView = new GerenciaClienteView();
				desktop.add(gerenciaClienteView);
				gerenciaClienteView.setVisible(true);
		}
		
	}

}
