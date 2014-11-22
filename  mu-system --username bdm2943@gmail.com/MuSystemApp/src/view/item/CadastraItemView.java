package view.item;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Comercializavel;
import model.Item;
import services.validator.OnlyNumberField;
import controller.ItemController;
import enums.TipoItem;

public class CadastraItemView extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -7529042649591839785L;

	private JPanel painel;
	
	private GridLayout grade;
	
	private JLabel lbProduto;
	private JLabel lbItem;
	private JLabel lbQuant;
	
	private JTextField tfQuant;
	
	private JComboBox<TipoItem> cbItem;
	private JComboBox<Comercializavel> cbProduto;
	private DefaultComboBoxModel<Comercializavel> cbModel;
		
	private JButton btnAdicionar;
	private JButton btnCancelar;
	
	private ItemController itemController;
	private Vector<Comercializavel> vetorItens;
	
	public CadastraItemView(ItemController itemController){
		this.itemController = itemController;
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
		 setTitle("Cadastro de acess��rios");
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 setSize(400,200);		 		
		 setResizable(true);
		 setModal(true);
		 pack();
		 
	}

	/**
	 * Inicializa os componentes da tela
	 * @author joão
	 */
	private void inicializar(){		
		
		itemController = new ItemController();
		vetorItens     = new Vector<Comercializavel>();
		
		lbProduto     = new JLabel("Produto: ");
		lbItem        = new JLabel("Item: ");
		lbQuant       = new JLabel("Quantidade: ");
			
		cbItem     = new JComboBox<TipoItem>(TipoItem.values());
		cbProduto  = new JComboBox<Comercializavel>();
		tfQuant    = new JTextField(3);
		
		tfQuant.setDocument(new OnlyNumberField(3));
				
		lbProduto.setLabelFor(cbProduto);
		lbItem.setLabelFor(cbItem);
		lbQuant.setLabelFor(tfQuant);
		btnAdicionar   = new JButton("Gravar");
		btnCancelar = new JButton("Cancelar");
	
		grade = new GridLayout(4, 2, 5, 5);
		
		painel = new JPanel(grade);
	}
	
	/**
	 * Adiciona os actions listeners aos bot��es
	 * @author joão
	 */
	private void setActions(){
		btnAdicionar.addActionListener(this);
		btnCancelar.addActionListener(this);
		cbItem.addActionListener(this);
	}
	
	
	/**
	 * Adiciona os componentes na 
	 * tela
	 * @author joão
	 */
	private void setLayout(){
		
		painel.add(lbItem);
		painel.add(cbItem);
		
		painel.add(lbProduto);
		painel.add(cbProduto);
		
		painel.add(lbQuant);
		painel.add(tfQuant);
				
		painel.add(btnAdicionar);
		painel.add(btnCancelar);
		
		this.add(painel);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
	
			if (src.equals(btnCancelar)) {
				this.dispose();
			} else if (src.equals(cbItem)) {
				try {
					TipoItem enumItem = (TipoItem) cbItem.getSelectedItem();
					vetorItens = itemController.listarItens(enumItem.getClazz());
					vetorItens.forEach(item->System.out.println(item));
					cbModel = new DefaultComboBoxModel<Comercializavel>(vetorItens);
					cbProduto.setModel(cbModel);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}	
			} else if(src.equals(btnAdicionar)){
				Item item = new Item((Comercializavel) cbProduto.getSelectedItem());
				item.setQtd(Integer.parseInt(tfQuant.getText()));
				itemController.addItem(item);
				this.dispose();
			}
		}
	}

