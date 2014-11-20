package view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import view.acessorios.CadastraAcessorioView;
import view.acessorios.GerenciaAcessorioView;
import view.clientes.CadastraClienteView;
import view.clientes.GerenciaClienteView;
import view.fabricantes.CadastraFabricanteView;
import view.fabricantes.GerenciaFabricanteView;
import view.media.CadastraMediaView;
import view.media.GerenciaMediaView;
import view.luthier.CadastraLuthierView;
import view.luthier.GerenciaLuthierView;

public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JDesktopPane desktop;

	private JMenuBar menuBar;

	private JMenu menuRegistros;
	private JMenuItem cadastraCliente;
	private JMenuItem cadastraLuthier;
	private JMenuItem cadastraFabricante;
	private JMenuItem cadastraAcessorio;
	private JMenuItem cadastraMidia;

	private JMenu menuGerenciamento;
	private JMenuItem gerenciaClientes;
	private JMenuItem gerenciaLuthier;
	private JMenuItem gerenciaFabricantes;
	private JMenuItem gerenciaAcessorios;
	private JMenuItem gerenciaMedia;

	private CadastraClienteView cadastraClienteView;
	private CadastraLuthierView cadastraLuthierView;
	private CadastraFabricanteView cadastraFabricanteView;
	private CadastraAcessorioView cadastraAcessorioView;
	private CadastraMediaView cadastraMediaView;

	private GerenciaClienteView gerenciaClienteView;
	private GerenciaLuthierView gerenciaLuthierView;
	private GerenciaFabricanteView gerenciaFabricanteView;
	private GerenciaAcessorioView gerenciaAcessorioView;
	private GerenciaMediaView gerenciaMediaView;

	public Menu() {
		inicializar();
		setLayout();
		setActions();
		setFrame();
	}

	private void setActions() {
		cadastraCliente.addActionListener(this);
		cadastraLuthier.addActionListener(this);
		cadastraFabricante.addActionListener(this);
		cadastraAcessorio.addActionListener(this);
		cadastraMidia.addActionListener(this);
		gerenciaClientes.addActionListener(this);
		gerenciaLuthier.addActionListener(this);
		gerenciaFabricantes.addActionListener(this);
		gerenciaAcessorios.addActionListener(this);
		gerenciaMedia.addActionListener(this);
	}

	/**
	 * Configuar o frame da janela
	 * 
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
	 * 
	 * @author bruno
	 */
	private void inicializar() {
		desktop = new JDesktopPane();

		menuBar = new JMenuBar();

		menuRegistros = new JMenu("Cadastros");
		cadastraCliente = new JMenuItem("Clientes");
		cadastraLuthier = new JMenuItem("Luthier");
		cadastraFabricante = new JMenuItem("Fabricantes");
		cadastraAcessorio = new JMenuItem("Acessorios");
		cadastraMidia = new JMenuItem("Media");

		menuGerenciamento = new JMenu("Gerenciamento");
		gerenciaClientes = new JMenuItem("Clientes");
		gerenciaLuthier = new JMenuItem("Luthier");
		gerenciaFabricantes = new JMenuItem("Fabricantes");
		gerenciaAcessorios = new JMenuItem("Acessorios");
		gerenciaMedia = new JMenuItem("Media");
	}

	/**
	 * Adiciona os componentes na tela
	 * 
	 * @author bruno
	 */
	private void setLayout() {
		desktop.setVisible(true);
		desktop.setLayout(null);

		menuBar.add(menuRegistros);
		menuBar.add(menuGerenciamento);

		menuRegistros.add(cadastraCliente);
		menuRegistros.add(cadastraLuthier);
		menuRegistros.add(cadastraFabricante);
		menuRegistros.add(cadastraAcessorio);
		menuRegistros.add(cadastraMidia);

		menuGerenciamento.add(gerenciaClientes);
		menuGerenciamento.add(gerenciaLuthier);
		menuGerenciamento.add(gerenciaFabricantes);
		menuGerenciamento.add(gerenciaAcessorios);
		menuGerenciamento.add(gerenciaMedia);
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
	 * Cria um frame interno baseado no bot��o selecionado
	 * 
	 * @param src
	 * @throws PropertyVetoException
	 */
	private void makesInternalFrame(Object src) throws PropertyVetoException {
		if (src.equals(cadastraCliente)) {
			cadastraClienteView = new CadastraClienteView();
			desktop.add(cadastraClienteView);
			cadastraClienteView.setVisible(true);
		} else if (src.equals(cadastraLuthier)) {
			cadastraLuthierView = new CadastraLuthierView();
			desktop.add(cadastraLuthierView);
			cadastraLuthierView.setVisible(true);
		} else if (src.equals(cadastraFabricante)) {
			cadastraFabricanteView = new CadastraFabricanteView();
			desktop.add(cadastraFabricanteView);
			cadastraFabricanteView.setVisible(true);
		} else if (src.equals(cadastraAcessorio)) {
			cadastraAcessorioView = new CadastraAcessorioView();
			desktop.add(cadastraAcessorioView);
			cadastraAcessorioView.setVisible(true);
		} else if (src.equals(cadastraMidia)) {
			cadastraMediaView = new CadastraMediaView();
			desktop.add(cadastraMediaView);
			cadastraAcessorioView.setVisible(true);
		} else if (src.equals(gerenciaClientes)) {
			gerenciaClienteView = new GerenciaClienteView();
			desktop.add(gerenciaClienteView);
			gerenciaClienteView.setVisible(true);
		} else if (src.equals(gerenciaLuthier)) {
			gerenciaLuthierView = new GerenciaLuthierView();
			desktop.add(gerenciaLuthierView);
			gerenciaLuthierView.setVisible(true);
		} else if (src.equals(gerenciaFabricantes)) {
			gerenciaFabricanteView = new GerenciaFabricanteView();
			desktop.add(gerenciaFabricanteView);
			gerenciaFabricanteView.setVisible(true);
		} else if (src.equals(gerenciaAcessorios)) {
			gerenciaAcessorioView = new GerenciaAcessorioView();
			desktop.add(gerenciaAcessorioView);
			gerenciaAcessorioView.setVisible(true);
		} else if (src.equals(gerenciaMedia)) {
			gerenciaMediaView = new GerenciaMediaView();
			desktop.add(gerenciaMediaView);
			gerenciaMediaView.setVisible(true);
		}

	}

}
