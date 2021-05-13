package domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Worker extends User implements Serializable{

	public Worker(String log, String pass) {
		super(log, pass);
	}
	
	public Worker() {
		super();
	}
	
}
