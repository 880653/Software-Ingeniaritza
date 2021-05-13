package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Fee implements Serializable{
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@Id 
	private Integer id;
	private String result;
	private float pond;
	private Date date;
	
	@XmlIDREF
	private Question question;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Bet> apostuak = new ArrayList<Bet>();
	
	
	public Fee() {
		super();
	}
	
	public Fee(String result, float pond, Question q) {

		this.result=result;
		this.pond=pond;
		this.question=q;
	}
	
	public List<Bet> getApostuak() {
		return apostuak;
	}

	public void setApostuak(List<Bet> apostuak) {
		this.apostuak = apostuak;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setPond(float pond) {
		this.pond = pond;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getId() {
		return this.id;
	}
	public String getResult() {
		return this.result;
	}
	
	public float getPond() {
		return this.pond;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public String toString() {
		return "Result: " + this.getResult() +". Ponderation: " + Float.toString(this.getPond());
	}
	
	public void addBet(Bet b) {
		apostuak.add(b);
	}
	

}
