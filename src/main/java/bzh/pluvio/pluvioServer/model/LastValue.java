package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class LastValue {
	
	private int id;
	private String date;
	private int valeur;
	
	public LastValue() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date = string;
	}
	
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
		return String.format("RelevePluieLastValue[date='%s',valeur='%s' ]", date, valeur);
	}

}


