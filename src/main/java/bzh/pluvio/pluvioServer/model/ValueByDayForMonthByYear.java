package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class ValueByDayForMonthByYear {

	private int annee;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jour;
	private int mois;
	private int valeur;
	
	
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
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
		return String.format("ValueByDayForMonthByYear[annee='%s',mois='%s',jour='%s',valeur='%s' ]",annee, mois, jour, valeur);
	}

}

