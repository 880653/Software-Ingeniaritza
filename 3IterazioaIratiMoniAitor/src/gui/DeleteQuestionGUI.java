package gui;

import businessLogic.BLFacade;
import domain.*;

import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import domain.Fee;

import javax.swing.table.DefaultTableModel;


public class DeleteQuestionGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private Date firstDay;
	private Question question=null;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private Vector<Event> events;
	private Fee fee;
	private JFrame leihoa;
	
	private JTable tableEvents= new JTable();
	private JLabel label;
	private domain.Event ev;


	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private static User erabiltzaile;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnSetResult = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.btnSetResult.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textField;
	private final JComboBox comboBox = new JComboBox();

	public DeleteQuestionGUI()
	{
		leihoa=this;
		getContentPane().setEnabled(false);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
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

	
	private void jbInit() throws Exception
	{
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 15, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(182, 218, 277, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(295, 19, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(398, 420, 130, 30);

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose);
		jCalendar1.setBounds(40, 50, 225, 150);


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=AdminGUI.getBusinessLogic();

						events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						//tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
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
				int i=tableEvents.getSelectedRow();
				ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				ArrayList <Question> queries= new ArrayList(ev.getQuestions());
				comboBox.removeAllItems();
				for(Question q: ev.getQuestions()) {
					if(q.getQuestion()!=null) {
						comboBox.addItem(q);
					}
				}
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);
				
				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
//				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
//				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
//				tableQueries.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2));
			}
		});
		scrollPaneEvents.setBounds(292, 46, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		this.getContentPane().add(scrollPaneEvents);
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date data = trim(firstDay);
	  		    if (data.before(Calendar.getInstance().getTime())) {
	  		    	label.setText("please, choose a valid date");
	  		    }
				else if(question==null)
					label.setText("You need to select a Question");
				else 
				{

					BLFacade facade=AdminGUI.getBusinessLogic();
					facade.deleteQuestion(question.getQuestionNumber());
					comboBox.removeItem(question);
					JOptionPane.showMessageDialog(null, "The Question Was Succesfully Deleted");
					leihoa.setVisible(false);
					AdminGUI a = new AdminGUI();
					a.setVisible(true);
					a.setFrame(a);
					

	  		    }
	  		    }
			
		});
		btnSetResult.setBounds(182, 420, 130, 30);
		
		getContentPane().add(btnSetResult);
		
		textField = new JTextField();
		textField.setBounds(179, 389, 35, -2);
		//textField.setText(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.textField.text"));
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(160, 393, 346, 16);
		getContentPane().add(label);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				question = (Question) comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(160, 243, 338, 25);
		
		getContentPane().add(comboBox);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		BLFacade facade=AdminGUI.getBusinessLogic();
		AdminGUI a = new AdminGUI();
		a.setVisible(true);
		a.setFrame(a);
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
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
	
	public void setErabiltzaile(User erab) {
		this.erabiltzaile=erab;
	}
	
	public static User getErabiltzaile() {
		return DeleteQuestionGUI.erabiltzaile;
	}
	

}
