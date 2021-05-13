package domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admin extends User implements Serializable{

	public Admin(String log, String pass) {
		
		super(log, pass);
		
	}
	
	public Admin() {
		
		super();
	}
	
	

}
