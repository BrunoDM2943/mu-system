package view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import view.clients.RegisterClientView;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	
	private JDesktopPane desktop    = new JDesktopPane();
	
	private JMenuBar  menuBar       = new JMenuBar();
	private JMenu     menuRegisters = new JMenu("Registers");
	private JMenuItem signClients   = new JMenuItem("Clients");
	
	private RegisterClientView registerClientView;
	
	public Menu() {
		super("Menu");
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setSize(800, 600);		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(desktop);		
		
		desktop.setVisible(true);
		desktop.setLayout(null);
		
		loadMenuRegisters();		
		loadMenuBar();
		
		
		this.setJMenuBar(menuBar);
		
		
		this.setVisible(true);
	}

	
	/**
	 * Loads all menus in the bar
	 * @author bruno
	 * @since 1.0
	 */
	private void loadMenuBar() {
		menuBar.add(menuRegisters);		
	}

	/**
	 * Loads register's tens
	 * @author bruno
	 * @since 1.0
	 */
	private void loadMenuRegisters() {
		signClients.addActionListener(this);
		menuRegisters.add(signClients);		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(signClients)){
			registerClientView = new RegisterClientView();
			desktop.add(registerClientView);
			registerClientView.setVisible(true);
		}
		
	}

}
