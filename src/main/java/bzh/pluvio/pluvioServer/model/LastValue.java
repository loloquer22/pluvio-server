package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class LastValue {
	
	private int id;
	private String date;
	private float valeur;
	
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
	
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
		return String.format("RelevePluieLastValue[date='%s',valeur='%s' ]", date, valeur);
	}

}


