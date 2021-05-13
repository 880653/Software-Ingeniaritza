package gui;

import domain.*;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class SeeProfitsGUI extends JFrame {
	private JPanel contentPane;
	private JFrame leihoa;
	private static User erabiltzaile;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeProfitsGUI frame = new SeeProfitsGUI();
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
	public SeeProfitsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 622, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		leihoa = this;
		
		JButton btnNewButton = new JButton("See Transactions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade a = AdminGUI.getBusinessLogic();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				SeeProfitsGUI.erabiltzaile= a.isLogin(SeeProfitsGUI.erabiltzaile.getLog(), SeeProfitsGUI.erabiltzaile.getPass());
				ArrayList<Transaction> trantsakzioak = (ArrayList<Transaction>) SeeProfitsGUI.erabiltzaile.getTransactions();
				String emaitza="";
				
				
				if (model.getRowCount() > 0) {
				    for (int i = model.getRowCount() - 1; i > -1; i--) {
				        model.removeRow(i);
				    }
				}
				
				for (int i=0; i<trantsakzioak.size(); i++) {
					Transaction t = trantsakzioak.get(i);
					model.addRow(new Object[]{
							
							t.getTransactionNumber(), t.getDirua(), t.getMovement(), t.getDate()
							
					});

				}
				
				textField.setText(AdminGUI.getErabiltzaile().getDirua() + "");
				
			}
		});
		btnNewButton.setBounds(268, 256, 174, 23);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminGUI a=new AdminGUI();
				leihoa.setVisible(false);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(477, 256, 89, 23);
		contentPane.add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 586, 220);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Transaction #", "Money", "Movement", "Date"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblProfits = new JLabel("Profits:");
		lblProfits.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblProfits.setBounds(41, 260, 64, 14);
		contentPane.add(lblProfits);
		
		textField = new JTextField();
		textField.setBounds(104, 257, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		
	}
	
	public void setErabiltzaile(User b) {
		this.erabiltzaile=b;
	}
}
