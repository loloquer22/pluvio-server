package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class MaxRainValue {
	
	private int id;
	private String date;
	private float value;
	
	public MaxRainValue() {}
	
	@Id
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
		return value;
	}
	public void setValeur(float valeur) {
		this.value = valeur;
	}
	
	@Override
	public String toString() {
		return String.format("RelevePluieMaxRaintValue[date='%s',valeur='%s' ]", date, value);
	}

}


