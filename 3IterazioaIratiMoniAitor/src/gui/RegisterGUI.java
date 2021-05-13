package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewLabel_1;
	private static RegisterGUI frame;
	private JLabel lblIdNum;
	private JLabel lblAccountNum;
	private JTextField name;
	private JTextField last;
	private JTextField id;
	private JTextField account;
	
	
	
	private static boolean isNumeric(String s) {
		
        boolean num;
        try {
            Integer.parseInt(s);
            num = true;
        } catch (NumberFormatException excepcion) {
            num = false;
        }
        return num;
    }
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewUser = new JLabel("New Username");
		lblNewUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewUser.setBounds(21, 11, 91, 25);
		contentPane.add(lblNewUser);
		
		textField = new JTextField();
		textField.setBounds(144, 14, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(21, 47, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRepitePassword = new JLabel("Repeat Password");
		lblRepitePassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRepitePassword.setBounds(21, 85, 113, 14);
		contentPane.add(lblRepitePassword);
		
		JButton btnNewButton = new JButton("SIGN IN!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String log = textField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String pass2 = String.valueOf(passwordField_1.getPassword());
				String izena = name.getText();
				String abizen = last.getText();
				String nana = id.getText();
				String kontu = account.getText();
				
				if (log.isEmpty()||pass.isEmpty()||pass2.isEmpty()||izena.isEmpty()||abizen.isEmpty()||nana.isEmpty()||kontu.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to fill all the fields");
				}
				
				else if(!kontu.isEmpty()&&!isNumeric(kontu)) {
					JOptionPane.showMessageDialog(null, "The bank account must be a number");
				}
				
				else if(!nana.isEmpty()&&!isNumeric(nana)) {
					JOptionPane.showMessageDialog(null, "The NAN must be a number");
				}
				else if(isNumeric(abizen)) {
					JOptionPane.showMessageDialog(null, "The last name must be a string");
				}
				else if(isNumeric(izena)) {
					JOptionPane.showMessageDialog(null, "The name must be a string");
				}
				
//				String ab = nana.substring(0, Math.min(nana.length(), 8));
//				
//				if(nana.length()!=9) {
//					lblNewLabel_1.setText("The NAN must consist of 9 characters");
//				}
//				
//				if(!isNumeric(ab)) {
//					lblNewLabel_1.setText("The first 8 characters of the NAN must be numbers");	
//				}
//				
//
//				
//				if(java.lang.Character.isLetter(nana.charAt(9))) {
//					lblNewLabel_1.setText("The last character of the NAN must be a letter");	
//				}
				


				
				else{
					long nan = Long.valueOf(nana.trim());
					long kontua = Long.valueOf(kontu.trim());
					
					
					BLFacade negozioLogika = AdminGUI.getBusinessLogic();
					int b = negozioLogika.register(log, pass, pass2, izena, abizen, nan, kontua);
					
					switch(b) {
						case 0: JOptionPane.showMessageDialog(null, "The username you entered is already registered ");
								break;
						case 1: JOptionPane.showMessageDialog(null, "The passwords you entered are not equal");
								break;
						case 2:{ JOptionPane.showMessageDialog(null, "You were succesfully registered");
								IsLoginGUI a= new IsLoginGUI();
								a.setVisible(true);
								a.setFrame(a);
								frame.setVisible(false);
								System.out.println("login egin du");
						}
					}
				}
			}
		});
		
		
		btnNewButton.setBounds(137, 193, 163, 28);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 45, 86, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(144, 83, 86, 20);
		contentPane.add(passwordField_1);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(86, 177, 254, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblIzena = new JLabel("Name");
		lblIzena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIzena.setBounds(21, 120, 113, 14);
		contentPane.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Last Name");
		lblAbizena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAbizena.setBounds(21, 152, 113, 14);
		contentPane.add(lblAbizena);
		
		lblIdNum = new JLabel("ID Num");
		lblIdNum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdNum.setBounds(240, 11, 91, 25);
		contentPane.add(lblIdNum);
		
		lblAccountNum = new JLabel("Bank account");
		lblAccountNum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAccountNum.setBounds(240, 42, 91, 25);
		contentPane.add(lblAccountNum);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(144, 118, 86, 20);
		contentPane.add(name);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(144, 150, 86, 20);
		contentPane.add(last);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(327, 14, 86, 20);
		contentPane.add(id);
		
		account = new JTextField();
		account.setColumns(10);
		account.setBounds(327, 45, 86, 20);
		contentPane.add(account);
		
		JButton btnNewButton_1 = new JButton("\u2190");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IsLoginGUI a = new IsLoginGUI();
				frame.setVisible(false);
				a.setVisible(true);
			}

			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_1.setBounds(355, 225, 69, 25);
		contentPane.add(btnNewButton_1);
	}
	
	public void setFrame(RegisterGUI lehioa) {
 	   frame=lehioa;		
	}
}
