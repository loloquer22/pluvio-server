package bzh.pluvio.pluvioServer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
		System.out.println("****  valeur "+ valeur);
		return String.format("RelevepluieByDate[id='%s',date='%s',valeur='%f.1' ]",id , date, valeur);
	}



}