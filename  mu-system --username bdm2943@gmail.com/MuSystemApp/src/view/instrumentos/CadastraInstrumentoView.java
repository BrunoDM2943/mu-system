package view.instrumentos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.validator.OnlyNumberField;
import model.Fabricante;
import model.Instrumento;
import controller.FabricanteController;
import controller.InstrumentoController;
import enums.Especialidade;

public class CadastraInstrumentoView extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbFabricante;
	private JLabel lbNomeIns;
	private JLabel lbTipoIns;
	private JLabel lbPreco;
	private JLabel lbEspecificacao;
	
	private JTextField tfNomeIns;
	private JTextField tfPreco;
	private JTextField tfEspecificacao;
	
	private JComboBox<Fabricante> cbFabricante;
	private JComboBox<Especialidade> cbTipo;
	
	private JButton btnGravar;
	private JButton btnCancelar;
	
	
	public CadastraInstrumentoView() {		
		try {
			inicializar();		
			setActions();
			setLayout();
			setFrame();
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * Configurar o frame da janela
	 * @author Guilherme
	 */
	private void setFrame() {
		 this.setTitle("Cadastro de Instrumentos");
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
	 * @author Guilherme
	 * @throws Exception 
	 */
	private void inicializar() throws Exception{
		lbFabricante 		= new JLabel("Fabricante:");
		lbNomeIns  			= new JLabel("Nome:");
		lbTipoIns	    	= new JLabel("Tipo:");
		lbPreco 			= new JLabel("Preço:");		
		lbEspecificacao 	= new JLabel("Especificação:");
			
			
		tfNomeIns       = new JTextField(30);
		tfPreco         = new JTextField(6);
		tfEspecificacao = new JTextField(30);
				
		tfPreco.setDocument(new OnlyNumberField());
				
		cbTipo = new JComboBox<Especialidade>(Especialidade.values());
		
		Vector<Fabricante> fabricantes;
		fabricantes = getVetorFabricantes();		
		cbFabricante = new JComboBox<Fabricante>(fabricantes);
		
		
		
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
	
		grade = new GridLayout(7, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Transforma a lista de fabricantes em um vetor
	 * @return
	 * @throws Exception 
	 */
	private Vector<Fabricante> getVetorFabricantes() throws Exception {
		Vector<Fabricante> fabricantes = new Vector<Fabricante>();
		new FabricanteController().listarTodos().forEach(e -> fabricantes.add(e));
		return fabricantes;
	}

	/**
	 * Adiciona os actions listeners aos botões
	 * @author Guilherme
	 */
	private void setActions(){
		btnGravar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}
	
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author Guilherme
	 */
	private void setLayout(){
		painel.add(lbFabricante);
		painel.add(cbFabricante);
		
		painel.add(lbNomeIns);
		painel.add(tfNomeIns);
		
		painel.add(lbTipoIns);
		painel.add(cbTipo);
		
		painel.add(lbPreco);
		painel.add(tfPreco);
		
		painel.add(lbEspecificacao);
		painel.add(tfEspecificacao);
		
		
		
		painel.add(btnGravar);
		painel.add(btnCancelar);
		
		this.add(painel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(btnCancelar)){
			this.dispose();
		}else{
			Instrumento ins = new Instrumento();
			
			try{
				ins.setNome(tfNomeIns.getText());
				ins.setTipo(cbTipo.getSelectedItem().toString());
				ins.setPreco(Float.parseFloat(tfPreco.getText()));
				ins.setEspecificacao(tfEspecificacao.getText());
				new InstrumentoController().gravarInstrumento(ins);
				JOptionPane.showMessageDialog(painel, "Instrumento gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}
