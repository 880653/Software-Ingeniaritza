package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable{

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@Id 
	private Integer id;
	

	
	@XmlIDREF
	private Vector<Fee> results;
	private ArrayList<Boolean> boolearrak;
	private int money;
	@XmlIDREF
	private User erabiltzaile;

	public int egoera; //0 bidean, 1 irabazita, 2 galduta.

	
	public Vector<Fee> getResults() {
		return this.results;
	}

	public void addResult(Fee result) {
		this.results.add(result);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setErabiltzaile(User erabiltzaile) {
		this.erabiltzaile = erabiltzaile;
	}
	

	public Bet(int Money, User erab, Vector<Fee> result ) {
		this.egoera=0;
		this.money=Money;
		this.erabiltzaile=erab;
		this.results= new Vector<Fee>();
		if(result.isEmpty())
			System.out.println("ya venía vacío");
		for(Fee f : result) {
			this.results.add(f);
		}
		this.boolearrak = new ArrayList<Boolean>();
		if(this.results.isEmpty())
			System.out.println("está vacio desde el principio");
		
	}
	
	public Bet() {
		super();
	}
	
	public Vector<Fee> getFee() {
		return this.results;
	}
	
	public void AddFee(Fee f) {
		this.results.add(f);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public int getEgoera() {
		return this.egoera;
	}
	
	public void setEgoera(int i) {
		this.egoera=i;
	}
	
	public ArrayList<Boolean> getBoolearrak(){
		return this.boolearrak;
	}
	
	public User getErabiltzaile() {
		return this.erabiltzaile;
	}
	
	public void print() {
		System.out.println(this.getErabiltzaile().getLog());
		for(Fee f: this.getResults()) {
			System.out.println(f.toString());
		}
	}
	
	public float getPondOsoa() {
		float pond = 1;
		for(Fee f: this.results)
			pond = pond * f.getPond();
		return pond;
	}
}
