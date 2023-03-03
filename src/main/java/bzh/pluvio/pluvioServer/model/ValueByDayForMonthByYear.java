package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class ValueByDayForMonthByYear {

	private int annee;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jour;
	private int mois;
	private float valeur;
	
	public ValueByDayForMonthByYear() {}
	
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
	
	@Override
	public String toString() {
		return String.format("ValueByDayForMonthByYear[annee='%s',mois='%s',jour='%s',valeur='%f.1']",annee, mois, jour, valeur);
	}

}

