package view.luthier;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Luthier;
import services.validator.TextValidator;
import controller.LuthierController;
import enums.Especialidade;
import enums.Estado;

public class CadastraLuthierView extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062537354323286011L;

	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbEndereco;
	private JLabel lbBairro;
	private JLabel lbCidade;
	private JLabel lbUF;
	private JLabel lbTelefone;
	private JLabel lbEmail;
	private JLabel lbEspecialidade;
	
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfEndereco;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	
	private JComboBox<Estado> cbEstado;
	private JComboBox<Especialidade> cbEspecialiade;
	
	private JButton btnGravar;
	private JButton btnCancelar;
	
	private MaskFormatter maskCPF;
	private MaskFormatter maskTelefone;
	
	public CadastraLuthierView() {		
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
		 this.setTitle("Cadastro de luthier");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(600,400);
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
		lbNome          = new JLabel("Nome: ");
		lbCPF           = new JLabel("CPF: ");
		lbEndereco      = new JLabel("Endereço: ");
		lbBairro        = new JLabel("Bairro: ");
		lbCidade        = new JLabel("Cidade: ");
		lbUF            = new JLabel("Estado: ");
		lbTelefone      = new JLabel("Telefone: ");
		lbEmail         = new JLabel("E-mail: ");
		lbEspecialidade = new JLabel("Especialidade: ");
		
		try {
			maskCPF = new MaskFormatter("###.###.###-##");
			maskCPF.setPlaceholder(" ");
			maskTelefone = new MaskFormatter("#.####-####");
			maskTelefone.setPlaceholder(" ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tfNome     = new JTextField(30);
		tfCPF      = new JFormattedTextField(maskCPF);
		tfEndereco = new JTextField(50);
		tfBairro   = new JTextField(20);
		tfCidade   = new JTextField(20);
		tfTelefone = new JFormattedTextField(maskTelefone);
		tfEmail    = new JTextField(20);
		
		tfNome.setDocument(new TextValidator("1234567890"));
		tfBairro.setDocument(new TextValidator("1234567890"));
		tfCidade.setDocument(new TextValidator("1234567890"));
		
		cbEstado       = new JComboBox<Estado>(Estado.values());
		cbEspecialiade = new JComboBox<Especialidade>(Especialidade.values());
		
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
		
		grade = new GridLayout(10, 2, 5, 5);
		
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
		
		painel.add(lbCPF);
		painel.add(tfCPF);
		
		painel.add(lbEndereco);
		painel.add(tfEndereco);
		
		painel.add(lbBairro);
		painel.add(tfBairro);
		
		painel.add(lbCidade);
		painel.add(tfCidade);
		
		painel.add(lbUF);
		painel.add(cbEstado);
		
		painel.add(lbTelefone);
		painel.add(tfTelefone);
		
		painel.add(lbEmail);
		painel.add(tfEmail);
		
		painel.add(lbEspecialidade);
		painel.add(cbEspecialiade);
		
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
			Luthier luthier = new Luthier();
			try {
				luthier.setNome(tfNome.getText());
				luthier.setCpf(tfCPF.getText());
				luthier.setEndereco(tfEndereco.getText());
				luthier.setBairro(tfBairro.getText());
				luthier.setCidade(tfCidade.getText());
				luthier.setCpf(tfCPF.getText());
				luthier.setUf(cbEstado.getSelectedItem().toString());
				luthier.setTelefone(tfTelefone.getText());
				luthier.setEmail(tfEmail.getText());
				luthier.setEspecialidade(cbEspecialiade.getSelectedItem().toString());
				
				new LuthierController().gravarLuthier(luthier);	
				JOptionPane.showMessageDialog(painel, "Luthier gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}