package view.clientes;

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

import model.Cliente;
import controller.ClienteController;
import enums.Estado;

public class CadastraClienteView extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbNome;
	private JLabel lbRg;
	private JLabel lbBairro;
	private JLabel lbCidade;
	private JLabel lbEndereco;
	private JLabel lbTelefone;
	private JLabel lbEmail;
	private JLabel lbEstado;
	
	private JTextField tfNome;
	private JTextField tfRg;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JTextField tfEndereco;
	private JTextField tfTelefone;
	private JTextField tfEmail;	
	
	private JComboBox<Estado> cbEstado;
	
	private JButton btnGravar;
	private JButton btnCancelar;
	
	private MaskFormatter maskRg;
	private MaskFormatter maskTelefone;
	
	
	public CadastraClienteView() {		
		 inicializar();
		 setActions();
		 setLayout();
		 setFrame();
	}
	
	/**
	 * Configuar o frame da janela
	 * @author bruno
	 */
	private void setFrame() {
		 this.setTitle("Cadastro de clientes");
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
		lbNome     = new JLabel("Nome: ");
		lbRg       = new JLabel("Rg");
		lbBairro   = new JLabel("Bairro");
		lbCidade   = new JLabel("Cidade");
		lbEstado   = new JLabel("Estado");
		lbEndereco = new JLabel("Endereco");
		lbTelefone = new JLabel("Telefone");
		lbEmail    = new JLabel("Email");
		
		try {
			maskRg 		 = new MaskFormatter("##.###.###-?");
			maskTelefone = new MaskFormatter("#.####-####");
			maskRg.setPlaceholder(" ");
			maskTelefone.setPlaceholder(" ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tfNome     = new JTextField(30);
		tfRg       = new JFormattedTextField(maskRg);
		tfBairro   = new JTextField(20);
		tfCidade   = new JTextField(20);
		tfEndereco = new JTextField(50);
		tfTelefone = new JFormattedTextField(maskTelefone);
		tfEmail    = new JTextField(20);
		
		
		cbEstado = new JComboBox<Estado>(Estado.values());
		
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
	
		grade = new GridLayout(9, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Adiciona os actions listeners aos botões
	 * @author bruno
	 */
	private void setActions(){
		btnGravar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}
	
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author bruno
	 */
	private void setLayout(){
		painel.add(lbNome);
		painel.add(tfNome);
		
		painel.add(lbRg);
		painel.add(tfRg);
		
		painel.add(lbTelefone);
		painel.add(tfTelefone);
		
		painel.add(lbEmail);
		painel.add(tfEmail);
		
		painel.add(lbEstado);
		painel.add(cbEstado);
		
		painel.add(lbCidade);
		painel.add(tfCidade);
		
		painel.add(lbBairro);
		painel.add(tfBairro);
		
		painel.add(lbEndereco);
		painel.add(tfEndereco);
		
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
			Cliente cliente = new Cliente();
			try {
				cliente.setNome(tfNome.getText());
				cliente.setBairro(tfBairro.getText());
				cliente.setCidade(tfCidade.getText());
				cliente.setEmail(tfEmail.getText());
				cliente.setRg(tfRg.getText());
				cliente.setEndereco(tfEndereco.getText());
				cliente.setTelefone(tfTelefone.getText());
				cliente.setUf(cbEstado.getSelectedItem().toString());
				
				new ClienteController().gravarCliente(cliente);	
				JOptionPane.showMessageDialog(painel, "Cliente gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}
