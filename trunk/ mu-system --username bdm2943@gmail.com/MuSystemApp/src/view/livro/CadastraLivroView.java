package view.livro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Livro;
import controller.LivroController;

public class CadastraLivroView extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 2501542300653384230L;

	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbTitulo;
	private JLabel lbAutor;
	private JLabel lbEditora;
	private JLabel lbPreco;
	private JLabel lbAno;
	
	private JTextField tfTitulo;
	private JTextField tfAutor;
	private JTextField tfDistribuidora;
	private JTextField tfPreco;
	
	private JComboBox<Integer> cbAno;
	
	private JButton btnGravar;
	private JButton btnCancelar;
	
	public CadastraLivroView() {		
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
		 this.setTitle("Cadastro de Livro");
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
	 * @author joão
	 */
	private void inicializar(){
		lbTitulo 		= new JLabel("Titulo:");
		lbAutor  		= new JLabel("Autor");
		lbEditora       = new JLabel("Editora:");		
		lbPreco 		= new JLabel("Preco:");
		lbAno 			= new JLabel("Ano:");	
			
		tfTitulo        = new JTextField(30);
		tfAutor         = new JTextField(30);
		tfDistribuidora = new JTextField(30);
		tfPreco 		= new JTextField(6);
		
		Arrays.asList(IntStream.range(1900, 2099)).forEach(e -> System.out.println(e));;
		Vector<Integer> anos = getVectorAnos();
		cbAno = new JComboBox<Integer>(anos);
		
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
	
		grade = new GridLayout(7, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Retorna o vetor de anos para 
	 * o combo box cdAno
	 * 
	 * @return Vetor contendo os anos
	 */
	private Vector<Integer> getVectorAnos() {
		Vector<Integer> vetor = new Vector<Integer>();
		IntStream.range(1930, 2100).forEach(e -> vetor.addElement(e));
		return vetor;
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
		painel.add(lbTitulo);
		painel.add(tfTitulo);
		
		painel.add(lbAutor);
		painel.add(tfAutor);
		
		painel.add(lbEditora);
		painel.add(tfDistribuidora);
		
		painel.add(lbAno);
		painel.add(cbAno);
		
		painel.add(lbPreco);
		painel.add(tfPreco);
		
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
			Livro livro = new Livro();
			
			try{
				livro.setTitulo(tfTitulo.getText());
				livro.setAutor(tfAutor.getText());
				livro.setEditora(tfDistribuidora.getText());
				livro.setPreco(Float.parseFloat(tfPreco.getText()));
				livro.setAno((Integer)cbAno.getSelectedItem());
				new LivroController().gravarLivro(livro);
				JOptionPane.showMessageDialog(painel, "Livro gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}
