//♥
package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jdo.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import exceptions.QuestionAlreadyExist;
import gui.AdminGUI;
import gui.MakeABetGUI;
import gui.MakeAMultipleBetGUI;
import gui.SetMoneyGUI;
import domain.*;
import javax.persistence.*;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public DataAccess() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Event ev1 = new Event(1, "Atletico-Athletic", newDate(2019

					, 1, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", newDate(2019, 1, 17));
			Event ev3 = new Event(3, "Getafe-Celta", newDate(2019, 1, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", newDate(2019, 1, 17));
			Event ev5 = new Event(5, "Español-Villareal", newDate(2019, 1, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", newDate(2019, 1, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", newDate(2019, 1, 17));
			Event ev8 = new Event(8, "Girona-Leganés", newDate(2019, 1, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", newDate(2019, 1, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", newDate(2019, 1, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", newDate(2019, 2, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", newDate(2019, 2, 1));
			Event ev13 = new Event(13, "Getafe-Celta", newDate(2019, 2, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", newDate(2019, 2, 1));
			Event ev15 = new Event(15, "Español-Villareal", newDate(2019, 2, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", newDate(2019, 2, 1));

			Event ev17 = new Event(17, "Malaga-Valencia", newDate(2019, 2, 31));
			Event ev18 = new Event(18, "Girona-Leganés", newDate(2019, 2, 31));
			Event ev19 = new Event(19, "Real Sociedad-Levante", newDate(2019, 2, 31));
			Event ev20 = new Event(20, "Betis-Real Madrid", newDate(2019, 2, 31));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quien ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quien meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quien ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quien ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			Admin admin = new Admin("admin", "sartu");
			 db.persist(admin);
			 Worker langile = new Worker("langile", "sartu");
			 db.persist(langile);
			 
			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event
	 *            to which question is added
	 * @param question
	 *            text of the question
	 * @param betMinimum
	 *            minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist
	 *             if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);
		Event ev = db.find(Event.class, event.getEventNumber());
		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date
	 *            in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;

	}

	public boolean doesEventExist(Event event) {
		/*
		 * Vector<Event> bektore = getEvents(event.getEventDate()); for (Event q:
		 * bektore) { if (q.getDescription().compareTo(event.getDescription())==0) {
		 * System.out.println("Ez da existitzen"); return true; } }
		 */
		TypedQuery<Event> query;

		query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.description=?2", Event.class);
		query.setParameter(1, event.getEventDate());

		query.setParameter(2, event.getDescription());

		List<Event> lista = null;
		lista = query.getResultList();

		boolean emaitza = false;
		// System.out.println(event.getDescription());
		// System.out.println(event.getEventDate());
		for (Event ev : lista) {
			if (ev.getDescription().equals(event.getDescription()) && ev.getEventDate().equals(event.getEventDate())) {
				System.out.println(ev);
				System.out.println("existitzen da");
				emaitza = true;
			}
		}
		return emaitza;
		/*
		 * if(lista.size()==1) { System.out.println("ez da existitzen"); return false; }
		 * else {
		 * 
		 * }
		 */

	}

	/**
	 * This method checks if the event we want to create is already created. If it
	 * isn't created, it will return true and creates it. Otherwise, it returns
	 * false.
	 * 
	 * @param Event
	 * @param pass
	 *            user password
	 * @return If the user exists, it will return that user. Otherwise, it will
	 *         return null.
	 */
	public boolean createEvent(String description, Date eventDate) {
		Event Berria = new Event(description, eventDate);
		if (!doesEventExist(Berria)) {
			db.getTransaction().begin();
			db.persist(Berria);
			db.getTransaction().commit();
			System.out.println("dataAccess true");
			return true;
		} else
			System.out.println("dataAccess false");
		return false;
	}

	/**
	 * This method checks if that log user exists in the database with the given
	 * pass
	 * 
	 * @param log
	 *            user name
	 * @param pass
	 *            user password
	 * @return If the user exists, it will return that user. Otherwise, it will
	 *         return null.
	 */
	public User isLogin(String log, String pass) {

		User erabiltzaile = getUserByLog(log);
		if (erabiltzaile == null) {
			return null;
		} else {
			if (erabiltzaile.getPass().compareTo(pass) == 0) {
				return erabiltzaile;
			}
		}
		return null;

	}

	/**
	 * This method checks if a user exists in the database with the given log
	 * 
	 * @param log
	 *            user name
	 * @return If the user exists, it will return that user. Otherwise, it will
	 *         return null.
	 */
	public User getUserByLog(String log) {
		return db.find(User.class, log);
	}

	/**
	 * 
	 * @param log
	 *            username
	 * @param pass
	 *            the new password
	 * @param pass2
	 *            the new password (given twice)
	 * @return 0 if the user exists, 1 if the passwords are not equal and 2 if the
	 *         User was successfully registered.
	 */
	public int register(String log, String pass, String pass2, String izena, String ab1, long nan, long kontuZenbakia) {
		if (getUserByLog(log) != null)
			return 0;
		else if (!pass.equals(pass2))
			return 1;
		else {
			db.getTransaction().begin();
			User berria = new Registered(log, pass, izena, ab1, nan, kontuZenbakia);
			db.persist(berria);
			db.getTransaction().commit();
			System.out.println(berria + "gorde da");
			return 2;

		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public boolean setResult(Question q, Fee result) {
		db.getTransaction().begin();
		Question q2 = db.find(Question.class, q.getQuestionNumber());
		Fee r = db.find(Fee.class, result.getId());
		if (q2.getResult() == null) {
			q2.setResult(result);
			db.persist(q2);
			if(r.getApostuak().isEmpty()) {
			}
			else {
			for (Bet b : r.getApostuak()) {
				System.out.println("apostua aurkitu du");
				b.getBoolearrak().add(true);
				if(b.getBoolearrak().size() == b.getResults().size()) {
					
					User u = db.find(User.class, b.getErabiltzaile().getLog());
					
					float diru = b.getPondOsoa() * b.getMoney();
					
					u.setDirua(diru);
					
					System.out.println(u.getLog() + "-(e)k " + diru + " irabazi du.");
					
					Date date = new Date();

					Transaction t = new Transaction(diru, "won a bet", date, u);

					u.addTransaction(t);
					

					////////////////


					Admin a = (Admin) db.find(User.class, "admin");

					Transaction tr = new Transaction(-diru, u.getLog() + " won a bet", date, AdminGUI.getErabiltzaile());

					a.setDirua(-diru);

					a.addTransaction(tr);
			}
				else {}

			}
			db.getTransaction().commit();
			return true;
			}
			db.getTransaction().commit();
			return true;
		} else {
			db.getTransaction().commit();
			return false;
		}
	}

	/*
	 * public void setDirua (String log, float diru) { db.getTransaction().begin();
	 * Registered r = db.find(Registered.class, log); float hasiera = r.getDirua();
	 * r.setDirua(diru); db.getTransaction().commit(); }
	 */

	public void setDirua(String log, float diru) {
		db.getTransaction().begin();
		User r = db.find(User.class, log);
		float hasiera = r.getDirua();
		r.setDirua(diru);
		db.getTransaction().commit();
	}

	/*
	 * public void addTransaction(String log, Transaction Transaction) {
	 * db.getTransaction().begin(); Registered r = db.find(Registered.class, log);
	 * r.addTransaction(Transaction); db.getTransaction().commit(); }
	 */

	public void addTransaction(String log, Transaction Transaction) {
		db.getTransaction().begin();
		User r = db.find(User.class, log);
		r.addTransaction(Transaction);
		db.getTransaction().commit();
	}

	public String getTransaction(Integer num) {
		db.getTransaction().begin();
		Transaction t = db.find(Transaction.class, num);
		db.getTransaction().commit();
		return t.getTransaction();
	}

	public boolean addFee(Integer q, String result, float pond) {
		db.getTransaction().begin();
		Question q1 = db.find(Question.class, q);
		Fee f = new Fee(result, pond, q1);
		if (q1.doesFeeExist(f)) {
			db.getTransaction().commit();
			return false;
		} else {
			if (f.getResult() != null)
				q1.addFee(f);
			db.persist(q1);
			db.getTransaction().commit();
			return true;
		}
	}

	public String getFees(Integer num) {
		db.getTransaction().begin();
		Fee f = db.find(Fee.class, num);
		db.getTransaction().commit();
		return f.toString();
	}

	public void deleteFees(Integer num) {

		db.getTransaction().begin();
		Fee f = db.find(Fee.class, num);
		List<Bet> apostuak = f.getApostuak();

		// Apostuak ezabatu
		for (Bet b: apostuak) {

			Date date = new Date();
			User u = db.find(Registered.class, b.getErabiltzaile().getLog());
			Transaction tr = new Transaction(b.getMoney(), num + " kuota ezabatu da", date, u);
			u.setDirua(b.getMoney());
			u.addTransaction(tr);
			u.borratuApostua(b);
			db.persist(u);
			db.remove(b);
			User admin = db.find(User.class, "admin");
			Transaction t = new Transaction(-b.getMoney(), num + " kuota ezabatu da", date, u);
			admin.setDirua(-b.getMoney());
			admin.addTransaction(t);

		}
		// Galderatik ezabatu
		Question q = f.getQuestion();
		Question q1 = db.find(Question.class, q.getQuestionNumber());
		q1.removeFee(f);
		db.persist(q1);
		db.remove(f);
		db.getTransaction().commit();

	}

	public void deleteQuestions(Integer num) {

		db.getTransaction().begin();
		Question q = db.find(Question.class, num);
		List<Fee> fees = q.getFees();
		db.getTransaction().commit();

		// Kuotak ezabatu
		for (Fee f: fees) {

			deleteFees(f.getId());
			
		}
		db.getTransaction().begin();
		db.remove(q);
		db.getTransaction().commit();
	}

	public boolean addBet(String user, Vector<Fee> bektorea, int money) {
		db.getTransaction().begin();
		User u = db.find(User.class, user);
		Bet apostua = new Bet(money, u, bektorea);
		ArrayList<Fee> emaitzak = new ArrayList<Fee>();
		for(Fee f1: apostua.getResults()) {
			Fee f2 = db.find(Fee.class, f1.getId());
			f2.addBet(apostua);
			db.persist(f2);
		}
		u.addBet(apostua);
		for(Fee f: apostua.getResults()) {
			System.out.println(f.getResult());
		}
		db.persist(u);
		db.getTransaction().commit();
		return true;
	}
}
