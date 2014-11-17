package view.fabricantes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Fabricante;
import controller.FabricanteController;

public class CadastraFabricanteView extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2265548205326681589L;

	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbNome;
	private JLabel lbTelefone;
	private JLabel lbContato;
	
	private JTextField tfNome;
	private JTextField tfTelefone;
	private JTextField tfContato;
	
	private JButton btnGravar;
	private JButton btnCancelar;
	
	private MaskFormatter maskTelefone;	
	
	public CadastraFabricanteView() {		
		 inicializar();
		 setActions();
		 setLayout();
		 setFrame();
	}
	
	/**
	 * Configuar o frame da janela
	 * @author joão
	 */
	private void setFrame() {
		 this.setTitle("Cadastro de fabricantes");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(400,150);
		 this.setVisible(true);		 
		 this.setClosable(true);
		 this.setMaximizable(true);
		 this.setIconifiable(true);
		 this.setResizable(true);
		 
	}

	/**
	 * Inicializa os componentes da tela
	 * @author joão
	 */
	private void inicializar(){
		lbNome     = new JLabel("Nome: ");
		lbTelefone = new JLabel("Telefone:");
		lbContato  = new JLabel("Contato:");
		
		try {
			maskTelefone = new MaskFormatter("#.####-####");
			maskTelefone.setPlaceholder(" ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tfNome     = new JTextField(30);
		tfTelefone = new JFormattedTextField(maskTelefone);
		tfContato  = new JTextField(30);
		
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
		
		grade = new GridLayout(4, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author joão
	 */
	private void setActions(){
		btnGravar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}
	
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author joão
	 */
	private void setLayout(){
		painel.add(lbNome);
		painel.add(tfNome);
		
		painel.add(lbTelefone);
		painel.add(tfTelefone);
		
		painel.add(lbContato);
		painel.add(tfContato);
		
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
			Fabricante fabricante = new Fabricante();
			try {
				fabricante.setNome(tfNome.getText());
				fabricante.setTelefone(tfTelefone.getText());
				fabricante.setContato(tfContato.getText());
				
				new FabricanteController().gravarFabricante(fabricante);	
				JOptionPane.showMessageDialog(painel, "Fabricante gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}