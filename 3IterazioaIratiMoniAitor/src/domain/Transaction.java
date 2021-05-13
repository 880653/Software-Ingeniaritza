package domain;

import java.io.Serializable;
import java.util.Date;

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
public class Transaction implements Serializable{
	
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@Id 
	private Integer transactionNumber;
	private float dirua;
	private String movement;
	private Date data;
	
	@XmlIDREF
	private User user;
	
	public Transaction() {}
	
	public Transaction(float dirua, String movement, Date data, User u) {
		this.movement=movement;
		this.dirua=dirua;
		this.data=data;
		this.user=u;
	}
	
	
	public Integer getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public String getTransaction() {
		return this.movement;
	}
	
	public Integer getNumber() {
		return this.transactionNumber;
	}

	public float getDirua() {
		return this.dirua;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return this.data;
	}
}
