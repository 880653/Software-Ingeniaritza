package service;

import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import domain.*;

@WebService
public interface WebServiceLogicInterface {
	@WebMethod  
		public Event getEvent(String eventID);  
	@WebMethod  
		public Vector<Event> getListEvents();    
	
	@WebMethod  
		public Bet getBet(String address);  
	@WebMethod  
		public List<Bet> getListBets(); 
	
	@WebMethod  
		public Fee getFee(String address);  
	@WebMethod  
		public List<Fee> getListFees();
	
	@WebMethod  
		public Question getQuestion(String address);  
	@WebMethod  
		public List<Question> getListQuestions();
	
	@WebMethod  
		public Transaction getTransaction(String address);  
	@WebMethod  
		public List<Transaction> getListTransactions();
	
}
