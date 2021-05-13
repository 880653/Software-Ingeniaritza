package businessLogic;

import java.util.Vector;

//import domain.Booking;
import domain.*;
import java.util.Date;

import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	
	/**
	 * This method checks if log user exists in the database with the given pass
	 * 
	 * @param log user name
	 * @param pass user password
	 * @return If the user exists, it will return that user. Otherwise, it will return null.
	 */
	@WebMethod public User isLogin(String log, String pass);

	/**
	 * This method saves a new user in the database if the given log doesn't exist in it and the two given passes are equal.
	 * 
	 * @param log user name
	 * @param pass user password
	 * @param pass2 user password (used to confirm)
	 * @return it returns true if the user has been correctly registered. Otherwise, it will return false;
	 */
	@WebMethod public int register(String log, String pass, String pass2, String izena, String ab1, long nan,  long kontuZenbakia);
	
	/**
	 * This method checks if the event we want to create is already created. If it isn't created, it will return true and
	 * creates it. Otherwise, it returns false.
	 * 
	 * @param Event
	 * @param pass user password
	 * @return If the user exists, it will return that user. Otherwise, it will return null.
	 */
	@WebMethod public boolean createEvent( String description, Date eventDate);
	
	/**
	 * This method checks if the question passed as a parameter has already got a result and if it doesn't it 
	 * sets the result passed as the second parameter as its result.
	 * 
	 * @param Question q
	 * @param String result
	 * @return If the result was successfully set, it returns true. Otherwise it will return false.
	 */
	@WebMethod public boolean setResult(Question q, Fee result);
	
	/**
	 * This method adds to the given users money quantity the new money quantity given.
	 * 
	 * @param String log
	 * @param int diruKop
	 *
	 */	
	@WebMethod public void setDirua (String log, float diruKop);
	
	/**
	 * This method adds to the given users transaction's collection a new transaction
	 * 
	 * @param String log
	 * @param Transaction Transaction
	 *
	 */	
	@WebMethod public void addTransaction (String log, Transaction Transaction);
	
	/**
	 * This method returns the Transaction needed
	 * 
	 * @param int num
	 * return String
	 */	
	@WebMethod public String getTransaction(Integer number);
	
	/**
	 * This method adds to the given Question's collection a new Fee
	 * 
	 * @param String id
	 * @param String result
	 * @param float pond
	 *
	 */	
	@WebMethod public boolean addFee (Integer id, String result, float pond);
	
	/**
	 * This method returns the Fee needed
	 * 
	 * @param int num
	 * return String
	 */	
	@WebMethod public String getFee(Integer number);
	
	/**
	 * This method removes the Fee needed
	 * 
	 * @param int num
	 */	
	@WebMethod public void deleteFees(Integer number);
	
	/**
	 * This method removes the Question needed
	 * 
	 * @param int num
	 */	
	@WebMethod public void deleteQuestion(Integer number);
	
	/**
	 * This method adds to the given users Bet collection and to the given Fee's bet collection a new bet.
	 * 
	 * @param String User
	 * @param String Fee
	 * @param Bet b
	 *
	 */	
	@WebMethod boolean addBet (String User, Vector<Fee> bektorea, int money);
	

}
