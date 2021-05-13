package gui;
import domain.*;
import java.awt.EventQueue;
import domain.Registered;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class IsLoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static IsLoginGUI frame;
	private JLabel lblNewLabel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IsLoginGUI frame = new IsLoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public IsLoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("ENTER");
		btnNewButton.setBounds(181, 167, 110, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String izena = textField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				
				if(izena.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please, fill the gaps");
				}
				else {
					
				
					BLFacade negozioLogika = AdminGUI.getBusinessLogic();
					
					User b = negozioLogika.isLogin(izena, pass);
					
					if (b!=null ) {
						frame.setVisible(false);
						if (b instanceof Registered) {
						RegisteredGUI a = new RegisteredGUI(b);
						a.setVisible(true);
						a.setFrame(a);
						a.setErabiltzaile(b);
						System.out.println("erregistratua sartu da");
						frame.setVisible(false);
						}
						else if(b instanceof Admin) {
							AdminGUI a = new AdminGUI();
							frame.setVisible(false);
							a.setVisible(true);
							a.setFrame(a);
							a.setErabiltzaile(b);
							System.out.println("administratzailea sartu da");
						}
						else if(b instanceof Worker) {
							WorkerGUI a = new WorkerGUI();
							frame.setVisible(false);
							a.setVisible(true);
							a.setFrame(a);
							a.setErabiltzaile(b);
							System.out.println("langilea sartu da");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "This user isn't registered");
					
				}
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(181, 39, 222, 25);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 88, 222, 25);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(passwordField);
		
		JLabel lblLog = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("log"));
		lblLog.setBounds(75, 37, 116, 31);
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblLog);
		
		JLabel lblPass = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("pass"));
		lblPass.setBounds(75, 90, 99, 23);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblPass);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(123, 135, 225, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("MAKE A NEW ACCOUNT");
		btnNewButton_1.setBounds(10, 209, 455, 52);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				RegisterGUI a = new RegisterGUI();
				a.setFrame(a);
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);
	}

	public void setFrame(IsLoginGUI a) {
		frame=a;
		
	}
	
	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
}
