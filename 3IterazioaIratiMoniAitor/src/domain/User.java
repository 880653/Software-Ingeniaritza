package domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@XmlSeeAlso({Admin.class,Worker.class, Registered.class})
public abstract class User implements Serializable{

	private float dirua;
	@Id
	@XmlID
	private String log;
	private String pass;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Transaction> trantsakzioak = new ArrayList<Transaction>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Bet> apostuak = new ArrayList<Bet>();
	
	public User() {
		super();
	}

	public User(String log, String pass) {
		super();
		this.log = log;
		this.pass = pass;
		this.dirua=0;
	}

	
	public List<Transaction> getTrantsakzioak() {
		return trantsakzioak;
	}

	public void setTrantsakzioak(Vector<Transaction> trantsakzioak) {
		this.trantsakzioak = trantsakzioak;
	}

	public List<Bet> getApostuak() {
		return apostuak;
	}

	public void setApostuak(Vector<Bet> apostuak) {
		this.apostuak = apostuak;
	}

	public float getDirua () {
		return this.dirua;
	}
	
	public void setDirua (float diru) {
		this.dirua+=diru;
	}

	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void addTransaction(Transaction transaction) {
		trantsakzioak.add(transaction);
	}
	
	public List<Transaction> getTransactions(){
		return this.trantsakzioak;
	}
	
	public void addBet(Bet b) {
		apostuak.add(b);
	}
	
	public void gehituDirua(float d) {
		this.dirua+=d;
	}
	
	public void borratuApostua(Bet b) {
		this.apostuak.remove(b);
	}
}
