package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;
import domain.Question;
import exceptions.EventFinished;
import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class AddFeesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Transactions")); 
	private Date firstDay;
	private Question question=null;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private Vector<Event> events;
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JLabel label;
	private static AddFeesGUI frame;

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
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
	private final JTextField result = new JTextField();
	private final JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddFeesGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField Pond = new JTextField();

	public AddFeesGUI()
	{
		Pond.setText(ResourceBundle.getBundle("Etiquetas").getString("AddFeesGUI.textField_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		Pond.setBounds(138, 389, 406, 20);
		Pond.setColumns(10);
		result.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		result.setBounds(138, 358, 406, 20);
		result.setColumns(10);
		this.getContentPane().add(result);
		getContentPane().setEnabled(false);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		frame=this;
	}

	
	private void jbInit() throws Exception
	{
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 15, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(138, 211, 406, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(295, 19, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jButtonClose.setBounds(398, 420, 120, 30);

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
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				ArrayList<Question> queries=new ArrayList(ev.getQuestions());
				
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);
				
				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

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
		scrollPaneEvents.setBounds(292, 50, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				question =(domain.Question)tableQueries.getValueAt(i,2);
			}
		});
		scrollPaneQueries.setBounds(138, 243, 406, 75);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries);
		
		btnSetResult.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade=AdminGUI.getBusinessLogic();
				if(question==null)
					JOptionPane.showMessageDialog(null, "You need to choose a Question");
			      
		  		Date data = jCalendar1.getDate();
		  		if (data==null)
		  			JOptionPane.showMessageDialog(null, "You haven't chosen a date");
		  		
		  		/*data=trim(data);
		  		
		  		if (data.before(Calendar.getInstance().getTime()))
		  		    label.setText("please, choose a valid date");
		  		*/
				String resultado = result.getText();
				if (resultado  == " ")
		  			JOptionPane.showMessageDialog(null, "You need to enter a Result");
				
				String pondi = Pond.getText().trim();
				
				if(pondi.isEmpty()||pondi==" ")
					JOptionPane.showMessageDialog(null, "You need to enter a Ponderation");
				else if((Float.parseFloat(pondi)) <= 1) 
					JOptionPane.showMessageDialog(null, "You need to enter a Ponderation above 1");
				else if(!isFloat(pondi)) {
					JOptionPane.showMessageDialog(null, "The Ponderation must be a number");
				}
				
		  		else{

					float pond = Float.parseFloat(pondi);
					Integer a = question.getQuestionNumber();
					boolean b = facade.addFee(question.getQuestionNumber(), resultado, pond);
					if(b)
						JOptionPane.showMessageDialog(null, "The fee was succesfully added");
					else
						JOptionPane.showMessageDialog(null, "That fee already existed");

				}
			}
		});
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSetResult.setBounds(182, 420, 130, 30);
		
		getContentPane().add(btnSetResult);
		
		JLabel lblResult = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.lblResult.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblResult.setBounds(82, 353, 55, 29);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblResult);
		
		textField = new JTextField();
		textField.setBounds(179, 389, 35, -2);
		//textField.setText(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.textField.text"));
		getContentPane().add(textField);
		textField.setColumns(10);
		
		getContentPane().add(result);
		
		label = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(138, 329, 406, 16);
		getContentPane().add(label);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(82, 393, 71, 14);
		
		getContentPane().add(lblNewLabel);
		
		getContentPane().add(Pond);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		AdminGUI a= new AdminGUI();
		frame.setVisible(false);
		a.setVisible(true);
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
	
	private static boolean isFloat(String s) {
		
        boolean num;
        try {
            Float.parseFloat(s);
            num = true;
        } catch (NumberFormatException excepcion) {
            num = false;
        }
        return num;
    }
}
