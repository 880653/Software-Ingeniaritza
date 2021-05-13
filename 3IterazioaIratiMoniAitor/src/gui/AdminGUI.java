package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private static User erabiltzaile;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static AdminGUI leihoa;
	private JButton button;
	private JButton btnCreateEvent;
	private JButton btnSetResult;
	private JButton btnAddFeesTo ;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		AdminGUI.erabiltzaile=erabiltzaile;
		leihoa=this;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		leihoa=this;
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 428);
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
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			jContentPane.add(getBoton2());
			jContentPane.add(getBtnCreateEvent());
			
			btnAddFeesTo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.btnAddFeesTo.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnAddFeesTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					JFrame a = new AddFeesGUI();
					a.setVisible(true);
				}
			});
			btnAddFeesTo.setBounds(0, 115, 479, 29);
			jContentPane.add(btnAddFeesTo);
			jContentPane.add(getBtnSetResult());
			
			btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CheckFee")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					CheckFeesGUI a = new CheckFeesGUI();
					a.setVisible(true);
					a.setErabiltzaile(AdminGUI.erabiltzaile);
				}
			});
			btnNewButton_1.setBounds(0, 142, 479, 29);
			jContentPane.add(btnNewButton_1);
			
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteQuestion")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					leihoa.setVisible(false);
					JFrame a = new DeleteQuestionGUI();
					a.setVisible(true);
					
				}
			});
			btnNewButton.setBounds(0, 197, 479, 29);
			jContentPane.add(btnNewButton);
			jContentPane.add(getBtnNewButton_2());
			jContentPane.add(getBtnNewButton_3());
			
			
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(0, 87, 479, 29);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=AdminGUI.getBusinessLogic();
					//Vector<Event> events=facade.getAllEvents();
					leihoa.setVisible(false);
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 59, 479, 29);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					leihoa.setVisible(false);
					JFrame a = new FindQuestionsGUI();
					a.setVisible(true);					
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(116, 0, 239, 37);
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
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
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
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
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
					System.out.println("Locale: "+Locale.getDefault());
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
			panel.setBounds(116, 316, 239, 62);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		btnAddFeesTo.setText(ResourceBundle.getBundle("Etiquetas").getString("AddFee"));
		btnCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckFee"));
		btnSetResult.setText(ResourceBundle.getBundle("Etiquetas").getString("SetResult"));
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteQuestion"));
		btnNewButton_2.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteFee"));
		btnNewButton_3.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeProfits"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	public void setFrame(AdminGUI a) {
		leihoa=a;
		
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.button.text")); //$NON-NLS-1$ //$NON-NLS-2$
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IsLoginGUI a = new IsLoginGUI();
					leihoa.setVisible(false);
					a.setVisible(true);
				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return button;
	}
	
	

	private JButton getBtnCreateEvent() {
		if (btnCreateEvent == null) {
			btnCreateEvent = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$ //
			System.out.println(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.btnCreateEvent.text"));
			btnCreateEvent.setBounds(0, 32, 479, 29);
			btnCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
			btnCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CreateEventGUI a = new CreateEventGUI();
				    leihoa.setVisible(false);
					a.setVisible(true);
				}
			});
			
		}
		return btnCreateEvent;
	}
	private JButton getBtnSetResult() {
		if (btnSetResult == null) {
			btnSetResult = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.btnSetResult.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSetResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					SetResultGUI a=new SetResultGUI();
					a.setVisible(true);
				}
			});
			btnSetResult.setBounds(0, 170, 479, 29);
		}
		return btnSetResult;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteFee")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					JFrame a = new DeleteFeeGUI();
					a.setVisible(true);
				}
			});
			btnNewButton_2.setBounds(0, 225, 479, 29);
		}
		return btnNewButton_2;
	}
	
	public void setErabiltzaile(User erab) {
		this.erabiltzaile=erab;
	}
	
	public static User getErabiltzaile() {
		return AdminGUI.erabiltzaile;
	}

	
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeProfits")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeeProfitsGUI a = new SeeProfitsGUI();
					a.setVisible(true);
					a.setErabiltzaile(AdminGUI.getErabiltzaile());
					AdminGUI.leihoa.setVisible(false);
				}
			});
			btnNewButton_3.setBounds(0, 254, 479, 29);
		}
		return btnNewButton_3;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

