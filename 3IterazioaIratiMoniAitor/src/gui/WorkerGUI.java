package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import businessLogic.BLFacade;
import domain.Event;
import domain.User;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WorkerGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	private static WorkerGUI leihoa;

    private static BLFacade appFacadeInterface;
    
	private static User erabiltzaile;
	
	public void setErabiltzaile(User erab) {
		this.erabiltzaile=erab;
	}
	
	public static User getErabiltzaile() {
		return WorkerGUI.erabiltzaile;
	}
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	private JButton btnCheckFees;
	private JButton btnSetResult;
	private JButton button;
	private JButton btnQueryQuestion;
	private JButton btnCreateQuestion;
	/**
	 * This is the default constructor
	 */
	public WorkerGUI() {
		super();
		leihoa = this;
		
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
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 396);
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
//			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			
			
			JButton btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreateEventGUI a = new CreateEventGUI();
					leihoa.setVisible(false);
					a.setVisible(true);
				}
			});
			btnCreateEvent.setBounds(0, 32, 479, 29);
			jContentPane.add(btnCreateEvent);
			
			JLabel lblSelectAnOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.lblSelectAnOption.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblSelectAnOption.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSelectAnOption.setBounds(193, 11, 129, 16);
			jContentPane.add(lblSelectAnOption);
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getBtnCheckFees());
			jContentPane.add(getBtnSetResult_1());
			jContentPane.add(getButton());
			jContentPane.add(getBtnQueryQuestion());
			jContentPane.add(getBtnCreateQuestion());
			
			JButton btnDeleteQuestions = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnDeleteQuestions.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeleteQuestions.setBounds(0, 197, 479, 29);
			jContentPane.add(btnDeleteQuestions);
			
			JButton btnDeleteFees = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnDeleteFees.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeleteFees.setBounds(0, 225, 479, 29);
			jContentPane.add(btnDeleteFees);
			
			
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
//	private JButton getBoton3() {
//		if (jButtonQueryQueries == null) {
//			jButtonQueryQueries = new JButton();
//			jButtonQueryQueries.setEnabled(false);
//			jButtonQueryQueries.setBounds(0, 80, 479, 29);
//			jButtonQueryQueries.setText("Set Results To Questions");
//			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
//			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					leihoa.setVisible(false);
//					JFrame a = new IrrelevantaGUI();
//					a.setVisible(true);
//					
//				}
//			});
//		}
//		return jButtonQueryQueries;
//	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setEnabled(false);
			rdbtnNewRadioButton.setBounds(304, 21, 83, 23);
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
			rdbtnNewRadioButton_1.setEnabled(false);
			rdbtnNewRadioButton_1.setBounds(122, 21, 83, 23);
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
			rdbtnNewRadioButton_2.setEnabled(false);
			rdbtnNewRadioButton_2.setBounds(207, 21, 95, 23);
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
			panel.setBounds(0, 311, 479, 44);
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {

		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		//btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnSetResult = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnSetResult.text"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	public void setFrame(WorkerGUI a) {
		leihoa=a;
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					JFrame a = new AddFeesGUI();
					a.setVisible(true);
				}
			});
			btnNewButton.setBounds(0, 115, 479, 29);
		}
		return btnNewButton;
	}
	private JButton getBtnCheckFees() {
		if (btnCheckFees == null) {
			btnCheckFees = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnCheckFees.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCheckFees.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					leihoa.setVisible(false);
					CheckFeesGUI a = new CheckFeesGUI();
					a.setVisible(true);
					a.setErabiltzaile(WorkerGUI.erabiltzaile);
				}
			});
			btnCheckFees.setBounds(0, 142, 479, 29);
		}
		return btnCheckFees;
	}
	private JButton getBtnSetResult_1() {
	JButton btnSetResult_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnSetResult_1.text"));
	btnSetResult_1.setBounds(0, 170, 479, 29);
	jContentPane.add(btnSetResult_1);
	btnSetResult_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			leihoa.setVisible(false);
			SetResultGUI a=new SetResultGUI();
			a.setVisible(true);
		}
	
	});
	return btnSetResult_1;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.button.text")); //$NON-NLS-1$ //$NON-NLS-2$
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IsLoginGUI a=new IsLoginGUI();
					leihoa.setVisible(false);
					a.setVisible(true);
				}

			});
			button.setBounds(390, 277, 89, 23);
		}
		return button;
	}
	private JButton getBtnQueryQuestion() {
		if (btnQueryQuestion == null) {
			btnQueryQuestion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnQueryQuestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnQueryQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leihoa.setVisible(false);
					JFrame a = new FindQuestionsGUI();
					a.setVisible(true);
				}
			});
			btnQueryQuestion.setBounds(0, 59, 479, 29);
		}
		return btnQueryQuestion;
	}
	private JButton getBtnCreateQuestion() {
		if (btnCreateQuestion == null) {
			btnCreateQuestion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerGUI.btnCreateQuestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade=AdminGUI.getBusinessLogic();
					leihoa.setVisible(false);
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
			btnCreateQuestion.setBounds(0, 87, 479, 29);
		}
		return btnCreateQuestion;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

