package gui;

/**
 * @author Software Engineering teachers
 */

import javax.swing.*;

import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.*;

public class RegisteredGUI extends JFrame {
	private static User erabiltzaile;
	private static RegisteredGUI leihoa;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JButton btnNewButton;
	private JButton btnSeeTransactions;
	private JButton btnNewButton_1;
	private JButton btnCheckFees;
	private JButton btnMakeAMultiple;

	/**
	 * This is the default constructor
	 */

	public RegisteredGUI() {
		super();
		RegisteredGUI.erabiltzaile = erabiltzaile;
		leihoa = this;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public RegisteredGUI(User erabiltzaile) {
		super();
		RegisteredGUI.erabiltzaile = erabiltzaile;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 365);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getBtnSeeTransactions());

			btnCheckFees = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI3")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCheckFees.setBounds(0, 172, 479, 49);
			btnCheckFees.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CheckFeesGUI a = new CheckFeesGUI();
					a.setVisible(true);
					RegisteredGUI.leihoa.setVisible(false);
					a.setErabiltzaile(RegisteredGUI.erabiltzaile);
				}
			});
			jContentPane.add(btnCheckFees);
			jContentPane.add(getBtnNewButton_1());
			jContentPane.add(getbtnMakeAMultiple());

		}
		return jContentPane;
	}

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 11, 479, 23);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 290, 479, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("DepositMoney"));
		btnSeeTransactions.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeTransactions"));
		btnCheckFees.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckFee"));
		btnMakeAMultiple.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.btnMakeAMultiple.text"));

	}

	public void setFrame(RegisteredGUI a) {
		leihoa = a;

	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI1"));
			btnNewButton.setBounds(0, 80, 479, 49);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SetMoneyGUI a = new SetMoneyGUI(RegisteredGUI.getErabiltzaile());
					a.setVisible(true);
					a.setErabiltzaile(RegisteredGUI.getErabiltzaile());
					RegisteredGUI.leihoa.setVisible(false);
					System.out.println("erregistratua sartu da");
				}
			});
		}
		return btnNewButton;
	}

	public void setErabiltzaile(User erab) {
		this.erabiltzaile = erab;
	}

	public static User getErabiltzaile() {
		return RegisteredGUI.erabiltzaile;
	}

	private JButton getBtnSeeTransactions() {
		if (btnSeeTransactions == null) {
			btnSeeTransactions = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI2")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSeeTransactions.setBounds(0, 127, 479, 49);
			btnSeeTransactions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeeTransactionsGUI a = new SeeTransactionsGUI();
					a.setVisible(true);
					a.setErabiltzaile(RegisteredGUI.getErabiltzaile());
					RegisteredGUI.leihoa.setVisible(false);
				}
			});
		}
		return btnSeeTransactions;
	}

	private JButton getbtnMakeAMultiple() {
		if (btnMakeAMultiple == null) {
			btnMakeAMultiple = new JButton(
					ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.btnMakeAMultiple.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnMakeAMultiple.setBounds(0, 218, 479, 49);
			btnMakeAMultiple.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 MakeAMultipleBetGUI a = new MakeAMultipleBetGUI();
					 a.setVisible(true);
					 a.setErabiltzaile(RegisteredGUI.getErabiltzaile());
					 RegisteredGUI.leihoa.setVisible(false);
				}
			});
		}
		return btnMakeAMultiple;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton(
					ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_1.setBounds(366, 35, 89, 23);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					IsLoginGUI a = new IsLoginGUI();
					a.setVisible(true);
					a.setFrame(a);
					leihoa.setVisible(false);
					System.out.println("login egin du");
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return btnNewButton_1;
	}

} // @jve:decl-index=0:visual-constraint="0,0"
