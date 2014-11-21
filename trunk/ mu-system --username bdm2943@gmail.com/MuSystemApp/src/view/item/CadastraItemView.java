package view.item;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Item;
import controller.ItemController;
import enums.TipoItem;

public class CadastraItemView extends JInternalFrame implements ActionListener {
	
	private static final long serialVersionUID = -7529042649591839785L;

	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbProduto;
	private JLabel lbItem;
	private JLabel lbQuant;
	
	private JTextField tfQuant;
	
	private JComboBox<TipoItem> cbItem;
	private JComboBox cbProduto;
		
	private JButton btnGravar;
	private JButton btnCancelar;
	
	
	public CadastraItemView(){		
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
		 this.setTitle("Cadastro de acess��rios");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(400,100);
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
		
		lbProduto     = new JLabel("Produto: ");
		lbItem        = new JLabel("Item: ");
		lbQuant       = new JLabel("Quantidade: ");
			
		tfQuant    = new JTextField(3);
				
		lbProduto.setLabelFor(cbProduto);
		lbItem.setLabelFor(cbProduto);
		lbQuant.setLabelFor(tfQuant);
		btnGravar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
	
		grade = new GridLayout(4, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Adiciona os actions listeners aos bot��es
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
		painel.add(lbProduto);
		painel.add(cbProduto);
		
		painel.add(lbItem);
		painel.add(cbItem);
		
		painel.add(lbQuant);
		painel.add(tfQuant);
				
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
			Acessorio acessorio = new Acessorio();
			try {
				acessorio.setNome(tfNome.getText());
				acessorio.setPreco(Float.parseFloat(tfQuant.getText()));				
				
				new AcessorioController().gravarAcessorio(acessorio);	
				JOptionPane.showMessageDialog(painel, "Acessorio gravado com sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(painel, e1.getMessage());
			}
		}
	}

}