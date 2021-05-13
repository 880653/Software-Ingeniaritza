package gui;
import java.text.DateFormat;

import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;

import domain.Event;



public class CreateEventGUI extends JFrame  {
private static final long serialVersionUID = 1L;
  DefaultComboBoxModel<Event> modelEvents=new DefaultComboBoxModel<Event>();
  private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
  private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));


  private JTextField jTextFieldQuery = new JTextField();
  private JCalendar jCalendar = new JCalendar();
  private Calendar calendarMio = null;

  private JScrollPane scrollPaneEvents = new JScrollPane();


  private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
  private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
  private JLabel jLabelMsg = new JLabel();
  private JLabel jLabelError = new JLabel();
  private final JLabel jLabelListOfEvents = new JLabel();
  private JComboBox jComboBoxEvents;
  
  
  
  public CreateEventGUI()
  {
    try
    {
      jbInit(null);
      jLabelQuery.setText("Description");
      jButtonCreate.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEventGUI.jButtonCreate.text")); //$NON-NLS-1$ //$NON-NLS-2$
      jLabelListOfEvents.setForeground(Color.RED);
      jLabelListOfEvents.setBounds(325, 81, 46, 14);
      
      getContentPane().add(jLabelListOfEvents);
      
    }
    
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit(Vector<domain.Event> v) throws Exception
  {
    this.setSize(new Dimension(604, 370));
    this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
    scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
    jButtonCreate.setBounds(100, 275, 130, 30);
    
    jButtonCreate.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButtonCreate_actionPerformed(e);
        }
      });
    jButtonClose.setBounds(275, 275, 130, 30);
    jButtonClose.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButtonClose_actionPerformed(e);
        }
      });
    jLabelMsg.setBounds(275, 182, 305, 20);
    jLabelMsg.setForeground(Color.red);
    jLabelError.setBounds(175, 240, 305, 20);
    jLabelError.setForeground(Color.red);
    getContentPane().setLayout(null);
    
    
    this.getContentPane().add(jLabelMsg);
    this.getContentPane().add(jLabelError);

    this.getContentPane().add(jButtonClose);
    this.getContentPane().add(jButtonCreate);
    jTextFieldQuery.setBounds(100, 228, 429, 20);
    this.getContentPane().add(jTextFieldQuery);
    jLabelQuery.setBounds(25, 228, 75, 20);
    this.getContentPane().add(jLabelQuery);
    jCalendar.setBounds(40, 50, 225, 150);
    
    this.getContentPane().add(jCalendar);
    jLabelEventDate.setBounds(40, 16, 140, 25);
    getContentPane().add(jLabelEventDate);
    

    
    // Code for JCalendar
    this.jCalendar.addPropertyChangeListener(new PropertyChangeListener()
    {
        public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
          jCalendar.setCalendar(calendarMio);
          Date firstDay=trim(new Date(jCalendar.getCalendar().getTime().getTime()));
    	  

      	
     
          try {
      		BLFacade facade=AdminGUI.getBusinessLogic();
      		
      		Vector<domain.Event> events=facade.getEvents(firstDay);
      		System.out.println(events);
      		for(Event q : events) {
      			jComboBoxEvents.addItem(q);
      		}
      		//if (jComboBoxEvents.getItemCount()==0 ) jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
      		 // else jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
			jComboBoxEvents.removeAllItems();
    		 // System.out.println("Events "+events);

      		for (domain.Event ev:events)
                  modelEvents.addElement(ev);
      		//jComboBoxEvents.repaint();
      		
      		if (events.size()-1==0)
      			jButtonCreate.setEnabled(true);
      		else
      			jButtonCreate.setEnabled(true);

            } catch (Exception e1) {

     	        jLabelError.setText(e1.getMessage());
 	       }

        }
        paintDaysWithEvents(jCalendar);
      } 
    });
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
  
	public static void paintDaysWithEvents(JCalendar jCalendar){
		// For each day in current month, it is checked if there are events, and in that case, the background color for that day is changed.
				BLFacade facade=AdminGUI.getBusinessLogic();

				      Calendar calendar = Calendar.getInstance();
				      calendar.setTime(jCalendar.getDate());
				      
				      calendar.set(Calendar.DAY_OF_MONTH, 1);     
				      calendar.set(Calendar.MILLISECOND, 0);
				      calendar.set(Calendar.SECOND, 0);
				      calendar.set(Calendar.MINUTE, 0);
				      calendar.set(Calendar.HOUR_OF_DAY, 0);

				      int offset=calendar.get(Calendar.DAY_OF_WEEK);
				      if (Locale.getDefault().equals(new Locale("es"))) offset+=4;
				      else	offset+=5;

				      int month=calendar.get(Calendar.MONTH);
				      
				      while (month==calendar.get(Calendar.MONTH)){
				    	  Vector<domain.Event> events=facade.getEvents(calendar.getTime());
				    	  if (events.size()>0) {
				    		  // Obtain the component of the day in the panel of the DayChooser of the JCalendar.
				    		  // The component is located after the decorator buttons of "Sun", "Mon",... or "Lun", "Mar"...,
				    		  // the empty days before day 1 of month, and all the days previous to each day.
				    		  // That number of components is calculated with "offset" and is different in English and Spanish
				    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(calendar.get(Calendar.DAY_OF_MONTH)+offset);; 
				    		  o.setBackground(Color.CYAN);
				    	  }
				    	  calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
				      }
			}
	
  private void jButtonCreate_actionPerformed(ActionEvent e)
  {
	   // domain.Event event=((domain.Event)jComboBoxEvents.getSelectedItem());
	  	 	
	  	try {
	  		jLabelError.setText("");
	  		jLabelMsg.setText("");

	  	    //Displays an exception if the query field is empty
	  		String inputQuery= jTextFieldQuery.getText();
	  		

		      
	  		Date data = jCalendar.getDate();
	  		if (data==null)
	  			JOptionPane.showMessageDialog(null, "You haven't chosen a date");
	  		else
	  		{
	  		if (inputQuery.length()>0) { 
	  			data=trim(data);
	  		    if (data.before(Calendar.getInstance().getTime())) {
	  		    	JOptionPane.showMessageDialog(null, "please, choose a valid date");
	  		    }
	  		    else
	  		    {
	  			//Obtain the business logic from a StartWindow class (local or remote)
	  			BLFacade facade=AdminGUI.getBusinessLogic();
	  			boolean a = facade.createEvent(inputQuery, data);
	  			System.out.println(data);
	  			System.out.println(inputQuery);
	  			if(a) {
	  				JOptionPane.showMessageDialog(null, "EventCreated");
	  			}
	  			else
	  			{
	  				JOptionPane.showMessageDialog(null, "That event already exists");
	  			}	

	  			
	  		    }
	  		}
	  		else
	  			JOptionPane.showMessageDialog(null, "You haven't entered a description");
	  		}
	   }catch (Exception e1) {
	  		
	  	} 
	  	
  }
  private void jButtonClose_actionPerformed(ActionEvent e)
  {
	AdminGUI a=new AdminGUI();
    this.setVisible(false);
    a.setVisible(true);
  }
}