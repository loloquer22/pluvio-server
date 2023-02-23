package bzh.pluvio.pluvioServer.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "relevepluie") 
public class RelevepluieByDate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "fr_FR", timezone = "UTC")
	
	private Date date;
	private float valeur;
	private int id;
	
	
	public RelevepluieByDate() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
//		System.out.println("****  valeur "+ valeur);
		return String.format("RelevepluieByDate[id='%s',date='%s',valeur='%f.1' ]",id , date, valeur);
	}



}