import javax.swing.JFrame;
import javax.swing.JTextField;


public class Tela extends JFrame{
	
	public static void main(String[] args) {
		try {
			new Tela();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JTextField tfNome = new JTextField();
	
	public Tela() throws Exception {
		tfNome.setText("Bruno Damasceno");
		tfNome.setName("Nome");
		this.add(tfNome);
		Front.invoke(this, HelloWorld.class, "ola");
	}

}
