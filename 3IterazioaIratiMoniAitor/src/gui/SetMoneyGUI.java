package gui;

import java.util.Date;
import domain.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SetMoneyGUI extends JFrame {

	private JLabel totala;
	private JPanel contentPane;
	private JTextField Dirua;
	private JLabel info;
	private JFrame leihoa;
	private static User erabiltzaile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetMoneyGUI frame = new SetMoneyGUI(erabiltzaile);
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
	public SetMoneyGUI(User erabiltzaile) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblSetAmount = new JLabel("Deposit Money Amount");
		lblSetAmount.setBounds(30, 48, 131, 29);
		contentPane.add(lblSetAmount);
		
		JButton btnBack = new JButton("CLOSE");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisteredGUI a=new RegisteredGUI();
				leihoa.setVisible(false);
				a.setErabiltzaile(erabiltzaile);
				a.setVisible(true);
				
			}
		});
		btnBack.setBounds(302, 205, 78, 23);
		contentPane.add(btnBack);		
		JLabel lblTotalAmount = new JLabel("Total Money Amount");
		lblTotalAmount.setBounds(30, 138, 156, 20);
		contentPane.add(lblTotalAmount);
		
		
		
		JButton btnNewButton = new JButton("Dirua Sartu");
		leihoa = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade a = AdminGUI.getBusinessLogic();
				String dir = Dirua.getText().trim();
				
				if (dir.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to specify a money amount");
				}
				else {
					Integer dirua = Integer.parseInt(dir);
					if(dirua <= 0) {
						JOptionPane.showMessageDialog(null, "You need to enter a valid money amount");
					}
					else {
						try {
							
							a.setDirua(SetMoneyGUI.getLog(), dirua);
							JOptionPane.showMessageDialog(null, "Money Amount updated");
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date date = new Date();
							Transaction t = new Transaction(dirua, "deposited money", date, SetMoneyGUI.erabiltzaile);
							a.addTransaction(SetMoneyGUI.getLog(),t);
							SetMoneyGUI.erabiltzaile = a.isLogin(SetMoneyGUI.getLog(), SetMoneyGUI.getPass());
							totala.setText(Float.toString(SetMoneyGUI.erabiltzaile.getDirua()));
							
							} catch(Exception e) {
								e.printStackTrace();
							}
					}
				}
			}
		});
		
		
		btnNewButton.setBounds(54, 205, 132, 23);
		contentPane.add(btnNewButton);
		
		info = new JLabel("");
		info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		info.setForeground(Color.RED);
		info.setBounds(98, 88, 200, 29);
		contentPane.add(info);
		
		totala = new JLabel("");
		totala.setForeground(new Color(0, 128, 0));
		totala.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totala.setBounds(171, 138, 127, 20);
		contentPane.add(totala);
		
		Dirua = new JTextField();
		Dirua.setBounds(171, 52, 138, 20);
		contentPane.add(Dirua);
		Dirua.setColumns(10);
		
	
	}
	
	public void setErabiltzaile(User b) {
		this.erabiltzaile=b;
	}
	
	public static String getPass() {
		return SetMoneyGUI.erabiltzaile.getPass();
	}
	
	public static String getLog() {
		return SetMoneyGUI.erabiltzaile.getLog();
	}
}
