package businessLogic;

import java.util.Date;
import domain.*;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	
	private static DataAccess DB;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		DB =new DataAccess();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			
			DB.initializeDB();
			DB.close();
			}
		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    DB = new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=DB.createQuestion(event,question,betMinimum);		

		DB.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
    	DB = new DataAccess();
		Vector<Event>  events=DB.getEvents(date);
		DB.close();
		return events;
	}
    

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DB=new DataAccess();
		DB.initializeDB();
		DB.close();
	}
    
	/**
	 * This method checks is log user exists in the database with the given pass
	 * 
	 * @param log user name
	 * @param pass user password
	 * @return user exists or not in the database with the given password
	 */
    @WebMethod
	public User isLogin(String log, String pass) {
    	DB = new DataAccess();
    	User b = DB.isLogin(log, pass);
    	DB.close();
    	return b;
    }


	@Override
	public int register(String log, String pass, String pass2, String izena, String ab1, long nan,  long kontuZenbakia) {
		DB = new DataAccess();
		int b = DB.register(log,pass,pass2,izena,ab1,nan,kontuZenbakia);
		DB.close();
		return b;
	}
	
	/**
	 * This method checks if the event we want to create is already created. If it isn't created, it will return true and
	 * creates it. Otherwise, it returns false.
	 * 
	 * @param Event
	 * @param pass user password
	 * @return If the user exists, it will return that user. Otherwise, it will return null.
	 */
	@WebMethod public boolean createEvent( String description, Date eventDate) {
		DB = new DataAccess();
		boolean b = DB.createEvent(description,eventDate);
		DB.close();
		return b;
	}
	
	@WebMethod public boolean setResult( Question q, Fee result) {
		DB = new DataAccess();
		boolean b = DB.setResult(q, result);
		DB.close();
		return b;
		
	}
	
	@WebMethod public void setDirua (String log, float diruKop) {
		DB = new DataAccess();
		DB.setDirua(log, diruKop);
		DB.close();
			
	}
	
	@WebMethod public void addTransaction (String log, Transaction Transaction) {
		DB = new DataAccess();
		DB.addTransaction(log, Transaction);
		DB.close();
	}
	
	@WebMethod
	public String getTransaction (Integer num) {
		DB = new DataAccess();
		String t = DB.getTransaction(num);
		DB.close();
		return t;
	}
	
	@WebMethod public boolean addFee (Integer id, String result, float pond) {
		DB = new DataAccess();
		boolean b = DB.addFee(id, result, pond);
		DB.close();
		return b;
	}
	
	@WebMethod
	public String getFee (Integer num) {
		DB = new DataAccess();
		String t = DB.getFees(num);
		DB.close();
		return t;
	}
	
	@WebMethod public boolean addBet (String User, Vector<Fee> bektorea, int money) {
		DB = new DataAccess();
		Boolean b = DB.addBet(User, bektorea, money);
		DB.close();
		return b;
	}
	
	
	@WebMethod public void deleteFees(Integer number) {
		
		DB = new DataAccess();
		DB.deleteFees(number);
		DB.close();
		
	}
	
@WebMethod public void deleteQuestion(Integer number) {
		
		DB = new DataAccess();
		DB.deleteQuestions(number);
		DB.close();
		
	}
	
	
}

