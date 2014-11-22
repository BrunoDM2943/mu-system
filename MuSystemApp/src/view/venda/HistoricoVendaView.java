package view.venda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import model.Venda;
import view.GUIModels.tableModels.VendaTableModel;
import controller.VendaController;

public class HistoricoVendaView extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = -7087120418438369171L;
	
	private JPanel painel;
	private JPanel painelFiltro;
	private JPanel painelBotoes;
	private JScrollPane painelScroll;
	
	private JTable tblVenda;	
	private VendaTableModel tblModel;
	
	private JLabel lbFiltro;
	
	private JTextField tfFiltro;
	
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JButton btnDetalhar;
	
	private TableRowSorter<VendaTableModel> sorter;
	
	private static final int COL_CODIGO = 0;
	
	private VendaController crtl = new VendaController();	
	
	
	public HistoricoVendaView() {
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
		 this.setTitle("Historico de Vendas");
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
	 * @author bruno
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{		
		carregarTabela();
		
		btnCancelar  = new JButton("Cancelar");	
		btnPesquisar = new JButton("Pesquisar");
		btnDetalhar  = new JButton("Detalhar");
		
		lbFiltro = new JLabel("Filtro");
		tfFiltro = new JTextField(20);
		
		painel = new JPanel(new BorderLayout(10, 10));
		painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
		painelFiltro.setSize(getWidth(), 20);
		painelScroll = new JScrollPane(tblVenda);
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
	}
	
	/**
	 * Carrega a tabela de Vendas
	 * @throws Exception 
	 */
	private void carregarTabela() throws Exception {
		carregarTableModel();			
		tblVenda = new JTable();
		popularTabela();		
	}
	
	/**
	 * Popula a tabela
	 */
	private void popularTabela() {
		tblVenda.setModel(tblModel);
		sorter = new TableRowSorter<VendaTableModel>(tblModel);
		tblVenda.setRowSorter(sorter);
	}

	/**
	 * Carrega um tableModel
	 * @return 
	 * @throws Exception 
	 */
	private void carregarTableModel() throws Exception{
		List<Venda> listaVendas = crtl.listarTodos();
		tblModel = new VendaTableModel(listaVendas);		
	}
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author bruno
	 */
	private void setLayout(){

		painelFiltro.add(lbFiltro);
		painelFiltro.add(tfFiltro);

		painelBotoes.add(btnPesquisar);		
		painelBotoes.add(btnDetalhar);
		painelBotoes.add(btnCancelar);

		painel.add(painelFiltro, BorderLayout.NORTH);
		painel.add(painelScroll, BorderLayout.CENTER);
		painel.add(painelBotoes, BorderLayout.SOUTH);
		
		this.add(painel);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author bruno
	 */
	private void setActions(){
		btnDetalhar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnCancelar.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(btnDetalhar)){
			detalhar();
		}else if(src.equals(btnPesquisar)){
			procurar();
		}else if(src.equals(btnCancelar)){
			this.dispose();
		}
		
	}

		
	private void detalhar(){
		int idx = tblVenda.getSelectedRow();
		try{
			validarIndice(idx);
			int codigo = (int) tblModel.getValueAt(idx, COL_CODIGO);
			new DetalharItemView(tblModel.get(codigo));			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * Valida o indice selecionado em uma
	 * linha da tabela
	 * @param idx
	 * @throws Exception 
	 */
	private void validarIndice(int idx) throws Exception {
		if(idx == -1)
			throw new Exception("Nenhum venda foi selecionada");		
	}

	public List<Venda> procurar() {
		String filtro = tfFiltro.getText();
		if(filtro.length() == 0){
			sorter.setRowFilter(null);
		}else{
			sorter.setRowFilter(RowFilter.regexFilter(filtro, 0));
		}
		return null;
	}
		

}
