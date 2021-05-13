package domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Registered extends User implements Serializable{

	private String izena;
	private String Abizen1;
	private long NANa;
	private long kontuZenbakia;
	

	public Registered(String log, String pass, String izena, String Abizen1,  long nan, long kontuZenbakia) {
		super(log,pass);
		this.izena=izena;
		this.Abizen1=Abizen1;
		this.NANa=nan;
		this.kontuZenbakia=kontuZenbakia;
	}
	
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizen1() {
		return Abizen1;
	}

	public void setAbizen1(String abizen1) {
		Abizen1 = abizen1;
	}

	public long getNANa() {
		return NANa;
	}

	public void setNANa(long nANa) {
		NANa = nANa;
	}

	public long getKontuZenbakia() {
		return kontuZenbakia;
	}

	public void setKontuZenbakia(long kontuZenbakia) {
		this.kontuZenbakia = kontuZenbakia;
	}

	public Registered() {
		super();
	}
	
	
	

}
