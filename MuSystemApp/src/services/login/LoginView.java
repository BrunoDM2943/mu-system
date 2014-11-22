package services.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.main.Menu;

/**
 * This is responsible of 
 * showing the frame for login  
 * @author bruno
 * @since 1.0 
 * 
 */
public class LoginView extends JFrame implements ActionListener{

	private static final long serialVersionUID = -4972101784603327025L;
	
	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	private JTextField tfLogin    = new JTextField(8);
	private JTextField tfPassWord = new JTextField(8);
	
	private JLabel lbLogin    = new JLabel("Login:");
	private JLabel lbPassWord = new JLabel("Senha:");
	
	private JButton btnLogin = new JButton("Login");
	private JButton btnSign  = new JButton("Sign up");
	private JButton btnExit  = new JButton("Exit");
	
	
	public LoginView() {
		this.setVisible(true);
		this.setSize(100, 220);
		this.setResizable(false);
		
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		btnSign.addActionListener(this);
		
		panel.add(lbLogin);
		panel.add(tfLogin);
		panel.add(lbPassWord);
		panel.add(tfPassWord);
		panel.add(btnLogin);
		panel.add(btnSign);
		panel.add(btnExit);
		
		this.add(panel);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object call = e.getSource();
		if(call.equals(btnLogin)){
			LoginService logService = new LoginService();
			String nome = tfLogin.getText();
			String pw   = tfPassWord.getText();
			try {
				logService.doLogin(nome, pw);
				this.dispose();
				new Menu();
			} catch (Exception error) {
				JOptionPane.showMessageDialog(panel, error.getMessage());
			}
		}else if(call.equals(btnSign)){
			
		}else{
			this.dispose();
		}
		
	}

}
