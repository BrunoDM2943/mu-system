package view.instrumentos;

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

import model.Instrumento;
import services.auxiliarityViews.AlterarDadoView;
import services.auxiliarityViews.GerenciamentoServices;
import services.validator.Validator;
import view.GUIModels.tableModels.InstrumentoTableModel;
import controller.InstrumentoController;
import exceptions.BusinessException;

public class GerenciaInstrumentoView extends JInternalFrame implements ActionListener, GerenciamentoServices<Instrumento>{

	private static final long serialVersionUID = -7087120418438369171L;
	
	private JPanel painel;
	private JPanel painelFiltro;
	private JPanel painelBotoes;
	private JScrollPane painelScroll;
	
	private JTable tblInstrumento;	
	private InstrumentoTableModel tblModel;
	
	private JLabel lbFiltro;
	
	private JTextField tfFiltro;
	
	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	
	private TableRowSorter<InstrumentoTableModel> sorter;
	
	private static final int COL_TITULO = 0; 
	
	private InstrumentoController crtl = new InstrumentoController();	
	
	
	public GerenciaInstrumentoView() {
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
	 * Configurar o frame da janela
	 * @author Guilherme
	 */
	private void setFrame() {
		 this.setTitle("Gerenciador de Instrumentos");
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
	 * @author Guilherme
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{		
		carregarTabela();
		btnAlterar   = new JButton("Alterar");
		btnDeletar   = new JButton("Deletar");
		btnCancelar  = new JButton("Cancelar");	
		btnPesquisar = new JButton("Pesquisar");
		
		lbFiltro = new JLabel("Filtro");
		tfFiltro = new JTextField(20);
		
		painel = new JPanel(new BorderLayout(10, 10));
		painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
		painelFiltro.setSize(getWidth(), 20);
		painelScroll = new JScrollPane(tblInstrumento);
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
	}
	
	/**
	 * Carrega a tabela de Medias
	 * @throws Exception 
	 */
	private void carregarTabela() throws Exception {
		carregarTableModel();			
		tblInstrumento = new JTable();
		popularTabela();		
	}
	
	/**
	 * Popula a tabela
	 */
	private void popularTabela() {
		tblInstrumento.setModel(tblModel);
		sorter = new TableRowSorter<InstrumentoTableModel>(tblModel);
		tblInstrumento.setRowSorter(sorter);
	}

	/**
	 * Carrega um tableModel
	 * @return 
	 * @throws Exception 
	 */
	private void carregarTableModel() throws Exception{
		List<Instrumento> listaInstrumento = crtl.listarTodos();
		tblModel = new InstrumentoTableModel(listaInstrumento);		
	}
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author Guilherme
	 */
	private void setLayout(){

		painelFiltro.add(lbFiltro);
		painelFiltro.add(tfFiltro);
		
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnDeletar);
		painelBotoes.add(btnPesquisar);
		painelBotoes.add(btnCancelar);

		painel.add(painelFiltro, BorderLayout.NORTH);
		painel.add(painelScroll, BorderLayout.CENTER);
		painel.add(painelBotoes, BorderLayout.SOUTH);
		
		this.add(painel);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author Guilherme
	 */
	private void setActions(){
		btnAlterar.addActionListener(this);
		btnDeletar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(btnAlterar)){
			alterar();
		}else if(src.equals(btnDeletar)){
			deletar();
		}else if(src.equals(btnPesquisar)){
			procurar();
		}else if(src.equals(btnCancelar)){
			this.dispose();
		}
		
	}
	
	/**
	 * Confirma a deleção de um Media
	 * @param c
	 * @return
	 */
	private boolean confirmaDeletar(Instrumento ins) {
		String msg = "Deseja realmente deletar o instrumento " + ins + " do sistema?";
		int opt = JOptionPane.showConfirmDialog(this, msg);
		System.out.println(opt);
		return opt == 0;
	}
		
	@Override
	public void alterar() {
		int idx = tblInstrumento.getSelectedRow();
		int col = tblInstrumento.getSelectedColumn();
		try{
			validarIndice(idx);
			
			String nomeCampo = tblModel.getColumnName(col);		
			if (nomeCampo.equals("Fabricante"))
				throw new BusinessException("Alteração de fabricante não é permitida!");
			Class<?> clazz = tblModel.getColumnClass(col);
			Object e = AlterarDadoView.alterarDado(clazz, nomeCampo);
			
			if(!Validator.isEmpty(String.valueOf(e))){				
				tblModel.setValueAt(e, idx, col);
				String titulo = (String) tblInstrumento.getValueAt(idx, COL_TITULO);
				Instrumento cli = tblModel.get(titulo);
				crtl.atualizarInstrumento(cli);
				JOptionPane.showMessageDialog(this, "Media atualizado com sucesso");
				carregarTableModel();
				popularTabela();
			} else {
				JOptionPane.showMessageDialog(null, "Alteração de fabricante não é permitida!");
			}
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
			throw new Exception("Nenhum Media foi selecionado");		
	}

	@Override
	public List<Instrumento> procurar() {
		String filtro = tfFiltro.getText();
		if(filtro.length() == 0){
			sorter.setRowFilter(null);
		}else{
			sorter.setRowFilter(RowFilter.regexFilter(filtro, 0));
		}
		return null;
	}
	
	@Override
	public void deletar() {
		int idx = tblInstrumento.getSelectedRow();
		try {
			validarIndice(idx);
			String rg = (String) tblInstrumento.getValueAt(idx, COL_TITULO);
			Instrumento c = tblModel.get(rg);
			if(confirmaDeletar(c)){
				crtl.deletarInstrumento(c);
				JOptionPane.showMessageDialog(this, "Media removido com sucesso");
				carregarTableModel();
				popularTabela();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
			e1.printStackTrace();
		}
		
		
	}


}