package gui;

import businessLogic.BLFacade;
import domain.*;

import com.toedter.calendar.JCalendar;

import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class MakeABetGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private Date firstDay;
	private Question question = null;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private Vector<Event> events;
	private Fee fee;

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();
	private JLabel label;

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private static User erabiltzaile;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnSetResult = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.btnSetResult.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textField;
	private final JComboBox comboBox = new JComboBox();
	private final JTextField money = new JTextField();
	private final JLabel lblNewLabel = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_1 = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel min = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.label_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_2 = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel left = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.lblNewLabel_3.text")); //$NON-NLS-1$ //$NON-NLS-2$

	public MakeABetGUI() {
		money.setText(ResourceBundle.getBundle("Etiquetas").getString("MakeABetGUI.textField_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		money.setBounds(155, 389, 135, 20);
		money.setColumns(10);
		getContentPane().setEnabled(false);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isNumeric(String s) {

		try {
			Integer.parseInt(s);

		} catch (NumberFormatException excepcion) {

			return false;

		}
		return true;
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 15, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(40, 218, 245, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(295, 19, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(398, 420, 130, 30);

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose);
		jCalendar1.setBounds(40, 50, 225, 150);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					firstDay = trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade = AdminGUI.getBusinessLogic();

						events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						else
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events " + ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						// tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2));
						// // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			}
		});

		this.getContentPane().add(jCalendar1);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				ArrayList<Question> queries = new ArrayList(ev.getQuestions());

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(
							ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
				else
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
							+ ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();
					if(q.getQuestion() != null) {
						row.add(q.getQuestionNumber());
						row.add(q.getQuestion());
						row.add(q);
						tableModelQueries.addRow(row);	
					}

				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2));
			}
		});
		scrollPaneEvents.setBounds(292, 46, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableQueries.getSelectedRow();
				question = (domain.Question) tableQueries.getValueAt(i, 2);
				min.setText(Float.toString(question.getBetMinimum()));
				comboBox.removeAllItems();
				for (domain.Fee f : question.getFees()) {
					if (f.getResult() != null)
						comboBox.addItem(f);
				}

			}

		});
		scrollPaneQueries.setBounds(40, 243, 250, 135);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries);
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date data = trim(firstDay);
				if (data.before(Calendar.getInstance().getTime())) {
					JOptionPane.showMessageDialog(null, "Please, choose a valid date");
				} else {

					String dirua = money.getText();
					if (dirua == null) {
						JOptionPane.showMessageDialog(null, "You need to enter a money amount");
					} else if (!isNumeric(dirua)) {
						JOptionPane.showMessageDialog(null, "The money must be a int number");
					} else {

						int diru = Integer.parseInt(dirua.trim());

						if (diru <= 0 || diru < fee.getQuestion().getBetMinimum()) {
							System.out.println("diru " + diru);
							System.out.println("fee " + fee.getQuestion().getBetMinimum());

							JOptionPane.showMessageDialog(null, "You need to enter a valid money amount");
						} else if (fee == null) {
							JOptionPane.showMessageDialog(null, "You need to select a Fee");
						} else {

							BLFacade facade = AdminGUI.getBusinessLogic();
						/*	for()
							Bet bet = new Bet(diru, MakeABetGUI.getErabiltzaile(), )
							facade.addBet(MakeABetGUI.getErabiltzaile().getLog(), fee.getId(), diru);
							Date date = new Date();
							//if (b) {

								JOptionPane.showMessageDialog(null, "You made a bet!");

								Transaction t = new Transaction(-diru, "Made a bet", date, MakeABetGUI.erabiltzaile);
								facade.setDirua(MakeABetGUI.getErabiltzaile().getLog(), -diru);
								facade.addTransaction(MakeABetGUI.getErabiltzaile().getLog(), t);
								MakeABetGUI.erabiltzaile = facade.isLogin(MakeABetGUI.getErabiltzaile().getLog(),
										MakeABetGUI.getErabiltzaile().getPass());
								left.setText(Float.toString(MakeABetGUI.erabiltzaile.getDirua()));

								///////////////////////////////////////////////////////////////////////////////////////

								Transaction tr = new Transaction(diru,
										MakeABetGUI.getErabiltzaile().getLog() + " made a bet", date,
										AdminGUI.getErabiltzaile());

								facade.setDirua("admin", diru);

								facade.addTransaction("admin", tr);

							}// else
								JOptionPane.showMessageDialog(null, "You don't have enough money");
						*/}
					}
				}
			}
		});
		btnSetResult.setBounds(182, 420, 130, 30);

		getContentPane().add(btnSetResult);

		textField = new JTextField();
		textField.setBounds(179, 389, 35, -2);
		// textField.setText(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.textField.text"));
		getContentPane().add(textField);
		textField.setColumns(10);

		label = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(295, 389, 346, 16);
		getContentPane().add(label);

		JLabel lblFees = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CheckFeesGUI.lblFees.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblFees.setBounds(340, 218, 225, 14);
		getContentPane().add(lblFees);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fee = (Fee) comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(300, 243, 338, 25);

		getContentPane().add(comboBox);

		getContentPane().add(money);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 389, 141, 20);

		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(40, 420, 66, 14);

		getContentPane().add(lblNewLabel_1);
		min.setForeground(Color.RED);
		min.setFont(new Font("Tahoma", Font.PLAIN, 12));
		min.setBounds(126, 420, 46, 14);

		getContentPane().add(min);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(40, 436, 66, 14);

		getContentPane().add(lblNewLabel_2);
		left.setFont(new Font("Tahoma", Font.PLAIN, 12));
		left.setForeground(new Color(0, 128, 0));
		left.setBounds(126, 436, 46, 14);

		getContentPane().add(left);

	}

	private void jButton2_actionPerformed(ActionEvent e) {

		this.setVisible(false);
		BLFacade facade = AdminGUI.getBusinessLogic();
		User user = facade.isLogin(MakeABetGUI.erabiltzaile.getLog(), MakeABetGUI.erabiltzaile.getPass());
		RegisteredGUI a = new RegisteredGUI(user);
		a.setVisible(true);
		a.setFrame(a);
		a.setErabiltzaile(user);
		System.out.println("erregistratua sartu da");
	}

	private Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public void setErabiltzaile(User erab) {
		this.erabiltzaile = erab;
	}

	public static User getErabiltzaile() {
		return MakeABetGUI.erabiltzaile;
	}

}
