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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class SeeTransactionsGUI extends JFrame {
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
					SeeTransactionsGUI frame = new SeeTransactionsGUI();
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
	public SeeTransactionsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		leihoa = this;
		
		JButton btnNewButton = new JButton("See Transactions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Transaction> trantsakzioak = new ArrayList(SeeTransactionsGUI.erabiltzaile.getTransactions());
				String emaitza="";
				BLFacade a = AdminGUI.getBusinessLogic();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i=0; i< model.getRowCount(); i++)
					model.removeRow(i);
				for (int i=0; i<trantsakzioak.size(); i++) {
					Transaction t = trantsakzioak.get(i);
					model.addRow(new Object[]{t.getTransactionNumber(), t.getDirua(), t.getMovement(), t.getDate()});

				}
				textField.setText(SeeTransactionsGUI.erabiltzaile.getDirua() + "");
				btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setBounds(218, 259, 158, 23);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisteredGUI a=new RegisteredGUI();
				leihoa.setVisible(false);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(440, 259, 89, 23);
		contentPane.add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 549, 226);
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
		
		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(29, 263, 60, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(88, 260, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		
		
	
	}
	
	public void setErabiltzaile(User b) {
		this.erabiltzaile=b;
	}
	
	public static String getPass() {
		return SeeTransactionsGUI.erabiltzaile.getPass();
	}
	
	public static String getLog() {
		return SeeTransactionsGUI.erabiltzaile.getLog();
	}
}
