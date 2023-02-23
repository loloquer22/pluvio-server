package bzh.pluvio.pluvioServer.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class Relevepluie implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "date")
	private Date date;
	
	@Column(name = "jour")
	private int jour;
	
	@Column(name = "mois")
	private int mois;
	
	@Column(name = "annee")
	private int annee;

	@Column(name = "valeur")
	private float valeur;
	
	public Relevepluie() {
	}
	
	public long getId() {
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

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	
	public Relevepluie(Date date,  float valeur) {
		this.date = date;
//		this.jour = jour;
//		this.mois = mois;
//		this.annee = annee;
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return String.format("Relevepluie[id=%d,date='%s',jour='%s',mois='%s',annee='%s',valeur='%f.1' ]", id, date,jour, annee,  mois, valeur);
	}

}

